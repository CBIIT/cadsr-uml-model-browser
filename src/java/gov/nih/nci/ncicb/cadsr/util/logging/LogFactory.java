/**
 * LogFactory
 *
 * This class serves as the factory for the CaDSR Log interface to allow the 
 * application to be independent of any underlying logging package
 *
 * @release 3.0
 * @author: Jane Jiang
 * @date: 8/16/2005
 * @version: $Id: LogFactory.java,v 1.2 2009-08-13 17:08:48 davet Exp $
 */
 
package gov.nih.nci.ncicb.cadsr.util.logging;

public class LogFactory  {
   private LogFactory() {
   }
   
   public static Log getLog (java.lang.String name) {
      
      return new CaDSRLogImpl(name);
   }

}