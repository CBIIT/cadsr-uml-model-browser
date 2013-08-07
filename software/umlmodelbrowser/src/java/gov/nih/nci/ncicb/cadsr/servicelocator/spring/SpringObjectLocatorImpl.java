/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

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