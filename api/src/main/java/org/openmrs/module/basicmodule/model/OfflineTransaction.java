package org.openmrs.module.basicmodule.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
@Entity(name = "offline_transaction")
public class OfflineTransaction implements Serializable {

    public static final long serialVersionUID = 0L;

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;
    private String uuid = null;
    private String payload = null;
    private Date timestamp = null;
    @Column(name = "retry_count")
    private int retryCount;
    @Column(name = "is_updated")
    private int isUpdated;
    @Column(name = "type")
    private String type;
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "national_id_type")
    private String nationalIdType;

    public String getNationalId() {
        return nationalId == null ? "NA": nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationalIdType() {
        return nationalIdType == null ? "NA": nationalIdType;
    }

    public void setNationalIdType(String nationalIdType) {
        this.nationalIdType = nationalIdType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(int isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
