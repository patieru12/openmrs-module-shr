
package org.openmrs.module.basicmodule.pojo.mrsEncounter;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Form {

    private String uuid;
    private String display;
    private String name;
    private Object description;
    private EncounterType encounterType;
    private String version;
    private Object build;
    private Boolean published;
    private List<Object> formFields = null;
    private Boolean retired;
    private List<Object> resources = null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public EncounterType getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(EncounterType encounterType) {
        this.encounterType = encounterType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getBuild() {
        return build;
    }

    public void setBuild(Object build) {
        this.build = build;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public List<Object> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<Object> formFields) {
        this.formFields = formFields;
    }

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public List<Object> getResources() {
        return resources;
    }

    public void setResources(List<Object> resources) {
        this.resources = resources;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

}
