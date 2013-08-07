/*L
 * Copyright Oracle Inc, SAIC-F
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/cadsr-uml-model-browser/LICENSE.txt for details.
 */

package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.servlets;

import gov.nih.nci.ncicb.cadsr.util.UMLBrowserParams;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SessionListener implements HttpSessionListener{

    protected static Log log = LogFactory.getLog(SessionListener.class.getName());

    public void sessionCreated(HttpSessionEvent se) {
        if (log.isDebugEnabled()){
            log.debug("New UML Browser session " + se.getSession().getId() + " is created");
        }
        UMLBrowserParams.reloadInstance();
        return;
    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }




}
