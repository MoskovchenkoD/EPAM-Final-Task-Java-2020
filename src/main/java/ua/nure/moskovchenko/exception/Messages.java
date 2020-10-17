package ua.nure.moskovchenko.exception;

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



    //========== COMMON DATABASE ERRORS =============
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

    public static final String ERR_NO_PASSWORD_HASHING_ALG = "Cannot find or use that password hashing algorithm";

    public static final String ERR_CANNOT_FIND_I18N_WORD = "Cannot find a word in the properties";

    public static final String ERR_INVALID_VALUE_LANGUAGE = "Cannot find such a language";

    //=========== Non fatal messages =========
    public static final String MARK_WAS_PUT_SUCCESSFULLY = "Mark was put successfully!";

}
