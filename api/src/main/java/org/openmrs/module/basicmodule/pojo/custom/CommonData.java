package org.openmrs.module.basicmodule.pojo.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openmrs.module.basicmodule.pojo.obs.ObsResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonData {
    private String upid;
    private String value;
    private String type;
    private Object response;
}
