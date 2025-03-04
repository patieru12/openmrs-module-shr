package org.openmrs.module.basicmodule.pojo.vlsm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class VlsmOrderRequest {

    @SerializedName("patientId")
    @Expose
    public String patientId;
    @SerializedName("formId")
    @Expose
    public Integer formId;
    @SerializedName("uniqueId")
    @Expose
    public String uniqueId;
    @SerializedName("appSampleCode")
    @Expose
    public String appSampleCode;
    @SerializedName("remoteSampleCode")
    @Expose
    public String remoteSampleCode;
    @SerializedName("sampleCodeTitle")
    @Expose
    public String sampleCodeTitle;
    @SerializedName("sampleReordered")
    @Expose
    public String sampleReordered;
    @SerializedName("sampleCodeFormat")
    @Expose
    public String sampleCodeFormat;
    @SerializedName("facilityId")
    @Expose
    public String facilityId;
    @SerializedName("provinceId")
    @Expose
    public String provinceId;
    @SerializedName("serialNo")
    @Expose
    public String serialNo;
    @SerializedName("clinicianName")
    @Expose
    public String clinicianName;
    @SerializedName("clinicanTelephone")
    @Expose
    public String clinicanTelephone;
    @SerializedName("sampleCollectionDate")
    @Expose
    public String sampleCollectionDate;
    @SerializedName("patientFirstName")
    @Expose
    public String patientFirstName;
    @SerializedName("patientMiddleName")
    @Expose
    public String patientMiddleName;
    @SerializedName("patientLastName")
    @Expose
    public String patientLastName;
    @SerializedName("patientGender")
    @Expose
    public String patientGender;
    @SerializedName("patientDob")
    @Expose
    public String patientDob;
    @SerializedName("ageInYears")
    @Expose
    public String ageInYears;
    @SerializedName("ageInMonths")
    @Expose
    public String ageInMonths;
    @SerializedName("patientPregnant")
    @Expose
    public String patientPregnant;
    @SerializedName("trimester")
    @Expose
    public String trimester;
    @SerializedName("isPatientNew")
    @Expose
    public String isPatientNew;
    @SerializedName("breastfeeding")
    @Expose
    public String breastfeeding;
    @SerializedName("patientArtNo")
    @Expose
    public String patientArtNo;
    @SerializedName("dateOfArtInitiation")
    @Expose
    public String dateOfArtInitiation;
    @SerializedName("artRegimen")
    @Expose
    public String artRegimen;
    @SerializedName("hasChangedRegimen")
    @Expose
    public String hasChangedRegimen;
    @SerializedName("reasonForArvRegimenChange")
    @Expose
    public String reasonForArvRegimenChange;
    @SerializedName("regimenInitiatedOn")
    @Expose
    public String regimenInitiatedOn;
    @SerializedName("vlTestReason")
    @Expose
    public String vlTestReason;
    @SerializedName("lastViralLoadResult")
    @Expose
    public String lastViralLoadResult;
    @SerializedName("lastViralLoadTestDate")
    @Expose
    public String lastViralLoadTestDate;
    @SerializedName("conservationTemperature")
    @Expose
    public String conservationTemperature;
    @SerializedName("durationOfConservation")
    @Expose
    public String durationOfConservation;
    @SerializedName("dateOfCompletionOfViralLoad")
    @Expose
    public String dateOfCompletionOfViralLoad;
    @SerializedName("viralLoadNo")
    @Expose
    public String viralLoadNo;
    @SerializedName("patientPhoneNumber")
    @Expose
    public String patientPhoneNumber;
    @SerializedName("receiveSms")
    @Expose
    public String receiveSms;
    @SerializedName("specimenType")
    @Expose
    public String specimenType;
    @SerializedName("arvAdherence")
    @Expose
    public String arvAdherence;
    @SerializedName("stViralTesting")
    @Expose
    public String stViralTesting;
    @SerializedName("rmTestingLastVLDate")
    @Expose
    public String rmTestingLastVLDate;
    @SerializedName("rmTestingVlValue")
    @Expose
    public String rmTestingVlValue;
    @SerializedName("repeatTestingLastVLDate")
    @Expose
    public String repeatTestingLastVLDate;
    @SerializedName("repeatTestingVlValue")
    @Expose
    public String repeatTestingVlValue;
    @SerializedName("suspendTreatmentVlValue")
    @Expose
    public String suspendTreatmentVlValue;
    @SerializedName("reqClinician")
    @Expose
    public String reqClinician;
    @SerializedName("reqClinicianPhoneNumber")
    @Expose
    public String reqClinicianPhoneNumber;
    @SerializedName("requestDate")
    @Expose
    public String requestDate;
    @SerializedName("vlFocalPerson")
    @Expose
    public String vlFocalPerson;
    @SerializedName("vlFocalPersonPhoneNumber")
    @Expose
    public String vlFocalPersonPhoneNumber;
    @SerializedName("labId")
    @Expose
    public String labId;
    @SerializedName("testingPlatform")
    @Expose
    public String testingPlatform;
    @SerializedName("sampleReceivedAtHubOn")
    @Expose
    public String sampleReceivedAtHubOn;
    @SerializedName("sampleReceivedDate")
    @Expose
    public String sampleReceivedDate;
    @SerializedName("sampleTestingDateAtLab")
    @Expose
    public String sampleTestingDateAtLab;
    @SerializedName("sampleDispatchedOn")
    @Expose
    public String sampleDispatchedOn;
    @SerializedName("resultDispatchedOn")
    @Expose
    public String resultDispatchedOn;
    @SerializedName("isSampleRejected")
    @Expose
    public String isSampleRejected;
    @SerializedName("rejectionReason")
    @Expose
    public String rejectionReason;
    @SerializedName("rejectionDate")
    @Expose
    public String rejectionDate;
    @SerializedName("vlResult")
    @Expose
    public String vlResult;
    @SerializedName("vlResultAbsoluteDecimal")
    @Expose
    public String vlResultAbsoluteDecimal;
    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("revisedBy")
    @Expose
    public String revisedBy;
    @SerializedName("revisedOn")
    @Expose
    public String revisedOn;
    @SerializedName("reasonForVlResultChanges")
    @Expose
    public String reasonForVlResultChanges;
    @SerializedName("vlLog")
    @Expose
    public String vlLog;
    @SerializedName("testedBy")
    @Expose
    public String testedBy;
    @SerializedName("reviewedBy")
    @Expose
    public String reviewedBy;
    @SerializedName("reviewedOn")
    @Expose
    public String reviewedOn;
    @SerializedName("approvedBy")
    @Expose
    public String approvedBy;
    @SerializedName("approvedOnDateTime")
    @Expose
    public String approvedOnDateTime;
    @SerializedName("labComments")
    @Expose
    public String labComments;
    @SerializedName("resultStatus")
    @Expose
    public String resultStatus;
    @SerializedName("fundingSource")
    @Expose
    public String fundingSource;
    @SerializedName("implementingPartner")
    @Expose
    public String implementingPartner;
    @SerializedName("upid")
    @Expose
    public String upid;
    @SerializedName("patientInfoTodisplay")
    @Expose
    public String patientInfoTodisplay;
    @SerializedName("participant")
    @Expose
    public String participant;

}