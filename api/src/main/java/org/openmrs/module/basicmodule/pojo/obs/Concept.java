
package org.openmrs.module.basicmodule.pojo.obs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Concept {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("datatype")
    @Expose
    private Datatype datatype;
    @SerializedName("conceptClass")
    @Expose
    private ConceptClass conceptClass;
    @SerializedName("set")
    @Expose
    private Boolean set;
    @SerializedName("version")
    @Expose
    private Object version;
    @SerializedName("retired")
    @Expose
    private Boolean retired;
    @SerializedName("names")
    @Expose
    private List<Name__1> names = null;
    @SerializedName("descriptions")
    @Expose
    private List<Description> descriptions = null;
    @SerializedName("mappings")
    @Expose
    private List<Mapping> mappings = null;
    @SerializedName("answers")
    @Expose
    private List<Object> answers = null;
    @SerializedName("setMembers")
    @Expose
    private List<Object> setMembers = null;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("links")
    @Expose
    private List<Link__6> links = null;
    @SerializedName("resourceVersion")
    @Expose
    private String resourceVersion;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Datatype getDatatype() {
        return datatype;
    }

    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

    public ConceptClass getConceptClass() {
        return conceptClass;
    }

    public void setConceptClass(ConceptClass conceptClass) {
        this.conceptClass = conceptClass;
    }

    public Boolean getSet() {
        return set;
    }

    public void setSet(Boolean set) {
        this.set = set;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Boolean getRetired() {
        return retired;
    }

    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    public List<Name__1> getNames() {
        return names;
    }

    public void setNames(List<Name__1> names) {
        this.names = names;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }

    public List<Object> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Object> answers) {
        this.answers = answers;
    }

    public List<Object> getSetMembers() {
        return setMembers;
    }

    public void setSetMembers(List<Object> setMembers) {
        this.setMembers = setMembers;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public List<Link__6> getLinks() {
        return links;
    }

    public void setLinks(List<Link__6> links) {
        this.links = links;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

}
