package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.db.Status;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseStatusServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CourseStatusServlet.class);

    CourseService courseService = new CourseService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = 0;
        Status courseStatus = Status.FINISHED;

        try {
            courseId = Integer.parseInt(req.getParameter("id"));
            courseStatus = Status.getByName(req.getParameter("status"));
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_DATA_TRANSFER);
        }

        int success = courseService.updateCourseStatus(courseId, courseStatus);

        if (success == 1) {
            resp.sendRedirect(WebPath.SERVLET_CABINET);
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_DB_BASIC_TEXT);
            getServletContext().getRequestDispatcher(WebPath.PAGE_ERROR_PAGE);
        }

    }
}
