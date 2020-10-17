package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Status;
import ua.nure.moskovchenko.db.Topic;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class OperateCourseAddEditMethod {

    private static final Logger LOG = Logger.getLogger(OperateCourseAddEditMethod.class);

    CourseService courseService = new CourseService();
    UserService userService = new UserService();

    void addEditCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String destination = WebPath.SERVLET_ERROR_PAGE;

        String courseId = req.getParameter("courseId");
        String headline = req.getParameter("headline");
        String description = req.getParameter("description");
        String length = req.getParameter("length");
        String topic = req.getParameter("topic");
        String userId = req.getParameter("userId");
        String status = req.getParameter("status");

        String error = courseService.checkAndUpdateCourseData(courseId, headline, description, length, topic, userId, status);

        if (error == null) {
            destination = WebPath.SERVLET_OPERATE_COURSE;
        } else {
            session.setAttribute(Messages.ERR_MESSAGE, error);
        }
        resp.sendRedirect(destination);
    }

    HttpServletRequest getCourseEditInfo(HttpServletRequest req) {
        List<Topic> topics = Arrays.asList(Topic.values());
        List<Status> statuses = Arrays.asList(Status.values());
        List<User> lecturers = userService.getLecturersForCourse();
        req.setAttribute("topics", topics);
        req.setAttribute("statuses", statuses);
        req.setAttribute("lecturers", lecturers);

        return req;
    }
}
