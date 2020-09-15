/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tuyetnta.dtos.UserDTO;

/**
 *
 * @author tuyet
 */
public class FilterRole implements Filter {

    private static Logger LOGGER = Logger.getLogger(FilterRole.class);
    private static String ERROR_PAGE = "error.jsp";
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterRole() {
    }
    private static Map<String, List<String>> roleMapping = new HashMap<>();
    private static ArrayList<String> allowJsp = new ArrayList<>();

    private boolean checkRole(UserDTO user, String action) {
        String role;
        if (user == null) {
            role = "guest";
        } else {
            role = user.getRole().getName();
        }
        return roleMapping.get(role).contains(action);
    }

    private boolean isAllowJspPage(String page) {
        return allowJsp.contains(page);
    }

    private static void initRoleMapping() {
        roleMapping.put("admin", new ArrayList());
        roleMapping.put("member", new ArrayList());
        roleMapping.put("guest", new ArrayList());

        roleMapping.get("admin").add("Search");
        roleMapping.get("admin").add("Delete");
        roleMapping.get("admin").add("Accept");
        roleMapping.get("admin").add("Detail");

        roleMapping.get("member").add("Search");
        roleMapping.get("member").add("Detail");
        roleMapping.get("member").add("Post Article");
        roleMapping.get("member").add("Post Comment");

        roleMapping.get("guest").add("Search");
        roleMapping.get("guest").add("Detail");
        roleMapping.get("guest").add("Post Comment");

        allowJsp.add("login.jsp");
        allowJsp.add("register.jsp");
        allowJsp.add("error.jsp");
        allowJsp.add("postArticle.jsp");
        allowJsp.add("loadUser.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterRole:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterRole:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
//        HttpServletRequest req = (HttpServletRequest) request;
//        try {
//            String uri = req.getRequestURI();
//            int lastIndex = uri.lastIndexOf("/");
//            String resource = uri.substring(lastIndex + 1);
//            
//            // home page
//            if (resource.isEmpty()) {
//                chain.doFilter(request, response);
//                return;
//            }
//            // check jsp page
//            if (resource.contains(".jsp")) {
//                if (isAllowJspPage(resource)) {
//                    chain.doFilter(request, response);
//                } else {
//                    request.setAttribute("ERROR", "You cannot do this action");
//                    request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//                }
//                return;
//            }
//
//            // neu k di qua MainController thi error
//            if (!resource.equals("MainController")) {
//                request.setAttribute("ERROR", "You cannot do this action");
//                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//                return;
//            }
//            // neu action null thi error
//            String action = request.getParameter("btnAction");
//            if (action == null) {
//                request.setAttribute("ERROR", "Invalid action");
//                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//                return;
//            }
//
//            if (action.equals("Logout")) {
//                chain.doFilter(request, response);
//                return;
//            }
//
//            HttpSession session = ((HttpServletRequest) request).getSession();
//            UserDTO user = (UserDTO) session.getAttribute("USER");
//
//            if (action.equals("Login") || action.equals("Register")) {
//                if (user == null) {
//                    chain.doFilter(request, response);
//                } else {
//                    request.setAttribute("ERROR", "Please Logout before do this action");
//                    request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//                    return;
//                }
//            }
//            
//            if (checkRole(user, action)) {
//                chain.doFilter(request, response);
//            } else {
//                request.setAttribute("ERROR", "You cannot do this action");
//                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//                return;
//            }
//        } catch (IOException | ServletException e) {
//            request.setAttribute("ERROR", "Something was wrong");
//            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
//            LOGGER.error(e.getMessage());
//        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        //initRoleMapping();
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterRole:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterRole()");
        }
        StringBuffer sb = new StringBuffer("FilterRole(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
