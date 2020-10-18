package ua.nure.moskovchenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.Journal;
import ua.nure.moskovchenko.db.Column;
import ua.nure.moskovchenko.db.DBManager;
import ua.nure.moskovchenko.db.Query;
import ua.nure.moskovchenko.exception.DBException;
import ua.nure.moskovchenko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JournalDAO class contains various methods that connect to the database using DBManager singleton instance,
 * declare SQL queries in prepared statements and perform CRUD operations related to the 'journal' table.
 * get- methods usually retrieve data and return objects or lists of objects back to the service layer.
 * insert- and update- methods transfer data to the database, and delete- methods remove data from the database.
 * Non-get methods return integer values to indicate if a specific operation was successful.
 */
public class JournalDAO {

    private static final Logger LOG = Logger.getLogger(JournalDAO.class);

    /**
     * Connects to the DB and selects a new 'journal' row into the table to insure of the student
     * has already joined a specific course.
     * @param userId specifies which user should be looked for in the journal table
     * @param courseId specifies the course which may contain a specific student
     * @return an boolean value that indicates if the operation was successful
     */
    public boolean checkStudentsParticipation(int courseId, int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Journal journal = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_STUDENT_IN_JOURNAL);
            ps.setInt(1, courseId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                journal = new Journal(
                        rs.getInt(Column.JOURNAL_COURSE_ID),
                        rs.getInt(Column.JOURNAL_USER_ID)
                );
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps);
        }

        if (journal != null && journal.getCourseId() == courseId && journal.getUserId() == userId) {
            LOG.info("Student id= " + userId + " is signed up for Course id= " + courseId);
            return true;
        } else {
            LOG.info("Student id= " + userId + " is NOT signed up for Course id= " + courseId);
            return false;
        }

    }

    /**
     * Connects to the DB and inserts a new 'journal' row into the table using a prepare statement which is specified
     * by the input parameters.
     * @param userId specifies which user should be entered into the journal table
     * @param courseId specifies the course which the user wants to join
     * @return an integer value that indicates if the operation was successful
     */
    public int insertStudentIntoJournal(int courseId, int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_INSERT_STUDENT_INTO_JOURNAL);
            ps.setInt(1, courseId);
            ps.setInt(2, userId);

            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOG.error(sqlException.getMessage());
            }
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps);
        }

        if (result == 1) {
            LOG.info("Student id= " + userId + " is now signed for Course id= " + courseId);
        }
        return result;
    }

    /**
     * Connects to the DB and retrieves a list of Journal objects using a prepare statement.
     * Each Journal object contains info about students who have joined the course with the specified id.
     * @param courseId specifies which course should be looked for
     * @return a list of Journal objects that match the condition
     */
    public List<Journal> getStudentsByCourseId(int courseId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Journal> list = new ArrayList<>();
        Journal journal;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_STUDENTS_BY_JOURNAL_COURSE_ID);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();

            while (rs.next()) {
                journal = new Journal(
                        rs.getInt(Column.JOURNAL_COURSE_ID),
                        rs.getInt(Column.JOURNAL_USER_ID),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getString(Column.USER_FIRST_NAME),
                        rs.getString(Column.USER_PATRONYMIC),
                        rs.getString(Column.USER_LOGIN),
                        rs.getDate(Column.JOURNAL_DATE_JOIN),
                        rs.getInt(Column.JOURNAL_USER_SCORE)
                );
                list.add(journal);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " students joined the course #" + courseId);
        return list;
    }

    /**
     * Connects to the DB and makes a change to a specific 'journal' row using a prepare statement which is specified
     * by the input parameters.
     * @param userId specifies which journal row should be updated
     * @param courseId specifies the course which the user has joined
     * @param mark specifies which mark should be put
     * @return an integer value that indicates if the operation was successful
     */
    public int putMarkToStudentByCourseId(int userId, int courseId, int mark) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(Query.SQL_UPDATE_MARK_IN_JOURNAL);
            ps.setInt(1, mark);
            ps.setInt(2, courseId);
            ps.setInt(3, userId);

            result = ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                LOG.error(sqlException.getMessage());
            }
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps);
        }

        if (result == 1) {
            LOG.info("Student id= " + userId + " now has score of " + mark + " for Course id= " + courseId);
        } else {
            LOG.info("Student id= " + userId + " DOESN'T HAVE score of " + mark + " for Course id= " + courseId);
        }
        return result;
    }
}
