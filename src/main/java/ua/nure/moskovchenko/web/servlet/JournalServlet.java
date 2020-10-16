package ua.nure.moskovchenko.web.servlet;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.WebPath;
import ua.nure.moskovchenko.bean.Journal;
import ua.nure.moskovchenko.exception.Messages;
import ua.nure.moskovchenko.service.JournalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JournalServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(JournalServlet.class);

    JournalService journalService = new JournalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = 0;

        try {
            courseId = Integer.parseInt(req.getParameter("id"));
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_DATA_TRANSFER);
        }

        List<Journal> students = journalService.getStudentsByCourseId(courseId);
        req.setAttribute("students", students);

        getServletContext().getRequestDispatcher(WebPath.PAGE_JOURNAL).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination = WebPath.PAGE_ERROR_PAGE;
        int userId = 0;
        int courseId = 0;
        int userScore = 0;

        try {
            userId = Integer.parseInt(req.getParameter("userId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
            userScore = Integer.parseInt(req.getParameter("score"));
        } catch (NullPointerException e) {
            LOG.error("id and score haven't been successfully received", e);
            req.setAttribute(Messages.ERR_MESSAGE, Messages.ERR_INVALID_DATA_TRANSFER);
            //pass it to the Error servlet
        }

        int success = journalService.putMarkToStudentByCourseId(userId, courseId, userScore);

        if (success == 1) {
            req.setAttribute(Messages.ERR_MESSAGE, Messages.MARK_WAS_PUT_SUCCESSFULLY); //doesn't show up
            resp.sendRedirect(WebPath.SERVLET_JOURNAL + "?id=" + courseId); //fix it
        } else {
            getServletContext().getRequestDispatcher(destination).forward(req, resp); //fix it
        }

    }
}
