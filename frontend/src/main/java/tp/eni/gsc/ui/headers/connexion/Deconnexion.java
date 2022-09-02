package main.java.tp.eni.gsc.ui.headers.connexion;

import javax.swing.*;
import java.awt.*;

public class Deconnexion extends JPanel {
    JButton deconnexion;
    ImageIcon icon;
    public Deconnexion(){
        initUI();
    }
    private  void  initUI(){
        /**************Components setting***************/
        icon = new ImageIcon("src/main/java/resources/static/icon/icon.png");
        JLabel iconUI = new JLabel(icon);
        deconnexion = new JButton("Deconnexion");
        deconnexion.setFont(new Font("iceberg", Font.BOLD,18));
        /*****************************/
        createLayout(iconUI,deconnexion);
        /******************************/
        setOpaque(false);
        setBackground(new Color(0xffc0c0));
        setPreferredSize(new Dimension(200,50));
    }
    private void createLayout(JComponent... args){
        GroupLayout group = new GroupLayout(this);
        setLayout(group);
        GroupLayout.SequentialGroup hGroup = group.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = group.createBaselineGroup(false,false);
        hGroup.addGroup(group.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(args[0]).addComponent(args[1]));
        vGroup.addGroup(group.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(args[0]).addComponent(args[1]));
        group.setHorizontalGroup(hGroup);
        group.setVerticalGroup(vGroup);

    }
}
