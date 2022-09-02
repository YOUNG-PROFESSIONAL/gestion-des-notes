package main.java.tp.eni.gsc.ui.headers.logo;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class Logo extends JPanel {
    JLabel label;
    JLabel labelAnnee;
    public Logo(){
        initUI();
    }
    private void initUI(){
        /*****************************/
        label = new JLabel("SCO");
        label.setFont(new Font("iceberg", Font.BOLD,60));
        labelAnnee = new JLabel("Année universitaire " + LocalDateTime.now().getYear());
        labelAnnee.setFont(new Font("iceberg", Font.BOLD,12));
        /*****************************/
        MigLayout layout = new MigLayout("wrap 1","[]","[][]");
        setLayout(layout);
        add(label,"cell 0 0");
        add(labelAnnee,"cell 0 1");
        setOpaque(false);
        setPreferredSize(new Dimension(200,80));
    }
}
