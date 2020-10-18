package ua.nure.moskovchenko.db.dao;

import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Column;
import ua.nure.moskovchenko.db.DBManager;
import ua.nure.moskovchenko.db.Query;
import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.exception.DBException;
import org.apache.log4j.Logger;
import ua.nure.moskovchenko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;
import static ua.nure.moskovchenko.db.Query.SQL_SELECT_USER_BY_LOGIN;

/**
 * UserDAO class contains various methods that connect to the database using DBManager singleton instance,
 * declare SQL queries in prepared statements and perform CRUD operations related to the 'user' table.
 * get- methods usually retrieve data and return objects or lists of objects back to the service layer.
 * insert- and update- methods transfer data to the database, and delete- methods remove data from the database.
 * Non-get methods return integer values to indicate if a specific operation was successful.
 */
public class UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    /**
     * Connects to the DB and retrieves a list of User objects using a prepare statement.
     * Each User object contains basic info about students.
     * @return a list of users that match the condition
     */
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

    /**
     * Connects to the DB and retrieves a list of User objects using a prepare statement.
     * Each User object contains basic info about lecturers.
     * @return a list of users that match the condition
     */
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

    /**
     * Connects to the DB and retrieves a User object using a prepare statement where a specific user login is
     * declared. The object contains full info about the user.
     * @param login a String values that specifies which login should be looked for
     * @return a User object found in that database whose login matches the login param
     */
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

    /**
     * Connects to the DB and makes a change to a specific 'user' row using a prepare statement which is specified
     * by the input parameters.
     * @param userId specifies which user should be updated
     * @param stateId specifies which state should be set to the user
     * @return an integer value that indicates if the update was successful
     */
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

    /**
     * Connects to the DB, insures that there is no user in the database which could be found using the passed data
     * and inserts a new 'user' row using a prepare statement which is specified by the method input.
     * @param firstName
     * @param lastName
     * @param patronymic can be empty
     * @param login must be unique
     * @param email
     * @param password
     * @param roleId
     * @return an integer value that indicates if the operation was successful
     */
    public int addNewUser(String firstName, String lastName, String patronymic, String login, String email, String password, int roleId) {
        Connection connection = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int success = 0;

        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (connection.getMetaData().supportsTransactionIsolationLevel(TRANSACTION_REPEATABLE_READ)) {
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
        return success;
    }


}
