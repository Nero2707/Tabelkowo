package com.company.com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class laptopymapper {
    public void setValue(String propertyName, String value) {
        Laptops laptops = new Laptops();
        Screen screen = new Screen();
        Laptop laptop = new Laptop();
        Processor procesor = new Processor();
        Disc dysk = new Disc();
        GraphicCard grafika = new GraphicCard();
        switch (propertyName) {
            case "manufacturer":
                laptop.setManufacturer();
                break;
            case "screen":
                screen.setResolution(value);
                break;
            case "resolution":
                laptop.screen.setSize();
                break;
            case "matrixType":
                screen.setType(value);
                break;
            case "touchable":
                screen.setTouchscreen(value);
                break;
            case "processor_name":
                laptop.getProcessor().setName(value);
                break;
            case "physical_cores":
                laptop.getProcessor().setPhysical_cores(value);
                break;
            case "clock_speed":
                laptop.getProcessor().setClock_speed(value);
                break;
            case "ram":
                laptop.setRam(value);
                break;
            case "disk_type":
                laptop.getDisc().setType(value);
                break;
            case "disk_storage":
                laptop.disc.setStorage(value);
                break;
            case "graphic_card_name":
                laptop.getGraphic_card().setName(value);
                break;
            case "graphic_card_memory":
                laptop.getGraphic_card().setMemory(value);
                break;
            case "operating_system":
                laptop.setOs(value);
                break;
        }
    }

    public static List<Laptops> mapResultSetToObjectsList(ResultSet notebooksFromDB){
        while(true){
            try {
                List<Laptops> notebooksList = new ArrayList<>();
                while (notebooksFromDB.next()) {
                    Laptops notebook = new Laptops();
                    for(String property : Laptops.columnNames){
                        notebook.setValue(property, notebooksFromDB.getString(property));
                    }
                    notebooksList.add(notebook);
                }
                return notebooksList;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
