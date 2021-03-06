package ua.nure.moskovchenko.exception;

/**
 * Messages class contains all String messages which pop up on various pages when different errors occur,
 * both client and server-side.
 */
public class Messages {

    private Messages() {}

    public static final String ERR_MESSAGE = "errorMessage";
    public static final String ERR_MESSAGE_TEXT_MISSING =
            "Looks like we got an unexpected trouble on our server. Please stand by!";
    public static final String ERR_INVALID_ROLE =
            "Uh-oh! Looks like there is an error in your identity. Please, re-login one more time!";
    public static final String ERR_DB_BASIC_TEXT =
            "Uh-oh! Looks like we got a trouble in a database. Please, stand by!";
    public static final String ERR_ACCESS_DENIED_COMMON =
            "Unfortunately, this page isn't available for guests. Please, log in!";
    public static final String ERR_INVALID_DATA_TRANSFER =
            "Looks like we got an error while transfering data from client to server. Please, try the same action again!";
    public static final String ERR_NO_PAGE_PARAMETER =
            "Unfortunately, the page you requested can't be loaded.";


    //========== COMMON DATABASE ERRORS =============
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_NO_PASSWORD_HASHING_ALG = "Cannot find or use that password hashing algorithm";

    public static final String ERR_CANNOT_FIND_I18N_WORD = "Cannot find a word in the properties";

    public static final String ERR_INVALID_VALUE_LANGUAGE = "Cannot find such a language";

    //=========== Positive messages =========

    public static final String MES_BEGINNING_COURSE = "The course with ID = ";

    public static final String MES_ENDING_DELETED = " has been successfully deleted.";
    public static final String MES_ENDING_NOT_FOUND = " has NOT been found.";
}
