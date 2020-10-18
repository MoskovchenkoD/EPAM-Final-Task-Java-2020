package ua.nure.moskovchenko.bean;

import java.io.Serializable;

/**
 * CoursesForStud class is used to transfer data which is related to the "course" table of the database,
 * usually in combination with fields from other tables.
 */
public class CoursesForStud implements Serializable {

    private static final long serialVersionUID = -3857973441030043286L;

    private int id;
    private String headline;
    private String topicName;
    private String lastName;
    private int length;
    private int studentCount;
    private String statusName;
    private int userScore;


    public CoursesForStud(int id, String headline, String topicName, String lastName, int length, int studentCount, String statusName) {
        this.id = id;
        this.headline = headline;
        this.topicName = topicName;
        this.lastName = lastName;
        this.length = length;
        this.studentCount = studentCount;
        this.statusName = statusName;
    }

    public CoursesForStud(int id, String headline, String topicName, String lastName, int length, int studentCount, String statusName, int userScore) {
        this.id = id;
        this.headline = headline;
        this.topicName = topicName;
        this.lastName = lastName;
        this.length = length;
        this.studentCount = studentCount;
        this.statusName = statusName;
        this.userScore = userScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}