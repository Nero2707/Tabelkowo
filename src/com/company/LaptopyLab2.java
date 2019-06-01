package com.company;

import com.company.com.company.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Endpoint;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class LaptopyLab2 extends JPanel {
    private boolean DEBUG = false;
    private JTable table;
    public LaptopyLab2() {
        super(new GridLayout(1,0));

        String[] columnNames = {"Producent",
                "Wielkość matrycy",
                "Rozdzielczość",
                "Powłoka matrycy",
                "Ekran dotykowy",
                "Seria procesora",
                "Liczba rdzeni",
                "Taktowanie bazowe",
                "Wielkość pamięci RAM",
                "Pojemność dysku",
                "Typ dysku",
                "Karta graficzna",
                "Pamięć karty graficznej",
                "System operacyjny",
                "Napęd optyczny"};


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        table = new JTable(new DefaultTableModel(columnNames,0));
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        //static JTextField liczbaNowychRekordow;

        JButton wczytajDaneTxtButton = new JButton("Wczytaj dane z TXT");
        JButton wczytajDaneXmlButton = new JButton("Wczytaj dane z XML");
        JButton zapiszDaneTxtButton = new JButton("Zapisz dane do TXT");
        JButton zapiszDaneXmlButton = new JButton("Zapisz dane do XML");
        JButton importujZBazyDanych = new JButton("Importuj z bazy danych");
        JButton eksportujDoBazyDanych = new JButton("Eksportuj do bazy danych");
        //JButton TEST = new JButton("TEST =");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(wczytajDaneTxtButton);
        buttonsPanel.add(wczytajDaneXmlButton);
        buttonsPanel.add(zapiszDaneTxtButton);
        buttonsPanel.add(zapiszDaneXmlButton);
        buttonsPanel.add(importujZBazyDanych);
        buttonsPanel.add(eksportujDoBazyDanych);
       // buttonsPanel.add(TEST);
        add(buttonsPanel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        wczytajDaneTxtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wczytajTXT();
            }
        } );
        wczytajDaneXmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wczytajXML();
            }
        } );
        zapiszDaneTxtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    zapiszTXT();
            }
        } );
        zapiszDaneXmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zapiszXML();
            }
        } );
        eksportujDoBazyDanych.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eksportujDoBazyDanych();
            }
        } );

        importujZBazyDanych.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DBHelper dbHelper = new DBHelper();
                dbHelper.createConnection();
                ResultSet rs = dbHelper.getAllRecords();
                imprZBD(rs);
            }
        } );
//        TEST.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                DBHelper dbHelper = new DBHelper();
//                dbHelper.createConnection();
//                List<String> cechy = Arrays.asList("manufacturer",
//                        "screenSize",
//                        "screenType",
//                        "screenResolution");
//                ResultSet rs = dbHelper.laptopyWgCech(cechy);
//                imprZBD(rs);
//            }
//        } );
    }
    private void imprZBD(ResultSet rs){
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (rs.next()){
                String manufacturer = rs.getString("manufacturer");
                String screenSize = rs.getString("screenSize");
                String screenResolution = rs.getString("screenResolution");
                String screenType = rs.getString("screenType");
                String touchscreen = rs.getString("touchscreen");
                String processor_name = rs.getString("processor_name");
                String physical_cores = rs.getString("physical_cores");
                String clock_speed = rs.getString("clock_speed");
                String ram = rs.getString("ram");
                String disk_storage = rs.getString("disk_storage");
                String disk_type = rs.getString("disk_type");
                String graphic_card_name = rs.getString("graphic_card_name");
                String graphic_card_memory = rs.getString("graphic_card_memory");
                String operating_system = rs.getString("operating_system");
                String disc_reader = rs.getString("disc_reader");
 //               System.out.println("test"+manufacturer);
                String a[]=new String[15];

                a[0]= manufacturer;

                a[1]= screenSize;
                a[2]=screenResolution;
                a[3]=screenType;
                a[4]=touchscreen;
                a[5]=processor_name;
                a[6] = physical_cores;
                a[7] = clock_speed;
                a[8]= ram;
                a[9]= disk_storage;
                a[10]= disk_type;
                a[11]= graphic_card_name;
                a[12]= graphic_card_memory;
                a[13]=operating_system;
                a[14]=disc_reader;
                model.addRow(a);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //przycisk do eksportu danych z bazy do pliku XML, zawierającego tylko 5 wybranych przez użytkownika cech laptopów.
    private void TEST(){
        ServerClass server = new ServerClass();
        try {

            server.eksportujDoXML();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void eksportujDoBazyDanych () {
        DBHelper dbHelper = new DBHelper();
        dbHelper.createConnection();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int row = 0; row < table.getRowCount(); row++) {
            String query = "insert into laptopy (manufacturer, screenSize, screenType, screenResolution, touchscreen, processor_name, physical_cores, clock_speed, ram, disk_storage, disk_type, graphic_card_name,graphic_card_memory, disc_reader, operating_system) values ( ";
            for (int col = 0; col < table.getColumnCount(); col++) {
                if(col==table.getColumnCount()-1){
                    if("".equals(table.getValueAt(row, col))){
                        query += "null";
                    }else{
                        query += "\'"+ table.getValueAt(row, col) +"\'";
                    }
                }else {
                    if ("".equals(table.getValueAt(row, col))) {
                        query += "null,";
                    } else {
                        query +="\'"+ table.getValueAt(row, col) +"\'" + ",";
                    }
                }
            }
            query +=");";
            dbHelper.insertRecord(query);

        }
        dbHelper.closeConnection();


    }
    private void wczytajTXT() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try (Stream<String> wiersze = Files.lines(Paths.get("laptopy.txt"))) {
            wiersze.forEach(wiersz->model.addRow(wiersz.split(";",-1)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void wczytajXML() {
       try {
//            DefaultTableModel model = (DefaultTableModel) table.getModel();
//            //getting the xml file to read
            File file = new File("laptopy.xml");
//            //creating the JAXB context
            JAXBContext jContext = JAXBContext.newInstance(Laptops.class);
//            //creating the unmarshall object
           Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
//            //calling the unmarshall method
           Laptops laptopy = (Laptops) unmarshallerObj.unmarshal(file);
                System.out.println("test");
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
            for (int row = 0; row < laptopy.getLaptops().size(); row++) {
                Laptop laptop= laptopy.getLaptops().get(row);

                String a[]=new String[15];

                a[0]=  laptop.getManufacturer();

                if (laptop.getScreen()!=null) {
                    a[1]= laptop.getScreen().getSize();
                    a[2]=laptop.getScreen().getResolution();
                    a[3]=laptop.getScreen().getType();
                    a[4]=laptop.getScreen().getTouchscreen();
                }
                if (laptop.getScreen()!=null) {
                    a[5]=laptop.getProcessor().getName();
                    a[6] = laptop.getProcessor().getPhysical_cores();
                    a[7] = laptop.getProcessor().getClock_speed();

                }
                a[8]= laptop.getRam();
                if (laptop.getDisc()!=null) {
                    a[9]= laptop.getDisc().getStorage();
                    a[10]= laptop.getDisc().getType();

                }
                if (laptop.getDisc()!=null) {
                    a[9]= laptop.getDisc().getStorage();
                    a[10]= laptop.getDisc().getType();
                }
                if (laptop.getGraphic_card()!=null) {
                    a[11]= laptop.getGraphic_card().getName();
                    a[12]= laptop.getGraphic_card().getMemory();
                }
                a[13]=laptop.getOs();
                a[14]=laptop.getDisc_reader();
                model.addRow(a);

               }
            }catch(Exception e){
                e.printStackTrace();
            }
//        }
    }
        private void zapiszTXT () {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            try {
                FileWriter fileOut = new FileWriter("laptopy.txt");
                fileOut.write("");
                for (int row = 0; row < table.getRowCount(); row++) {
                    String wiersz = "";
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        wiersz += table.getValueAt(row, col) + ";";
                    }
                    fileOut.append(wiersz);
                    fileOut.append(System.getProperty("line.separator"));
                }
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    private void zapiszXML() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Laptop> laptopList = new ArrayList<>();
        Laptops laptops = new Laptops();
        for (int row = 0; row < table.getRowCount(); row++) {
            Screen screen = new Screen();
            Laptop laptop = new Laptop();
            Processor procesor = new Processor();
            Disc dysk = new Disc();
            GraphicCard grafika = new GraphicCard();
            for (int col = 0; col < table.getColumnCount(); col++) {
                String nazwaKolumny = table.getColumnName(col);
                if("producent".equalsIgnoreCase(nazwaKolumny)){
                    laptop.setManufacturer((String)table.getValueAt(row, col));
                }
                if("rozdzielczość".equalsIgnoreCase(nazwaKolumny)){
                    screen.setResolution((String)table.getValueAt(row, col));
                }
                if("wielkość matrycy".equalsIgnoreCase(nazwaKolumny)){
                    screen.setSize((String)table.getValueAt(row, col));
                }
                if("powłoka matrycy".equalsIgnoreCase(nazwaKolumny)){
                    screen.setType((String)table.getValueAt(row, col));
                }
                if("ekran dotykowy".equalsIgnoreCase(nazwaKolumny)){
                    screen.setTouchscreen((String)table.getValueAt(row, col));
                }
                if("seria procesora".equalsIgnoreCase(nazwaKolumny)){
                    procesor.setName((String)table.getValueAt(row, col));
                }
                if("liczba rdzeni".equalsIgnoreCase(nazwaKolumny)){
                    procesor.setPhysical_cores((String)table.getValueAt(row, col));
                }
                if("Taktowanie bazowe".equalsIgnoreCase(nazwaKolumny)){
                    procesor.setClock_speed((String)table.getValueAt(row, col));
                }
                if("wielkość pamięci ram".equalsIgnoreCase(nazwaKolumny)){
                    laptop.setRam((String)table.getValueAt(row, col));
                }
                if("pojemność dysku".equalsIgnoreCase(nazwaKolumny)){
                    dysk.setStorage((String)table.getValueAt(row, col));
                }
                if("typ dysku".equalsIgnoreCase(nazwaKolumny)){
                    dysk.setType((String)table.getValueAt(row, col));
                }
                if("karta graficzna".equalsIgnoreCase(nazwaKolumny)){
                    grafika.setName((String)table.getValueAt(row, col));
                }
                if("pamięć karty graficznej".equalsIgnoreCase(nazwaKolumny)){
                    grafika.setMemory((String)table.getValueAt(row, col));
                }
                if("system operacyjny".equalsIgnoreCase(nazwaKolumny)){
                    laptop.setOs((String)table.getValueAt(row, col));
                }
                if("napęd optyczny".equalsIgnoreCase(nazwaKolumny)){
                    laptop.setDisc_reader((String)table.getValueAt(row, col));
                }
                laptop.setScreen(screen);
                laptop.setProcessor(procesor);
                laptop.setDisc(dysk);
                laptop.setGraphic_card(grafika);

            }
            laptopList.add(laptop);
        }
        laptops.setLaptops(laptopList);
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Laptops.class);
            Marshaller jaxbMarshaller  = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(laptops, new File("laptopy.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

        private static void createAndShowGUI() {

        JFrame frame = new JFrame("Tabelkowo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LaptopyLab2 newContentPane = new LaptopyLab2();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        String url = "http://localhost:1212/ServerClass";
        Endpoint.publish(url, new ServerClass());
        System.out.println("Service started @ " + url);
    }
}
