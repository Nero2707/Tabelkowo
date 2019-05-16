package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
        try (Stream<String> wiersze = Files.lines(Paths.get("laptopy.txt"))) {
            wiersze.forEach(wiersz->model.addRow(wiersz.split(";",-1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajXML() {

    }
    private void zapiszTXT() throws FileNotFoundException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        PrintWriter plik = new PrintWriter("laptopy.txt");
        plik.write("");
        for (int wiersz = 0; wiersz < table.getRowCount(); wiersz++) {
            for (int kolumna = 0; kolumna < table.getColumnCount(); kolumna++) {
                String doPliku="";
                doPliku=+ table.getValueAt(wiersz, kolumna).toString()+";";

            }
        }

        plik.append();
        plik.close();

    }
    private void zapiszXML() {

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
