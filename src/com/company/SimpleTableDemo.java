package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(wczytajDaneTxtButton);
        buttonsPanel.add(wczytajDaneXmlButton);
        buttonsPanel.add(zapiszDaneTxtButton);
        buttonsPanel.add(zapiszDaneXmlButton);
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

    }
    private void zapiszTXT() {
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
                fileOut.append(System.getProperty( "line.separator" ));
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void zapiszXML() {
       // JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);

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
