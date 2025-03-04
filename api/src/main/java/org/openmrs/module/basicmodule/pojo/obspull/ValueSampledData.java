
package org.openmrs.module.basicmodule.pojo.obspull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class ValueSampledData {

    @SerializedName("period")
    @Expose
    private Integer period;
    @SerializedName("lowerLimit")
    @Expose
    private Double lowerLimit;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

}
