package ua.nure.moskovchenko.service;

import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.db.State;
import ua.nure.moskovchenko.db.dao.UserDAO;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * UserService class is responsible for transferring both the input data to DAO methods which specifies
 * the information the programmer would like to get, and output data back to servlets.
 * Some methods validate the input data before moving it to a DAO method.
 */
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    UserDAO userDAO = new UserDAO();

    public List<User> getAllStudentsAndCourses() {
        List<User> list = userDAO.getAllStudentsAndCourses();
        return list;
    }

    public User getUserById(int id) {
        User user = userDAO.getUserById(id);
        return user;
    }

    /**
     * Invokes the appropriate DAO method, receives its result and transfers it back to the StudentServlet.
     * Each User objects consists of the basic info about students which would help administration to
     * analyze it and find the students which should get blocked or unblocked.
     */
    public List<User> getAllStudents() {
        List<User> list = userDAO.getAllStudents();
        return list;
    }

    /**
     * Validates the user id and gets an appropriate state which follows the one that's passed to the method.
     * Transfers the validated data to UserDAO and returns the result back to StudentServlet.
     */
    public int changeStudentState(int userId, State state) {
        int statusId = 0;

        if (userId <= 0) {
            return 0;
        }

        switch(state) {
            case FREE: {
                statusId = State.BLOCKED.getId();
                break;
            }
            case BLOCKED: {
                statusId = State.FREE.getId();
                break;
            }
            default: {
                return 0;
            }
        }
        int success = userDAO.changeUserState(userId, statusId);
        return success;
    }

    /**
     * Transfers the login value to UserDAO, gets a possible User object, compares its password
     * to the one in the input and returns either the User object or null if the passwords mismatch.
     */
    public User checkUserRegistration(String login, String password) {
        User user = null;
        if (!login.trim().isEmpty() && !password.trim().isEmpty()) {
            user = userDAO.getUserByLogin(login);
        }
        if (user != null && password.equals(user.getPassword())) { //TODO use getPasswordSalt and Hash
            try {
                String passHash = PasswordUtil.hashAndSaltPassword(password);
                LOG.debug("hash-test: " + passHash);
                LOG.debug("length = " + passHash.length());
            } catch (NoSuchAlgorithmException e) {
                LOG.debug("The password hashing algorithm can't be used");
                return null;
            }

            LOG.debug("The password of user '" + user.getLogin() + "' is correct");
            return user;
        } else if (user != null && !password.equals(user.getPassword())) { //TODO use getPasswordSalt and Hash
            LOG.debug("The password of user '" + user.getLogin() + "' is incorrect");
            return null;
        } else {
            LOG.debug("User '" + login + "' hasn't been found");
            return null;
        }
    }

    /**
     * Checks if all the necessary data is valid, transfers it to the UserDAO and returns the result if
     * the operation was successful.
     */
    public int addNewUser(String firstName, String lastName, String patronymic, String login, String email, String password, String role) {
        LOG.trace("inside AddNewUser");
        int success = 0;
        int roleId;

        if (role == null) {
            roleId = Role.STUDENT.getId();
        } else {
            roleId = Role.getByName(role).getId();
        }

        if (firstName != null && !firstName.trim().isEmpty() && firstName.length() < 45
                && lastName != null && !lastName.trim().isEmpty() && lastName.length() < 45
                && login != null && !login.trim().isEmpty() && login.length() < 45
                && email != null && !email.trim().isEmpty() && email.length() < 60
                && password != null && !password.trim().isEmpty() && password.length() < 45
                && patronymic.length() < 45) {
            success = userDAO.addNewUser(firstName, lastName, patronymic, login, email, password, roleId);
        } else {
            LOG.debug("Null fields");
        }
        LOG.trace("AddNewUser in service. success =" + success);
        return success;
    }

    public List<User> getLecturersForCourse() {
        List<User> list = userDAO.getLecturerForCourse();
        return list;
    }



}
