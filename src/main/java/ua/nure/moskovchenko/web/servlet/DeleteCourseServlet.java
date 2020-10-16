package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCourseServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(OperateCourseServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(WebPath.SERVLET_OPERATE_COURSE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId;

        try {
            courseId = Integer.parseInt(req.getParameter("id"));
        } catch (NullPointerException e) {
            LOG.error("Failed to parse parameter");
        }

        //will finish by monday

    }
}
