package ua.nure.moskovchenko.service;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.Course;
import ua.nure.moskovchenko.bean.CoursesForLecturer;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.Status;
import ua.nure.moskovchenko.db.Topic;
import ua.nure.moskovchenko.db.dao.CourseDAO;

import java.util.List;

/**
 * CourseService class is responsible for transferring both the input data to DAO methods which specifies
 * the information the programmer would like to get, and output data back to servlets.
 */
public class CourseService {

    private static final Logger LOG = Logger.getLogger(CourseService.class);

    CourseDAO courseDAO = new CourseDAO();

    public List<CoursesForLecturer> getCoursesByLecturerId(int userId) {
        List<CoursesForLecturer> list = courseDAO.getCoursesByLecturerId(userId);
        return list;
    }

    public Course getCourseDetails(int courseId) {
        Course course = courseDAO.getCourseById(courseId);
        return course;
    }

    public List<Course> getAllCourses() {
        List<Course> list = courseDAO.getCourses();
        return list;
    }

    public Course getCourseForEdit(int courseId) {
        Course course = courseDAO.getCourseForEdit(courseId);
        return course;
    }

    /**
     * Tries to parse all the data to the appropriate variable types, validates it and transfers it either to
     * updateCourseInfo method (courseId is present) or addNewCourse method (courseId is missing).
     * @return  a String variable that indicates a possible issue (or empty) if an operation was successful
     */
    public String checkAndUpdateCourseData(String courseId, String headline, String description, String length, String topic, String userId, String status) {

        String error = "";
        int success = 0;

        int courseIdParsed = 0;
        int lengthParsed = 0;
        int topicIdParsed = 0;
        int userIdParsed = 0;
        int statusIdParsed = 0;

        try {
            if (courseId != null && !courseId.trim().isEmpty()) {
                LOG.debug("course != null = " + courseId);
                courseIdParsed = Integer.parseInt(courseId);
            }
            lengthParsed = Integer.parseInt(length.trim());
            userIdParsed = Integer.parseInt(userId.trim());
        } catch (NullPointerException | NumberFormatException e) {
            error = "Please check if numeric data is correct!";
        }

        if (Topic.checkByName(topic)) {
            topicIdParsed = Topic.getByName(topic).getId();
        } else {
            error = "Topic data is invalid";
        }

        if (Status.checkByName(status)) {
            statusIdParsed = Status.getByName(status).getId();
        } else {
            error = "Status data is invalid";
        }

        LOG.debug("Error checking: " + error);

        if (headline != null && !headline.trim().isEmpty() && headline.length() <= 60
                && description != null && !description.trim().isEmpty() && description.length() <= 500
                && lengthParsed > 0 && userIdParsed > 0 // && courseIdParsed > 0
                && error.equals("")) {
            if (courseIdParsed > 0) { // courseId != null && !courseId.trim().isEmpty()
                success = courseDAO.updateCourseInfo(courseIdParsed, headline, description, lengthParsed, topicIdParsed, userIdParsed, statusIdParsed);
            } else {
                success = courseDAO.addNewCourse(headline, description, lengthParsed, topicIdParsed, userIdParsed, statusIdParsed);
            }
            if (success == 0) {
                error = "Failed to make changes in database.";
            }
        } else {
            error = "Sorry, but the entered data is either incorrect or missing.";
        }

        return error;
    }

    /**
     * Checks the status condition and sets it to the one that follows it.
     * If the course status is NEW, then it gets set to STARTED.
     * If the course status is STARTED, then it gets set to FINISHED.
     * After that, it gets passed to the courseDAO, along with courseId, to indicate which course must be updated.
     * @param courseId  id of the course that indicates which one must be updated
     * @param status    the course phase that must be changed according the rules described above.
     * @return          the values that indicates whether the status update was successful
     */
    public int updateCourseStatus(int courseId, Status status) {
        int statusId = 0;
        switch(status) {
            case NEW: {
                statusId = Status.STARTED.getId();
                break;
            }
            case STARTED: {
                statusId = Status.FINISHED.getId();
                break;
            }
            default: {
                return 0;
            }
        }
        int success = courseDAO.updateCourseStatus(courseId, statusId);

        return success;
    }

    public int deleteCourseById(int courseId) {
        //int success = courseDAO.deleteCourseById(courseId);
        return courseDAO.deleteCourseById(courseId); //success;
    }

}
