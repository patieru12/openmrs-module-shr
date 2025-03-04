package org.openmrs.module.basicmodule.pojo.encounter;

import lombok.Data;

@Data
public class EncounterResponse {

    private String formDisplay;
    private String encounterDisplay;
    private String status;
    private String upi;
    private String date;
    private String uuid;
    private String location;
    private String locationId;
}
