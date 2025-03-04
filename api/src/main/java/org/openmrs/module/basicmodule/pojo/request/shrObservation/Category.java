package org.openmrs.module.basicmodule.pojo.request.shrObservation;

import lombok.Builder;
import lombok.Data;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Coding;

import java.util.List;

@Data
@Builder
public class Category {
    private List<Coding> coding;
}
