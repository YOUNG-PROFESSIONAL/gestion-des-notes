package main.java.tp.eni.gsc.ui.bodies.contents.matieres;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.EtudiantService;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.NIVEAU;
import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MatieresUI extends JPanel{
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    MatieresService service;

    /**************FORM*************/
    JLabel code, libelle,coef;
    JTextField fId,fCode,fLibelle, fCoef;
    JButton addBtn,editBtn,saveBtn,deleteBtn,resetBtn;
    JPanel formPanel;

    public MatieresUI() throws IOException {
        initUI();
    }
    private  void initUI() throws IOException {
        /********** Init component*********/
        tableModel = new DefaultTableModel();
        table = new JTable();
        scrollPane = new JScrollPane();
        /********* Init Form Matiere **********/
        formPanel = new JPanel();
        initMatiereForm();
        /*************set Components values ************/
        initTableRow();
        /************* init components *********/
        /********* set Components *********/
        scrollPane.setViewportView(table);
        setPreferredSize(new Dimension(1200,400));
        /************* set Layout *************/
        MigLayout layout = new MigLayout("fillx","[]","[][]");
        setLayout(layout);
        add(scrollPane,"span 1,w 1000!,wrap");
        add(formPanel,"span ,w 1000!");
        Title.setMainTitle("Gérer les Matieres");
        /************* events ***********************/
        btnActionEvent();
    }
    public  void  initTableRow() throws IOException {
        tableModel.setColumnIdentifiers(new Object[]{"Numero","Code matiere","Libellé","Coefficient","ID"});
        table.setModel(tableModel);
        /**** set table column ******/
        table.getColumnModel().setColumnMargin(10);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(100);

        // Hide table column four 04
        table.removeColumn(table.getColumnModel().getColumn(4));
        service = new MatieresService();
        int i = 1;
        for (Matiere mat: service.getAllMatieres("",null)) {
            tableModel.addRow(new Object[]{
                    i++, mat.getMatiereCode(),mat.getMatiereLibelle(),mat.getMatiereCoeff(),mat.getMatiereId()});
        }
    }
    private  void initMatiereForm(){
        code = new JLabel("Code matière");
        libelle = new JLabel("Libellé");
        coef = new JLabel("Coefficient");
        fId = new JTextField();
        fCode = new JTextField();
        fLibelle = new JTextField();
        fCoef = new JTextField();

        addBtn = new JButton("Ajouter");
        editBtn = new JButton("Modifier");
        saveBtn = new JButton("Enregistrer");
        deleteBtn = new JButton("Supprimer");
        resetBtn = new JButton("Annuler");

        MigLayout layout = new MigLayout("fillx","[][100!][][400!][][100!]","[]");
        formPanel.setLayout(layout);
        formPanel.setBackground(new Color(0xDEDEDE));
        formPanel.add(code,"span 1");
        formPanel.add(fCode,"span 1, w 100!,h 30!");
        formPanel.add(libelle, "span 1");
        formPanel.add(fLibelle,"span 1,w 400!,h 30!");
        formPanel.add(coef, "span 1");
        formPanel.add(fCoef,"span 1,w 100!,h 30!, wrap");

        MigLayout panelLayout = new MigLayout("fillx","0[]20[]20[]20[]","[][]10[]");
        JPanel btnPanel = new JPanel(panelLayout);
        btnPanel.add(addBtn, "w 120!");
        btnPanel.add(editBtn, "w 120!");
        btnPanel.add(saveBtn, "w 120!");
        btnPanel.add(deleteBtn, "w 120!");
        btnPanel.add(resetBtn, "w 120!");
        formPanel.add(btnPanel,"cell 1 2 1 1");
        disableBtnOnInit();
    }
    private  void disableBtnOnInit(){
        fCode.setEnabled(false);
        fLibelle.setEnabled(false);
        fCoef.setEnabled(false);
        addBtn.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        fId.setText(""); // Set Id empty
        fCode.setText("");// Set nom field empty
        fLibelle.setText("");// Set nom field empty
        fCoef.setText("");// Set nom field empty
    }
    private  void disableBtnOnAdd(){
        fCode.setEnabled(true);
        fLibelle.setEnabled(true);
        fCoef.setEnabled(true);
        addBtn.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(false);
        resetBtn.setEnabled(true);
        fId.setText(""); // Set Id empty
        fCode.setText("");// Set nom field empty
        fLibelle.setText("");// Set nom field empty
        fCoef.setText("");// Set nom field empty
    }
    private  void disableBtnOnEdit(){
        fCode.setEnabled(true);
        fLibelle.setEnabled(true);
        fCoef.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        resetBtn.setEnabled(true);
    }
    private  void disableBtnOnSelect(){
        fCode.setEnabled(false);
        fLibelle.setEnabled(false);
        fCoef.setEnabled(false);
        editBtn.setEnabled(true);
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        resetBtn.setEnabled(false);
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
                Matiere mat = new Matiere();
                mat.setMatiereId(fId.getText());
                mat.setMatiereCode(fCode.getText());
                mat.setMatiereLibelle(fLibelle.getText());
                try{
                    mat.setMatiereCoeff(Integer.valueOf(fCoef.getText()));
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Veuillez saisir un nombre entier!");
                }
                if (mat.getMatiereId().isEmpty()) {
                    try {
                        service.addMatiere(mat);
                        int count = model.getRowCount();
                        for(int x = 0; x< count ; x++) model.removeRow(0);
                        initTableRow();
                        disableBtnOnInit();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Une Erreur c'était produit!");
                    }}
                else {
                    try {
                        service.updateMatiere(mat);
                        int count = model.getRowCount();
                        for(int x = 0; x< count ; x++) model.removeRow(0);
                        initTableRow();
                        disableBtnOnInit();
                    }  catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Une Erreur c'était produit!");
                    }
                }
            }});
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Matiere mat = new Matiere();
                mat.setMatiereId(fId.getText());
                mat.setMatiereCode(fCode.getText());
                mat.setMatiereLibelle(fLibelle.getText());
                try{
                    mat.setMatiereCoeff(Integer.valueOf(fCoef.getText()));
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Veuillez saisir un nombre entier!");
                }
                try {
                    service.removeMatiere(mat);
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
                if(fId.getText().isEmpty()){
                    fCode.setText("");
                    fCoef.setText("");
                    fLibelle.setText("");
                }
                disableBtnOnInit();

            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                fId.setText(table.getModel().getValueAt(table.getSelectedRow(),4).toString());
                fCode.setText(model.getValueAt(table.getSelectedRow(),1).toString());
                fLibelle.setText(model.getValueAt(table.getSelectedRow(),2).toString());
                fCoef.setText(model.getValueAt(table.getSelectedRow(),3).toString());
                /**************** Get value from hidden table*******************/
                //System.out.println("ID : " + fId.getText() );
                //table.getModel().getValueAt(table.getSelectedRow(),1);
                disableBtnOnSelect();
            }
        });
    }
}
