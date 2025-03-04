
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Individual {

    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private Identifier__1 identifier;
    @SerializedName("display")
    @Expose
    private String display;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Identifier__1 getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier__1 identifier) {
        this.identifier = identifier;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
