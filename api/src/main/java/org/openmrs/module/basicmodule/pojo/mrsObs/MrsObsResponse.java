
package org.openmrs.module.basicmodule.pojo.mrsObs;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class MrsObsResponse {

    private String uuid;
    private String display;
    private Concept concept;
    private Person person;
    private String obsDatetime;
    private Object accessionNumber;
    private ObsGroup obsGroup;
    private Object valueCodedName;
    private Object comment;
    private Location location;
    private Object order;
    private Encounter encounter;
    private Boolean voided;
    private Object value;
    private Object valueModifier;
    private Object formFieldPath;
    private Object formFieldNamespace;
    private String status;
    private Object interpretation;
    private String resourceVersion;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getObsDatetime() {
        return obsDatetime;
    }

    public void setObsDatetime(String obsDatetime) {
        this.obsDatetime = obsDatetime;
    }

    public Object getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(Object accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public ObsGroup getObsGroup() {
        return obsGroup;
    }

    public void setObsGroup(ObsGroup obsGroup) {
        this.obsGroup = obsGroup;
    }

    public Object getValueCodedName() {
        return valueCodedName;
    }

    public void setValueCodedName(Object valueCodedName) {
        this.valueCodedName = valueCodedName;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Object getOrder() {
        return order;
    }

    public void setOrder(Object order) {
        this.order = order;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }

    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValueModifier() {
        return valueModifier;
    }

    public void setValueModifier(Object valueModifier) {
        this.valueModifier = valueModifier;
    }

    public Object getFormFieldPath() {
        return formFieldPath;
    }

    public void setFormFieldPath(Object formFieldPath) {
        this.formFieldPath = formFieldPath;
    }

    public Object getFormFieldNamespace() {
        return formFieldNamespace;
    }

    public void setFormFieldNamespace(Object formFieldNamespace) {
        this.formFieldNamespace = formFieldNamespace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(Object interpretation) {
        this.interpretation = interpretation;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }
}
