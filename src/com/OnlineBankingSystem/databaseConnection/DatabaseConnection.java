package com.OnlineBankingSystem.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection provideConnection() {
        Connection con= null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        try{
            con = DriverManager.getConnection(url , "root", "Karan@12345");
        } catch (Exception e) {
            System.out.println("Exception occur in Database");
        }
        return con;
    }

}
