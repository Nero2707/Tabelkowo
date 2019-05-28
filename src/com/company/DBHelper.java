package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class DBHelper {

    private static Connection connection;

    public void createConnection(){
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
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllRecords()  {
        Statement query = null;
        ResultSet resultSet = null;
        try {
            query = connection.createStatement();
            resultSet = query.executeQuery("select * from laptopy");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void insertRecord(String query){
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
