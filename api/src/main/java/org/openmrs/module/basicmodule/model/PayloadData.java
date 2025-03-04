package org.openmrs.module.basicmodule.model;

public class PayloadData {

    public String url;
    public String method;
    public String payload;
    public String type;
    public Integer localPatientId;


    public PayloadData() {
    }

    public PayloadData(String url, String method, String payload, String type, Integer localPatientId) {
        this.url = url;
        this.method = method;
        this.payload = payload;
        this.type = type;
        this.localPatientId = localPatientId;
    }
}