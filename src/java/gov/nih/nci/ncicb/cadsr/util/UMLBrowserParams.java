package gov.nih.nci.ncicb.cadsr.util;

import gov.nih.nci.ncicb.cadsr.servicelocator.ApplicationServiceLocator;
import gov.nih.nci.ncicb.cadsr.servicelocator.spring.ApplicationServiceLocatorImpl;
import gov.nih.nci.ncicb.cadsr.util.logging.Log;
import gov.nih.nci.ncicb.cadsr.util.logging.LogFactory;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.ResourceBundle;

public class UMLBrowserParams
{
    private static Log log = LogFactory.getLog(UMLBrowserParams.class.getName());
      //This is used monitor application mode
    public static String mode ="";
    //TODO has to be moved to read from Spring Application Context
    private static ApplicationServiceLocator appServiceLocator= new ApplicationServiceLocatorImpl();
    //String sbrextDSN = "";
    //String sbrDSN = "";
    String excludeTestContext = "no";
    String excludeTrainingContext="no";
    String testContext="Test";
    String trainingContext="Training";

    String curationToolUrl = "";
    String formbuilderToolUrl = "";
    String nciMetathesaurusUrl="";
    String nciTerminologyServerUrl="";
    String sentinelToolUrl="";
    String adminToolUrl="";
    String cadsrURL="";
    String cdebrowserURL="";
    int itemPerPage=100;
    String cdebrowserToolURL="";
    
    Map evsUrlMap = new HashMap();

    static UMLBrowserParams instance;

   /**
   *  Read the resource bundle file
   *  propFilename - the specified resource file (fn.properties) without the extension
   *  (e.g., medsurv)
   */
   private UMLBrowserParams()
   {
   }


    public static UMLBrowserParams getInstance(){
        if (instance == null ) {
        try {
            Properties properties = appServiceLocator.findCaDSRQueryService().getApplicationProperties(Locale.US);
            instance = new UMLBrowserParams();
            instance.initAttributesFromProperties(properties);
            log.debug("Using database for properties");
        } catch (Exception exp) {
            log.error("error retrieving properties from database, use property file instead");
              if (instance == null ) {
                try
                {
                  getDebugInstance();
                  log.debug("Using debug properties file");
                  mode="DEBUG MODE";
                  return instance;
                }
                catch (NoSuchElementException nse)
                {
                  log.error("Cannot find property"+nse);
                  throw nse;
                }
              }
        }
        }
      return instance;
    }

    public static UMLBrowserParams getDebugInstance(){
      if (instance == null ) {
          ResourceBundle b = ResourceBundle.getBundle("umlbrowser", java.util.Locale.getDefault());
          Properties properties = new Properties();

            for (Enumeration e = b.getKeys() ; e.hasMoreElements() ;) {
                 String key = (String)e.nextElement();
                if(key!=null)
                {
                log.debug(" Get CDEBrowser.property = "+ key );
                properties.setProperty(key,b.getString(key));
                }
            }


        instance = new UMLBrowserParams();
        instance.initAttributesFromProperties(properties);
      }

      return instance;
    }

  public void setExcludeTestContext(String excludeTestContext)
  {
    this.excludeTestContext = excludeTestContext;
  }


  public String getExcludeTestContext()
  {
    return excludeTestContext;
  }

  public void setExcludeTrainingContext(String excludeTrainingContext)
  {
    this.excludeTrainingContext = excludeTrainingContext;
  }


  public String getExcludeTrainingContext()
  {
    return excludeTrainingContext;
  }

  public void setCurationToolUrl(String curationToolUrl)
  {
    this.curationToolUrl = curationToolUrl;
  }


  public String getCurationToolUrl()
  {
    return curationToolUrl;
  }
  
  public void setFormBuilderToolUrl(String formbuilderToolUrl)
  {
	  this.formbuilderToolUrl = formbuilderToolUrl;
  }
  
  public String getFormBuilderToolUrl()
  {
	  return formbuilderToolUrl;
  }
  

  public void setNciMetathesaurusUrl(String nciMetathesaurusUrl)
  {
    this.nciMetathesaurusUrl = nciMetathesaurusUrl;
  }


  public String getNciMetathesaurusUrl()
  {
    return nciMetathesaurusUrl;
  }


  public void setNciTerminologyServerUrl(String nciTerminologyServerUrl)
  {
    this.nciTerminologyServerUrl = nciTerminologyServerUrl;
  }


  public String getNciTerminologyServerUrl()
  {
    return nciTerminologyServerUrl;
  }


  public void setSentinelToolUrl(String sentinelToolUrl)
  {
    this.sentinelToolUrl = sentinelToolUrl;
  }


  public String getSentinelToolUrl()
  {
    return sentinelToolUrl;
  }


  public void setAdminToolUrl(String adminToolUrl)
  {
    this.adminToolUrl = adminToolUrl;
  }


  public String getAdminToolUrl()
  {
    return adminToolUrl;
  }

  private void initAttributesFromProperties(Properties properties)
  {
        // read the init parameters from the resource bundle
        int index = 0;
        String propertyValue;
        try
        {

            propertyValue = properties.getProperty("EXCLUDE_TEST_CONTEXT_BY_DEFAULT");
            if (propertyValue != null)
                excludeTestContext = propertyValue;
            index++;
            propertyValue = properties.getProperty("EXCLUDE_TRAINING_CONTEXT_BY_DEFAULT");
            if (propertyValue != null)
                excludeTrainingContext = propertyValue;
            index++;
            propertyValue = properties.getProperty("TEST_CONTEXT");
            if (propertyValue != null)
                testContext = propertyValue;
            index++;
            propertyValue =properties.getProperty("TRAINING_CONTEXT");
            if (propertyValue != null)
                trainingContext = propertyValue;
            index++;
            //Temporary Page for Admin Tool
            adminToolUrl = properties.getProperty("ADMIN_TOOL_URL");
            index++;
            curationToolUrl = properties.getProperty("CURATION_TOOL_URL");
            index++;
            nciMetathesaurusUrl = properties.getProperty("NCI_METATHESAURUS_URL");
            index++;
            nciTerminologyServerUrl = properties.getProperty("NCI_TERMINOLOGY_SERVER_URL");
            index++;
            sentinelToolUrl = properties.getProperty("SENTINEL_TOOL_URL");
            index++;
            formbuilderToolUrl = properties.getProperty("FORMBUILDER_TOOL_URL");
            index++;
            propertyValue = properties.getProperty("CACORE_URL");
            if (propertyValue != null)
                cadsrURL = propertyValue;
            index++;
            cdebrowserURL = properties.getProperty("CDEBROWSER_URL");
            index++;
            cdebrowserToolURL = properties.getProperty("CDEBROWSER_TOOL_URL");
            index++;
            String itemPerPageStr =properties.getProperty("ITEM_PER_PAGE");
            if (itemPerPageStr !=null)
                itemPerPage = Integer.parseInt(itemPerPageStr);
         
           log.info("Loaded Properties"+properties);

        }
        catch (java.util.MissingResourceException mre)
        {
            log.error("Error getting init parameters, missing resource values");
            log.error("Property missing index: " + index);
            log.error(mre.getMessage(), mre);
        }
        catch (Exception e)
        {
            log.error("Exception occurred", e);
        }
  }
  
    public static void reloadInstance(){
       instance = null;
       getInstance();
    }


    public String getCadsrURL() {
        return cadsrURL;
    }

   public String getCdebrowserURL() {
      return cdebrowserURL;
   }

    public void setItemPerPage(int itemPerPage)
    {
        this.itemPerPage = itemPerPage;
    }

    public int getItemPerPage()
    {
        return itemPerPage;
    }

    public void setCdebrowserToolURL(String cdebrowserToolURL)
    {
        this.cdebrowserToolURL = cdebrowserToolURL;
    }

    public String getCdebrowserToolURL()
    {
        return cdebrowserToolURL;
    }

    public void setTraingContext(String newTraingContext) {
        this.trainingContext = newTraingContext;
    }

    public String getTrainingContext() {
        return trainingContext;
    }

    public void setTestContext(String testContext) {
        this.testContext = testContext;
    }

    public String getTestContext() {
        return testContext;
    }
}
