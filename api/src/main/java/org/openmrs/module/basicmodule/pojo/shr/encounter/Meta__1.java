
package org.openmrs.module.basicmodule.pojo.shr.encounter;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Meta__1 {

    @SerializedName("versionId")
    @Expose
    private String versionId;
    @SerializedName("tag")
    @Expose
    private List<Tag> tag = null;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

}
