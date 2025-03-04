package org.openmrs.module.basicmodule.controller;

import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.PrimaryCareBusinessLogic;
import org.openmrs.module.basicmodule.RunShrSchedulerSHR;
import org.openmrs.module.basicmodule.constants.Sync2Constants;
import org.openmrs.module.basicmodule.customSync.CustomSyncUtil;
import org.openmrs.module.basicmodule.model.ShrOffline;
import org.openmrs.module.basicmodule.pojo.OpenHimConnection;
import org.openmrs.module.basicmodule.pojo.globalResponse.ResponseDto;
import org.openmrs.module.basicmodule.pojo.globalResponse.result.ResultSet;
import org.openmrs.module.basicmodule.pojo.response.shrEncounter.Entry;
import org.openmrs.module.basicmodule.pojo.response.shrEncounter.ShrObservationResponse;
import org.openmrs.module.basicmodule.utils.CustomUtils;
import org.openmrs.util.PrivilegeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller("sync2.SyncController")
@RequestMapping(value = "/rest/shr")
public class SyncController {

    private final Logger LOGGER = LoggerFactory.getLogger(SyncController.class);
    private OpenHimConnection openHimConnection = null;

    @RequestMapping(value = "/getShr", method = RequestMethod.GET)
    public ResponseEntity<Object> getSHRData(@RequestParam String searchType, @RequestParam String upi, @RequestParam(defaultValue = "1", required = false) String page, @RequestParam(defaultValue = "10", required = false) String size,
                                             @RequestParam(defaultValue = "", required = false) String fromDate,
                                             @RequestParam(defaultValue = "", required = false) String toDate,
                                             @RequestParam(defaultValue = "", required = false) String type,
                                             @RequestParam(defaultValue = "", required = false) String location) {


        LOGGER.error("--------------------->>>> Find Patient");
        LOGGER.error("Params : " + searchType + " ---->" + upi + " ---->" + page + " ---->" + size);

        if (!CustomUtils.isOnline()) {
            ResponseDto o = new ResponseDto();
            o.setStatus("NO SHR DATA FOUND!");
            return new ResponseEntity<>(o, HttpStatus.OK);
        }

        if (!Context.isAuthenticated()) {
            return new ResponseEntity<>("unatuh", HttpStatus.UNAUTHORIZED);
        }
        ResponseEntity<Object> o = null;
        ShrObservationResponse shrObs = null;
        ShrObservationResponse shrOfflineObs = null;
        ShrObservationResponse shrEncounter = null;
        ShrObservationResponse shrOfflineEncounter = null;
        Integer count = 0;
        if (searchType.equalsIgnoreCase(Sync2Constants.SYSTEM_OBS)) {
            LOGGER.error(">>>>>>>>>>>>>>>>>>> In Get Response");
            if (CustomUtils.isOnline()) {
                shrObs = checkObservationSHR(upi, page, size);
            } else {
                ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi, searchType);
                if (shrOffline != null) {
                    shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                    int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                    shrObs = shrOfflineObs;
                    List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                    shrObs.setEntry(entries);
                    shrObs.setTotal(shrObs.getEntry().size());
                }
            }
            if (shrObs != null) {
                count = shrObs.getTotal();
            }
            o = getResponse(shrObs, count);
        } else {
            if (searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_OTHERS) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_ALL) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DRUG_ADMINISTRATION) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_REGISTRATION) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DRUG_ORDER) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_LAB_TEST) || searchType.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DIAGNOSIS)) {

                if (CustomUtils.isOnline()) {
                    shrEncounter = checkEncounterSHR(upi, searchType, page, size, fromDate, toDate, type, location);
                } else {
                    ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi, searchType);
                    shrOfflineEncounter = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                    int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineEncounter.getTotal());
                    shrEncounter = shrOfflineEncounter;
                    List<Entry> entries = shrEncounter.getEntry().size() <= pos[0] ? new ArrayList<>() : shrEncounter.getEntry().size() <= pos[1] ? shrEncounter.getEntry().subList(pos[0], shrEncounter.getEntry().size() - 1) : shrEncounter.getEntry().subList(pos[0], pos[1]);
                    shrEncounter.setEntry(entries);
                    shrEncounter.setTotal(shrEncounter.getEntry().size());
                }

                if (shrEncounter != null) count = shrEncounter.getTotal();

                o = getResponse(shrEncounter, count);
            }
        }

        if (CustomUtils.isOnline() && count > 0) {
            saveOffline(upi, searchType);
        }

            /*if (count > 0) {
                ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi,Sync2Constants.SYSTEM_OBS);
                if(shrOffline == null) {
                    shrOffline = new ShrOffline();
                    shrOffline.setUpid(upi);
                    shrOffline.setNid("NID");
                    shrOffline.setPatientId(upi);
                    shrOffline.setType(Sync2Constants.SYSTEM_OBS);
                    shrOffline.setResponse(new Gson().toJson(shrObs));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                } else {
                    shrOffline.setResponse(new Gson().toJson(shrObs));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                }
            }*/

/*
            if(shrObs!=null) {
                ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi,"OBS");
                if(shrOffline == null) {
                    shrOffline = new ShrOffline();
                    shrOffline.setPatientId(upi);
                    shrOffline.setType("OBS");
                    shrOffline.setResponse(new Gson().toJson(shrObs));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                } else {
                    shrOffline.setResponse(new Gson().toJson(shrObs));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                }
            }
            if(shrEncounter!=null) {
                ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi,"ENC");
                if(shrOffline == null) {
                    shrOffline = new ShrOffline();
                    shrOffline.setPatientId(upi);
                    shrOffline.setType("ENC");
                    shrOffline.setResponse(new Gson().toJson(shrEncounter));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                } else {
                    shrOffline.setResponse(new Gson().toJson(shrObs));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                }
            }
*/
        return o;
    }

    @RequestMapping(value = "/syncShr", method = RequestMethod.GET)
    public ResponseEntity<Object> syncShr() {
        LOGGER.error("-----------OBS SIZE -- RUnning RunShrSchedulerSHR");
        try {
            RunShrSchedulerSHR schedulerSHR = new RunShrSchedulerSHR();
            schedulerSHR.run();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("-----FAILURE------", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("-----SUCCESS------", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/migrateShr", method = RequestMethod.GET)
    public ResponseEntity<Object> migrateRecord(@RequestParam Integer patentId) {
        LOGGER.error("-----------OBS SIZE -- RUnning RunShrSchedulerSHR");
        try {
            Context.addProxyPrivilege(PrivilegeConstants.GET_OBS);
            Context.addProxyPrivilege(PrivilegeConstants.GET_ENCOUNTERS);
            Context.addProxyPrivilege(PrivilegeConstants.GET_PATIENTS);
            Patient patient = Context.getPatientService().getPatient(patentId);
            Context.openSession();
            Context.authenticate("admin", "Umurwayi5!");
           /* Context.openSession();
            Credentials credentials = new UsernamePasswordCredentials("admin", "Umurwayi5!");*/
            // Context.authenticate(credentials);

            List<Obs> obses = Context.getObsService().getObservations(Collections.singletonList(patient.getPerson()), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), null, null, null, null, true, null);
            LOGGER.error("-----------OBS SIZE --" + obses.size());

            CustomSyncUtil customSyncUtil = new CustomSyncUtil();
            for (Obs o : obses) {
                customSyncUtil.saveObsEncounter("pid", o.getUuid(), 1, new Date());
            }

            List<Encounter> enc = Context.getEncounterService().getEncountersByPatient(patient);
            //Map<Integer, List<Encounter>> enc = Context.getEncounterService().getAllEncounters(null);
            LOGGER.error("-----------ENC SIZE --" + enc.size());

            for (Encounter entry : enc) {

                customSyncUtil.saveObsEncounter(String.valueOf(entry.getPatient().getId()), entry.getUuid(), 2, new Date());

               /* LOGGER.error("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue().size());

                for (Encounter e : entry.getValue()) {
                    customSyncUtil.saveObsEncounter(String.valueOf(entry.getKey()), e.getUuid(), 2, new Date());
                    a++;
                }
                if(a == 101){
                    LOGGER.error("-----------ENC BREAK --=== i "+ a);
                    break;
                }*/
            }

            /*   for (Map.Entry<Integer, List<Encounter>> entry : enc.entrySet()) {
             *//*  LOGGER.error("-----------ENC SIZE --" + obses.size());

                LOGGER.error("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue().size());*//*

                for (Encounter e : entry.getValue()) {
                    customSyncUtil.saveObsEncounter(String.valueOf(entry.getKey()), e.getUuid(), 2, new Date());
                    a++;
                }
                if(a == 101){
                    LOGGER.error("-----------ENC BREAK --=== i "+ a);
                    break;
                }
            }*/


            //List<Order> orders = PrimaryCareBusinessLogic.getService().getShrData(String.valueOf(patentId));
            List<Order> orders = Context.getOrderService().getAllOrdersByPatient(patient);
            LOGGER.error("-----------Order SIZE --" + orders.size());
            for (Order or : orders) {
                LOGGER.error("Order Key = " + or.getOrderType().getName() +
                        ", Order Value = " + or.getUuid());
                customSyncUtil.saveObsEncounter("pid", or.getUuid(), 4, new Date());
            }
            /*for (Map.Entry<Integer, List<Encounter>> entry : enc.entrySet()) {
                LOGGER.error("-----------ENC SIZE --" + obses.size());

                LOGGER.error("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue().size());

                for (Encounter e : entry.getValue()) {
                    customSyncUtil.saveObsEncounter("pid", e.getUuid(), 3, new Date());
                }
            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("-----------", HttpStatus.ACCEPTED);
    }


    private int[] paginationPos(int size, int page, int listSize) {

        int startingPos = (page - 1) * size;
        int endingPos = ((page) * size);
        return new int[]{startingPos, endingPos};
    }

    private void saveOffline(String upi, String searchType) {
       /* ShrOffline shrOffline = PrimaryCareBusinessLogic.getService().getShrDataById(upi, searchType);
        ShrObservationResponse shrObs = null;
        if (searchType.equalsIgnoreCase(Sync2Constants.SYSTEM_OBS)) {
            shrObs = checkObservationSHR(upi, "1", "10000");
        } else {
            shrObs = checkEncounterSHR(upi, searchType, "1", "10000", "", "", "", "");
        }

        if (shrOffline == null) {
            shrOffline = new ShrOffline();
            shrOffline.setUpid(upi);
            shrOffline.setNid("NID");
            shrOffline.setPatientId(upi);
            shrOffline.setType(searchType.toUpperCase(Locale.ROOT));
            shrOffline.setResponse(new Gson().toJson(shrObs));
            shrOffline.setEncounterId("NA");
            shrOffline.setPullTimestamp(new Date());
            PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
        } else {
            shrOffline.setResponse(new Gson().toJson(shrObs));
            shrOffline.setPullTimestamp(new Date());
            PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
        }*/

    }

    private void saveEncOffline(String searchSet, String uuid, ShrObservationResponse response) {
        /*List<ShrOffline> shrOfflineList = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
        if (shrOfflineList == null || shrOfflineList.isEmpty()) {
            ShrOffline shrOffline = new ShrOffline();
            shrOffline.setUpid("UPI");
            shrOffline.setNid("NID");
            shrOffline.setPatientId("upi");
            shrOffline.setType(searchSet.toUpperCase(Locale.ROOT));
            shrOffline.setResponse(new Gson().toJson(response));
            shrOffline.setPullTimestamp(new Date());
            shrOffline.setEncounterId(uuid);
            PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
        } else {
            for (ShrOffline shrOffline : shrOfflineList) {
                if (shrOffline.getType().equalsIgnoreCase(searchSet)) {
                    shrOffline.setResponse(new Gson().toJson(response));
                    shrOffline.setPullTimestamp(new Date());
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                } else {
                    shrOffline = new ShrOffline();
                    shrOffline.setUpid("UPI");
                    shrOffline.setNid("NID");
                    shrOffline.setPatientId("upi");
                    shrOffline.setType(searchSet.toUpperCase(Locale.ROOT));
                    shrOffline.setResponse(new Gson().toJson(response));
                    shrOffline.setPullTimestamp(new Date());
                    shrOffline.setEncounterId(uuid);
                    PrimaryCareBusinessLogic.getService().saveShrOffline(shrOffline);
                }
            }

        }*/
    }

    @RequestMapping(value = "/{searchSet}/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEncObservation(@PathVariable String searchSet, @PathVariable String uuid, @RequestParam(defaultValue = "1", required = false) String page, @RequestParam(defaultValue = "50", required = false) String size) {

        if (!Context.isAuthenticated()) {
            return new ResponseEntity<>("unatuh", HttpStatus.UNAUTHORIZED);
        }

        openHimConnection = getOpenHimConnection();
        LOGGER.error("--------------------->>>> Find Patient");
        ShrObservationResponse shrObs = null;
        ShrObservationResponse shrOfflineObs = null;
        ShrObservationResponse shrMedication = null;
        ShrObservationResponse shrOfflineMedication = null;
        ShrObservationResponse shrService = null;
        ShrObservationResponse shrOfflineService = null;
        ResponseEntity<Object> o = null;
        ResultSet result = new ResultSet();
        if (searchSet.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DRUG_ORDER)) {
            if (CustomUtils.isOnline()) {
                shrObs = checkEncObservation(uuid, page, size);
                if (shrObs != null) {
                    saveEncOffline(Sync2Constants.SYSTEM_OBS, uuid, shrObs);
                }
                shrMedication = checkMedicationSHR(uuid, page, size);
                if (shrMedication != null) {
                    saveEncOffline(Sync2Constants.SEARCH_SET_MEDICATION_REQUEST, uuid, shrMedication);
                }
            } else {
                List<ShrOffline> shrDataByUuid = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
                if (shrDataByUuid != null && !shrDataByUuid.isEmpty()) {
                    for (ShrOffline shrOffline : shrDataByUuid) {
                        if (shrOffline.getType().equalsIgnoreCase(Sync2Constants.SEARCH_SET_MEDICATION_REQUEST)) {
                            shrOfflineMedication = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineMedication.getTotal());
                            shrMedication = shrOfflineMedication;
                            List<Entry> entries = shrOfflineMedication.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineMedication.getEntry().size() <= pos[1] ? shrOfflineMedication.getEntry().subList(pos[0], shrOfflineMedication.getEntry().size()) : shrOfflineMedication.getEntry().subList(pos[0], pos[1]);
                            shrMedication.setEntry(entries);
                            shrMedication.setTotal(shrMedication.getEntry().size());
                        }
                        if (shrOffline.getType().equalsIgnoreCase(Sync2Constants.SYSTEM_OBS)) {
                            shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                            shrObs = shrOfflineObs;
                            List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                            shrObs.setEntry(entries);
                            shrObs.setTotal(shrObs.getEntry().size());
                        }
                    }
                }
            }
            Integer count = 0;
            if (shrObs != null) {
                count += shrObs.getTotal();
                result.setObservation(shrObs);
            }
            if (shrMedication != null) {
                count += shrMedication.getTotal();
                result.setMedicationRequest(shrMedication);
            }
            o = getResponse(result, count);
        } else if (searchSet.equalsIgnoreCase(Sync2Constants.SEARCH_SET_LAB_TEST)) {
            if (CustomUtils.isOnline()) {
                shrObs = checkEncObservation(uuid, page, size);
                if (shrObs != null) {
                    saveEncOffline(Sync2Constants.SYSTEM_OBS, uuid, shrObs);
                }
                shrService = checkServiceSHR(uuid, page, size);
                if (shrService != null) {
                    saveEncOffline(Sync2Constants.SEARCH_SET_SERVICE_REQUEST, uuid, shrService);
                }
            } else {
                List<ShrOffline> shrDataByUuid = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
                if (shrDataByUuid != null && !shrDataByUuid.isEmpty()) {
                    for (ShrOffline shrOffline : shrDataByUuid) {
                        if (shrOffline.getType().equalsIgnoreCase(Sync2Constants.SEARCH_SET_SERVICE_REQUEST)) {
                            shrOfflineService = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineService.getTotal());
                            shrService = shrOfflineService;
                            List<Entry> entries = shrOfflineService.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineService.getEntry().size() <= pos[1] ? shrOfflineService.getEntry().subList(pos[0], shrOfflineService.getEntry().size()) : shrOfflineService.getEntry().subList(pos[0], pos[1]);
                            shrService.setEntry(entries);
                            shrService.setTotal(shrService.getEntry().size());
                        }
                        if (shrOffline.getType().equalsIgnoreCase(Sync2Constants.SYSTEM_OBS)) {
                            shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                            shrObs = shrOfflineObs;
                            List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                            shrObs.setEntry(entries);
                            shrObs.setTotal(shrObs.getEntry().size());
                        }
                    }
                }
            }

            Integer count = 0;
            if (shrObs != null) {
                count += shrObs.getTotal();
                result.setObservation(shrObs);
            }
            if (shrService != null) {
                count += shrService.getTotal();
                result.setServiceRequest(shrService);
            }
            o = getResponse(result, count);
        } else if (searchSet.equalsIgnoreCase(Sync2Constants.SEARCH_SET_OTHERS)) {
            if (CustomUtils.isOnline()) {
                shrObs = checkEncObservation(uuid, page, size);
                if (shrObs != null) {
                    saveEncOffline(searchSet, uuid, shrObs);
                }
            } else {
                List<ShrOffline> shrDataByUuid = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
                if (shrDataByUuid != null && !shrDataByUuid.isEmpty()) {
                    for (ShrOffline shrOffline : shrDataByUuid) {
                        if (shrOffline.getType().equalsIgnoreCase(searchSet)) {
                            shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                            shrObs = shrOfflineObs;
                            List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                            shrObs.setEntry(entries);
                            shrObs.setTotal(shrObs.getEntry().size());
                        }
                    }
                }
            }
            Integer count = 0;
            if (shrObs != null) count = shrObs.getTotal();
            o = getResponse(shrObs, count);
        } else if (searchSet.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DRUG_ADMINISTRATION)) {
            if (CustomUtils.isOnline()) {
                shrObs = checkEncObservation(uuid, page, size);
                if (shrObs != null) {
                    saveEncOffline(searchSet, uuid, shrObs);
                }
            } else {
                List<ShrOffline> shrDataByUuid = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
                if (shrDataByUuid != null && !shrDataByUuid.isEmpty()) {
                    for (ShrOffline shrOffline : shrDataByUuid) {
                        if (shrOffline.getType().equalsIgnoreCase(searchSet)) {
                            shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                            shrObs = shrOfflineObs;
                            List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                            shrObs.setEntry(entries);
                            shrObs.setTotal(shrObs.getEntry().size());
                        }
                    }
                }
            }
            Integer count = 0;
            if (shrObs != null) count = shrObs.getTotal();
            o = getResponse(shrObs, count);
        } else if (searchSet.equalsIgnoreCase(Sync2Constants.SEARCH_SET_DIAGNOSIS)) {
            if (CustomUtils.isOnline()) {
                shrObs = checkEncObservation(uuid, page, size);
                if (shrObs != null) {
                    saveEncOffline(searchSet, uuid, shrObs);
                }
            } else {
                List<ShrOffline> shrDataByUuid = PrimaryCareBusinessLogic.getService().getShrDataByUuid(uuid);
                if (shrDataByUuid != null && !shrDataByUuid.isEmpty()) {
                    for (ShrOffline shrOffline : shrDataByUuid) {
                        if (shrOffline.getType().equalsIgnoreCase(searchSet)) {
                            shrOfflineObs = new Gson().fromJson(shrOffline.getResponse(), ShrObservationResponse.class);
                            int[] pos = paginationPos(Integer.parseInt(size), Integer.parseInt(page), shrOfflineObs.getTotal());
                            shrObs = shrOfflineObs;
                            List<Entry> entries = shrOfflineObs.getEntry().size() <= pos[0] ? new ArrayList<>() : shrOfflineObs.getEntry().size() <= pos[1] ? shrOfflineObs.getEntry().subList(pos[0], shrOfflineObs.getEntry().size()) : shrOfflineObs.getEntry().subList(pos[0], pos[1]);
                            shrObs.setEntry(entries);
                            shrObs.setTotal(shrObs.getEntry().size());
                        }
                    }
                }
            }
            Integer count = 0;
            if (shrObs != null) count = shrObs.getTotal();
            o = getResponse(shrObs, count);
        }
        if(o == null){
            ResponseDto o1 = new ResponseDto();
            o1.setStatus("NO SHR DATA FOUND!");
            return new ResponseEntity<>(o1, HttpStatus.OK);
        }
        return o;
    }

    private ShrObservationResponse checkEncObservation(String uuid, String page, String size) {

        openHimConnection = getOpenHimConnection();
        if (openHimConnection.getStatus().equals("SUCCESS")) {
            if (uuid != null && !uuid.isEmpty()) {
                LOGGER.error("===================Get Obs");
                String UPID = "";

                uuid = uuid.trim().replaceAll(" ", "");
                final String uri = openHimConnection.getOpenhimUrl() + "/Observation?searchSet=ENCOUNTER&value=" + uuid + "&page=" + page + "&size=" + size;
                RestTemplate restTemplate = new RestTemplate();

                String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Basic " + base64Creds);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<ShrObservationResponse> response = null;
                ShrObservationResponse result = null;
                try {
                    response = restTemplate.exchange(uri, HttpMethod.GET, request, ShrObservationResponse.class);
                    result = response.getBody();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                LOGGER.error("===================" + new Gson().toJson(result));
                //Check CR
                if (result != null && result.getEntry() != null) {
                    return result;
                } else {
                    return null;
                }
            }
        }
        return null;

    }

    public ResponseEntity<Object> getResponse(Object shrObs, Integer count) {

        ResponseDto o = new ResponseDto();
        if (shrObs != null) {
            o.setResults(shrObs);
        }
        o.setCount(count);
        if (shrObs == null) {
            o.setStatus("NO SHR DATA FOUND!");
        } else {
            o.setStatus("SUCCESS");
        }
        return new ResponseEntity<>(o, HttpStatus.OK);

    }

    private ShrObservationResponse checkObservationSHR(String identifier, String page, String size) {
        openHimConnection = getOpenHimConnection();
        if (openHimConnection.getStatus().equals("SUCCESS")) {
            if (identifier != null && !identifier.isEmpty()) {
                LOGGER.error("===================Get Obs");
                String UPID = "";
                identifier = identifier.trim().replaceAll(" ", "");
                final String uri = openHimConnection.getOpenhimUrl() + "/Observation?searchSet=UPI&value=" + identifier + "&page=" + page + "&size=" + size;
                RestTemplate restTemplate = new RestTemplate();

                String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Basic " + base64Creds);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<ShrObservationResponse> response = null;
                ShrObservationResponse result = null;
                try {
                    response = restTemplate.exchange(uri, HttpMethod.GET, request, ShrObservationResponse.class);
                    result = response.getBody();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                LOGGER.error("===================" + new Gson().toJson(result));
                //Check CR
                if (result != null && result.getEntry() != null) {
                    return result;
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    private ShrObservationResponse checkMedicationSHR(String identifier, String page, String size) {
        openHimConnection = getOpenHimConnection();
        if (openHimConnection.getStatus().equals("SUCCESS")) {
            if (identifier != null && !identifier.isEmpty()) {
                LOGGER.error("===================Get Obs");
                String UPID = "";
                identifier = identifier.trim().replaceAll(" ", "");
                final String uri = openHimConnection.getOpenhimUrl() + "/MedicationRequest?searchSet=Medication&value=" + identifier + "&page=" + page + "&size=" + size;
                RestTemplate restTemplate = new RestTemplate();

                String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Basic " + base64Creds);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<ShrObservationResponse> response = null;
                ShrObservationResponse result = null;
                try {
                    response = restTemplate.exchange(uri, HttpMethod.GET, request, ShrObservationResponse.class);
                    result = response.getBody();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                LOGGER.error("===================" + new Gson().toJson(result));
                //Check CR
                if (result != null && result.getEntry() != null) {
                    return result;
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    private ShrObservationResponse checkServiceSHR(String identifier, String page, String size) {
        openHimConnection = getOpenHimConnection();
        if (openHimConnection.getStatus().equals("SUCCESS")) {
            if (identifier != null && !identifier.isEmpty()) {
                LOGGER.error("===================Get Obs");
                String UPID = "";
                identifier = identifier.trim().replaceAll(" ", "");
                final String uri = openHimConnection.getOpenhimUrl() + "/ServiceRequest?searchSet=Service&value=" + identifier + "&page=" + page + "&size=" + size;
                RestTemplate restTemplate = new RestTemplate();

                String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Basic " + base64Creds);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<ShrObservationResponse> response = null;
                ShrObservationResponse result = null;
                try {
                    response = restTemplate.exchange(uri, HttpMethod.GET, request, ShrObservationResponse.class);
                    result = response.getBody();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                LOGGER.error("===================" + new Gson().toJson(result));
                //Check CR
                if (result != null && result.getEntry() != null) {
                    return result;
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    private ShrObservationResponse checkEncounterSHR(String identifier, String searchSet, String page, String size,
                                                     String fromDate, String toDate, String type, String location) {
        openHimConnection = getOpenHimConnection();
        if (openHimConnection.getStatus().equals("SUCCESS")) {
            if (identifier != null && !identifier.isEmpty()) {
                LOGGER.error("===================get ShrEncounter...");
                String UPID = "";

                identifier = identifier.trim().replaceAll(" ", "");
//                final String uri = openHimConnection.getOpenhimUrl() + "/Encounter?searchSet=" + searchSet + "&value=" + identifier + "&page=" + page + "&size=" + size;
                String uri = openHimConnection.getOpenhimUrl() + "/Encounter?searchSet=" + searchSet + "&value=" + identifier + "&page=" + page + "&size=" + size;

                if(!fromDate.isEmpty()){
                    uri = uri + "&fromDate=" + fromDate ;
                }
                if(!toDate.isEmpty()){
                    uri = uri + "&toDate=" + toDate ;
                }

                if(!type.isEmpty()){
                    uri = uri + "&type=" + type ;
                }

                if(!location.isEmpty()){
                    uri = uri + "&location=" + location ;
                }


                RestTemplate restTemplate = new RestTemplate();

                String plainCreds = openHimConnection.getOpenhimClientId() + ":" + openHimConnection.getOpenhimPassword();
                byte[] plainCredsBytes = plainCreds.getBytes();
                byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
                String base64Creds = new String(base64CredsBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", "Basic " + base64Creds);
                HttpEntity<String> request = new HttpEntity<String>(headers);
                ResponseEntity<String> response = null;
                String resultStr = null;
                ShrObservationResponse result = null;
                try {
                    response = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
                    resultStr = response.getBody().replace("\"class\"", "\"detail\"");
                    result = new Gson().fromJson(resultStr, ShrObservationResponse.class);
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }

                LOGGER.error("===================" + new Gson().toJson(result));
                //Check CR
                if (result != null && result.getEntry() != null) {
                    return result;
                } else {
                    return null;
                }
            }
        }
        return null;
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
                openHimConnection.setOpenhimUrl(openhimPatientUrl + "/shr");
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


}
