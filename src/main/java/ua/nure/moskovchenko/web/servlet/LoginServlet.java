package ua.nure.moskovchenko.web.servlet;

import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.State;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    /**
     * Returns the login webpage to the client.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Login page has been requested");

        req.getRequestDispatcher(WebPath.PAGE_LOGIN).forward(req, resp);
    }

    //TODO: method plan
    // 0. Receive incoming login and password from the user
    // 1. Retrieve the User object -> consider that he is registered then
    // 1.1 Find user in DB by login
    // 1.2 Check his password
    // 1.3 Return the User object
    // 2 Set the page for Dispatcher according to the User role
    // 2.1 For student & lector -> /courses
    // 2.2 For admin -> /admin
    // 2.3 For lector -> /admin
    // 3. Redirect to the appropriate servlet

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String servletDestination = WebPath.SERVLET_LOGIN;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        LOG.debug("User '" + login + "' attempts to log in");
        User user = userService.checkUserRegistration(login, password);

        if (user != null) { //&& user.getState() != State.BLOCKED
            LOG.debug("User '" + user.getLogin() + "', with role '" + user.getRole().getName() + "' has been found");
            session.setAttribute("user", user);

            switch (user.getRole()) {
                case ADMIN:
                case LECTURER: {
                    servletDestination = WebPath.SERVLET_CABINET;
                    break;
                }
                case STUDENT: {
                    servletDestination = WebPath.SERVLET_COURSES;
                    break;
                }
                default: {
                    req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_ROLE);
                    servletDestination = WebPath.PAGE_ERROR_PAGE;
                }
            }
        } else {
            req.setAttribute("login", login);
            req.setAttribute(Messages.ERR_MESSAGE, "basic_error");
        }
        resp.sendRedirect(servletDestination);

    }
}
