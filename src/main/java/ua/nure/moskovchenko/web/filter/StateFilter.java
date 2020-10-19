package ua.nure.moskovchenko.web.filter;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.db.State;
import ua.nure.moskovchenko.exception.Messages;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StateFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(StateFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();

        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (user.getRole() == Role.STUDENT && user.getState() == State.BLOCKED) {
                LOG.trace("Used is blocked");

                session.removeAttribute("user");
                servletRequest.setAttribute(Messages.ERR_MESSAGE, "Unfortunately, you have been blocked by the administration");
                servletRequest.getRequestDispatcher(WebPath.PAGE_ERROR_PAGE)
                        .forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}
