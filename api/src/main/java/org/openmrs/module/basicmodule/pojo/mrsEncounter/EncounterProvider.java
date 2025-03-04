
package org.openmrs.module.basicmodule.pojo.mrsEncounter;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class EncounterProvider {

    private String uuid;
    private Provider provider;
    private EncounterRole encounterRole;
    private Boolean voided;
    private String resourceVersion;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public EncounterRole getEncounterRole() {
        return encounterRole;
    }

    public void setEncounterRole(EncounterRole encounterRole) {
        this.encounterRole = encounterRole;
    }

    public Boolean getVoided() {
        return voided;
    }

    public void setVoided(Boolean voided) {
        this.voided = voided;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

}
