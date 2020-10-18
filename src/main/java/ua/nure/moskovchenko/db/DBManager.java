package ua.nure.moskovchenko.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

import ua.nure.moskovchenko.exception.DBException;
import ua.nure.moskovchenko.exception.Messages;
import org.apache.log4j.Logger;

/**
 * DBManager class is responsible for managing the stuff which is required to be able to work with the database.
 */
public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;
    private DataSource ds;

    /**
     * Shares a singleton model object to save memory and performance.
     * @return
     * @throws DBException
     */
    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Gets invoked at the very first try to connect to the DB. Looks up for the resource name "jdbc/epam"
     * in the webapp/META-INF/context.xml to get all the necessary parameters to work with DB.
     * @throws DBException
     */
    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");

            ds = (DataSource) envContext.lookup("jdbc/epam");
            LOG.trace("Data source has been accessed");
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    /**
     * Shares a set connection which can be used by DAO classes to perform various operations with DB.
     */
    public Connection getConnection() throws DBException {
        try {
            return ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }

    }

    /**
     * Closes connection.
     */
    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes prepared statement.
     */
    public static void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes ResultSet.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, PreparedStatement stmt) {
        close(stmt);
        close(con);
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, PreparedStatement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }
}
