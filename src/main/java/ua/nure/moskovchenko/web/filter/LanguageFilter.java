package ua.nure.moskovchenko.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Tracks each request, gets user session, retrieves language attribute and tries to match its value with
 * ones that represent available interface languages. If it doesn't match, it gets set to a default one, which is RU.
 */
public class LanguageFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LanguageFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("lang");

        if (!("en".equals(lang) || "ru".equals(lang))) {
            LOG.info("Desired language hasn't been found. Switching language to RU");
            lang = "ru";
            session.setAttribute("lang", lang);
        }

        LOG.info("Page language is : " + lang.toUpperCase());
        chain.doFilter(req, resp);
    }
}
