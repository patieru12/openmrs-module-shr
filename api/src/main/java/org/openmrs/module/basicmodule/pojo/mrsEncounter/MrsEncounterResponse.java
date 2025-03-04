
package org.openmrs.module.basicmodule.pojo.mrsEncounter;

import lombok.Data;

import java.util.List;
import javax.annotation.Generated;

@Data
public class MrsEncounterResponse {

    private String uuid;
    private String display;
    private String encounterDatetime;
    private Patient patient;
    private Location location;
    private Form form;
    private EncounterType encounterType;
    private List<Object> orders = null;
    private Boolean voided;
    private AuditInfo auditInfo;
    private Object visit;
    private List<EncounterProvider> encounterProviders = null;
    private List<Object> diagnoses = null;
    private String resourceVersion;

}
