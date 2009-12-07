package gov.nih.nci.ncicb.cadsr.servicelocator.spring;

import gov.nih.nci.ncicb.cadsr.servicelocator.ObjectLocator;
import gov.nih.nci.ncicb.cadsr.servicelocator.ServiceLocatorException;

import org.springframework.context.ApplicationContext;


public class SpringObjectLocatorImpl implements ObjectLocator

{
  public static ApplicationContext applicationContext = null;
  public SpringObjectLocatorImpl()
  {
  }
  
  public Object findObject(String key)
  {
     if(applicationContext==null)
      throw new ServiceLocatorException("applicationContext is null");
     return applicationContext.getBean(key);
  }
  
}