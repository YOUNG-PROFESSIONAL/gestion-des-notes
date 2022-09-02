package main.java.tp.eni.gsc.ui.footers;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class FootersJPanel extends JPanel {
    JLabel label ;
    public FootersJPanel(){
        initUI();
    }
    private void initUI(){
        /************************************/
        label = new JLabel("ECOLE NATIONALE D'INFORMATIQUE FIANARANTSOA");
        label.setFont(new Font("iceberg",Font.ITALIC,16));
        /************** Layout ****************/
        MigLayout layout = new MigLayout("fill","5[center]5","[]");
        setLayout(layout);
        add(label,"span");

        /********* Set this **********/
        setBackground(new Color(0x8181EC));
        setSize(new Dimension(1920,80));
        setPreferredSize(new Dimension(1920,80));
    }
}
