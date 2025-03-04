
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Type {

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
