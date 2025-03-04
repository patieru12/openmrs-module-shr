
package org.openmrs.module.basicmodule.pojo.shr.observation;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Interpretation {

    private List<Coding> coding = null;

    public List<Coding> getCoding() {
        return coding;
    }

    public void setCoding(List<Coding> coding) {
        this.coding = coding;
    }

}
