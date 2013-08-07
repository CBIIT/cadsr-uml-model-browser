/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto;

import java.sql.Blob;

import java.util.Date;

public class ReferenceDocumentAttachment {
    public ReferenceDocumentAttachment() {
    }
    private String name;
    private String mimeType;
    private int docSize;
    private Blob blobContent;
    private String contentType;
    private String createdBy;
    private Date dateCreated;
    private String modifiedBy;
    private Date dateModified;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }

    public int getDocSize() {
        return docSize;
    }

    public void setBlobContent(Blob blobContent) {
        this.blobContent = blobContent;
    }

    public Blob getBlobContent() {
        return blobContent;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDateModified() {
        return dateModified;
    }
}
