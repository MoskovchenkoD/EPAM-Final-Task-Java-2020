package ua.nure.moskovchenko.db;

public final class Query {

    //Registration process
    public static final String SQL_ADD_USER =
            "INSERT into user (firstName, lastName, patronymic, login, email, password, role_id) " +
            "values (?, ?, ?, ?, ?, ?, ?)";

    // TODO: JSP = courseDetails, Servlet = CourseDetailsServlet
    public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";

    // TODO: таблица курсов для студента
    public static final String SQL_SELECT_ALL_COURSES =
            "SELECT c.id, c.headline, t.topicName, u.lastName, c.length, COUNT(journal.course_id) AS students_amount, s.statusName " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id AND s.statusName <> 'finished' " +
            "group by c.id";

    public static final String SQL_SELECT_COURSES_FOR_STUDENT =
            "SELECT c.id, c.headline, t.topicName, u.lastName, c.length, COUNT(journal.course_id) AS students_amount, s.statusName " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id AND s.statusName <> 'finished' " +
            "AND c.id NOT IN (select j.course_id FROM journal AS j WHERE j.user_id = ?)" +
            "group by c.id";

    // TODO: JSP = courseDetails, Servlet = CourseDetailsServlet
    public static final String SQL_SELECT_COURSE_DETAILS =
            "SELECT c.id, c.headline, c.description, t.topicName, u.lastName, u.firstName, u.patronymic, " +
            "c.length, COUNT(journal.course_id) AS students_amount, s.statusName " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id AND c.id = ? " +
            "group by c.id";

    public static final String SQL_SELECT_STUDENT_IN_JOURNAL =
            "SELECT course_id, user_id FROM journal WHERE course_id = ? AND user_id = ?";
    public static final String SQL_INSERT_STUDENT_INTO_JOURNAL =
            "INSERT INTO journal (course_id, user_id, dateJoin) VALUES (?, ?, curdate())";

    public static final String SQL_INSERT_MARK_INTO_JOURNAL =
            "UPDATE journal SET userScore = ? WHERE course_id = ? AND user_id = ?";

    //TODO: таблица курсов препода в личном кабинете
    public static final String SQL_SELECT_COURSES_FOR_LECTURER =
            "SELECT c.id, c.headline, t.topicName, c.length, COUNT(journal.course_id) AS students_amount, s.statusName " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id AND c.user_id = ? " +
            "group by c.id";

    public static final String SQL_UPDATE_COURSE_STATUS =
            "UPDATE course SET status_id = ? WHERE id = ?";

    //TODO: таблица студентов по конкретному курсу (журнал)
    public static final String SQL_SELECT_STUDENTS_BY_JOURNAL_COURSE_ID =
            "SELECT j.course_id, j.user_id, u.lastName, u.firstName, u.patronymic, u.login, j.dateJoin, j.userScore " +
            "FROM journal AS j, user AS u " +
            "WHERE j.user_id = u.id AND j.course_id = ?";

    //TODO: таблица курсов студента в личном кабинете
    public static final String SQL_SELECT_JOINT_COURSES_FOR_STUDENT =
            "SELECT c.id, c.headline, t.topicName, u.lastName, c.length, COUNT(journal.course_id) AS students_amount, " +
            "s.statusName, journal.userScore " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id AND journal.course_id IN " +
            "(SELECT course_id FROM journal WHERE user_id = ?) " +
            "group by c.id";

    public static final String SQL_SELECT_USER_INFO =
            "SELECT u.id, u.lastName, u.firstName, u.patronymic, u.login, u.email, r.roleName " +
            "FROM user AS u, role AS r " +
            "WHERE u.role_id = r.id AND u.id = ?";

    //TODO: тетрадка 9 - Админ. Курсы
    // вариант 1 - указан id препода
    public static final String SQL_INSERT_NEW_COURSE_V1_DIRTY =
            "INSERT INTO course (headline, description, length, topic_id, user_id) " +
            "VALUES ('Headline 1', 'Description 1', 5, " +
            "(SELECT id FROM topic WHERE topicName = 'Math'), 8)";

    //TODO: вариант 2 - указан login препода
    public static final String SQL_INSERT_NEW_COURSE_V2_DIRTY =
            "INSERT INTO course (headline, description, length, topic_id, user_id) " +
            "VALUES ('Headline 1', 'Description 1', 5, " +
            "(SELECT id FROM topic WHERE topicName = 'Math'), " +
            "(SELECT id FROM user WHERE login = 'p2'))";

    public static final String SQL_SELECT_LECTURER_FOR_COURSE =
            "SELECT u.id, u.firstName, u.lastName, u.patronymic, u.login, u.email " +
            "FROM user AS u WHERE u.role_id = ?";

    //admin functionality - students (un-)blocking
    public static final String SQL_SELECT_ALL_STUDENTS =
            "SELECT id, lastName, firstName, patronymic, login, email, state_id FROM user " +
            "WHERE role_id = ?";

    public static final String SQL_UPDATE_USER_STATE =
            "UPDATE user SET state_id = ? WHERE id = ?";

    public static final String SQL_SELECT_COURSES =
            "SELECT c.id, c.headline, c.description, t.topicName, u.lastName, c.length, COUNT(journal.course_id) AS students_amount, s.statusName " +
            "FROM user AS u, topic AS t, status AS s, course AS c " +
            "LEFT JOIN journal ON c.id = journal.course_id " +
            "WHERE c.topic_id = t.id AND c.user_id = u.id AND c.status_id = s.id " +
            "group by c.id";

    public static final String SQL_SELECT_COURSE_DB =
            "SELECT c.id, c.headline, c.description, " +
            "c.length, c.topic_id, c.user_id, s.statusName " +
            "FROM course AS c, status AS s " +
            "WHERE c.status_id = s.id AND c.id = ?";

    public static final String SQL_SELECT_LECTURER_BY_ID =
            "SELECT * FROM user WHERE id = ? AND role_id = ?";

    public static final String SQL_ADD_COURSE =
            "INSERT INTO course (headline, description, length, topic_id, user_id, status_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_COURSE =
            "UPDATE course " +
            "SET headline = ?, description = ?, length = ?, topic_id = ?, user_id = ?, status_id = ? " +
            "WHERE id = ?";

    public static final String SQL_SELECT_COURSE = "SELECT * FROM course WHERE id = ?";
}
