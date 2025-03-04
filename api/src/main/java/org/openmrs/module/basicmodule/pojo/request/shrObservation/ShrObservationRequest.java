package org.openmrs.module.basicmodule.pojo.request.shrObservation;

import lombok.Builder;
import lombok.Data;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Identifier;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.ShrEncounterRequest;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Subject;

import java.util.List;

@Data
@Builder
public class ShrObservationRequest {
    private String resourceType;
    private String id;
    private List<Identifier> identifier;
    private String status;
    private Subject subject;
    private ShrObservationEncounter encounter;
    private List<Category> category = null;
    private String effectiveDateTime;
    private String valueString;
}