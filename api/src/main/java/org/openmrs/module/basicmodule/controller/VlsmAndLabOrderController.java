package org.openmrs.module.basicmodule.controller;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.PrimaryCareBusinessLogic;
import org.openmrs.module.basicmodule.constants.Sync2Constants;
import org.openmrs.module.basicmodule.model.OfflineTransaction;
import org.openmrs.module.basicmodule.model.PayloadData;
import org.openmrs.module.basicmodule.pojo.OpenHimConnection;
import org.openmrs.module.basicmodule.pojo.laborder.LapOrderReq;
import org.openmrs.module.basicmodule.pojo.vlsm.VlsmOrderRequest;
import org.openmrs.module.basicmodule.utils.CustomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Controller("vlsm.VlsmAndLabOrderController")
@RequestMapping(value = "/rest/v1")
public class VlsmAndLabOrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(VlsmAndLabOrderController.class);
    private OpenHimConnection openHimConnection = null;

    String facilityid = "FC1111";
    String facilityName = "FC1111";
    /*
    @RequestMapping(value = "/api/getNpcProducts", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct() {
        openHimConnection = getOpenHimConnection();
        LOGGER.error("--------------------->>>> api/getNpcProducts");

        return getDataWithUrl("/vlsm/order", "");
    }*/


    @RequestMapping(value = "/vlsm/order", method = RequestMethod.POST)
    public ResponseEntity<?> postVlsm(@RequestBody VlsmOrderRequest request) {
        openHimConnection = getOpenHimConnection();
        LOGGER.error("--------------------->>>> vlsm/order");
        LOGGER.error("------------vlsm/order--->0" + new Gson().toJson(request));

        String fcId = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_FACILITY_ID);
        if (fcId == null || fcId.isEmpty()) request.facilityId = facilityid;
        else request.facilityId = fcId;
        if(!CustomUtils.isOnline()) {

            //LOGGER.error("Try to save in Offline Table now.");
            PayloadData payloadData = new PayloadData();
            payloadData.payload = new Gson().toJson(request);
            payloadData.type = "vlsm_order";

            OfflineTransaction offlineTransaction = new OfflineTransaction();
            offlineTransaction.setTimestamp(new Date());
            offlineTransaction.setIsUpdated(0);
            offlineTransaction.setRetryCount(0);
            offlineTransaction.setPayload(new Gson().toJson(payloadData));
            offlineTransaction.setType(payloadData.type);

            offlineTransaction.setNationalIdType("N/A");
            offlineTransaction.setNationalId("N/A");

            offlineTransaction.setUuid(UUID.randomUUID().toString());

            PrimaryCareBusinessLogic.getService().saveOfflineTransaction(offlineTransaction);

            return new ResponseEntity<>("{\"success\": true, \"message\": \"VLSM Order Saved in Local Database\"}", HttpStatus.OK);
        } else {
            return postData("/vlsm/order", new Gson().toJson(request));
        }
    }


    @RequestMapping(value = "/lab/order", method = RequestMethod.POST)
    public ResponseEntity<?> postLabOrder(@RequestBody LapOrderReq request) {

        //Here Make sure to check if the systment is online
        //PrimaryCareBusinessLogic.getService().
        openHimConnection = getOpenHimConnection();
        //LOGGER.error("--------------------->>>> lab/order");
        //LOGGER.error("------------lab/order--->0" + new Gson().toJson(request));
        String fcId = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_FACILITY_ID);
        String fcName = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_FOSILITY_NAME);
        if (fcId == null || fcId.isEmpty()) request.FOSAID = facilityid;
        else request.FOSAID = fcId;

        if (fcName == null || fcName.isEmpty()) request.FacilityName = facilityName;
        else request.FacilityName = fcName;

        //PrimaryCareBusinessLogic.getService().

        if(!CustomUtils.isOnline()) {

            //LOGGER.error("Try to save in Offline Table now.");
            PayloadData payloadData = new PayloadData();
            payloadData.payload = new Gson().toJson(request);
            payloadData.type = "lab_order";

            OfflineTransaction offlineTransaction = new OfflineTransaction();
            offlineTransaction.setTimestamp(new Date());
            offlineTransaction.setIsUpdated(0);
            offlineTransaction.setRetryCount(0);
            offlineTransaction.setPayload(new Gson().toJson(payloadData));
            offlineTransaction.setType(payloadData.type);

            offlineTransaction.setNationalIdType("N/A");
            offlineTransaction.setNationalId("N/A");

            offlineTransaction.setUuid(UUID.randomUUID().toString());

            PrimaryCareBusinessLogic.getService().saveOfflineTransaction(offlineTransaction);

            return new ResponseEntity<>("{\"success\": true, \"message\": \"Lab Order Saved in Local Database\"}", HttpStatus.OK);
        } else {
            return postData("/lab/order", new Gson().toJson(request));
        }
    }


    @RequestMapping(value = "/vlsm/findorder", method = RequestMethod.GET)
    public ResponseEntity<?> getVlsmOrder(@RequestParam String patientid, @RequestParam(required = false) String testname, @RequestParam(required = false) String dateTo,
                                          @RequestParam(required = false) String dateFrom, @RequestParam(defaultValue = "1", required = false) String page, @RequestParam(defaultValue = "100", required = false) String size) {
        openHimConnection = getOpenHimConnection();
        //LOGGER.error("--------------------->>>> Find Patient");
        //LOGGER.error("Params : " + patientid + " ---->");
        //  return getData("/vlsm/findorder", "facilityid=" + facilityid + "&patientid=" + patientid);
        String param = "uniqueid=" + patientid + "&testname=" + testname + "&datefrom=" + dateFrom + "&dateto=" + dateTo + "&page=" +page + "&size="+size;
        return getData("/vlsm/findorder", param);
    }

    @RequestMapping(value = "/lab/findorder", method = RequestMethod.GET)
    public ResponseEntity<?> getLabOrder(@RequestParam String patientid, @RequestParam(required = false) String testname, @RequestParam(required = false) String dateTo,
                                         @RequestParam(required = false) String dateFrom, @RequestParam(defaultValue = "1", required = false) String page, @RequestParam(defaultValue = "100", required = false) String size) {
        openHimConnection = getOpenHimConnection();
        //LOGGER.error("--------------------->>>> Find Patient");
        //LOGGER.error("Params : " + patientid + " ---->");
        // return getData("/lab/findorder", "fosaid=" + facilityid + "&patientid=" + patientid);
        String fcId = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_FACILITY_ID);
        if (fcId != null && !fcId.isEmpty()) facilityid = fcId;

        String param = "fosaid=" + facilityid + "&patientid=" + patientid + "&testname=" + testname + "&datefrom=" + dateFrom + "&dateto=" + dateTo + "&page=" +page + "&size="+size;

        return getData("/lab/findorder", param);
    }

    public OpenHimConnection getOpenHimConnection() {
        try {
            openHimConnection = new OpenHimConnection();
            final String openhimPatientUrl = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_NIDA_API);
            if (openhimPatientUrl == null || openhimPatientUrl.isEmpty()) {
//                    log.error("[error]------ Openhim patient report URL is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("Undefined");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimUrl(openhimPatientUrl);
            }

            final String openhimClientID = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_USER_NAME);
            if (openhimClientID == null || openhimClientID.isEmpty()) {
//                    log.error("[error]------ Openhim client ID is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("CLIENT_ID_UNDEFINED");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimClientId(openhimClientID);
            }
            final String openhimPwd = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_USER_PWD);
            if (openhimPwd == null || openhimPwd.isEmpty()) {
//                    log.error("[error]------ Openhim client Basic Auth Password is not defined on administration settings.");
                //model.addAttribute("nidaResult", "NOAPI");
                openHimConnection.setStatus("PASSWORD_UNDEFINED");
                return openHimConnection;
            } else {
                openHimConnection.setOpenhimPassword(openhimPwd);
            }
            openHimConnection.setStatus("SUCCESS");
        } catch (Exception e) {
//                log.error("");
        }
        return openHimConnection;
    }

    @RequestMapping(value = "/lab/find-latest", method = RequestMethod.GET)
    public ResponseEntity<?> getLatestLabAndVLSM(@RequestParam String patientid) {
        //check if the patient is having some data
        if( patientid == null || patientid.equalsIgnoreCase("")){
            return new ResponseEntity<>("{\"response\":false, \"patientId\":\""+ patientid +"\", \"message\":\"Patient does not have UPID: Viral Load latest results can't be filtered.\"}", HttpStatus.BAD_REQUEST);
        }
        openHimConnection = getOpenHimConnection();
        
        return getData("/lab/find-latest", "patientid=" + patientid);
    }


    public ResponseEntity<?> getData(String url, String param) {
        final String uri = openHimConnection.getOpenhimUrl() + url + "?" + param;
        RestTemplate restTemplate = new RestTemplate();

        String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Object> response = null;
        ResponseEntity<?> result = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.POST, request, Object.class);
            result = ResponseEntity.ok(response.getBody());
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        //LOGGER.error("===================" + new Gson().toJson(result));
        //Check CR
        return result;
    }

    private ResponseEntity<?> postData(String url, String data) {
        //LOGGER.error("-------\n\n-----FINAL REQ> " + data);
        final String uri = openHimConnection.getOpenhimUrl() + url;
        RestTemplate restTemplate = new RestTemplate();

        String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(data, headers);
        ResponseEntity<?> response = null;
        ResponseEntity<?> result = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.POST, request, Object.class);
            result = ResponseEntity.ok(response.getBody());
        } catch (HttpStatusCodeException e) {
            //LOGGER.error("result : " + result + " ---->");
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        //LOGGER.error("result : " + result + " ---->");
        return result;
    }
    /*
    public ResponseEntity<?> getDataWithUrl(String url, String param) {
        final String uri = "http://197.243.52.190:6009/api/getNpcProducts";
        RestTemplate restTemplate = new RestTemplate();

        String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Object> response = null;
        ResponseEntity<?> result = null;
        try {
            response = restTemplate.exchange(uri, HttpMethod.GET, request, Object.class);
            result = ResponseEntity.ok(response.getBody());
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        LOGGER.error("===================" + new Gson().toJson(result));
        //Check CR
        return result;
    }*/

}
