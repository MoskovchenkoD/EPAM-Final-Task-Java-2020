package ua.nure.moskovchenko.bean;

import ua.nure.moskovchenko.db.Role;
import ua.nure.moskovchenko.db.State;

//TODO:
// 1. add salt filed
// 2. rename "password" to "hashedPassword"

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String login;
    private String email;
    private String password;
    private Role role;
    private State state;

    public User() {}

    public User(int id, String firstName, String lastName, String patronymic, String login, String email, int state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.email = email;
        this.state = State.getById(state);
    }

    public User(int id, String firstName, String lastName, String patronymic,
                String login, String email, String password, int role, int state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = Role.getById(role);
        this.state = State.getById(state);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
