/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.servicelocator;
import gov.nih.nci.ncicb.cadsr.exception.NestedRuntimeException;

public class ServiceLocatorException extends NestedRuntimeException {
  
  public ServiceLocatorException(String msg) {
    super(msg);
  }

  public ServiceLocatorException(
    String msg,
    Throwable cause) {
    super(msg, cause);
  }
 
}
