
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Tag {

    @SerializedName("system")
    @Expose
    private String system;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("display")
    @Expose
    private String display;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
