
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Identifier {

    @SerializedName("type")
    @Expose
    private Type__1 type;
    @SerializedName("value")
    @Expose
    private String value;

    public Type__1 getType() {
        return type;
    }

    public void setType(Type__1 type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
