package ua.nure.moskovchenko.bean;

import ua.nure.moskovchenko.db.Status;

import java.io.Serializable;

/**
 * CoursesForLecturer class is used to transfer data which is related to the "course" table of the database,
 * usually in combination with fields from other tables.
 */
public class CoursesForLecturer implements Serializable {

    private static final long serialVersionUID = -2506571128307476308L;

    private int id;
    private String headline;
    private String topicName;
    private int length;
    private int studentCount;
    private Status statusName;

    public CoursesForLecturer(int id, String headline, String topicName, int length, int studentCount, String statusName) {
        this.id = id;
        this.headline = headline;
        this.topicName = topicName;
        this.length = length;
        this.studentCount = studentCount;
        this.statusName = Status.getByName(statusName);
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

    public Status getStatusName() {
        return statusName;
    }

    public void setStatusName(Status statusName) {
        this.statusName = statusName;
    }
}
