/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.exception;

public class FatalException extends NestedRuntimeException 
{

  public FatalException(String str, Exception ex)
  {
    super(str,ex);
  } 
    public FatalException(Throwable ex)
  {
    super(null,ex);
  } 
}