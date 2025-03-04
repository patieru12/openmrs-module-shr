package org.openmrs.module.basicmodule.pojo.observation;

import lombok.Data;
import org.openmrs.module.basicmodule.pojo.request.shrObservation.ShrObservationEncounter;

import java.util.List;

@Data
public class ObservationResponse {

    private String status;
    private List<String> conceptList;
//    private String conceptClass;
//    private String conceptValue;
    private String patientId;
    private String display;
    private String obsDatetime;
    private ShrObservationEncounter encounter;
    private String value;

}
