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
    public int countRecords(String nazwaProducenta) throws SQLException {
        ResultSet resultSet = null;
        try {
            Statement query = null;
            query = connection.createStatement();
            resultSet = query.executeQuery("select COUNT(*) from laptopy where manufacturer ='"+nazwaProducenta+"'");
            if(resultSet.next()){
                int liczba = resultSet.getInt(1);
                return liczba;
            }else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int countRecordsRozdzielczosc(String rozdzcEkranuBox) throws SQLException {
        ResultSet resultSet = null;
        try {
            Statement query = null;
            query = connection.createStatement();
            resultSet = query.executeQuery("select COUNT(*) from laptopy where screenType ='"+rozdzcEkranuBox+"'");
            if(resultSet.next()){
                int liczba = resultSet.getInt(1);
                return liczba;
            }else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public ResultSet laptopyWgCech( List<String> cechy)  {
        Statement query = null;
        ResultSet resultSet = null;
        try {
            query = connection.createStatement();
            resultSet = query.executeQuery("select "+String.join(",", cechy)+" from laptopy");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
