
package org.openmrs.module.basicmodule.pojo.mrsEncounter;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class AuditInfo {

    private Creator creator;
    private String dateCreated;
    private Object changedBy;
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
