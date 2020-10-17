package ua.nure.moskovchenko.db.dao;

import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.*;
import ua.nure.moskovchenko.exception.DBException;
import org.apache.log4j.Logger;
import ua.nure.moskovchenko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;
import static ua.nure.moskovchenko.db.Query.SQL_SELECT_USER_BY_LOGIN;

public class UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    public List<User> getAllStudents() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        User user;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_ALL_STUDENTS);
            ps.setInt(1, Role.STUDENT.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getString(Column.USER_FIRST_NAME),
                        rs.getString(Column.USER_PATRONYMIC),
                        rs.getString(Column.USER_LOGIN),
                        rs.getString(Column.USER_EMAIL),
                        rs.getInt(Column.USER_STATE_ID)
                );
                list.add(user);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " students have been returned");
        return list;

    }

    public List<User> getLecturerForCourse() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<>();
        User user;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(Query.SQL_SELECT_LECTURER_FOR_COURSE);
            ps.setInt(1, Role.LECTURER.getId());
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getString(Column.USER_FIRST_NAME),
                        rs.getString(Column.USER_PATRONYMIC),
                        rs.getString(Column.USER_LOGIN),
                        rs.getString(Column.USER_EMAIL)
                );
                list.add(user);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        LOG.debug(list.size() + " lecturers have been returned");
        return list;
    }

    public User getUserByLogin(String login) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt(Column.ID_PRIMARY_KEY),
                        rs.getString(Column.USER_FIRST_NAME),
                        rs.getString(Column.USER_LAST_NAME),
                        rs.getString(Column.USER_PATRONYMIC),
                        rs.getString(Column.USER_LOGIN),
                        rs.getString(Column.USER_EMAIL),
                        rs.getString(Column.USER_PASSWORD),
                        rs.getInt(Column.USER_ROLE_ID),
                        rs.getInt(Column.USER_STATE_ID)
                );
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new DBException(Messages.ERR_DB_BASIC_TEXT);
        } finally {
            DBManager.close(connection, ps, rs);
        }
        return user;
    }

    public int changeUserState(int userId, int stateId) {
        Connection connection = null;
        PreparedStatement ps = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(Query.SQL_UPDATE_USER_STATE);
            ps.setInt(1, stateId);
            ps.setInt(2, userId);

            success = ps.executeUpdate();
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

        if (success == 1) {
            LOG.info("User with id= " + userId + " has been updated");
        }

        return success;
    }

    public void addNewUser(String firstName, String lastName, String patronymic, String login, String email, String password, int roleId) {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            DatabaseMetaData db = connection.getMetaData();
            if (db.supportsTransactionIsolationLevel(TRANSACTION_REPEATABLE_READ)) {
                connection.setTransactionIsolation(TRANSACTION_REPEATABLE_READ);
            } else {
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }

            ps = connection.prepareStatement(Query.SQL_SELECT_USER_BY_LOGIN);
            ps.setString(1, login);

            ps2 = connection.prepareStatement(Query.SQL_ADD_USER);
            ps2.setString(1, firstName);
            ps2.setString(2, lastName);
            ps2.setString(3, patronymic);
            ps2.setString(4, login);
            ps2.setString(5, email);
            ps2.setString(6, password);
            ps2.setInt(7, roleId);

            rs = ps.executeQuery();

            if(!rs.next()) {
                success = ps2.executeUpdate();
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
            DBManager.close(rs);
            DBManager.close(ps2);
            DBManager.close(connection, ps);
        }

        if (success == 1) {
            LOG.info("User with login= " + login + " has been added to DB");
        } else {
            LOG.warn("User with login= " + login + " has NOT been added to DB");
        }
    }



}
