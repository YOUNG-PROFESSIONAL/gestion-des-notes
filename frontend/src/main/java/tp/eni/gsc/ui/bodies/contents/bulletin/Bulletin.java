package main.java.tp.eni.gsc.ui.bodies.contents.bulletin;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Bulletin extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;

    public Bulletin(){
        initUI();
    }
    private  void initUI(){
        /********** Init component*********/
        tableModel = new DefaultTableModel();
        table = new JTable();
        scrollPane = new JScrollPane();
        /*************set Components values ************/
        tableModel.setColumnIdentifiers(new Object[]{"Numero","Nom et Prénom","NIVEAU"});
        table.setModel(tableModel);
        /************* init components *********/

        /********* set Components *********/
        scrollPane.setViewportView(table);
        setPreferredSize(new Dimension(1200,400));
        /************* set Layout *************/
        MigLayout layout = new MigLayout("fillx","[]","[][]");
        setLayout(layout);
        add(scrollPane,"span 1,w 1000!");

    }
}
