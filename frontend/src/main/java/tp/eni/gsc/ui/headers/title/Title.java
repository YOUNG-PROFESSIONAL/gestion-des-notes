package main.java.tp.eni.gsc.ui.headers.title;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel{
    private static JLabel mainTitle;
    public Title(String titre){
        initUI(titre);
    }
    private  void  initUI(String titre){
        /*****************************/
        mainTitle = new JLabel(titre);
        mainTitle.setFont(new Font("iceberg", Font.BOLD,30));
        add(mainTitle);
        /*****************************/
        setBackground(new Color(0x27A0FF));
        setPreferredSize(new Dimension(400,80));
    }
    public static void setMainTitle(String title){
        mainTitle.setText(title);
    }
}
