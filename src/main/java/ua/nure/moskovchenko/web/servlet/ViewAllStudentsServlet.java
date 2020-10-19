package ua.nure.moskovchenko.web.servlet;

import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewAllStudentsServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> studentsCourses = userService.getAllStudentsAndCourses();

        req.setAttribute("students", studentsCourses);
        getServletContext().getRequestDispatcher(WebPath.PAGE_VIEW_STUDENTS).forward(req, resp);
    }
}
