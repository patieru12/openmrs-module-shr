package org.openmrs.module.basicmodule.constants;

public class Sync2Constants {
    public static final String GLOBAL_PROPERTY_OPENHIM_NIDA_API = "primaryCare.openhim.nida.api";
    public static final String GLOBAL_PROPERTY_DHIS2_HOST = "primaryCare.dhis2.host";//"http://161.97.114.105:8080"
    public static final String GLOBAL_PROPERTY_OPENHIM_USER_NAME = "posttoopenhim.openhim.client.user";
    public static final String GLOBAL_PROPERTY_OPENHIM_USER_PWD = "posttoopenhim.openhim.client.pwd";

    public static final String GLOBAL_PROPERTY_SCHEDULER_PASSWORD = "scheduler.password";
    public static final String GLOBAL_PROPERTY_SCHEDULER_USERNAME = "scheduler.username";

    public static final String SYSTEM_LOCATION = "LOCATION";
    public static final String SYSTEM_DISPLAY = "OBSERVATION";
    public static final String SYSTEM_PERSON = "PERSON";
    public static final String SYSTEM_OBS = "OBSERVATION";
    public static final String SYSTEM_ENCOUNTER = "ENCOUNTER";
    public static final String SEARCH_SET_OTHERS = "OTHERS";
    public static final String SEARCH_SET_ALL = "ALL";
    public static final String SEARCH_SET_LAB_TEST = "LAB_TEST";
    public static final String SEARCH_SET_DRUG_ORDER = "DRUG_ORDER";
    public static final String SEARCH_SET_REGISTRATION = "VISIT";
    public static final String SEARCH_SET_DRUG_ADMINISTRATION = "DRUG_ADMINISTRATION";
    public static final String SEARCH_SET_DIAGNOSIS = "Diagnosis";
    public static final String SEARCH_SET_MEDICATION_REQUEST = "Medication";
    public static final String SEARCH_SET_SERVICE_REQUEST = "Service";

//    public static final String OBSERVATION_URL = "http://localhost:8080/openmrs/ws/rest/v1/obs/";
    public static final String OBSERVATION_URL = "http://localhost:8080/openmrs/ws/fhir2/R4/Observation/";
//    public static final String ENCOUNTER_URL = "http://localhost:8080/openmrs/ws/rest/v1/encounter/";
    public static final String ENCOUNTER_URL = "http://localhost:8080/openmrs/ws/fhir2/R4/Encounter/";
    public static final String SERVICE_REQUEST_URL = "http://localhost:8080/openmrs/ws/fhir2/R4/ServiceRequest/";
    public static final String MEDICATION_REQUEST_URL = "http://localhost:8080/openmrs/ws/fhir2/R4/MedicationRequest/";
    public static final String SHR_SERVER_URL = "http://10.100.100.25:8078/shr";
//    public static final String SHR_SERVER_URL = "http://localhost:8089";


    public static final String GLOBAL_PROPERTY_FACILITY_ID = "FOSAID";
    public static final String GLOBAL_PROPERTY_FOSILITY_NAME = "billing.healthFacilityName";

}
