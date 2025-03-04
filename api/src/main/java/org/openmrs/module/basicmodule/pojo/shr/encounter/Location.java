
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Location {

    @SerializedName("location")
    @Expose
    private Location__1 location;

    public Location__1 getLocation() {
        return location;
    }

    public void setLocation(Location__1 location) {
        this.location = location;
    }

}
