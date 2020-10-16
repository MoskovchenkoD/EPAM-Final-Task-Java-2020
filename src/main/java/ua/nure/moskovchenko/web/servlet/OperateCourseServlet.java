package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OperateCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OperateCourseServlet.class);

    CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Course> courses = courseService.getAllCourses();
        req.setAttribute("courses", courses);
        getServletContext().getRequestDispatcher(WebPath.PAGE_OPERATE_COURSE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SERVLET_OPERATE_COURSE
        //will be done by monday
    }
}
