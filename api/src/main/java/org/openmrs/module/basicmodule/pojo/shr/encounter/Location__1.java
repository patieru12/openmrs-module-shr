
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Location__1 {

    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private Identifier__2 identifier;
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

    public Identifier__2 getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier__2 identifier) {
        this.identifier = identifier;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
