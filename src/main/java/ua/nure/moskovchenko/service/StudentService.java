package ua.nure.moskovchenko.service;

import org.apache.log4j.Logger;
import ua.nure.moskovchenko.bean.CoursesForStud;
import ua.nure.moskovchenko.bean.User;
import ua.nure.moskovchenko.db.dao.CourseDAO;

import java.util.List;

/**
 * StudentService class is responsible for transferring both the input data to DAO methods which specifies
 * the information the programmer would like to get, and output data back to servlets.
 */
public class StudentService {

    private static final Logger LOG = Logger.getLogger(StudentService.class);

    CourseDAO courseDAO = new CourseDAO();

    public List<CoursesForStud> getAllCourses () {
        List<CoursesForStud> list = courseDAO.getAllCourses();
        return list;
    }

    public List<CoursesForStud> getCoursesForStudent (int userId) {
        List<CoursesForStud> list = courseDAO.getCoursesForStudent(userId);
        return list;
    }

    public List<CoursesForStud> getCoursesForStudentPlusScore (int userId) {
        List<CoursesForStud> list = courseDAO.getCoursesForStudentPlusScore(userId);
        return list;
    }
}
