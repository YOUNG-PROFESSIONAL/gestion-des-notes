package main.java.tp.eni.gsc.ui;

import main.java.tp.eni.gsc.ui.bodies.BodiesPanel;
import main.java.tp.eni.gsc.ui.bodies.contents.bulletin.BulletinUI;
import main.java.tp.eni.gsc.ui.bodies.contents.dashboard.DashboardUI;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.EtudiantUI;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.MatieresUI;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.NotesUI;
import main.java.tp.eni.gsc.ui.footers.FootersJPanel;
import main.java.tp.eni.gsc.ui.headers.HeadersJPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    JPanel headersPanel;
    JPanel menuPanel;
    BodiesPanel bodiesPanel;
    JPanel footersPanel;
    JPanel mainPanel;

    JButton btnDashboard;
    JButton btnEtudiant;
    JButton btnMatieres;
    JButton btnNotes;
    JButton btnBulletin;

    public MainFrame() throws IOException {
        initUI();
    }
    private void initUI() throws IOException {
        /********* ADD COMPONENTS ********/
        headersPanel = new HeadersJPanel();
        menuPanel = new JPanel();
        bodiesPanel = new BodiesPanel();
        footersPanel = new FootersJPanel();
        mainPanel = new JPanel();
        /************Menu Panel***********/
        menuPanel();
        /********* Set Layout *********/
        MigLayout migLayout = new MigLayout("filly","0[]0","[][top][]");
        mainPanel.setLayout(migLayout);
        mainPanel.add(headersPanel,"span");
        mainPanel.add(menuPanel,"cell 0 1");
        mainPanel.add(bodiesPanel," cell 0 1,span");
        mainPanel.add(footersPanel,"cell 0 2");
        mainEventListeners();
        createLayout(mainPanel);
        /********* Set this **********/
        setSize(new Dimension(1920,1080));
        /********* Set this **********/
        setBackground(new Color(0x27A0FF));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btnDashboard.setEnabled(false);
    }
    private  void createLayout(JComponent... args){
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(groupLayout);
        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        GroupLayout.ParallelGroup vGroup = groupLayout.createParallelGroup();
        hGroup.addComponent(args[0]);
        vGroup.addComponent(args[0]);
        groupLayout.setHorizontalGroup(hGroup);
        groupLayout.setVerticalGroup(vGroup);
    }
    private void menuPanel(){
        /********************Menu**********************/
        btnDashboard = new JButton("Tableau de bord");
        btnEtudiant = new JButton("Etudiants");
        btnMatieres = new JButton("Matieres");
        btnNotes = new JButton("Notes");
        btnBulletin = new JButton("Bulletin");
        /************** sous - menu -------------------*/

        /***************** set btn config ******************/
        btnDashboard.setFont(new Font("Chilanka",Font.PLAIN,16));
        btnEtudiant.setFont(new Font("Chilanka",Font.PLAIN,16));
        btnMatieres.setFont(new Font("Chilanka",Font.PLAIN,16));
        btnNotes.setFont(new Font("Chilanka",Font.PLAIN,16));
        btnBulletin .setFont(new Font("Chilanka",Font.PLAIN,16));
        /**********************set layout and Add to menuPanel *****************/
        menuPanel.setLayout(new MigLayout("flowy","[center]",""));
        menuPanel.add(btnDashboard,"w 200!");
        menuPanel.add(btnEtudiant,"w 200!");
        menuPanel.add(btnMatieres,"w 200!");
        menuPanel.add(btnNotes,"w 200!");
        menuPanel.add(btnBulletin,"w 200!");
    }
    private void mainEventListeners(){
        btnDashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnDashboard.setEnabled(false);
                btnEtudiant.setEnabled(true);
                btnMatieres.setEnabled(true);
                btnNotes.setEnabled(true);
                btnBulletin.setEnabled(true);
                super.mouseClicked(e);
                try {
                    bodiesPanel.showForm(new DashboardUI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnEtudiant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDashboard.setEnabled(true);
                btnEtudiant.setEnabled(false);
                btnMatieres.setEnabled(true);
                btnNotes.setEnabled(true);
                btnBulletin.setEnabled(true);
                try {
                    bodiesPanel.showForm(new EtudiantUI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnMatieres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDashboard.setEnabled(true);
                btnEtudiant.setEnabled(true);
                btnMatieres.setEnabled(false);
                btnNotes.setEnabled(true);
                btnBulletin.setEnabled(true);
                try {
                    bodiesPanel.showForm(new MatieresUI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnNotes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnDashboard.setEnabled(true);
                btnEtudiant.setEnabled(true);
                btnMatieres.setEnabled(true);
                btnNotes.setEnabled(false);
                btnBulletin.setEnabled(true);
                super.mouseClicked(e);
                try {
                    bodiesPanel.showForm(new NotesUI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnBulletin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDashboard.setEnabled(true);
                btnEtudiant.setEnabled(true);
                btnMatieres.setEnabled(true);
                btnNotes.setEnabled(true);
                btnBulletin.setEnabled(false);
                try {
                    bodiesPanel.showForm(new BulletinUI());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
