
package org.openmrs.module.basicmodule.pojo.request.shrEncounter;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Type {
    private List<Coding> coding = null;
}
