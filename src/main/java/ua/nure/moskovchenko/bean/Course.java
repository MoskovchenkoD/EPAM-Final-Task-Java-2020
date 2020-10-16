package ua.nure.moskovchenko.bean;

import ua.nure.moskovchenko.db.Status;
import ua.nure.moskovchenko.db.Topic;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 208277669136664276L;

    private int id;
    private String headline;
    private String description;
    private String topicName;
    private String lecturerLastName;
    private String lecturerFirstName;
    private String lecturerPatronymic;
    private int length;
    private int studentCount;
    private Status statusName;
    private int userId;
    private Topic topic;

    public Course(int id, String headline, String description, String topicName, String lecturerLastName, String lecturerFirstName, String lecturerPatronymic, int length, int studentCount, String statusName) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.topicName = topicName;
        this.lecturerLastName = lecturerLastName;
        this.lecturerFirstName = lecturerFirstName;
        this.lecturerPatronymic = lecturerPatronymic;
        this.length = length;
        this.studentCount = studentCount;
        this.statusName = Status.getByName(statusName);
    }

    public Course(int id, String headline, String description, String topicName, String lecturerLastName, int length, int studentCount, String statusName) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.topicName = topicName;
        this.lecturerLastName = lecturerLastName;
        this.length = length;
        this.studentCount = studentCount;
        this.statusName = Status.getByName(statusName);
    }

    public Course(int id, String headline, String description, int length, int topic, int userId, String statusName) {
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.length = length;
        this.topic = Topic.getById(topic);
        this.userId = userId;
        this.statusName = Status.getByName(statusName);
    }

    public Course(int id, int statusId) {
        this.id = id;
        this.statusName = Status.getById(statusId);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getLecturerLastName() {
        return lecturerLastName;
    }

    public void setLecturerLastName(String lecturerLastName) {
        this.lecturerLastName = lecturerLastName;
    }

    public String getLecturerFirstName() {
        return lecturerFirstName;
    }

    public void setLecturerFirstName(String lecturerFirstName) {
        this.lecturerFirstName = lecturerFirstName;
    }

    public String getLecturerPatronymic() {
        return lecturerPatronymic;
    }

    public void setLecturerPatronymic(String lecturerPatronymic) {
        this.lecturerPatronymic = lecturerPatronymic;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
