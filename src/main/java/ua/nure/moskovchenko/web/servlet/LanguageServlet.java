package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.WebPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LanguageServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LanguageServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> languages = Arrays.asList("en", "ru");

        String lang = req.getParameter("lang");
        String location = req.getParameter("location");
        boolean existLanguage = false;

        for (String l: languages) {
            if (lang.equals(l)) {
                existLanguage = true;
                break;
            }
        }

        if (existLanguage) {
            session.setAttribute("lang", lang);
            resp.sendRedirect(location);
        } else {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_VALUE_LANGUAGE);
            resp.sendRedirect(WebPath.PAGE_ERROR_PAGE);
        }
    }
}

