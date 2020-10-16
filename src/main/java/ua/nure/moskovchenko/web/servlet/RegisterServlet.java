package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RegisterServlet is responsible for transferring the registration webpage to the client and reading the data
 * which the user sends to the server.
 */
public class RegisterServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RegisterServlet.class);

    UserService userService = new UserService();

    /**
     * Forwards the request to the registration page, so the page is sent to the client.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebPath.PAGE_REGISTRATION).forward(req, resp);
    }

    /**
     * Retrieves the data from the registration page and transfers it the service layer
     * in order to add a new user to the database.
     * After the data transfer, it redirects to doGet method of the servlet.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination = WebPath.SERVLET_LOGIN;

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String patronymic = req.getParameter("patronymic");
        String login = req.getParameter("login");
        String email =  req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("patronymic" + patronymic);
        System.out.println("login = " + login);
        System.out.println("email = " + email);
        System.out.println("password = " + password);
        System.out.println("role = " + role);

        if (role != null) {
            destination = WebPath.SERVLET_CABINET; //fix later
        }

        userService.addNewUser(firstName, lastName, patronymic, login, email, password, role);

        resp.sendRedirect(destination);
    }
}
