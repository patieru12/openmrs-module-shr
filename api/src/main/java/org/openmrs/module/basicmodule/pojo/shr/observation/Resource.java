
package org.openmrs.module.basicmodule.pojo.shr.observation;

import lombok.Data;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Identifier;
import org.openmrs.module.basicmodule.pojo.request.shrEncounter.Subject;

import java.util.List;

@Data
public class Resource {
    private String resourceType;
    private String id;
    private Meta__1 meta;
    private List<Identifier> identifier = null;
    private String status;
    private List<Category> category = null;
    private Subject subject;
    private Encounter encounter;
    private String effectiveDateTime;
    private String valueString;
}
