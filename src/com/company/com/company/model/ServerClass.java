package com.company.com.company.model;

import com.company.DBHelper;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class ServerClass {
    public int liczLaptopy(String nazwaProducenta) {
        DBHelper dbHelper = new DBHelper();
        dbHelper.createConnection();
        try {
            int liczba = dbHelper.countRecords(nazwaProducenta);
            dbHelper.closeConnection();
            return liczba;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


    }
    public int liczLaptopyRozdzielczosc(String rozdzcEkranuBox) {
        DBHelper dbHelper = new DBHelper();
        dbHelper.createConnection();
        try {
            int liczba = dbHelper.countRecordsRozdzielczosc(rozdzcEkranuBox);
            dbHelper.closeConnection();
            return liczba;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


    }

    //List<String> cechy
    public void eksportujDoXML() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        dbHelper.createConnection();

        List<String> cechy = Arrays.asList("manufacturer",
                "screenSize",
                "screenType",
                "screenResolution",
                "touchscreen",
                "processor_name",
                "physical_cores",
                "clock_speed",
                "ram",
                "disk_storage",
                "disk_type",
                "graphic_card_name",
                "graphic_card_memory",
                "disc_reader",
                "operating_system"
        );
        dbHelper.laptopyWgCech(cechy);

        dbHelper.closeConnection();

    }
}