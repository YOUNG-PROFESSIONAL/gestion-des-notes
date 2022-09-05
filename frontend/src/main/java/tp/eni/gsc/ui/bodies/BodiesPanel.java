package main.java.tp.eni.gsc.ui.bodies;

import main.java.tp.eni.gsc.ui.bodies.contents.dashboard.DashboardUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class BodiesPanel extends JPanel {

    public BodiesPanel() throws IOException {

        initUI();
    }
    private void initUI() throws IOException {
        /***************Init Panel************/
        /**************Set Layout*********/
        /********* Set this **********/
        setBackground(new Color(0xFF7575));
        setSize(new Dimension(1920,500));
        setPreferredSize(new Dimension(1920,500));
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setMaximumSize(new Dimension(1190,500));
            showForm(new DashboardUI());
    }
    public void showForm(JComponent form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
}
