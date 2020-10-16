package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CoursesServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CoursesServlet.class);

    private StudentService studentService = new StudentService();

    //TODO: FOR OTHER SERVLETS
    // 2.1 For student -> courses.jsp: course table + Details button (go to courseDetails page)
    // 2.2 For lector -> courses.jsp: personal courses table + Evaluate button (go to journal page)
    // 2.3 For admin -> admin.jsp: go to admin page
    // 3 Retrieve additional data
    // 3.1 For student -> All courses where he isn't registered for
    // 3.2 For lector -> All courses where he is teaching
    // 3.3 For admin -> All courses (+ Add, Edit, Delete buttons), all students (+ Block, Unblock, Make a lector buttons)
    // 4. Forward to the page

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<CoursesForStud> coursesForUser = null;

        if (user == null) {
            coursesForUser = studentService.getAllCourses();
        } else {
            coursesForUser = studentService.getCoursesForStudent(user.getId());
        }

        if (coursesForUser != null) {
            req.setAttribute("coursesForUser", coursesForUser);
            req.getRequestDispatcher(WebPath.PAGE_COURSES).forward(req, resp);
        } else {
            LOG.error("Failed to find courses for user '" + user.getLogin() + "'");
            req.setAttribute(Messages.ERR_MESSAGE, "Failed to load courses");
            req.getRequestDispatcher(WebPath.PAGE_COURSES).forward(req, resp);
        }
    }

}
