package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddCourseServlet is responsible for providing the page, related to a course adding/editing, and reading the data
 * that comes from the client.
 * It can be accessed only by an administrator.
 */
public class AddCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddCourseServlet.class);

    OperateCourseAddEditMethod ocaem = new OperateCourseAddEditMethod();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = ocaem.getCourseEditInfo(req);

        getServletContext().getRequestDispatcher(WebPath.PAGE_EDIT_COURSE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ocaem.addEditCourse(req, resp);

    }
}
