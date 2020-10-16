package ua.nure.moskovchenko.service;

import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.bean.UserLogin;
import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.db.State;
import ua.nure.moskovchenko.db.Status;
import ua.nure.moskovchenko.db.dao.UserDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);

    UserDAO userDAO = new UserDAO();

    public List<User> getAllStudents() {
        List<User> list = userDAO.getAllStudents();
        return list;
    }

    public int changeStudentState(int userId, State state) {
        int statusId = 0;
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

    public User checkUserRegistration(String login, String password) {
        User user = userDAO.getUserByLogin(login);
        if (user != null && password.equals(user.getPassword())) { //TODO use getPasswordSalt and Hash
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

    public void addNewUser(String firstName, String lastName, String patronymic, String login, String email, String password, String role) {
        int roleId;

        if (role == null) {
            roleId = Role.STUDENT.getId();
        } else {
            roleId = Role.getByName(role).getId();
        }

        if (firstName != null && !firstName.trim().isEmpty()
                && lastName != null && !lastName.trim().isEmpty()
                && login != null && !login.trim().isEmpty()
                && email != null && !email.trim().isEmpty()
                && password != null && !password.trim().isEmpty()) {
            userDAO.addNewUser(firstName, lastName, patronymic, login, email, password, roleId);
        } else {
            LOG.debug("Null fields");
            return;
        }
    }

}
