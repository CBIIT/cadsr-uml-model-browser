package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.dto;

import gov.nih.nci.ncicb.cadsr.util.UMLBrowserParams;

import org.hibernate.criterion.DetachedCriteria;

public class SearchPreferences {

    private boolean excludeTestContext=true;
    private boolean excludeTrainingContext=true;
    
    public SearchPreferences() {
    }

    public void setExcludeTestContext(boolean excludeTestContext) {
        this.excludeTestContext = excludeTestContext;
    }

    public boolean isExcludeTestContext() {
        return excludeTestContext;
    }

    public void setExcludeTrainingContext(boolean excludeTrainingContext) {
        this.excludeTrainingContext = excludeTrainingContext;
    }

    public boolean isExcludeTrainingContext() {
        return excludeTrainingContext;
    }
    
    public void reset() {
       try {
         UMLBrowserParams params =UMLBrowserParams.getInstance();
         excludeTrainingContext = Boolean.parseBoolean(params.getExcludeTrainingContext());
         excludeTestContext =  Boolean.parseBoolean(params.getExcludeTestContext());
       }
       catch(Throwable t) {
           excludeTrainingContext = true;
           excludeTestContext = true;
       }
    }
    
    public boolean areDefaults() {
      try {
        UMLBrowserParams params =UMLBrowserParams.getInstance();
        if (excludeTrainingContext != Boolean.parseBoolean(params.getExcludeTrainingContext())) return false;
        if (excludeTestContext != Boolean.parseBoolean(params.getExcludeTestContext())) return false;
        return true;
      }
      catch(Throwable t) {
          return false;
      }
    }
}
