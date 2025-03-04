
package org.openmrs.module.basicmodule.pojo.obspull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Component {

    @SerializedName("valueSampledData")
    @Expose
    private ValueSampledData valueSampledData;

    public ValueSampledData getValueSampledData() {
        return valueSampledData;
    }

    public void setValueSampledData(ValueSampledData valueSampledData) {
        this.valueSampledData = valueSampledData;
    }

}
