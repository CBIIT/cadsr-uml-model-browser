package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.struts.common;

public class UmlClass {
    private String packageName;
    private String subProjectName;
    private String projectName;
    private String ocName;
    private String ocVersion;
    private String ocPublicId;
    private String className;

    public UmlClass() {
    }
    public UmlClass(String clsName, String pkgName, String subprojName, String projName,
    String ocname, String ocVer, String ocId) {
    super();
    className = clsName;
    packageName = pkgName;
    subProjectName = subprojName;
    projectName = projName;
    ocName = ocname;
    ocVersion = ocVer;
    ocPublicId = ocId;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }


    public void setOcName(String ocName) {
        this.ocName = ocName;
    }

    public String getOcName() {
        return ocName;
    }

    public void setOcVersion(String ocVersion) {
        this.ocVersion = ocVersion;
    }

    public String getOcVersion() {
        return ocVersion;
    }

    public void setOcPublicId(String ocPublicId) {
        this.ocPublicId = ocPublicId;
    }

    public String getOcPublicId() {
        return ocPublicId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }
}
