package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.service.CourseService;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
