/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.sql.*;
import javax.xml.transform.Result;


/**
 *
 * @author Charis
 */
public class DBconnection {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://BARIOCK:1433;databaseName=testaki;user=BARIOCK;password=1234";
        Connection con = DriverManager.getConnection(connectionURL);

        System.out.println("You are connected");
       
       
        // TODO code application logic here
    }
    
}
