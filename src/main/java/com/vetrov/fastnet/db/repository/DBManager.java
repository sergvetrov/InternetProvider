package com.vetrov.fastnet.db.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DBManager {
    private static final Logger log = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private ComboPooledDataSource cpds;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    private DBManager() {
        try {
            init();
            log.info("Pool created");
        } catch (Exception e) {
            log.warn("Pool couldn't create");
            e.printStackTrace();
        }
    }

    /**
     * Create pool
     *
     * @throws Exception the exception
     */
    private void init() throws Exception {
        createPool();
    }

    /**
     * Establishes a connection to the database
     *
     * @return the connection to the database
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = this.cpds.getConnection();
            log.info("DB connection created");
        } catch (SQLException e) {
            log.warn("DB connection couldn't create");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Closes the connection
     *
     * @param conn closes the database connection
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
            log.info("DB connection closed");
        } catch (SQLException e) {
            log.warn("DB connection couldn't close");
            e.printStackTrace();
        }
    }

    /**
     * Gets utils properties
     *
     * @return the databases properties
     * @throws IOException by failed or interrupted I/O operations.
     */
    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(DBManager.class.getResourceAsStream("/db.properties"));
        return props;
    }

    /**
     * Create a pool
     *
     * @throws Exception the exception
     */
    private void createPool() throws Exception {
        Properties props = getProperties();
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(props.getProperty("driver"));
        cpds.setJdbcUrl(props.getProperty("url"));
        cpds.setUser(props.getProperty("user"));
        cpds.setPassword(props.getProperty("password"));
        cpds.setMaxStatements(180);
    }
}
