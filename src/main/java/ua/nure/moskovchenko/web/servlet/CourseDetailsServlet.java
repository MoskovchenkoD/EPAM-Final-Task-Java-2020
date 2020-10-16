package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.exception.AppException;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;
import ua.nure.moskovchenko.service.JournalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CourseDetailsServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CourseDetailsServlet.class);
    CourseService courseService = new CourseService();
    JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String destination = WebPath.PAGE_ERROR_PAGE;
        int courseId = 0;

        User user = (User) session.getAttribute("user");

        try {
            courseId = Integer.parseInt(req.getParameter("id"));
        } catch (NullPointerException e) {
            LOG.error("Error when parsing an attribute", e);
        }


        LOG.debug("Trying to load the course #" + courseId);
        Course course = courseService.getCourseDetails(courseId);

        if (course != null) {
            req.setAttribute("course", course);
            if (user != null) {
                boolean isUserApplied = journalService.checkIfUserApplied(courseId, user.getId());

                if (isUserApplied) {
                    req.setAttribute("isUserApplied", isUserApplied);
                }
            }
            destination = WebPath.PAGE_COURSE_DETAILS;
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, "Course with id " + courseId + " hasn't been found");
        }
        getServletContext().getRequestDispatcher(destination).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //String servletDestination = WebPath.PAGE_ERROR_PAGE;

        User user = (User) session.getAttribute("user");
        int courseIdToJoin = Integer.parseInt(req.getParameter("courseIdToJoin"));
        LOG.debug("Trying to apply the student ID= " + user.getId() + " for the course #" + courseIdToJoin);

        int success = journalService.applyForCourse(courseIdToJoin, user.getId()); //fix to SELECT + INSERT transaction

        if (success == 1) {
            //session.setAttribute("id", courseIdToJoin);
            resp.sendRedirect(WebPath.SERVLET_COURSE_DETAILS + "?id=" + courseIdToJoin); // fix it
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, "Uh-oh! Looks like we got a error with signing you up for the course #" + courseIdToJoin);
            //resp.sendRedirect(WebPath.PAGE_ERROR_PAGE); //fix it
        }

    }
}
