package main.java.tp.eni.gsc;

import main.java.tp.eni.gsc.ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SwingUtilities.invokeLater(() ->
                new MainFrame().setVisible(true)
        );
    }
}
