package main.java.tp.eni.gsc.ui.bodies.contents.bulletin;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.Etudiant;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.EtudiantService;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.Matiere;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.MatieresService;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.Notes;
import main.java.tp.eni.gsc.ui.bodies.contents.notes.NotesService;
import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;
import org.jfree.chart.ChartColor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class BulletinUI extends JPanel{
    int level = 1;
    JTable table = new JTable();
    JTable bulletin = new JTable();
    JPanel leftPanel = new JPanel(new MigLayout("","[]","[][]"));
    /*********** Filter *******/
    JLabel niveau;
    JButton btnL1,btnL2,btnL3,btnM1,btnM2;
    EtudiantService etService = new EtudiantService();
    BulletinService bService = new BulletinService();
    /*********** etudiant ***********/
    JLabel fNiveau, fNom , fNumEt;

    public BulletinUI() throws IOException {
        initUI();
    }
    private void initUI() throws IOException {
        filterNiveau();
        setLayout(new MigLayout("","[][]","[]"));
        initEtudiantTable();
        add(leftPanel,"west");
        getEtudiantBulletinTable(null);
        eventsHandler();
    }
    private void initEtudiantTable() throws IOException {
        if (level == 1) btnL1.setEnabled(false);
        JPanel panel = new JPanel(new MigLayout());
        JScrollPane pane = new JScrollPane();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID","Numéro","Nom et Prénom"});
        table.setModel(model);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.removeColumn(table.getColumnModel().getColumn(0));
        pane.setViewportView(table);
        panel.add(pane);
        leftPanel.add(panel,"span 1,b");
        for(Etudiant et : etService.getAllStudent("",level)){
            model.addRow(new Object[]{et.getPersonId(),et.getPersonMatricule(),et.getPersonName()});
        }
        if(level< 4) Title.setMainTitle("Bulletin L" + level);
    }
    private void initEtudiantTableParNiveau(int level) throws IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int count = model.getRowCount();
        for(int i=0; i<count;i++ ) model.removeRow(0);
        for(Etudiant et : etService.getAllStudent("",level)){
            model.addRow(new Object[]{et.getPersonId(),et.getPersonMatricule(),et.getPersonName()});
        }
        if(level< 4) Title.setMainTitle("Bulletin L" + level);
        if(level == 4) Title.setMainTitle("Bulletin M1");
        if(level == 5) Title.setMainTitle("Bulletin M5");
    }
    private void getEtudiantBulletinTable(String id){
        JPanel titlePanel = new JPanel(new MigLayout("fill","[450!]","[]"));
        JPanel panel = new JPanel(new MigLayout("fillx","[]","[][][][]"));
        JPanel numPanel = new JPanel(new MigLayout("fillx","[100!][]","[]"));
        JPanel nomPanel = new JPanel(new MigLayout("fillx","[100!][][][]","[]"));
        JLabel title = new JLabel("Bulletin des notes");
        titlePanel.add(title,"c");
        JLabel numEt = new JLabel("N°ETUDIANT ");
        fNumEt = new JLabel("............................ ");
        numPanel.add(numEt,"");
        numPanel.add(fNumEt,"");
        JLabel nom = new JLabel("NOM ");
        fNom = new JLabel("............................ ");
        JLabel niveau = new JLabel("NIVEAU ");
        fNiveau = new JLabel(".......... ");
        nomPanel.add(nom,"");
        nomPanel.add(fNom,"w 150!");
        nomPanel.add(niveau,"");
        nomPanel.add(fNiveau,"");
        JScrollPane pane = new JScrollPane();
        pane.setBackground(new Color(0xFFFFFF));

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Désignation","Coefficient","Note", "Note Ponderée"});
        bulletin.setModel(model);
        /****** Center table cell *************/
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        bulletin.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        /*************************************/
        if(id == null) bulletin.setEnabled(false);
        else bulletin.setEnabled(true);
        pane.setViewportView(bulletin);
        panel.add(titlePanel,",wrap");
        panel.add(numPanel,"wrap");
        panel.add(nomPanel,"wrap");
        panel.add(pane,"span");
        panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(BasicStroke.CAP_ROUND)));
        add(panel,"span 1,b,,gapleft 100");

    }
    private  void filterNiveau(){
        btnL1 = new JButton("L1");
        btnL2 = new JButton("L2");
        btnL3 = new JButton("L3");
        btnM1 = new JButton("M1");
        btnM2 = new JButton("M2");
        niveau = new JLabel("Niveau : ");
        JPanel panel = new JPanel(new MigLayout("","[][][][][][]","[]"));
        panel.setOpaque(true);
        panel.add(niveau,"");
        panel.add(btnL1,"w 70!");
        panel.add(btnL2,"w 70!");
        panel.add(btnL3,"w 70!");
        panel.add(btnM1,"w 70!");
        panel.add(btnM2,"w 70!");
        leftPanel.add(panel,"span1,top,wrap");

        btnL1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 1;
                btnL1.setEnabled(false);
                btnL2.setEnabled(true);
                btnL3.setEnabled(true);
                btnM1.setEnabled(true);
                btnM2.setEnabled(true);
                try {
                    fNiveau.setText(".............. ");
                    fNom.setText("............................ ");
                    fNumEt.setText("............................ ");
                    DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                    int count = bModel.getRowCount();
                    for(int i=0; i<count;i++ ) bModel.removeRow(0);
                    initEtudiantTableParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnL2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 2;
                btnL1.setEnabled(true);
                btnL2.setEnabled(false);
                btnL3.setEnabled(true);
                btnM1.setEnabled(true);
                btnM2.setEnabled(true);
                try {
                    fNiveau.setText(".............. ");
                    fNom.setText("............................ ");
                    fNumEt.setText("............................ ");
                    DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                    int count = bModel.getRowCount();
                    for(int i=0; i<count;i++ ) bModel.removeRow(0);
                    initEtudiantTableParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnL3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 3;
                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(false);
                btnM1.setEnabled(true);
                btnM2.setEnabled(true);
                try {
                    fNiveau.setText(".............. ");
                    fNom.setText("............................ ");
                    fNumEt.setText("............................ ");
                    DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                    int count = bModel.getRowCount();
                    for(int i=0; i<count;i++ ) bModel.removeRow(0);
                    initEtudiantTableParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnM1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 4;
                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(true);
                btnM1.setEnabled(false);
                btnM2.setEnabled(true);
                try {
                    fNiveau.setText(".............. ");
                    fNom.setText("............................ ");
                    fNumEt.setText("............................ ");
                    DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                    int count = bModel.getRowCount();
                    for(int i=0; i<count;i++ ) bModel.removeRow(0);
                    initEtudiantTableParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnM2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = 5;
                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(true);
                btnM1.setEnabled(true);
                btnM2.setEnabled(false);
                try {
                    fNiveau.setText(".............. ");
                    fNom.setText("............................ ");
                    fNumEt.setText("............................ ");
                    DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                    int count = bModel.getRowCount();
                    for(int i=0; i<count;i++ ) bModel.removeRow(0);
                    initEtudiantTableParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    private void   eventsHandler(){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable t = (JTable) e.getSource();
                String id = t.getModel().getValueAt(t.getSelectedRow(),0).toString();
                DefaultTableModel bModel = (DefaultTableModel) bulletin.getModel();
                int count = bModel.getRowCount();
                for(int i=0; i<count;i++ ) bModel.removeRow(0);
                try {
                 for(Bulletin b : bService.getBulletin(null,id)){
                     for(Notes note : b.getbNotes())
                         bModel.addRow(new Object[]{
                                 note.getNoteMatiere().getMatiereLibelle(),
                                 note.getNoteMatiere().getMatiereCoeff(),
                                 note.getNote(),
                                 note.getNote()*note.getNoteMatiere().getMatiereCoeff()});
                     bModel.addRow(new Object[]{"","","MOYENNE",String.format("%.2f",b.moyenne())});
                     bModel.addRow(new Object[]{"","","Observation",b.getbObservation()});
                     fNumEt.setText(b.getbStudent().getPersonMatricule());
                     fNom.setText(b.getbStudent().getPersonName());
                     if(b.getbStudent().getStudentLevel().contentEquals("1")) fNiveau.setText("L1");
                     if(b.getbStudent().getStudentLevel().contentEquals("2")) fNiveau.setText("L2");
                     if(b.getbStudent().getStudentLevel().contentEquals("3")) fNiveau.setText("L3");
                     if(b.getbStudent().getStudentLevel().contentEquals("4")) fNiveau.setText("M1");
                     if(b.getbStudent().getStudentLevel().contentEquals("5")) fNiveau.setText("M2");
                 }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
