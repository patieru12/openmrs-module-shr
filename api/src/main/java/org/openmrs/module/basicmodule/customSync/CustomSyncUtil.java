package org.openmrs.module.basicmodule.customSync;

import org.hibernate.type.Type;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.PrimaryCareBusinessLogic;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomSyncUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSyncUtil.class);

    public void saveSyncRecord(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        try{
            String UpIDPattern = "^\\d{6}-\\d{4}-\\d{4}$";
            if (entity.getClass().getName().equalsIgnoreCase("org.openmrs.Obs")) {
                Obs obs = (Obs) entity;
                String patientUPID = obs.getEncounter().getPatient().getPatientIdentifier("UPID").getIdentifier();
                if(Pattern.matches(UpIDPattern, patientUPID)){
                    saveObsEncounter(patientUPID, (String) state[0], 1, new Date());
                } else {
                    LOGGER.info("Unable to save record for sync operation Due to invalid UPID: " + patientUPID);
                }
            } else if (entity.getClass().getName().equalsIgnoreCase("org.openmrs.Encounter")) {

                Encounter encounter = (Encounter) entity;
                String patientUPID = encounter.getPatient().getPatientIdentifier("UPID").getIdentifier();
                if(Pattern.matches(UpIDPattern, patientUPID)){
                    saveObsEncounter(patientUPID, (String) state[0], 2, new Date());
                } else {
                    LOGGER.info("Unable to save record for sync operation Due to invalid UPID: " + patientUPID);
                }
            } else if (entity.getClass().getName().equalsIgnoreCase("org.openmrs.TestOrder")) {
                TestOrder tro = (TestOrder) entity;
                String patientUPID = tro.getPatient().getPatientIdentifier("UPID").getIdentifier();
                if(Pattern.matches(UpIDPattern, patientUPID)){
                    saveObsEncounter(patientUPID, (String) state[0], 3, new Date());
                } else {
                    LOGGER.info("Unable to save record for sync operation Due to invalid UPID: " + patientUPID);
                }
            } else if (entity.getClass().getName().equalsIgnoreCase("org.openmrs.DrugOrder")) {
                DrugOrder dro = (DrugOrder) entity;
                String patientUPID = dro.getPatient().getPatientIdentifier("UPID").getIdentifier();
                if(Pattern.matches(UpIDPattern, patientUPID)){
                    saveObsEncounter(patientUPID, (String) state[0], 4, new Date());
                } else {
                    LOGGER.info("Unable to save record for sync operation Due to invalid UPID: " + patientUPID);
                }
            }  else {
                LOGGER.error(">>>>>>>>>>>>" + entity.getClass().getName() + "<<<<<<<<<<<<<<<<<");
            }
        } catch(NullPointerException npe){
            LOGGER.info(npe.getMessage());
        }
    }

    private String getPatientId(Patient patient) {

        Set<PatientIdentifier> identifiers = patient.getIdentifiers();
        for (PatientIdentifier pi : identifiers) {
            if (pi.getIdentifierType().getPatientIdentifierTypeId() == 5) {
                return pi.getIdentifier();
            }
        }
        return null;
    }

    public void saveObsEncounter(String pid, String o, int type, Date date) {

        ObsEncounterSync obsEncounterSync = new ObsEncounterSync();
        obsEncounterSync.setPatientId(pid);
        obsEncounterSync.setUuid(o);
        obsEncounterSync.setType(type);
        obsEncounterSync.setCreatedDate(date);
        obsEncounterSync.setIsSynced(false);
        LOGGER.error("Saving Object >>>>>>>>>>>>>>>>>>>>" + obsEncounterSync.toString());
        //  BasicModuleBusinessLogic.getService().saveOfflineTransaction(obsEncounterSync);
        PrimaryCareBusinessLogic.getService().saveTrans(obsEncounterSync);
        LOGGER.error("Successfully Saved Object >>>>>>>>>>>>>>>>>>>>" + obsEncounterSync.toString());

    }
    /*
    ToDo 1. public void saveEncounter();
           2. public void saveObservation();
    */

}
