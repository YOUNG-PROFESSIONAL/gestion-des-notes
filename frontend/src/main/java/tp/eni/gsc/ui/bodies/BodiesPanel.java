package main.java.tp.eni.gsc.ui.bodies;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BodiesPanel extends JPanel {

    public BodiesPanel(){

        initUI();
    }
    private void initUI(){
        /***************Init Panel************/
        /**************Set Layout*********/
        /********* Set this **********/
        setBackground(new Color(0xFF7575));
        setSize(new Dimension(1920,500));
        setPreferredSize(new Dimension(1920,500));
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 20, 10, 20));

    }
    public void showForm(JComponent form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
}
