package org.openmrs.module.basicmodule.pojo.globalResponse;

import lombok.Data;

@Data
public class ResponseDto {
    private String status;
    private Object results;
    private Integer Count;
}