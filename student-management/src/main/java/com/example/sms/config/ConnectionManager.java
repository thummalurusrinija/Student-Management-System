package com.example.sms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger log = LoggerFactory.getLogger(ConnectionManager.class);
    private static final DbConfig CONFIG = new DbConfig();

    static {
        try {
            Class.forName(CONFIG.getDriver());
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("JDBC Driver not found: " + e.getMessage());
        }
    }

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        String url = CONFIG.getUrl();
        String user = CONFIG.getUsername();
        String pass = CONFIG.getPassword();
        log.debug("Opening JDBC connection to {}", url);
        return DriverManager.getConnection(url, user, pass);
    }
}