package org.openmrs.module.basicmodule;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.constants.Sync2Constants;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.module.basicmodule.pojo.OpenHimConnection;
import org.openmrs.module.basicmodule.pojo.shr.encounter.Entry;
import org.openmrs.module.basicmodule.pojo.shr.encounter.ShrEncounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClientCustom {
    protected final Log log = LogFactory.getLog(getClass());
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClientCustom.class);
    private OpenHimConnection openHimConnection = null;

    public Boolean himCall(Object o, String type) {
        openHimConnection = getOpenHimConnection();
        String uri = null;

        if (type.equalsIgnoreCase("obs"))
            uri = openHimConnection.getOpenhimUrl() + "/Observation"; //Context.getAdministrationService().getGlobalProperty(GLOBAL_PROPERTY_OPENHIM_NIDA_API) + "/4_0_0/Observation";
        else if (type.equalsIgnoreCase("encounter"))
            uri = openHimConnection.getOpenhimUrl() + "/Encounter";
        else if (type.equalsIgnoreCase("medication"))
            uri = openHimConnection.getOpenhimUrl() + "/MedicationRequest";
        else if (type.equalsIgnoreCase("laboratory"))
            uri = openHimConnection.getOpenhimUrl() + "/ServiceRequest";

        RestTemplate restTemplate = new RestTemplate();
       String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
      // String plainCreds1 = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();


        //LOGGER.error("plainCreds1 OBJ :" + plainCreds);
        //LOGGER.error("plainCreds1 uri :" + uri);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        //  headers.add("Authorization", "Basic " + base64Creds);
        //LOGGER.error("Encounter OBJ :" + new Gson().toJson(o));
        HttpEntity<?> request = new HttpEntity<Object>(o, headers);
        ResponseEntity<String> response = null;
        try {
           // request1.getHeaders().add("Authorization", "Basic b3BlbmhpbWFkbWluQG1vaC5jb206T3BlbmhpbUAyMDIy");
           // request1.getHeaders().add("Content-Type", "application/json");
            response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
            //LOGGER.error("*************************result********************44444********+ result" + result);
            if (response.getStatusCode().value() > 199 && response.getStatusCode().value() < 400) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
            //LOGGER.error("*********************************************44444********+ Exception" + ignored.getMessage());
        }

        return false;
    }

    public Boolean syncData(ObsEncounterSync obsEncounterSync) {
          Integer type = obsEncounterSync.getType();
          String uuid = obsEncounterSync.getUuid();

        try {
            openHimConnection = getOpenHimConnection();
            String url = null;
            //LOGGER.error("------------------------========> In Sync");
            //  url = url.replace("?v=full", "");
            RestTemplate restTemplate = new RestTemplate();

            String plainCreds = openHimConnection.getLoginUserName() + ":" + openHimConnection.getLoginUserPassword();
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);
            //LOGGER.error("------------------------========> In Sync"+ plainCreds);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            headers.add("Content-Type", "text/json");
            HttpEntity<String> request = new HttpEntity<String>(headers);

            if (type == 1) {
                url = Sync2Constants.OBSERVATION_URL + uuid;
                //LOGGER.error("------------------------=======IF=>" + url);
                Object result = null;
                // try {
                ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
                result = response.getBody();
                /* } catch (Exception e) {

                }*/
                if(result!=null)
                return himCall(result, "Obs");
            } else if (type == 2) {
                //LOGGER.error("------------------------=======ELSE=>" + url);
                url = Sync2Constants.ENCOUNTER_URL + uuid;
                ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
                Object result = response.getBody();
                if(result!=null)
                return himCall(result, "encounter");
            } else if(type == 3){
                //LOGGER.error("------------------------=======ELSE=>" + url);
                url = Sync2Constants.SERVICE_REQUEST_URL + uuid;
                ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
                Object result = response.getBody();
                if(result!=null)
                    return himCall(result, "laboratory");
            } else if(type == 4 || type == 5){
                //LOGGER.error("------------------------=======ELSE=>" + url);
                url = Sync2Constants.MEDICATION_REQUEST_URL + uuid;
                ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, request, Object.class);
                Object result = response.getBody();
                if(result!=null)
                    return himCall(result, "medication");
            }

        } catch (Exception e) {
            //e.printStackTrace();
            String message = "";
            if(e.getMessage().trim().startsWith("404")){
                //LOGGER.error(uuid + " 404 THis was not found");
                message = "Main Record was not found";
            } else if(e.getMessage().trim().startsWith("410")){
                //LOGGER.error(uuid + " 410 This was deleted");
                message = "Main Record was deleted";
            } else {
                //LOGGER.error(uuid + " Has Additional Error to be checked later::> " + e.getMessage());
                message = "Unspecified error " + e.getMessage().substring(0, 200);
            }

            //Here try to update the database
            obsEncounterSync.setIsSynced(true);
            obsEncounterSync.setSyncComment(message);
            obsEncounterSync.setSyncPushDate(new Date());

            PrimaryCareBusinessLogic.getService().saveTrans(obsEncounterSync);
        }
        return false;
    }

    public Boolean syncMedication() {

        //LOGGER.error("In Sync Medication...");
        RestTemplate restTemplate = new RestTemplate();

        String plainCreds =  openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        //LOGGER.error("------------------------========> In Sync"+ plainCreds);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "text/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        String url = Sync2Constants.MEDICATION_REQUEST_URL;
        ResponseEntity<ShrEncounter> response = restTemplate.exchange(url, HttpMethod.GET, request, ShrEncounter.class);
        ShrEncounter result = response.getBody();
        //LOGGER.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Medication : " + new Gson().toJson(result));

        for (Entry e : result.getEntry()) {
            try {
                if(e.getResource()!=null)
                himCall(e.getResource(), "medication");
            } catch (Exception ex) {
                //LOGGER.error(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    public OpenHimConnection getOpenHimConnection() {
        try {
            openHimConnection = new OpenHimConnection();
            final String openhimPatientUrl = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_NIDA_API);
            if (openhimPatientUrl == null || openhimPatientUrl.isEmpty()) {
                // log.error("[error]------ Openhim patient report URL is not defined on administration settings.");
                // model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("Undefined");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimUrl(openhimPatientUrl+"/shr");
            }

            final String openhimClientID = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_USER_NAME);
            if (openhimClientID == null || openhimClientID.isEmpty()) {
                // log.error("[error]------ Openhim client ID is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("CLIENT_ID_UNDEFINED");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimClientId(openhimClientID);
            }
            final String openhimPwd = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_USER_PWD);
            if (openhimPwd == null || openhimPwd.isEmpty()) {
                // log.error("[error]------ Openhim client Basic Auth Password is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("PASSWORD_UNDEFINED");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimPassword(openhimPwd);
            }

            final String loginUsername = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_SCHEDULER_USERNAME);
            if (loginUsername == null || loginUsername.isEmpty()) {
                //log.error("[error]------ Openhim client loginUsername is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setLoginUserName("admin");
                return openHimConnection;
            } else {
                //log.error("[error]------ Openhim client loginUsername" + loginUsername);
                openHimConnection.setLoginUserName(loginUsername);
            }

            final String loginUserPwd = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_SCHEDULER_PASSWORD);
            if (loginUserPwd == null || loginUserPwd.isEmpty()) {
                //log.error("[error]------ Openhim client loginUserPwd is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setLoginUserPassword("Umurwayi5!");
                return openHimConnection;
            } else {
                //log.error("[error]------ Openhim client loginUsername" + loginUserPwd);
                openHimConnection.setLoginUserPassword(loginUserPwd);
            }
            openHimConnection.setStatus("SUCCESS");
        } catch (Exception e) {
                //log.error("");
        }
        return openHimConnection;
    }

}
