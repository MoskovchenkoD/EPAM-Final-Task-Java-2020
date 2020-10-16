package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.CoursesForLecturer;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.CourseService;
import ua.nure.moskovchenko.service.JournalService;
import ua.nure.moskovchenko.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CabinetServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CabinetServlet.class);

    StudentService studentService = new StudentService();
    CourseService courseService = new CourseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String destination = WebPath.PAGE_CABINET;

        List<CoursesForStud> studCoursesPlusScores = null;
        List<CoursesForLecturer> lecturerCourses = null;

        User user = (User) session.getAttribute("user");

        if (user != null) {
            LOG.debug("User '" + user.getLogin() + "', with role '" + user.getRole().getName() + "' has entered the cabinet");

            switch (user.getRole()) {
                case ADMIN: {
                    break; //no op
                }
                case STUDENT: {
                    studCoursesPlusScores = studentService.getCoursesForStudentPlusScore(user.getId());
                    req.setAttribute("studCourses", studCoursesPlusScores);
                    break;
                }
                case LECTURER: {
                    lecturerCourses = courseService.getCoursesByLecturerId(user.getId());
                    req.setAttribute("lecturerCourses", lecturerCourses);
                    break;
                }
                default: {
                    req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_ROLE);
                    destination = WebPath.PAGE_ERROR_PAGE;
                    break;
                }
            }

        } else {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_ACCESS_DENIED_COMMON);
            destination = WebPath.PAGE_ERROR_PAGE;
        }
        getServletContext().getRequestDispatcher(destination).forward(req, resp);

    }
}
