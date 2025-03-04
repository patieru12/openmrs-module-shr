package org.openmrs.module.basicmodule.pojo.obspull;

import lombok.Data;

import java.util.List;

@Data
public class ObsPullResponse {
    private List<Entry> entry;
}
