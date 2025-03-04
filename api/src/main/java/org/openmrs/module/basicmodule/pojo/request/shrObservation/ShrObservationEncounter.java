package org.openmrs.module.basicmodule.pojo.request.shrObservation;

import lombok.Builder;
import lombok.Data;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Identifier;
import java.util.List;

@Data
@Builder
public class ShrObservationEncounter {
    private List<Identifier> identifier;
}
