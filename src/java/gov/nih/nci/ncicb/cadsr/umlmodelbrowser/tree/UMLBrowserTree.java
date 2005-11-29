package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree;

import gov.nih.nci.ncicb.cadsr.cdebrowser.tree.BaseTreeNode;
import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.tree.TreeConstants;
import gov.nih.nci.ncicb.cadsr.resource.Context;
import gov.nih.nci.ncicb.cadsr.resource.ContextHolder;
import gov.nih.nci.ncicb.cadsr.util.CDEBrowserParams;
import gov.nih.nci.ncicb.cadsr.util.TimeUtils;
import gov.nih.nci.ncicb.webtree.WebNode;
import gov.nih.nci.ncicb.webtree.WebTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UMLBrowserTree extends WebTree implements TreeConstants {

    protected Log log = LogFactory.getLog(UMLBrowserTree.class.getName());

    private String treeType;

    private String functionName;

    private String extraURLParameters =
        "&PageId=DataElementsGroup&NOT_FIRST_DISPLAY=1&performQuery=yes";

    private String contextExcludeListStr = null;


    public UMLBrowserTree() {
    }

    public DefaultMutableTreeNode getTree(Hashtable params) throws Exception {
        treeType = (String)params.get("treeType");

        functionName = (String)params.get("functionName");
        contextExcludeListStr =
            (String)params.get(TreeConstants.BR_CONTEXT_EXCLUDE_LIST_STR);
        return buildTree(params);
    }

    public DefaultMutableTreeNode buildTree(Hashtable treeParams) throws Exception {

        Context ctx = null;
        DefaultMutableTreeNode tree = null;

        BaseTreeNode baseNode = null;

        //TimeUtils.recordStartTime("Tree");
        try {

            log.info("Tree Start " + TimeUtils.getEasternTime());

            CDEBrowserParams params = CDEBrowserParams.getInstance();
            baseNode = new BaseTreeNode(treeParams);
            UMLBrowserTreeCache cache = UMLBrowserTreeCache.getAnInstance();
            cache.init(baseNode, treeParams);
            WebNode contexts =
                new WebNode(cache.getIdGen().getNewId(), "caDSR Contexts",
                                           "javascript:classSearchAction('')");
            tree = new DefaultMutableTreeNode(contexts);
            List allContexts = cache.getAllContextHolders();

            if (allContexts == null)
                return tree;

            ListIterator contextIt = allContexts.listIterator();

            while (contextIt.hasNext()) {
                ContextHolder currContextHolder =
                    (ContextHolder)contextIt.next();

                Context currContext = currContextHolder.getContext();
                DefaultMutableTreeNode contextNode =
                    currContextHolder.getNode();

                if (currContext.getName().equalsIgnoreCase("caCORE")) {
                    WebNode projectTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Projects");
                    DefaultMutableTreeNode projectTextNode =
                        new DefaultMutableTreeNode(projectTexts);
                    contextNode.add(projectTextNode);
                    WebNode projects =
                        new WebNode(cache.getIdGen().getNewId(), "caCORE",
                                                   "javascript:classSearchAction('')");
                    DefaultMutableTreeNode projectNode =
                        new DefaultMutableTreeNode(projects);
                    WebNode subprojectTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Sub Projects");
                    DefaultMutableTreeNode subProjectTextNode =
                        new DefaultMutableTreeNode(subprojectTexts);
                    projectNode.add(subProjectTextNode);
                    WebNode subProjects =
                        new WebNode(cache.getIdGen().getNewId(),
                                                      "Enterprise Vocabulary Services (EVS)",
                                                      "javascript:classSearchAction('')");
                    DefaultMutableTreeNode subProjNode =
                        new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);
                    WebNode packageTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Packages");
                    DefaultMutableTreeNode packageTextNode =
                        new DefaultMutableTreeNode(packageTexts);
                    subProjNode.add(packageTextNode);

                    WebNode packages =
                        new WebNode(cache.getIdGen().getNewId(), "gov.nih.nci.evs.domain",
                                                   "javascript:classSearchAction('')");
                    DefaultMutableTreeNode packageNode =
                        new DefaultMutableTreeNode(packages);
                    packageTextNode.add(packageNode);

                    //add classes under package
                    ArrayList<String> classInPkg = new ArrayList<String>();
                    classInPkg.add("AttributeSetDescriptor");
                    classInPkg.add("Definition");
                    classInPkg.add("DescLogicConcept");
                    classInPkg.add("EditActionDate");
                    classInPkg.add("MetaThesaurusConcept");
                    classInPkg.add("Property");
                    classInPkg.add("Role");
                    classInPkg.add("SemanticType");
                    classInPkg.add("Source");
                    classInPkg.add("TreeNode");
                    this.addChildNode(packageNode, cache, classInPkg);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Cancer Bioinformatics Infrastructure Objects (caBIO)",
                                              "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);
                    packageTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Packages");
                    packageTextNode = new DefaultMutableTreeNode(packageTexts);
                    subProjNode.add(packageTextNode);
                    packages =
                        new WebNode(cache.getIdGen().getNewId(), "gov.nih.nci.cabio.domain",
                                           "javascript:classSearchAction('')");
                    packageNode = new DefaultMutableTreeNode(packages);

                    //add classes under package
                    classInPkg = new ArrayList<String>();
                    classInPkg.add("Agent");
                    classInPkg.add("Anomaly");
                    classInPkg.add("Chromosome");
                    classInPkg.add("ClinicalTrialProtocol");
                    classInPkg.add("Clone");
                    classInPkg.add("CloneRelativeLocation");
                    classInPkg.add("Cytoband");
                    classInPkg.add("CytogeneticLocation");
                    classInPkg.add("DiseaseOntology");
                    classInPkg.add("DiseaseOntologyRelationship");
                    classInPkg.add("Gene");
                    classInPkg.add("GeneAlias");
                    classInPkg.add("GeneOntology");
                    classInPkg.add("GeneOntologyRelationship");
                    classInPkg.add("GeneRelativeLocation");
                    classInPkg.add("GenericArray");
                    classInPkg.add("GenericReporter");
                    classInPkg.add("Histopathology");
                    classInPkg.add("HomologousAssociation");
                    classInPkg.add("Library");
                    classInPkg.add("Location");
                    classInPkg.add("MicroArray");
                    classInPkg.add("NucleicAcidSequence");
                    classInPkg.add("OrganOntology");
                    classInPkg.add("OrganOntologyRelationship");
                    classInPkg.add("Organism");
                    classInPkg.add("Pathway");
                    classInPkg.add("PhysicalLocation");
                    classInPkg.add("PopulationFrequency");
                    classInPkg.add("Protein");
                    classInPkg.add("ProteinAlias");
                    classInPkg.add("ProteinSequence");
                    classInPkg.add("Protocol");
                    classInPkg.add("ProtocolAssociation");
                    classInPkg.add("SNP");
                    classInPkg.add("Target");
                    classInPkg.add("Taxon");
                    classInPkg.add("Tissue");
                    classInPkg.add("Vocabulary");
                    this.addChildNode(packageNode, cache, classInPkg);
                    packageTextNode.add(packageNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Common Security Module (CSM)",
                                              "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);

                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Common Module",
                                              "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Cancer Data Standards Repository (caDSR)",
                                              "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);

                    subProjectTextNode.add(subProjNode);

                    projectTextNode.add(projectNode);
                    projects =
                        new WebNode(cache.getIdGen().getNewId(), "Mage-OM",
                                           "javascript:classSearchAction('')");
                    projectNode = new DefaultMutableTreeNode(projects);
                   subprojectTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Sub Projects");
                    subProjectTextNode =
                        new DefaultMutableTreeNode(subprojectTexts);
                    projectNode.add(subProjectTextNode);
                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Base Package", "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Array Design", "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Array", "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);

                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Bio Material", "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);
                    subProjectTextNode.add(subProjNode);

                    subProjects =
                        new WebNode(cache.getIdGen().getNewId(), "Higher Level Analysis", "javascript:classSearchAction('')");
                    subProjNode = new DefaultMutableTreeNode(subProjects);

                    subProjectTextNode.add(subProjNode);
                    projectTextNode.add(projectNode);

                }
                if (currContext.getName().equalsIgnoreCase("caBig")) {
                   WebNode projectTexts =  new WebNode(cache.getIdGen().getNewId(), "Projects");
                    DefaultMutableTreeNode projectTextNode =
                    new DefaultMutableTreeNode(projectTexts);
                    contextNode.add(projectTextNode);
                    WebNode projects =
                        new WebNode(cache.getIdGen().getNewId(), "Ardais","javascript:classSearchAction('')");
                    DefaultMutableTreeNode projectNode =
                        new DefaultMutableTreeNode(projects);
                    projectTextNode.add(projectNode);
                    WebNode subprojectTexts =
                        new WebNode(cache.getIdGen().getNewId(), "Sub Projects");
                    DefaultMutableTreeNode subProjectTextNode =
                        new DefaultMutableTreeNode(subprojectTexts);
                    projectNode.add(subProjectTextNode);
                    WebNode subProjects =
                        new WebNode(cache.getIdGen().getNewId(),
                                                      "UML CDE Pilot", "javascript:classSearchAction('')");
                    DefaultMutableTreeNode subProjNode =
                        new DefaultMutableTreeNode(subProjects);

                    subProjectTextNode.add(subProjNode);
                    projects =
                        new WebNode(cache.getIdGen().getNewId(), "Genomic Identifier", "javascript:classSearchAction('')");
                    projectTextNode.add(new DefaultMutableTreeNode(projects));
                    projects = new WebNode(cache.getIdGen().getNewId(), "PIR", "javascript:classSearchAction('')");
                    projects =
                        new WebNode(cache.getIdGen().getNewId(), "RProteomics", "javascript:classSearchAction('')");
                    projectTextNode.add(new DefaultMutableTreeNode(projects));

                }

                tree.add(contextNode);
            }

            log.info("Tree End " + TimeUtils.getEasternTime());
        } catch (Exception ex) {
            ex.printStackTrace();

            throw ex;
        }

        return tree;
    }

    private void addChildNode(DefaultMutableTreeNode parentNode,
                              UMLBrowserTreeCache cache,
                              Collection<String> childNodeLabels) {

        for (Iterator<String> i = childNodeLabels.iterator(); i.hasNext(); ) {
            WebNode child = new WebNode(cache.getIdGen().getNewId(), i.next(),
            "javascript:classDetailsAction('')");
            DefaultMutableTreeNode childNode =
                new DefaultMutableTreeNode(child);
            parentNode.add(childNode);

        }
    }
}
