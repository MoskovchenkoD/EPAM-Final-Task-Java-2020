package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OperateCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OperateCourseServlet.class);

    CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.setAttribute(Messages.ERR_MESSAGE, session.getAttribute(Messages.ERR_MESSAGE));

        List<Course> courses = courseService.getAllCourses();
        req.setAttribute("courses", courses);
        getServletContext().getRequestDispatcher(WebPath.PAGE_OPERATE_COURSE).forward(req, resp);
    }
}
