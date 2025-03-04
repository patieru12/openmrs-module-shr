
package org.openmrs.module.basicmodule.pojo.obspull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Code {

    @SerializedName("coding")
    @Expose
    private List<Coding> coding = null;

    public List<Coding> getCoding() {
        return coding;
    }

    public void setCoding(List<Coding> coding) {
        this.coding = coding;
    }

}
