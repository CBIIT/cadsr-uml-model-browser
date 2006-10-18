package gov.nih.nci.ncicb.cadsr.service;

import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ReferenceDocumentAttachment;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public interface CaDSRQueryService 
{
    /**
     * Queries XML for the DataElements associated with the attributes given
     * @param attributes
     * @return XML for DataElements associated with the attributes.
     * @throws Exception
     */
    public String  getXMLForAttributes(Collection<UMLAttributeMetadata> attributes) throws Exception;
    /**
     * 
     * @param referenceDocumentIdseq
     * @return Collection of ReferenceDocumentAttachment
     * @throws Exception
     */
    public Collection<ReferenceDocumentAttachment> getReferenceDocumentAttachments(String referenceDocumentIdseq) throws Exception;
    
    /**
     * 
     * @param referenceDocumentName
     * @return ReferenceDocumentAttachment
     * @throws Exception
     */
    public ReferenceDocumentAttachment getReferenceDocumentAttachment(String referenceDocumentName) throws Exception;
    
    public Properties getApplicationProperties(Locale locale) throws Exception ;

}
