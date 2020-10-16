package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.State;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.StudentService;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * StudentServlet is responsible for various operations on students from admin's side,
 * such as blocking and unblocking accounts.
 */
public class StudentServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(StudentServlet.class);

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> students = userService.getAllStudents();
        req.setAttribute("students", students);

        getServletContext().getRequestDispatcher(WebPath.PAGE_STUDENT_OPS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = 0;
        State userState = State.FREE;

        try {
            studentId = Integer.parseInt(req.getParameter("id"));
            userState = State.getByName(req.getParameter("status"));
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_DATA_TRANSFER);
        }

        int success = userService.changeStudentState(studentId, userState);

        if (success == 1) {
            resp.sendRedirect(WebPath.SERVLET_STUDENT);
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_DB_BASIC_TEXT);
            getServletContext().getRequestDispatcher(WebPath.PAGE_ERROR_PAGE);
        }
    }
}
