package ua.nure.moskovchenko.web.servlet;

import ua.nure.moskovchenko.WebPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The LogoutServlet removes the user entity from the session which basically ends his access to the website.
 * An non-authorized user has access to guest pages only, such as courses page.
 * Language setting stays actual.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect(WebPath.SERVLET_COURSES);
    }
}
