package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    }

    private void wczytajTXT() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        try (Stream<String> wiersze = Files.lines(Paths.get("laptopy.txt"))) {
            wiersze.forEach(wiersz->model.addRow(wiersz.split(";",-1)));

        } catch (IOException e) {
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
