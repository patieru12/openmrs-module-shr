
package org.openmrs.module.basicmodule.pojo.obs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class AuditInfo {

    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("changedBy")
    @Expose
    private Object changedBy;
    @SerializedName("dateChanged")
    @Expose
    private Object dateChanged;

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Object changedBy) {
        this.changedBy = changedBy;
    }

    public Object getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(Object dateChanged) {
        this.dateChanged = dateChanged;
    }

}
