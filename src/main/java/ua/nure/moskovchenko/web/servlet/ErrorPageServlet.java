package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.exception.Messages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ErrorPageServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ErrorPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processError(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        processError(req, resp);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();

        String error = (String) session.getAttribute(Messages.ERR_MESSAGE);
        LOG.info("Error page has been called. Error = " + error);

        if (error != null && !error.trim().isEmpty()) {
            req.setAttribute(Messages.ERR_MESSAGE, error);
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_MESSAGE_TEXT_MISSING);
        }

        // detailed error output

//        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
//        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
//        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
//        if (servletName == null) {
//            servletName = "Unknown";
//        }
//        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
//        if (requestUri == null) {
//            requestUri = "Unknown";
//        }
//        req.setAttribute("error", "Servlet " + servletName +
//                " has thrown an exception " + throwable.getClass().getName() +
//                " : " + throwable.getMessage());
        getServletContext().getRequestDispatcher(WebPath.PAGE_ERROR_PAGE).forward(req, resp);
    }
}
