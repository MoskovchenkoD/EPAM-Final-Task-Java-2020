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

public class EditCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(EditCourseServlet.class);

    CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = 0;

        try {
            courseId = Integer.parseInt("courseId");
        } catch (NullPointerException e) {
            LOG.error("Null courseId");
        }

        Course course = courseService.getCourseForEdit(courseId);
        req.setAttribute("course", course);
        getServletContext().getRequestDispatcher(WebPath.PAGE_OPERATE_COURSE).forward(req, resp);
    }


}
