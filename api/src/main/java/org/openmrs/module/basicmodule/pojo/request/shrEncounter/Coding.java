
package org.openmrs.module.basicmodule.pojo.request.shrEncounter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coding {
    private String code;
    private String display;
}
