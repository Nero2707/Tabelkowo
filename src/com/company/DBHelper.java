package com.company;

import java.sql.*;
import java.util.List;

public class DBHelper {

    private static Connection connection;

    public static void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lab3?serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllRecords() throws SQLException {
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("select * from laptopy");
        return resultSet;
    }
    public void insertRecord(String query) throws SQLException {
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.execute();
        connection.close();
    }
}
