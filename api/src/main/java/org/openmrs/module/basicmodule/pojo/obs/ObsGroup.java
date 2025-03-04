package org.openmrs.module.basicmodule.pojo.obs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObsGroup {

    private String uuid;
    private Concept concept;
    private Person person;
    private String obsDatetime;
    private Object accessionNumber;
    private ObsGroup obsGroup;
    private Object valueCodedName;
    private Object groupMembers;
    private Object comment;
    // private Location location;
    private Object order;
    //  private ShrEncounter encounter;
    private Boolean voided;
    //private Value value;
    private Object valueModifier;
    private Object formFieldPath;
    private Object formFieldNamespace;
    private String status;
    private Object interpretation;
    //private List<Link__9> links = null;
    private String resourceVersion;
}
