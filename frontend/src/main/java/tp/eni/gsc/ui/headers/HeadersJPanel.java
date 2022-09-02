package main.java.tp.eni.gsc.ui.headers;

import main.java.tp.eni.gsc.ui.headers.connexion.Deconnexion;
import main.java.tp.eni.gsc.ui.headers.logo.Logo;
import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class HeadersJPanel extends JPanel {
    JPanel logoPanel;
    JPanel titlePanel;
    JPanel deconnexionPanel;
    public HeadersJPanel(){

        initUI();
    }
    private void initUI(){
        logoPanel = new Logo();
        titlePanel = new JPanel();
        titlePanel.add(new Title("Titre"));
        deconnexionPanel = new Deconnexion();
        /************* Add to this components*******/
        titlePanel.setOpaque(false);
        MigLayout layout = new MigLayout("wrap,insets 0,fill","[left][center][right]","[]");
        setLayout(layout);
        add(logoPanel," gapleft 30, cell 0 0");
        add(titlePanel,"cell 1 0");
        add(deconnexionPanel,"cell 2 0");
        /********* Set this **********/
        setBackground(new Color(0x27A0FF));
        setSize(new Dimension(1920,80));
        setPreferredSize(new Dimension(1920,80));
    }
}
