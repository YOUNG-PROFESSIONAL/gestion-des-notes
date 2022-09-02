package main.java.tp.eni.gsc.ui.bodies.contents.notes;

import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.NIVEAU;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.Matiere;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.MatieresService;
import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class NotesUI extends JTabbedPane{
    JTable t1 = new JTable();
    JScrollPane sp1 = new JScrollPane();
    DefaultTableModel tm1 = new DefaultTableModel();
    JPanel p1= new JPanel();
    NotesService service = new NotesService();
    MatieresService matieresService = new MatieresService();
    MigLayout noteLayout = new MigLayout("fillx","[]","[][]");
    /************ Form **************/
    JLabel num,nom,note,id;
    JTextField fNum,fNom,fNote;
    JButton editBtn,saveBtn;
    JPanel formPanel = new JPanel();
    String defaultMatiereId;
    String defaultMatiere;

    public NotesUI() throws IOException {
        initUI();
    }
    private  void initUI() throws IOException {
        /********** Init component*********/
     /*************set Components values ************/
        t1.setModel(tm1);
        /************* init components tab *********/

        /********* set Components *********/
        sp1.setViewportView(t1);
        /************* set Layout *************/

        /********* Init matiere *********/
        getMatiere();
        /******** JTabbedPane components **************/
        initTable1Row();
        /*********Form ************/
        initNotesForm();
        /****** Evenements *********/
        eventsHandler();
        /********** setting JtabbedPane *********/
        Title.setMainTitle("Gérer les notes " + defaultMatiere);
    }
    public  void  initTable1Row() throws IOException {
        MigLayout layout = new MigLayout("filly","[]","[][]");
        p1.setLayout(layout);
        p1.add(sp1,"w 1200, wrap");
        add("L1",p1);
        tm1.setColumnIdentifiers(new Object[]{"Numéro","N°Etudiant","Nom et Prénom","Note sur 20","ID"});
        t1.setModel(tm1);
        /**** set table column ******/
        t1.getColumnModel().setColumnMargin(10);
        t1.getColumnModel().getColumn(0).setMaxWidth(100);
        t1.getColumnModel().getColumn(1).setMinWidth(200);
        t1.getColumnModel().getColumn(1).setMaxWidth(200);
        t1.getColumnModel().getColumn(2).setPreferredWidth(300);
        t1.getColumnModel().getColumn(3).setMinWidth(100);
        t1.getColumnModel().getColumn(3).setMaxWidth(100);
        // Hide table column four 04
        t1.removeColumn(t1.getColumnModel().getColumn(4));

        int i = 1;
        for (Notes note: service.getAllNotes(1,defaultMatiereId)) {
            tm1.addRow(new Object[]{
                    i++,
                    note.getNoteStudent().getPersonMatricule(),
                    note.getNoteStudent().getPersonName(),
                    note.getNote(),
                    note.getNoteId()});
        }
    }
    private void eventsHandler(){
        t1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               DefaultTableModel model = (DefaultTableModel) t1.getModel();
               fNum.setText((String) model.getValueAt(t1.getSelectedRow(),1));
               fNom.setText((String) model.getValueAt(t1.getSelectedRow(),2));
               fNote.setText((String) model.getValueAt(t1.getSelectedRow(),3));
               id.setText((String) model.getValueAt(t1.getSelectedRow(),4));
               System.out.println(id);
            }
        });
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                revalidate();
                repaint();
            }
        });
    }
    private  void initNotesForm(){
        num = new JLabel("N° Etudiant ");
        nom = new JLabel("Nom et Prénom ");
        note = new JLabel("Note ");
        fNum = new JTextField();
        fNom = new JTextField();
        fNote = new JTextField();

        editBtn = new JButton("Modifier");
        saveBtn = new JButton("Enregistrer");
        JPanel btnPanel = new JPanel();
        MigLayout btnLayout = new MigLayout("filly","[]","[][]");
        btnPanel.setLayout(btnLayout);
        btnPanel.add(editBtn,"wrap, w 200!");
        btnPanel.add(saveBtn," w 200!");

        MigLayout layout = new MigLayout("fillx","[][100!][][400!][][100!][200!]","[]");
        formPanel.setLayout(layout);

        formPanel.setBackground(new Color(0xDEDEDE));
        formPanel.add(num,"span 1");
        formPanel.add(fNum,"span 1, w 100!,h 30!");
        formPanel.add(nom, "span 1");
        formPanel.add(fNom,"span 1,w 390!,h 30!");
        formPanel.add(note, "span 1");
        formPanel.add(fNote,"span 1,w 100!,h 30!,");
        formPanel.add(btnPanel,"span 1");
        p1.add(formPanel,"span");
        disableBtnOnInit();
    }
    private  void disableBtnOnInit(){
        fNum.setEnabled(false);
        fNom.setEnabled(false);
        fNote.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
    }
    private void getMatiere() throws IOException {
        JTable table = new JTable();
        table.setBackground(new Color(0x99D3FF));
        JScrollPane scrollPane = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Liste des matières","ID"});
        for (Matiere mat: matieresService.getAllMatieres("",null)) {
            defaultMatiereId = mat.getMatiereId();
            defaultMatiere = mat.getMatiereLibelle();
            tableModel.addRow(new Object[]{
                    mat.getMatiereLibelle(),mat.getMatiereId()});
        }
        table.setModel(tableModel);
        table.getColumnModel().setColumnMargin(20);
        table.removeColumn(table.getColumnModel().getColumn(1));
        scrollPane.setViewportView(table);
        p1.add(scrollPane,"dock left");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) t1.getModel();
                String matId = table.getModel().getValueAt(table.getSelectedRow(),1).toString();
                Title.setMainTitle("Gérer les notes " +
                        table.getModel().getValueAt(table.getSelectedRow(),0).toString() );
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);

                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(1,matId)) {
                        tm1.addRow(new Object[]{
                                i++,
                                note.getNoteStudent().getPersonMatricule(),
                                note.getNoteStudent().getPersonName(),
                                note.getNote(),
                                note.getNoteId()});
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
