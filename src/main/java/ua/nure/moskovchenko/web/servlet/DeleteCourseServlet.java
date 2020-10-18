package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OperateCourseServlet.class);

    CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect(WebPath.SERVLET_OPERATE_COURSE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String message = "";
        int courseId;

        try {
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NullPointerException | NumberFormatException e) {
            courseId = 0;
            LOG.error("Failed to parse parameter");
            message = "ID of the course, which should be deleted, isn't valid";
        }

        if (courseId > 0) {
            int success = courseService.deleteCourseById(courseId);
            if (success == 1) {
                session.setAttribute(Messages.ERR_MESSAGE,
                        Messages.MES_BEGINNING_COURSE + courseId + Messages.MES_ENDING_DELETED);

            } else {
                message = Messages.MES_BEGINNING_COURSE + courseId + Messages.MES_ENDING_NOT_FOUND;
            }
        } else {
            message = "ID of the course, which should be deleted, is incorrect";
        }

        if (!message.trim().isEmpty()) {
            session.setAttribute(Messages.ERR_MESSAGE, message);
        }
        resp.sendRedirect(WebPath.SERVLET_OPERATE_COURSE);
    }
}
