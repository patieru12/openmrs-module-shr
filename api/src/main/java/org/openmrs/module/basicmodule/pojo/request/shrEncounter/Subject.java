
package org.openmrs.module.basicmodule.pojo.request.shrEncounter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subject {
    private Identifier identifier;
}
