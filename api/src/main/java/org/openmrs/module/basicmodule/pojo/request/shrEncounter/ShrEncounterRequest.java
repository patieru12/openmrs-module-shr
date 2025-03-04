
package org.openmrs.module.basicmodule.pojo.request.shrEncounter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShrEncounterRequest {
    private String resourceType;
    private String id;
    private List<Identifier> identifier = null;
    private String status;
    private List<Type> type = null;
    private Subject subject;
    private Period period;
    private List<Location> location = null;
}
