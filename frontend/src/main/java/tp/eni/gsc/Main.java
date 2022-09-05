package main.java.tp.eni.gsc;

import main.java.tp.eni.gsc.ui.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SwingUtilities.invokeLater(() ->
                {
                    try {
                        new MainFrame().setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
