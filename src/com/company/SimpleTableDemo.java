package com.company;

import com.company.com.company.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class SimpleTableDemo extends JPanel {
    private boolean DEBUG = false;
    private JTable table;
    public SimpleTableDemo() {
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

        JButton wczytajDaneTxtButton = new JButton("Wczytaj dane z TXT");
        JButton wczytajDaneXmlButton = new JButton("Wczytaj dane z XML");
        JButton zapiszDaneTxtButton = new JButton("Zapisz dane do TXT");
        JButton zapiszDaneXmlButton = new JButton("Zapisz dane do XML");
        JButton importujZBazyDanych = new JButton("Importuj z bazy danych");
        JButton eksportujDoBazyDanych = new JButton("Eksportuj do bazy danych");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(wczytajDaneTxtButton);
        buttonsPanel.add(wczytajDaneXmlButton);
        buttonsPanel.add(zapiszDaneTxtButton);
        buttonsPanel.add(zapiszDaneXmlButton);
        buttonsPanel.add(importujZBazyDanych);
        buttonsPanel.add(eksportujDoBazyDanych);
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
            for (int row = 0; row < laptopy.getLaptops().size(); row++) {
                Laptop laptop= laptopy.getLaptops().get(row);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                String a[]=new String[15];
                a[0]=  laptop.getManufacturer();
                a[1]= laptop.getScreen().getSize();
                a[2]=
                a[3]=
                a[4]=
                a[5]=
                a[6]=
                model.addRow();
//                Screen screen = new Screen();
//                Laptop laptop = new Laptop();
//                Processor procesor = new Processor();
//                Disc dysk = new Disc();
//                GraphicCard grafika = new GraphicCard();
//                for (int col = 0; col < laptopy.getLaptops().size(); col++) {
//                    String nazwaKolumny = laptopy(col);
//                    if ("producent".equalsIgnoreCase(nazwaKolumny)) {
//                        laptop.getManufacturer((String) laptopy.getValueAt(row, col));
//                    }
//                    if ("rozdzielczość".equalsIgnoreCase(nazwaKolumny)) {
//                        screen.getSize((String) table.getValueAt(row, col));
//                    }
//                    if ("powłoka matrycy".equalsIgnoreCase(nazwaKolumny)) {
//                        screen.getType((String) table.getValueAt(row, col));
//                    }
//                    if ("ekran dotykowy".equalsIgnoreCase(nazwaKolumny)) {
//                        screen.getTouchscreen((String) table.getValueAt(row, col));
//                    }
//                    if ("seria procesora".equalsIgnoreCase(nazwaKolumny)) {
//                        procesor.getName((String) table.getValueAt(row, col));
//                    }
//                    if ("liczba rdzeni".equalsIgnoreCase(nazwaKolumny)) {
//                        procesor.getPhysical_cores((String) table.getValueAt(row, col));
//                    }
//                    if ("Taktowanie bazowe".equalsIgnoreCase(nazwaKolumny)) {
//                        procesor.getClock_speed((String) table.getValueAt(row, col));
//                    }
//                    if ("wielkość pamięci ram".equalsIgnoreCase(nazwaKolumny)) {
//                        laptop.getRam((String) table.getValueAt(row, col));
//                    }
//                    if ("pojemność dysku".equalsIgnoreCase(nazwaKolumny)) {
//                        dysk.getStorage((String) table.getValueAt(row, col));
//                    }
//                    if ("typ dysku".equalsIgnoreCase(nazwaKolumny)) {
//                        dysk.getType((String) table.getValueAt(row, col));
//                    }
//                    if ("karta graficzna".equalsIgnoreCase(nazwaKolumny)) {
//                        grafika.getName((String) table.getValueAt(row, col));
//                    }
//                    if ("pamięć karty graficznej".equalsIgnoreCase(nazwaKolumny)) {
//                        grafika.getMemory((String) table.getValueAt(row, col));
//                    }
//                    if ("system operacyjny".equalsIgnoreCase(nazwaKolumny)) {
//                        laptop.getOs((String) table.getValueAt(row, col));
//                    }
//                    if ("napęd optyczny".equalsIgnoreCase(nazwaKolumny)) {
//                        laptop.getDisc_reader((String) table.getValueAt(row, col));
//                    }
//                    laptop.setScreen(screen);
//                    laptop.setProcessor(procesor);
//                    laptop.setDisc(dysk);
//                    laptop.setGraphic_card(grafika);
//
//                }
//                laptopList.add(laptop);
//                for (int row = 0; row < laptopy.getLaptops().size(); row++) {
//                    model.addRow();
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

        SimpleTableDemo newContentPane = new SimpleTableDemo();
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
    }
}
