package gov.nih.nci.ncicb.cadsr.service.impl;

import gov.nih.nci.cadsr.domain.DataElement;
import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;
import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;
import gov.nih.nci.ncicb.cadsr.CaDSRConstants;
import gov.nih.nci.ncicb.cadsr.service.CaDSRQueryService;

import gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto.ReferenceDocumentAttachment;
import gov.nih.nci.ncicb.cadsr.xml.XMLGeneratorBean;

import gov.nih.nci.system.applicationservice.ApplicationService;

import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Collection;
import java.util.List;


import java.util.Locale;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.OracleConnection;


import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import oracle.xml.sql.query.OracleXMLQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.nativejdbc.JBossNativeJdbcExtractor;

/**
 * A Oracle JDBC implementation of the CaDSRQueryService
 */
public class CaDSRQueryServiceImpl implements CaDSRQueryService{
    private DataSource dataSource;
    private Log log = LogFactory.getLog(CaDSRQueryServiceImpl.class);
    private QueryRefDocAttachments queryRefDocAttachments = null;
    private QueryRefDocAttachment queryRefDocAttachment = null;    
    private static final String encoding = "UTF-8";
    private static final String rowSetTag = "DataElementsList";
    private static final String rowTag = "DataElement";
    private static final String cdeXMLQuery =  " SELECT PublicId " +
                        ", LongName "+
                        ",  PreferredName  "+
                        ",  PreferredDefinition  "+
                        ",  Version  "+
                        ",  WorkflowStatus  "+
                        ",  ContextName  "+
                        ",  ContextVersion  "+
                        ",  Origin  "+
                        ",  RegistrationStatus  "+
                        ",  DataElementConcept  "+
                        ",  ValueDomain  " +
                        ",  ReferenceDocumentsList  " +
                        ",  ClassificationsList  " +
                        ",  AlternateNameList  " +                    
                        ",  DataElementDerivation  " +
                   " FROM sbrext.DE_XML_GENERATOR_VIEW ";
    
    public CaDSRQueryServiceImpl() {
    }
    /**
     * Queries the XML for the DataElements associated with the attributes.
     * @param attributes
     * @return CDE XML for data elements associated with the attributes
     */
    public String getXMLForAttributes(Collection<UMLAttributeMetadata> attributes) throws Exception {
        try {
            Connection conn = dataSource.getConnection();
            //Get the OracleConnection
            Connection oracleConn = conn.getMetaData().getConnection();
            if (!(oracleConn instanceof oracle.jdbc.driver.OracleConnection)){
                log.error("DataElement XML download can work with OracleConnection only.");
                throw new Exception("DataElement XML download can work with OracleConnection only.");
            }
            StringBuffer where = new StringBuffer(attributes.size()*33);
            where.append("DE_IDSEQ IN ('-1'");
            for(UMLAttributeMetadata attribute:attributes){
                DataElement de = attribute.getDataElement();
                where.append(",'"+de.getId()+"'");
            }
            where.append(")");
            XMLGeneratorBean cdeXmlGenerator = new XMLGeneratorBean();
            cdeXmlGenerator.setConnection(oracleConn);
            cdeXmlGenerator.setQuery(cdeXMLQuery);
            cdeXmlGenerator.setWhereClause(where.toString());
            cdeXmlGenerator.setRowsetTag(rowSetTag);
            cdeXmlGenerator.setRowTag(rowTag);
            cdeXmlGenerator.displayNulls(true);
            String xmlString = cdeXmlGenerator.getXMLString();
            return xmlString;        
        }
        catch (Exception e) {
            log.error("Error getting CDE Xml.",e);
            throw e;
        }
    }
    
    public Collection<ReferenceDocumentAttachment> getReferenceDocumentAttachments(String referenceDocumentIdseq) throws Exception{
        if (queryRefDocAttachments == null) {
            queryRefDocAttachments = new QueryRefDocAttachments(dataSource);
        }
        if ((referenceDocumentIdseq == null)&&(referenceDocumentIdseq.equals(""))) {
            throw new Exception("ReferenceDocumentIdseq is required to query attachments.");
        }
        try {
            Object[] parameters = new Object[1];
            parameters[0] = referenceDocumentIdseq;
            List<ReferenceDocumentAttachment> rdas = queryRefDocAttachments.execute(parameters);
            return rdas;
        }
        catch(Exception e) {
            log.error("Error quering reference document attachments for "+referenceDocumentIdseq,e);
            throw e;
        }
    }
    
    public ReferenceDocumentAttachment getReferenceDocumentAttachment(String rdaName) throws Exception {
        if (queryRefDocAttachment == null) {
            queryRefDocAttachment = new QueryRefDocAttachment(dataSource);
        }
        if ((rdaName == null)&&(rdaName.equals(""))) {
            throw new Exception("ReferenceDocumentAttachment  name is required to query attachment.");
        }
        try {
            return queryRefDocAttachment.query(rdaName);
        }
        catch(Exception e) {
            log.error("Error quering reference document attachment for "+rdaName,e);
            throw e;
        }
    }
    
    public Properties getApplicationProperties(Locale locale) throws Exception {
        ApplicationPropertiesQuery query = new ApplicationPropertiesQuery();
        try{
        query.setDataSource(getDataSource());
        query.setSql(CaDSRConstants.UMLBROWSER, locale.getCountry());
        query.execute();
        }
        catch(Exception exp)
        {
          log.error ("Error getting properties from database");
          throw exp;
        }
        return query.getProperties();
    }

    public Properties reloadApplicationProperties(Locale locale, String username)
    throws Exception
    {
      // Add code to valiad user preveleges
      return getApplicationProperties(locale);
    }

    

    /**
    * Inner class to get Properties
    */
    private class ApplicationPropertiesQuery extends MappingSqlQuery {
    private Properties properties =  new Properties();
    ApplicationPropertiesQuery() {
      super();
    }

    public Properties getProperties()
    {
     return properties;
    }
    public void setSql(String toolName,String locale) {
      super.setSql("select PROPERTY, VALUE from TOOL_OPTIONS_EXT " +
        " where tool_name = '"+toolName +"' and locale = '" +locale +"'");
    }

    protected Object mapRow(
      ResultSet rs,
      int rownum) throws SQLException {
      String key = rs.getString(1);
      String value = rs.getString(2);
      properties.put(key,value);
      return null;
    }
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
    
    /**
     * Inner class to query a ReferenceDocumentAttachment, using JDBC from
     * caDSR. This object is not available for query from caDSR API.
     */
    protected class QueryRefDocAttachments extends MappingSqlQuery {
        protected QueryRefDocAttachments() {
        }
        protected QueryRefDocAttachments(DataSource ds) {
            String query = "select name,mime_type," +
                          " content_type,blob_content, created_by,date_created, " +
                          " modified_by, date_modified "+
                           " from reference_blobs " +
                           " where rd_idseq=? ";
            setDataSource(ds);
            setSql(query);
            declareParameter(new SqlParameter("rd_idseq",Types.VARCHAR));
            compile();
        }

        protected Object mapRow(ResultSet resultSet, int i) throws SQLException 
        {
            ReferenceDocumentAttachment rda = new ReferenceDocumentAttachment();
            rda.setName(resultSet.getString("name"));
            rda.setMimeType(resultSet.getString("mime_type"));
            rda.setContentType(resultSet.getString("content_type"));
            rda.setCreatedBy(resultSet.getString("created_by"));
            rda.setDateCreated(resultSet.getDate("date_created"));
            rda.setBlobContent(resultSet.getBlob("blob_content"));
            return rda;
        }
    }
    
    protected class QueryRefDocAttachment extends QueryRefDocAttachments {
        protected QueryRefDocAttachment(DataSource ds) {
            String query = "select name,mime_type," +
                          " content_type,blob_content, created_by,date_created, " +
                          " modified_by, date_modified "+
                           " from reference_blobs " +
                           " where name=? ";
            setDataSource(ds);
            setSql(query);
            declareParameter(new SqlParameter("name",Types.VARCHAR));
            compile();
        }
        
        protected ReferenceDocumentAttachment query(String name) throws Exception {
            Object[] parameters = new Object[]{name};
            List list = execute(parameters);
            ReferenceDocumentAttachment rda = null;
            if ((list != null)&&(list.size()>0)) rda = (ReferenceDocumentAttachment)list.get(0);
            return rda;
        }
                
    }
    
    
    public static void main(String [] args){
     try{
        OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
        ocpds.setURL("jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:CBDEV");
        ocpds.setUser("cdebrowser");
        ocpds.setPassword("cdeuser");
        CaDSRQueryServiceImpl ci = new CaDSRQueryServiceImpl();
        ci.setDataSource(ocpds);
        UMLAttributeMetadata ua = new UMLAttributeMetadata();
        ua.setName("b");    
        ApplicationService as =ApplicationServiceProvider.getRemoteInstance("http://cbiodev104.nci.nih.gov:59080/cacore32/http/remoteService");
        List attributes = as.search(UMLAttributeMetadata.class, ua);
        String xml =ci.getXMLForAttributes((Collection<UMLAttributeMetadata>)attributes);
        System.out.println(xml);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
}
