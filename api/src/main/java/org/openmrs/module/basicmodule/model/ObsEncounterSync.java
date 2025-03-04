package org.openmrs.module.basicmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "obs_encounter_sync")
@Data
public class ObsEncounterSync {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;
    private String patientId;
    private String uuid;
    private Integer type;
    private Date createdDate;
    private Date syncPushDate;
    private int retryCount;
    private Boolean isSynced = false;
    private String syncComment;
}
