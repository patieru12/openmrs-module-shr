
package org.openmrs.module.basicmodule.pojo.response.shrEncounter;

import lombok.Data;

import javax.annotation.Generated;
import java.util.List;

@Data
public class Resource {
    private String resourceType;
    private String id;
    private List<Identifier> identifier = null;
    private String status;
    private List<Category> category = null;
    private Subject subject;
    private String effectiveDateTime;
    private String valueString;
    private Encounter encounter;
}
