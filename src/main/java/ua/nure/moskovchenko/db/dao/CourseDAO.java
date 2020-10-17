package ua.nure.moskovchenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.bean.CoursesForLecturer;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.db.*;
import ua.nure.moskovchenko.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ua.nure.moskovchenko.exception.Messages;

import static java.sql.Connection.*;

public class CourseDAO {

    private static final Logger LOG = Logger.getLogger(CourseDAO.class);

    public List<CoursesForStud> getAllCourses() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CoursesForStud> list = new ArrayList<>();
        CoursesForStud coursesForStud;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_ALL_COURSES);
            rs = ps.executeQuery();

            while (rs.next()) {
                coursesForStud = new CoursesForStud(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME)
                );
                list.add(coursesForStud);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " courses has been returned");
        return list;
    }

    public List<Course> getCourses() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        Course course;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_COURSES);
            rs = ps.executeQuery();

            while (rs.next()) {
                course = new Course(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.COURSE_DESCRIPTION),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME)
                );
                list.add(course);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " courses has been returned");
        return list;
    }

    public List<CoursesForStud> getCoursesForStudent(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CoursesForStud> list = new ArrayList<>();
        CoursesForStud coursesForStud;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_COURSES_FOR_STUDENT);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                coursesForStud = new CoursesForStud(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME)
                );
                list.add(coursesForStud);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " courses for student #" + userId + " has been returned");
        return list;
    }

    public List<CoursesForLecturer> getCoursesByLecturerId(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CoursesForLecturer> list = new ArrayList<>();
        CoursesForLecturer coursesForLecturer;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_COURSES_FOR_LECTURER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                coursesForLecturer = new CoursesForLecturer(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME)
                );
                list.add(coursesForLecturer);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " courses for lecturer #" + userId + " has been returned");
        return list;
    }

    public List<CoursesForStud> getCoursesForStudentPlusScore(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CoursesForStud> list = new ArrayList<>();
        CoursesForStud coursesForStud;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_JOINT_COURSES_FOR_STUDENT);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                coursesForStud = new CoursesForStud(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME),
                        rs.getInt(Column.JOURNAL_USER_SCORE)
                );
                list.add(coursesForStud);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " courses for student #" + userId + " has been returned");
        return list;
    }

    public Course getCourseById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_COURSE_DETAILS);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                course = new Course(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.COURSE_DESCRIPTION),
                        rs.getString(Column.TOPIC_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getString(Column.USER_FIRST_NAME),
                        rs.getString(Column.USER_PATRONYMIC),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.JOURNAL_STUDENTS_AMOUNT),
                        rs.getString(Column.STATUS_NAME)
                );
            }
            LOG.info("Course with id= " + course.getId() + " has been retrieved from a DB");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }

        return course;
    }

    public Course getCourseForEdit(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_COURSE_DB);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                course = new Course(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.COURSE_HEADLINE),
                        rs.getString(Column.COURSE_DESCRIPTION),
                        rs.getInt(Column.COURSE_LENGTH),
                        rs.getInt(Column.COURSE_TOPIC_ID),
                        rs.getInt(Column.COURSE_USER_ID),
                        rs.getString(Column.STATUS_NAME)
                );
            }
            LOG.info("Course with id= " + course.getId() + " has been retrieved from a DB");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }

        return course;
    }

    /**
     *
     * @param courseId  id of the course that indicates which one must be updated,
     *                  it gets placed in the placeholder 1
     * @param statusId  the course phase that must be placed in the placeholder 2
     * @return          the values that indicates whether the status update was successful
     */
    public int updateCourseStatus(int courseId, int statusId) {

        Connection connection = null;
        PreparedStatement ps = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(Query.SQL_UPDATE_COURSE_STATUS);
            ps.setInt(1, statusId);
            ps.setInt(2, courseId);

//            if (connection.getMetaData().supportsTransactionIsolationLevel(TRANSACTION_REPEATABLE_READ)) {
//                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
//            } else {
//                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//            }

            success = ps.executeUpdate();
            connection.commit();

            LOG.info("Course with id= " + courseId + " has been updated");
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

        if (success == 1) {
            LOG.info("The Status of the course id= " + courseId + " is now set to " + Status.getById(statusId));
        }

        return success;
    }

    public int updateCourseInfo(int id, String headline, String description, int length, int topicId, int userId, int statusId) {
        Connection connection = null;
        PreparedStatement psSelectCourse = null;
        PreparedStatement psSelectLecturer = null;
        PreparedStatement psUpdateCourse = null;
        ResultSet rsSelectCourse = null;
        ResultSet rsSelectLecturer = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (connection.getMetaData().supportsTransactionIsolationLevel(TRANSACTION_REPEATABLE_READ)) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
            } else {
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }

            psSelectCourse = connection.prepareStatement(Query.SQL_SELECT_COURSE);
            psSelectCourse.setInt(1, id);

            psSelectLecturer = connection.prepareStatement(Query.SQL_SELECT_LECTURER_BY_ID);
            psSelectLecturer.setInt(1, userId);
            psSelectLecturer.setInt(2, Role.LECTURER.getId());

            psUpdateCourse = connection.prepareStatement(Query.SQL_UPDATE_COURSE);
            psUpdateCourse.setString(1, headline);
            psUpdateCourse.setString(2, description);
            psUpdateCourse.setInt(3, length);
            psUpdateCourse.setInt(4, topicId);
            psUpdateCourse.setInt(5, userId);
            psUpdateCourse.setInt(6, statusId);
            psUpdateCourse.setInt(7, id);

            rsSelectCourse = psSelectCourse.executeQuery();
            rsSelectLecturer = psSelectLecturer.executeQuery();

            if (rsSelectCourse.next() && rsSelectLecturer.next()) {
                LOG.info("Found the course and the lecturer");
                success = psUpdateCourse.executeUpdate();
            }

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
            DBManager.close(rsSelectCourse);
            DBManager.close(rsSelectLecturer);
            DBManager.close(psSelectCourse);
            DBManager.close(psSelectLecturer);
            DBManager.close(connection, psUpdateCourse);
        }

        if (success == 1) {
            LOG.info("Course with id= " + id + " has been updated");
        } else {
            LOG.warn("Course with id= " + id + " has NOT been updated");
        }

        return success;
    }

    public int addNewCourse(String headline, String description, int length, int topicId, int userId, int statusId) {
        Connection connection = null;
        PreparedStatement psSelectLecturer = null;
        PreparedStatement psInsertCourse = null;
        ResultSet rsSelectLecturer = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (connection.getMetaData().supportsTransactionIsolationLevel(TRANSACTION_REPEATABLE_READ)) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
            } else {
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }

            psSelectLecturer = connection.prepareStatement(Query.SQL_SELECT_LECTURER_BY_ID);
            psSelectLecturer.setInt(1, userId);
            psSelectLecturer.setInt(2, Role.LECTURER.getId());

            psInsertCourse = connection.prepareStatement(Query.SQL_ADD_COURSE);
            psInsertCourse.setString(1, headline);
            psInsertCourse.setString(2, description);
            psInsertCourse.setInt(3, length);
            psInsertCourse.setInt(4, topicId);
            psInsertCourse.setInt(5, userId);
            psInsertCourse.setInt(6, statusId);

            rsSelectLecturer = psSelectLecturer.executeQuery();
            System.out.println("selectLecturer");

            if (rsSelectLecturer.next()) {
                success = psInsertCourse.executeUpdate();
                System.out.println("insertCourse");
            }
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
            DBManager.close(rsSelectLecturer);
            DBManager.close(psSelectLecturer);
            DBManager.close(connection, psInsertCourse);
        }

        if (success == 1) {
            LOG.info("A new course has been created");
        } else {
            LOG.info("A new course has NOT been created");
        }

        return success;
    }

}
