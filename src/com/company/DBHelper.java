package com.company;

import java.sql.*;

public class DBHelper {

    private static Connection connection;

    public static void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lab3", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllRecords() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("select * from lab3");
        return resultSet;
    }
}
