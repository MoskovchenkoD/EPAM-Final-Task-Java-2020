package ua.nure.moskovchenko.bean;

import ua.nure.moskovchenko.Constants;

import javax.servlet.http.HttpServletRequest;

public class UserLogin {
    private String login;
    private String password;

    public UserLogin(HttpServletRequest req) {
        login = req.getParameter(Constants.LOG_IN_USER_LOGIN).trim();
        password = req.getParameter(Constants.LOG_IN_USER_PASSWORD).trim();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
