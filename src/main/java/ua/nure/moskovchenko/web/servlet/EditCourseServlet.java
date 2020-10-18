package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.exception.DBException;
import ua.nure.moskovchenko.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditCourseServlet.class);

    CourseService courseService = new CourseService();
    OperateCourseAddEditMethod ocaem = new OperateCourseAddEditMethod();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int courseId = 0;

        try {
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NullPointerException e) {
            LOG.error("Null courseId");
            throw new DBException("Course ID which should be edited, is either missing or not a number");
        }

        if (courseId > 0) {
            Course course = courseService.getCourseForEdit(courseId);
            if (course != null) {
                req = ocaem.getCourseEditInfo(req);

                req.setAttribute("course", course);
                getServletContext().getRequestDispatcher(WebPath.PAGE_EDIT_COURSE).forward(req, resp);
            } else {
                throw new DBException("The course with ID = " + courseId + "hasn't been found");
            }
        } else {
            throw new DBException("ID of the course, which should be edited, isn't valid");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ocaem.addEditCourse(req, resp);

    }
}
