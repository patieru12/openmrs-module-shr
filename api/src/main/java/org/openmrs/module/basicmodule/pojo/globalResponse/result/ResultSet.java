package org.openmrs.module.basicmodule.pojo.globalResponse.result;

import lombok.*;

@Data
public class ResultSet {

    private Object observation;
    private Object medicationRequest;
    private Object serviceRequest;
}
