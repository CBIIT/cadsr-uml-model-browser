/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.servicelocator;

public interface ObjectLocator 
{
  public Object findObject(String key);
}