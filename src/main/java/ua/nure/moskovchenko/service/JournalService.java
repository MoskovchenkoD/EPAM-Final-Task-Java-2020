package ua.nure.moskovchenko.service;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.Journal;
import ua.nure.moskovchenko.db.dao.JournalDAO;

import java.util.List;

public class JournalService {

    private static final Logger LOG = Logger.getLogger(JournalService.class);

    JournalDAO journalDAO = new JournalDAO();

    public boolean checkIfUserApplied(int courseId, int userId) {
        boolean isUserApplied = journalDAO.checkStudentsParticipation(courseId, userId);
        return isUserApplied;
    }

    public int applyForCourse(int courseIdToJoin, int userId) {
        int success = journalDAO.insertStudentIntoJournal(courseIdToJoin, userId);
        return success;
    }

    public List<Journal> getStudentsByCourseId(int courseId) {
        List<Journal> list = journalDAO.getStudentsByCourseId(courseId);
        return list;
    }

    public int putMarkToStudentByCourseId(int studentId, int courseId, int mark) {
        int success = journalDAO.putMarkToStudentByCourseId(studentId, courseId, mark);
        return success;
    }

}
