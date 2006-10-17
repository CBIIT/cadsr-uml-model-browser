package gov.nih.nci.ncicb.cadsr.umlmodelbrowser.servlets;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This Filter is used to make sure there is valid session for uml model browser
 */
public class SessionFilter implements javax.servlet.Filter
{
    private FilterConfig filterConfig;
    protected static Log log = LogFactory.getLog(SessionFilter.class.getName());

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, javax.servlet.ServletException
    {
       String expiredSessionJSP = filterConfig.getInitParameter("expiredSessionJSP");
       HttpServletRequest httpservletrequest = (HttpServletRequest)request;
       HttpSession httpsession = httpservletrequest.getSession(false);
       if (httpsession == null && httpservletrequest.getRequestedSessionId() == null ) {
           //This is a client accessing the first time.
            chain.doFilter(request, response);
            return;
       }
       if (httpsession == null || httpservletrequest.getRequestedSessionId() == null )
       {
         ((HttpServletResponse)response).sendRedirect(httpservletrequest.getContextPath()+ expiredSessionJSP);
         return;
       } else
       {
          String s = httpsession.getId();
          if(s.equals(httpservletrequest.getRequestedSessionId()))
          {
             chain.doFilter(request, response);
          } else
          {
            ((HttpServletResponse)response).sendRedirect(httpservletrequest.getContextPath()+ expiredSessionJSP);
            return;
          }
      }
    }


    public void init(final FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void destroy()
    {
        filterConfig = null;
    }
}