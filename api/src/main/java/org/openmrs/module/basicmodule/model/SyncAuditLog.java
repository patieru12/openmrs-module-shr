package org.openmrs.module.basicmodule.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SyncAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;
    private Integer type;
    private String result;
    private String error;
    private String errorDetails;
    private Date createdDate;

}
