/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.basicmodule.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * SyncRecord is a collection of sync items that represents a smallest transactional unit.
 * In other words, all sync items within a record must be:
 * - transfered from/to sync source 
 * - committed/rolled back together
 * 
 * Information about sync records -- what was sent, received should be stored in DB by each
 * sync source. Minimally, each source should keep track of history of sync records that were
 * sent 'up' to parent. 
 * 
 * Consequently a sync 'transmission' is nothing more than a transport of a set of sync records from 
 * source A to source B.
 * 
 */
@Table
@Entity(name = "shr_offline")
public class ShrOffline implements Serializable {

    public static final long serialVersionUID = 0L;

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;
    private String patientId = null;
    private String upid = null;
    private String nid = null;
    private String encounterId = null;
    private String response = null;
    @Column(name="pull_timestamp")
    private Date pullTimestamp = null;
    private String type;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getPullTimestamp() {
        return pullTimestamp;
    }

    public void setPullTimestamp(Date pullTimestamp) {
        this.pullTimestamp = pullTimestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
