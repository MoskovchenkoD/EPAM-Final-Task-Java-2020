package ua.nure.moskovchenko.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * Journal class is used to transfer data which is related to the "journal" table and fields from other tables.
 */
public class Journal implements Serializable {

    private static final long serialVersionUID = 7227916995336503043L;

    private int courseId;
    private int userId;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String login;
    private Date dateJoin;
    private int userScore;

    // Used for getting info about students which gets transferred to the journal page.
    public Journal(int courseId, int userId, String lastName, String firstName, String patronymic, String login, Date dateJoin, int userScore) {
        this.courseId = courseId;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.login = login;
        this.dateJoin = dateJoin;
        this.userScore = userScore;
    }

    // Used to check if a specific student has joined a specific course
    public Journal(int courseId, int userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public Date getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(Date dateJoin) {
        this.dateJoin = dateJoin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
