/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.contam.helper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class DBHelper {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = attemptConnection();
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    private static Connection attemptConnection()
            throws ClassNotFoundException, SQLException, IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("db.properties")) {
            props.load(resourceStream);
        }

        String drivers = props.getProperty("jdbc.drivers");
        String connectionURL = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        
        Class.forName(drivers);
        Connection conn = DriverManager.getConnection(connectionURL, username, password);
        System.out.println("Connection Successful");
        
        return conn;
    }
}
