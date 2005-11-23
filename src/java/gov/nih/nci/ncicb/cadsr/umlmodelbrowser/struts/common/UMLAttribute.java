package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common;

public class UMLAttribute {
    private String attributeName;
    private String dataType;
    private String deName;
    private String dePublicId;
    private String definition;
    private String project;
    private String subProject;
    private String packageName;

    public UMLAttribute() {
    }
    public UMLAttribute(String attName, String dType, String projectName, String subpName,
    String packageN, String attDefinition, String deN, String deId) {
    super();
        attributeName = attName;
        dataType =dType;
        project = projectName;
        subProject = subpName;
        packageName = packageN;
        packageName = packageN;
        definition = attDefinition;
        deName = deN;
        dePublicId = deId;


    }
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDeName(String deName) {
        this.deName = deName;
    }

    public String getDeName() {
        return deName;
    }

    public void setDePublicId(String dePublicId) {
        this.dePublicId = dePublicId;
    }

    public String getDePublicId() {
        return dePublicId;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getDefinition() {
        return definition;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    public void setSubProject(String subProject) {
        this.subProject = subProject;
    }

    public String getSubProject() {
        return subProject;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }
}
