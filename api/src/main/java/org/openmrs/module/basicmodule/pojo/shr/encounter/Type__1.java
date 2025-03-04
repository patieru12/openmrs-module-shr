
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Type__1 {

    @SerializedName("coding")
    @Expose
    private List<Coding__1> coding = null;

    public List<Coding__1> getCoding() {
        return coding;
    }

    public void setCoding(List<Coding__1> coding) {
        this.coding = coding;
    }

}
