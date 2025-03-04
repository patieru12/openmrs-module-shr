package org.openmrs.module.basicmodule.pojo.obs;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ObsResponse  implements java.io.Serializable{

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("person")
    @Expose
    private Person person;
    @SerializedName("form")
    @Expose
    private Person form;
    @SerializedName("encounterType")
    @Expose
    private Person encounterType;
    @SerializedName("obs")
    @Expose
    private List<Person> obs = new ArrayList<>();
    @SerializedName("patient")
    @Expose
    private Person patient;
    @SerializedName("obsDatetime")
    @Expose
    private Date obsDatetime;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("location")
    @Expose
    private LocationPojo location;
    @SerializedName("encounter")
    @Expose
    private Person encounter;
    @SerializedName("voided")
    @Expose
    private Boolean voided;
    @SerializedName("value")
    @Expose
    private Object value;
    @SerializedName("resourceVersion")
    @Expose
    private String resourceVersion;

}
