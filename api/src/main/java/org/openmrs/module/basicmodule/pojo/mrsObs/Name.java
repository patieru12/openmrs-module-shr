
package org.openmrs.module.basicmodule.pojo.mrsObs;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Name {

    private String display;
    private String uuid;
    private String name;
    private String locale;
    private Boolean localePreferred;
    private String conceptNameType;
    private String resourceVersion;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Boolean getLocalePreferred() {
        return localePreferred;
    }

    public void setLocalePreferred(Boolean localePreferred) {
        this.localePreferred = localePreferred;
    }

    public String getConceptNameType() {
        return conceptNameType;
    }

    public void setConceptNameType(String conceptNameType) {
        this.conceptNameType = conceptNameType;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

}
