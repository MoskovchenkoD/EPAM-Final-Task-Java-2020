package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.CoursesForLecturer;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;
import ua.nure.moskovchenko.service.StudentService;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class WatchStudentProfileServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(WatchStudentProfileServlet.class);

    StudentService studentService = new StudentService();
    CourseService courseService = new CourseService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User student;
        User user = (User) session.getAttribute("user");

        int userID = 0;

        try {
            userID = Integer.parseInt(req.getParameter("userID"));
        } catch (NullPointerException | NumberFormatException e) {
            LOG.trace("User ID can't be parsed");
            req.setAttribute(Messages.ERR_MESSAGE, "User ID is missing");
        }

        if (user != null) {
            LOG.debug("User '" + user.getLogin() + "', with role '" + user.getRole().getName()
                    + "' has entered the student profile page");

            switch (user.getRole()) {
                case ADMIN: {
                    student = userService.getUserById(userID);

                    req.setAttribute("user", student);
                    break;
                }
                case STUDENT: {
                    break;
                }
                case LECTURER: {
                    break;
                }
                default: {
                    session.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_ROLE);
                    resp.sendRedirect(WebPath.SERVLET_ERROR_PAGE);
                    break;
                }
            }
            getServletContext().getRequestDispatcher(WebPath.PAGE_VIEW_USER_PROFILE).forward(req, resp);

        } else {
            session.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_ACCESS_DENIED_COMMON);
            resp.sendRedirect(WebPath.SERVLET_ERROR_PAGE);
        }
    }
}
