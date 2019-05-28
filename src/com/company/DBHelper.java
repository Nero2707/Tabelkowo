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
    public ResultSet insertRecord(List<String> query) throws SQLException {

        String zapyt = " insert into laptopy (manufacturer, screenSize, screenType, screenResolution, touchscreen, processor_name, physical_cores, clock_speed, ram, disk_storage, disk_type, graphic_card_name,graphic_card_memory, disc_reader, operating_system)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = connection.prepareStatement(zapyt);
        preparedStmt.setString (1, query.get(1));
        preparedStmt.setString (2, "Rubble");
        preparedStmt.setString (3, "Barney");
        preparedStmt.setString (4, "Rubble");
        preparedStmt.setString (5, "Barney");
        preparedStmt.setString (6, "Rubble");
        preparedStmt.setString (7, "Barney");
        preparedStmt.setString (8, "Rubble");
        preparedStmt.setString (9, "Barney");
        preparedStmt.setString (10, "Rubble");
        preparedStmt.setString (11, "Barney");
        preparedStmt.setString (12, "Rubble");
        preparedStmt.setString (13, "Barney");
        preparedStmt.setString (14, "Rubble");
        preparedStmt.setString (15, "Barney");
        preparedStmt.execute();
        conn.close();
    }
}
