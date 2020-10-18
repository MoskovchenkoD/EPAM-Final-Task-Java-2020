package ua.nure.moskovchenko.db;

/**
 * Column class consists of column names of database tables.
 * These strings are used to retrieve values from ResultSets by column names.
 * Some exotic columns, like COUNT(journal.course_id), are renamed in SQL queries to simpler ones.
 * Then, their simple names are stored here.
 */
public final class Column {

    public static final String ID_PRIMARY_KEY = "id";

    //========= USER TABLE ===========

    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_PATRONYMIC = "patronymic";
    public static final String USER_LOGIN = "login";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_STATE_ID = "state_id";

    //========= COURSE TABLE ===========

    public static final String COURSE_HEADLINE = "headline";
    public static final String COURSE_DESCRIPTION = "description";
    public static final String COURSE_LENGTH = "length";
    public static final String COURSE_TOPIC_ID = "topic_id";
    public static final String COURSE_USER_ID = "user_id";
    public static final String COURSE_STATUS_ID = "status_id";

    public static final String JOURNAL_STUDENTS_AMOUNT = "students_amount";

    //========= TOPIC TABLE ===========

    public static final String TOPIC_NAME = "topicName";

    //========= STATUS TABLE ===========

    public static final String STATUS_NAME = "statusName";

    //========= ROLE TABLE ===========

    public static final String ROLE_NAME = "roleName";

    //========= STATE TABLE ===========

    public static final String STATE_NAME = "stateName";

    //========= Journal TABLE ===========

    public static final String JOURNAL_COURSE_ID = "course_id";
    public static final String JOURNAL_USER_ID = "user_id";
    public static final String JOURNAL_USER_SCORE = "userScore";
    public static final String JOURNAL_DATE_JOIN = "dateJoin";

    //public static final String  = "";

}
