package main.java.tp.eni.gsc.ui.bodies.contents.notes;

import main.java.tp.eni.gsc.ui.bodies.BodiesPanel;
import main.java.tp.eni.gsc.ui.bodies.contents.etudiant.NIVEAU;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.Matiere;
import main.java.tp.eni.gsc.ui.bodies.contents.matieres.MatieresService;
import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class NotesUI extends JPanel{
    JTable table = new JTable();
    JPanel panelP= new JPanel(new MigLayout());
    NotesService service = new NotesService();
    MatieresService matieresService = new MatieresService();
    JTable tableMatiere = new JTable();
    /************ Form **************/
    JLabel num,nom,note,id;
    JTextField fNum,fNom,fNote;
    JButton editBtn,saveBtn;
    JPanel formPanel = new JPanel();
    String defaultMatiereId = "";
    String defaultMatiere ="";
    Integer niv = 1;
    /*********** Filter *******/
    JLabel niveau;
    JButton btnL1,btnL2,btnL3,btnM1,btnM2;

    /************ Layout **************/
    MigLayout layout = new MigLayout();

    public NotesUI() throws IOException {
        initUI();
    }
    private  void initUI() throws IOException {
        /********** Init component*********/
        fNum = new JTextField();
        fNom = new JTextField();
        fNote = new JTextField();
        id = new JLabel();
        /************* set Layout *************/
        setLayout(layout);
        /********* Init matiere *********/
        getMatiere();
        /******** JTabbedPane components **************/
        initTable1Row();
        /*********Form ************/
        initNotesForm();
        /****** Evenements *********/
        eventsHandler();
        filterNiveau();
        /********** setting JtabbedPane *********/
        Title.setMainTitle("Notes L1 (" + defaultMatiere+")");
        btnL1.setEnabled(false);
    }
    public  void  initTable1Row() throws IOException {
        JScrollPane sp1 = new JScrollPane();
        DefaultTableModel tm1 = new DefaultTableModel();
        JPanel panel= new JPanel(new MigLayout("","[]","[]"));
        /*************set Components values ************/
        table.setModel(tm1);
        /************* init components tab *********/

        /********* set Components *********/
        sp1.setViewportView(table);
        panel.add(sp1,"w 870!");
        tm1.setColumnIdentifiers(new Object[]{"Numéro","N°Etudiant","Nom et Prénom","Note sur 20","ID"});
        table.setModel(tm1);
        /**** set table column ******/
        table.getColumnModel().setColumnMargin(10);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        // Hide table column four 04
        table.removeColumn(table.getColumnModel().getColumn(4));

        int i = 1;
        for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
            tm1.addRow(new Object[]{
                    i++,
                    note.getNoteStudent().getPersonMatricule(),
                    note.getNoteStudent().getPersonName(),
                    note.getNote(),
                    note.getNoteId()});
        }
        add(panel,"dock center,span");
        /****** Center table cell *************/
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        /*************************************/
    }
    private void eventsHandler (){
        editBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               saveBtn.setEnabled(true);
               editBtn.setEnabled(false);
                fNote.setEnabled(true);
            }
        });

        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Notes notes = new Notes();
                notes.setNoteId(id.getText());
                notes.setNote(Double.parseDouble(fNote.getText()));

                try {
                    service.updateNotes(notes);
                    saveBtn.setEnabled(false);
                    fNote.setEnabled(false);
                    int count = model.getRowCount();
                    for(int x = 0; x< count ; x++) model.removeRow(0);
                    int i = 1;
                    defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                    for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                        model.addRow(new Object[]{
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

               DefaultTableModel model = (DefaultTableModel) table.getModel();
               fNum.setText(model.getValueAt(table.getSelectedRow(),1).toString());
               fNom.setText(model.getValueAt(table.getSelectedRow(),2).toString());
               fNote.setText(model.getValueAt(table.getSelectedRow(),3).toString());
               id.setText(model.getValueAt(table.getSelectedRow(),4).toString());
               editBtn.setEnabled(true);
               saveBtn.setEnabled(false);
               fNote.setEnabled(false);

            }
        });

    }
    private  void initNotesForm(JComponent... args){
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
        btnPanel.add(editBtn,"wrap, w 150!");
        btnPanel.add(saveBtn," w 150!");

        MigLayout layout = new MigLayout("fillx","[][100!][][][][60!][180!]","[]");
        formPanel.setLayout(layout);

        formPanel.setBackground(new Color(0xDEDEDE));
        formPanel.add(num,"span 1");
        formPanel.add(fNum,"span 1, w 80!,h 30!");
        formPanel.add(nom, "span 1");
        formPanel.add(fNom,"span 1,w 250!,h 30!");
        formPanel.add(note, "span 1");
        formPanel.add(fNote,"span 1,w 60!,h 30!,");
        formPanel.add(btnPanel,"span 1");
        panelP.add(formPanel,"span");
        add(panelP,"dock south");
        disableBtnOnInit();
    }
    private  void disableBtnOnInit(){
        fNum.setEnabled(false);
        fNom.setEnabled(false);
        fNote.setEnabled(false);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
    }
    private void getMatiere(JComponent... args) throws IOException {
        JPanel panel= new JPanel(new MigLayout());
        tableMatiere.setBackground(new Color(0x99D3FF));
        JScrollPane scrollPane = new JScrollPane();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Liste des matières","ID"});
        for (Matiere mat: matieresService.getAllMatieres(defaultMatiereId,null)) {
            defaultMatiereId = mat.getMatiereId();
            defaultMatiere = mat.getMatiereLibelle();
            tableModel.addRow(new Object[]{
                    mat.getMatiereLibelle(),mat.getMatiereId()});
        }
        tableMatiere.setModel(tableModel);
        tableMatiere.getColumnModel().setColumnMargin(20);
        tableMatiere.setRowHeight(30);
        tableMatiere.setFont(new Font("iceberg",Font.ITALIC,16));
        tableMatiere.removeColumn(tableMatiere.getColumnModel().getColumn(1));
        scrollPane.setViewportView(tableMatiere);
        panel.add(scrollPane,"dock center");

        tableMatiere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fNum.setText("");
                fNom.setText("");
                fNote.setText("");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String matId = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                tableMatiere.setSelectionBackground(new Color(0xffffff));
                String level = "L1";
                if(niv == 1) level = "L1";
                if(niv == 2) level = "L2";
                if(niv == 3) level = "L3";
                if(niv == 4) level = "M1";
                if(niv == 5) level = "M2";
                Title.setMainTitle("Notes " + level + " ("+
                        tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);

                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(niv,matId)) {
                        model.addRow(new Object[]{
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
        add(panel,"dock west, w 200!");
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
      panel.add(btnL1,"w 100!");
      panel.add(btnL2,"w 100!");
      panel.add(btnL3,"w 100!");
      panel.add(btnM1,"w 100!");
      panel.add(btnM2,"w 100!");
      add(panel,"dock north");

      btnL1.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              niv = 1;
              String level = "L1";
              if(niv == 1) level = "L1";
              if(niv == 2) level = "L2";
              if(niv == 3) level = "L3";
              if(niv == 4) level = "M1";
              if(niv == 5) level = "M2";

             btnL1.setEnabled(false);
             btnL2.setEnabled(true);
             btnL3.setEnabled(true);
             btnM1.setEnabled(true);
             btnM2.setEnabled(true);
              fNum.setText("");
              fNom.setText("");
              fNote.setText("");
              DefaultTableModel model = (DefaultTableModel) table.getModel();
              int count = model.getRowCount();

              for(int i=0; i<count;i++)  model.removeRow(0);
              defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
              int i = 1;
              try {
                  for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                      model.addRow(new Object[]{
                              i++,
                              note.getNoteStudent().getPersonMatricule(),
                              note.getNoteStudent().getPersonName(),
                              note.getNote(),
                              note.getNoteId()});
                  }
              } catch (IOException ex) {
                  ex.printStackTrace();
              }
              Title.setMainTitle("Notes " + level + " ("+
                      tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
          }
      });
        btnL2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                niv = 2;
                String level = "L1";
                if(niv == 1) level = "L1";
                if(niv == 2) level = "L2";
                if(niv == 3) level = "L3";
                if(niv == 4) level = "M1";
                if(niv == 5) level = "M2";

                btnL1.setEnabled(true);
                btnL2.setEnabled(false);
                btnL3.setEnabled(true);
                btnM1.setEnabled(true);
                btnM2.setEnabled(true);
                fNum.setText("");
                fNom.setText("");
                fNote.setText("");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);
                defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                        model.addRow(new Object[]{
                                i++,
                                note.getNoteStudent().getPersonMatricule(),
                                note.getNoteStudent().getPersonName(),
                                note.getNote(),
                                note.getNoteId()});
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Title.setMainTitle("Notes " + level + " ("+
                        tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
            }
        });
        btnL3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                niv = 3;
                String level = "L1";
                if(niv == 1) level = "L1";
                if(niv == 2) level = "L2";
                if(niv == 3) level = "L3";
                if(niv == 4) level = "M1";
                if(niv == 5) level = "M2";

                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(false);
                btnM1.setEnabled(true);
                btnM2.setEnabled(true);
                fNum.setText("");
                fNom.setText("");
                fNote.setText("");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);
                defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                        model.addRow(new Object[]{
                                i++,
                                note.getNoteStudent().getPersonMatricule(),
                                note.getNoteStudent().getPersonName(),
                                note.getNote(),
                                note.getNoteId()});
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Title.setMainTitle("Notes " + level + " ("+
                        tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
            }
        });
        btnM1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                niv = 4;
                String level = "L1";
                if(niv == 1) level = "L1";
                if(niv == 2) level = "L2";
                if(niv == 3) level = "L3";
                if(niv == 4) level = "M1";
                if(niv == 5) level = "M2";

                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(true);
                btnM1.setEnabled(false);
                btnM2.setEnabled(true);
                fNum.setText("");
                fNom.setText("");
                fNote.setText("");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);
                defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                        model.addRow(new Object[]{
                                i++,
                                note.getNoteStudent().getPersonMatricule(),
                                note.getNoteStudent().getPersonName(),
                                note.getNote(),
                                note.getNoteId()});
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Title.setMainTitle("Notes " + level + " ("+
                        tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
            }
        });
        btnM2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                niv = 5;
                String level = "L1";
                if(niv == 1) level = "L1";
                if(niv == 2) level = "L2";
                if(niv == 3) level = "L3";
                if(niv == 4) level = "M1";
                if(niv == 5) level = "M2";

                btnL1.setEnabled(true);
                btnL2.setEnabled(true);
                btnL3.setEnabled(true);
                btnM1.setEnabled(true);
                btnM2.setEnabled(false);
                fNum.setText("");
                fNom.setText("");
                fNote.setText("");
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int count = model.getRowCount();
                for(int i=0; i<count;i++)  model.removeRow(0);
                defaultMatiereId  = tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),1).toString();
                int i = 1;
                try {
                    for (Notes note: service.getAllNotes(niv,defaultMatiereId)) {
                        model.addRow(new Object[]{
                                i++,
                                note.getNoteStudent().getPersonMatricule(),
                                note.getNoteStudent().getPersonName(),
                                note.getNote(),
                                note.getNoteId()});
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Title.setMainTitle("Notes " + level + " ("+
                        tableMatiere.getModel().getValueAt(tableMatiere.getSelectedRow(),0).toString()+")" );
            }
        });

    }
}
