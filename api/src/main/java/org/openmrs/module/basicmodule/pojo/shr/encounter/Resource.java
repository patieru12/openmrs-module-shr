
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Resource {

    @SerializedName("resourceType")
    @Expose
    private String resourceType;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("text")
    @Expose
    private Text text;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("class")
    @Expose
    private java.lang.Class _class;
    @SerializedName("type")
    @Expose
    private List<Type> type = null;
    @SerializedName("subject")
    @Expose
    private Subject subject;
    @SerializedName("participant")
    @Expose
    private List<Participant> participant = null;
    @SerializedName("period")
    @Expose
    private Period period;
    @SerializedName("location")
    @Expose
    private List<Location> location = null;

}
