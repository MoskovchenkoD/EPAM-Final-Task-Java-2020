package ua.nure.moskovchenko.web.filter;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.exception.Messages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * AccessFilter is responsible giving access to various page to a user, depending on his authorization and role.
 * section in web.xml to enable.
 *
 */
public class AccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    // commands access
    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    /**
     * Tracks requests from users and passes requested URLs to accessAllowed method.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        LOG.debug("Request URL: " + httpRequest.getRequestURL());

        if (accessAllowed(request)) {
            LOG.trace("Access granted");
            chain.doFilter(request, response);
        } else {
            LOG.trace("Access denied");

            request.setAttribute(Messages.ERR_MESSAGE, "You do not have permission to access the requested resource");

            request.getRequestDispatcher(WebPath.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    /**
     * Gets a requested URL from the request and tries to find it in access maps, each of which is related to
     * a specific access level.
     *
     * @return   a boolean value that indicates if the user has access to a desired page
     */
    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        String commandName = httpRequest.getRequestURL().toString();
        if(commandName.contains("js") || commandName.contains("css") || commandName.contains("ico")
                || commandName.contains("jpg") || commandName.contains("png") || commandName.contains("font")) {
            LOG.trace("URL inside filter: " + commandName);
            return true;
        }
        LOG.trace("URL inside filter: " + commandName);

        if (outOfControl.contains(commandName)) {
            return true;
        }

        if (session == null) {
            return false;
        }

        Role userRole = null;
        User user = (User) session.getAttribute("user");
        if (user != null) {
            userRole = user.getRole();
        }

        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    /**
     * Retrieves AccessFilter initialization parameters from web.xml and places their values to different maps
     * where the key is user's role (access level) and values are requestURLs that user sends to the server.
     * @param fConfig
     * @throws ServletException
     */
    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.LECTURER, asList(fConfig.getInitParameter("lecturer")));
        accessMap.put(Role.STUDENT, asList(fConfig.getInitParameter("student")));
        LOG.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }


    /**
     * Extracts parameter values from string.
     *
     * @param str
     *            parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

    public void destroy() {
        LOG.debug("Filter destruction");
    }

}