
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Text {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("div")
    @Expose
    private String div;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

}
