package org.openmrs.module.basicmodule.pojo.laborder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class LapOrderResults {

    @SerializedName("log")
    @Expose
    public String log;
    @SerializedName("copies")
    @Expose
    public String copies;

}