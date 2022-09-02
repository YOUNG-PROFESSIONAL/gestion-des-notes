package main.java.tp.eni.gsc.ui.bodies.contents.etudiant;

import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EtudiantUI extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    JPanel formPanel;
    EtudiantService  service;
    /**************FORM*************/
    JLabel nom, niveau;
    JTextField fNom,fId,fNum, fsearch;
    JComboBox fNiveau;
    JComboBox<String> niveauFilter;
    JButton addBtn,editBtn,saveBtn,deleteBtn,resetBtn, searchBtn;

    public EtudiantUI() throws IOException {
        Title.setMainTitle("Gérer les étudiants");
        initUI();
    }
    private  void initUI() throws IOException {
        /********** Init component*********/
        tableModel = new DefaultTableModel();
        table = new JTable();
        scrollPane = new JScrollPane();
        formPanel = new JPanel();
        fsearch = new JTextField("");
        searchBtn = new JButton(new ImageIcon("src/main/java/resources/static/icon/search.png"));
        niveauFilter = new JComboBox<>(){};
        /************ Etudian form ************/
        nom = new JLabel("Nom et Prénom");
        niveau = new JLabel("NIVEAU ");
        fNom = new JTextField();
        fId = new JTextField();
        fNum = new JTextField();
        fNiveau = new JComboBox();
        /*************set Components values ************/
        initTableRow();
        /************* init search components *********/
        filterComponent();
        initEtudiantFilter();
        /************* init components form *********/
        initEtudiantForm();
        disableBtnOnInit();
        btnActionEvent();
        /********* set Components *********/
        scrollPane.setViewportView(table);
        setPreferredSize(new Dimension(1200,300));
        /************* set Layout *************/
        MigLayout layout = new MigLayout("flowy","[]","[][][]");
        setLayout(layout);
        add(scrollPane,"span ,w 1000!");
        add(formPanel,"span ,w 1000!");

    }
    public  void  initTableRow() throws IOException {
        tableModel.setColumnIdentifiers(new Object[]{"Numero","N°Etudiant","Nom et Prénom","NIVEAU","ID"});
        table.setModel(tableModel);
        /**** set table column ******/
        table.getColumnModel().setColumnMargin(10);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(100);

        // Hide table column four 04
        table.removeColumn(table.getColumnModel().getColumn(4));
        service = new EtudiantService();
        int i = 1;
        for (Etudiant et: service.getAllStudent("",0)) {
            tableModel.addRow(new Object[]{i++, et.getPersonMatricule(), et.getPersonName(), et.getStudentLevel(),et.getPersonId()});
        }
    }
    private  void initEtudiantForm(){
        fNiveau.addItem(NIVEAU.Select);
        fNiveau.addItem(NIVEAU.L1);
        fNiveau.addItem(NIVEAU.L2);
        fNiveau.addItem(NIVEAU.L3);
        fNiveau.addItem(NIVEAU.M1);
        fNiveau.addItem(NIVEAU.M2);
        addBtn = new JButton("Ajouter");
        editBtn = new JButton("Modifier");
        saveBtn = new JButton("Enregistrer");
        deleteBtn = new JButton("Supprimer");
        resetBtn = new JButton("Annuler");
        searchBtn = new JButton("Trouver");

        MigLayout layout = new MigLayout("fillx","","[][]10[]");
        formPanel.setLayout(layout);
        formPanel.setBackground(new Color(0xDEDEDE));
        formPanel.add(nom,"cell 0 0");
        formPanel.add(fNom,"cell 1 0, w 800!, h 30!");
        formPanel.add(niveau, "cell 0 1");
        formPanel.add(fNiveau,"cell 1 1 ,w 800!,h 30!,wrap");

        MigLayout panelLayout = new MigLayout("fillx","0[]20[]20[]20[]","[][]10[]");
        JPanel btnPanel = new JPanel(panelLayout);
        btnPanel.add(addBtn, "w 120!");
        btnPanel.add(editBtn, "w 120!");
        btnPanel.add(saveBtn, "w 120!");
        btnPanel.add(deleteBtn, "w 120!");
        btnPanel.add(resetBtn, "w 120!");
        formPanel.add(btnPanel,"cell 1 2 1 1");
    }
    private  void disableBtnOnInit(){
        fNom.setEnabled(false);
        fNiveau.setEnabled(false);
        addBtn.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        fId.setText(""); // Set Id empty
        fNom.setText("");// Set nom field empty
        fNum.setText("");// Set nom field empty
        fNiveau.setSelectedIndex(0); // Set Default niveau select
    }
    private  void disableBtnOnAdd(){
        fNom.setEnabled(true);
        fNiveau.setEnabled(true);
        addBtn.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
        resetBtn.setEnabled(true);
    }
    private  void disableBtnOnEdit(){
        fNom.setEnabled(true);
        fNiveau.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        resetBtn.setEnabled(true);
    }
    private  void disableBtnOnSelect(){
        fNom.setEnabled(false);
        fNiveau.setEnabled(false);
        editBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        resetBtn.setEnabled(false);
    }
    private void filterComponent(){
        JPanel searchPanel = new JPanel();
        JPanel filterPanel = new JPanel();
        JPanel niveauPanel = new JPanel();
        JLabel labelNiveau = new JLabel("Niveau");
        niveauFilter.addItem("Tous");
        niveauFilter.addItem("L1");
        niveauFilter.addItem("L2");
        niveauFilter.addItem("L3");
        niveauFilter.addItem("M1");
        niveauFilter.addItem("M2");
        niveauPanel.setLayout(new MigLayout("fillx","[][]","[]"));
        niveauPanel.add(labelNiveau,"span 1,h 25!");
        niveauPanel.add(niveauFilter,"span 1,h 25!,w 100!");

        searchPanel.setLayout(new MigLayout("","[]10[]","[]"));
        searchPanel.add(fsearch,"h 25!, w 500!");
        searchPanel.add(searchBtn,"h 25!,w 100!");

        filterPanel.setLayout(new MigLayout("fillx , debug","[]50[]","[]"));
        filterPanel.add( niveauPanel,"w 200!");
        filterPanel.add(searchPanel,"w 800!");
        add(filterPanel,"fill, w 1000!");
    }
    private void btnActionEvent(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable field and save , cancel btn
                disableBtnOnAdd();
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable field on edit
                disableBtnOnEdit();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Etudiant et = new Etudiant();
                et.setPersonId(fId.getText());
                et.setPersonMatricule(fNum.getText());
                et.setPersonName(fNom.getText());
                et.setStudentLevel(String.valueOf(fNiveau.getSelectedIndex()));
                System.out.println("ID : " + et.getPersonId() );
                if (et.getPersonId().isEmpty()) {
                    try {
                        service.addStudent(et);
                        int count = model.getRowCount();
                        for(int x = 0; x< count ; x++) model.removeRow(0);
                        initTableRow();
                        disableBtnOnInit();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }}
                else {
                    try {
                        service.updateStudent(et);
                        int count = model.getRowCount();
                        for(int x = 0; x< count ; x++) model.removeRow(0);
                        initTableRow();
                        disableBtnOnInit();
                    }  catch (IOException ex) {
                        ex.printStackTrace();
                       }
                }
            }});
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Etudiant et = new Etudiant();
                et.setPersonId(fId.getText());
                et.setPersonMatricule(fNum.getText());
                et.setPersonName(fNom.getText());
                et.setStudentLevel(String.valueOf(fNiveau.getSelectedIndex()));
                    try {
                        service.removeStudent(et);
                        int count = model.getRowCount();
                        for(int x = 0; x< count ; x++) model.removeRow(0);
                        initTableRow();
                        disableBtnOnInit();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            }});
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fNom.setText("");
                fNiveau.setSelectedIndex(0);
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                fId.setText(table.getModel().getValueAt(table.getSelectedRow(),4).toString());
                fNum.setText(model.getValueAt(table.getSelectedRow(),1).toString());
                fNom.setText(model.getValueAt(table.getSelectedRow(),2).toString());

                /**************** Get value from hidden table*******************/
                //System.out.println("ID : " + fId.getText() );
                //table.getModel().getValueAt(table.getSelectedRow(),1);

                if(model.getValueAt(table.getSelectedRow(),3) == ""){
                    fNiveau.setSelectedIndex(0);
                }
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("L1"))
                    fNiveau.setSelectedIndex(1);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("L2"))
                    fNiveau.setSelectedIndex(2);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("L3"))
                    fNiveau.setSelectedIndex(3);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("M1"))
                    fNiveau.setSelectedIndex(4);
                else if(model.getValueAt(table.getSelectedRow(),3).toString().contains("M2"))
                    fNiveau.setSelectedIndex(5);
                disableBtnOnSelect();
            }
        });
    }
    private void initEtudiantFilter(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        searchBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int count = model.getRowCount();
                String key = fsearch.getText();
                for(int i=0; i<count;i++)  model.removeRow(0);
                int i = 1;
                    try {
                        for (Etudiant et : service.getAllStudent(key,niveauFilter.getSelectedIndex()))
                            model.addRow(new Object[]{i++,et.getPersonMatricule(),et.getPersonName(),et.getStudentLevel(),et.getPersonId()});
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }}});
        niveauFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!niveauFilter.getSelectedItem().toString().contentEquals("Tous"))
                    Title.setMainTitle("Gérer les étudiants " + niveauFilter.getSelectedItem());
                else
                    Title.setMainTitle("Gérer les étudiants ");
                fNom.setText(""); fNiveau.setSelectedIndex(niveauFilter.getSelectedIndex());
                fsearch.setText("");
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);
                int i = 1;
                try {
                    for (Etudiant et : service.getAllStudent("",niveauFilter.getSelectedIndex()))
                        model.addRow(new Object[]{i++,et.getPersonMatricule(),et.getPersonName(),et.getStudentLevel(),et.getPersonId()});
                } catch (IOException ex) {
                    ex.printStackTrace();
                }}});
    }
}

