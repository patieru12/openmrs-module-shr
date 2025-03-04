package org.openmrs.module.basicmodule.pojo.laborder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class LapOrderReq {

    @SerializedName("SampleID")
    @Expose
    public String SampleID;
    @SerializedName("SampleBarcode")
    @Expose
    public String SampleBarcode;
    @SerializedName("Gender")
    @Expose
    public String Gender;
    @SerializedName("PatientID")
    @Expose
    public String PatientID;
    @SerializedName("PatientNames")
    @Expose
    public String PatientNames;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("TracnetID")
    @Expose
    public String TracnetID;
    @SerializedName("patientAge")
    @Expose
    public String patientAge;
    @SerializedName("birthDate")
    @Expose
    public String birthDate;
    @SerializedName("FOSAID")
    @Expose
    public String FOSAID;
    @SerializedName("CollectionDate")
    @Expose
    public String CollectionDate;
    @SerializedName("DateSampleReceived")
    @Expose
    public String DateSampleReceived;
    @SerializedName("TestDate")
    @Expose
    public String TestDate;
    @SerializedName("Laboratory")
    @Expose
    public String Laboratory;
    @SerializedName("FacilityName")
    @Expose
    public String FacilityName;
    @SerializedName("TestedBy")
    @Expose
    public String TestedBy;
    @SerializedName("ApprovedBy")
    @Expose
    public String ApprovedBy;
    @SerializedName("SpecimenType")
    @Expose
    public String SpecimenType;
    @SerializedName("TestName")
    @Expose
    public String TestName;
    @SerializedName("TestRequested")
    @Expose
    public String TestRequested;
    @SerializedName("Results")
    @Expose
    public LapOrderResults Results;

}