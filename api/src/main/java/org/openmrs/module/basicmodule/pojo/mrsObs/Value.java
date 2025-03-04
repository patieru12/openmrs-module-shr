
package org.openmrs.module.basicmodule.pojo.mrsObs;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Value {

    private String uuid;
    private String display;
    private Name name;
    private Datatype datatype;
    private ConceptClass conceptClass;
    private Boolean set;
    private Object version;
    private Boolean retired;
    private List<Name__1> names = null;
    private List<Description> descriptions = null;
    private List<Mapping> mappings = null;
    private List<Object> answers = null;
    private List<Object> setMembers = null;
    private List<Object> attributes = null;
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

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

}
