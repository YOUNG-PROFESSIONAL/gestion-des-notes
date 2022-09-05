package main.java.tp.eni.gsc.ui.bodies.contents.dashboard;

import main.java.tp.eni.gsc.ui.headers.title.Title;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardUI extends JPanel {
    JTable table;
    int level = 1;
    JLabel niveau;
    JButton btnL1,btnL2,btnL3,btnM1,btnM2;
    DashBoardService dashBoard = new DashBoardService();
    JPanel leftMainPanel = new JPanel(new MigLayout("filly","[]","[][]"));
    JPanel rightMainPanel = new JPanel(new MigLayout("filly","[]","[][]"));

    /*************************************************/
    int admis = 0 , redouble = 0, exclu = 0,effectif= 0;
    DashBoardChart dashBoardChart = new DashBoardChart(effectif,admis,redouble,exclu);


    public DashboardUI() throws IOException {
        initUI();
    }
    private  void initUI() throws IOException {
        /********** Init component*********/
        setLayout(new MigLayout("","[]10![]","[]"));
        table = new JTable();
        filterNiveau();
        initEtudiantTable();
        add(leftMainPanel,"w 650!");// Add to Main
        initDashboardChart();
        /********* set Components *********/
        setPreferredSize(new Dimension(1200,400));
        /************* set Layout *************/
    }
    private void initEtudiantTable() throws IOException {
        if (level == 1) btnL1.setEnabled(false);
        JPanel panel = new JPanel(new MigLayout());
        JScrollPane pane = new JScrollPane();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"N°Etudiant","Nom et Prénom","Moyenne"});
        table.setModel(model);
        /****** Center table cell *************/
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        /*************************************/
        pane.setViewportView(table);
        panel.add(pane);
        leftMainPanel.add(panel,"span 1, south");
        for(DashBoard d : dashBoard.getDashBoard(level,"")){
            model.addRow(
                    new Object[]{d.getbStudent().getPersonMatricule(),
                                 d.getbStudent().getPersonName(),
                                 String.format("%.2f",d.getbAverage())});
            if (d.getbObservation().contentEquals("ADMIS")) admis = admis + 1;
            if (d.getbObservation().contentEquals("REDOUBLANT")) redouble = redouble + 1;
            if (d.getbObservation().contentEquals("EXCLU")) exclu = exclu + 1;
            effectif = effectif + 1;
        }
        dashBoardChart.setVisible(false);
        dashBoardChart.layoutHistogram();
        dashBoardChart = new DashBoardChart(effectif,admis,redouble,exclu);
        Title.setMainTitle("Classement L1");
        dashBoardChart.revalidate();
        dashBoardChart.repaint();
        pane.setPreferredSize(new Dimension(1000,600));
    }
    private void initDashboardParNiveau(int level) throws IOException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int count = model.getRowCount();
        admis = 0 ; redouble = 0; exclu = 0;effectif=0;
        for(int i=0; i<count;i++ ) model.removeRow(0);
        for(DashBoard d : dashBoard.getDashBoard(level,"")){
            model.addRow(
                    new Object[]{d.getbStudent().getPersonMatricule(),
                            d.getbStudent().getPersonName(),
                            String.format("%.2f",d.getbAverage())});
            if (d.getbObservation().contentEquals("ADMIS")) admis = admis + 1;
            if (d.getbObservation().contentEquals("REDOUBLANT")) redouble = redouble + 1;
            if (d.getbObservation().contentEquals("EXCLU")) exclu = exclu + 1;
            effectif = effectif + 1;
        }
        System.out.println(effectif);
        if(level<4) Title.setMainTitle("Classement L" + level);
        if(level==4) Title.setMainTitle("Classement M1");
        if(level==5) Title.setMainTitle("Classement M2");
        dashBoardChart.setVisible(false);
        dashBoardChart.layoutHistogram();
        dashBoardChart = new DashBoardChart(effectif,admis,redouble,exclu);
        dashBoardChart.revalidate();
        dashBoardChart.repaint();
        initDashboardChart();
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
        leftMainPanel.add(panel,"span 1,wrap");

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
                    initDashboardParNiveau(level);
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
                    initDashboardParNiveau(level);
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
                    initDashboardParNiveau(level);
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
                    initDashboardParNiveau(level);
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
                    initDashboardParNiveau(level);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    private void   initDashboardChart(){
        JPanel chartPanel = new JPanel(new MigLayout("filly","[]","[]"));
        chartPanel.add(dashBoardChart);
        add(chartPanel,"east");

    }
    /****************************************/
    public class DashBoardChart extends JPanel {
        private int histogramHeight = 200;
        private int barWidth = 80;
        private int barGap = 5;

        private JPanel barPanel;
        private JPanel labelPanel;
        private int admis = 0,redoublant = 0, exclu = 0,effectif=0;

        private List<Bar> bars = new ArrayList<Bar>();

        public DashBoardChart(int effectif,int admis,int redoublant,int exclu){
            this.effectif = effectif;
            this.admis = admis;
            this.redoublant = redoublant;
            this.exclu = exclu;
            setBorder( new EmptyBorder(10, 10, 10, 10) );
            setLayout( new BorderLayout() );

            barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
            Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
            Border inner = new EmptyBorder(10, 10, 0, 10);
            Border compound = new CompoundBorder(outer, inner);
            barPanel.setBorder( compound );

            labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
            labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

            add(barPanel, BorderLayout.CENTER);
            add(labelPanel, BorderLayout.PAGE_END);

            createAndShowGUI();
        }
        public void addHistogramColumn(String label, int value, Color color)
        {
            Bar bar = new Bar(label, value, color);
            bars.add( bar );
        }
        public void layoutHistogram()
        {
            barPanel.removeAll();
            labelPanel.removeAll();

            int maxValue = 0;

            for (Bar bar: bars)
                maxValue = Math.max(maxValue, bar.getValue());

            for (Bar bar: bars)
            {
                JLabel label = new JLabel(bar.getValue() + "");
                label.setHorizontalTextPosition(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalTextPosition(JLabel.TOP);
                label.setVerticalAlignment(JLabel.BOTTOM);
                int barHeight = 0;
                if(maxValue> 0 ) barHeight = (bar.getValue() * histogramHeight) / maxValue;
                Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
                label.setIcon( icon );
                barPanel.add( label );

                JLabel barLabel = new JLabel( bar.getLabel() );
                barLabel.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add( barLabel );
            }
        }
        private class Bar
        {
            private String label;
            private int value;
            private Color color;

            public Bar(String label, int value, Color color)
            {
                this.label = label;
                this.value = value;
                this.color = color;
            }

            public String getLabel()
            {
                return label;
            }

            public int getValue()
            {
                return value;
            }

            public Color getColor()
            {
                return color;
            }
        }
        private class ColorIcon implements Icon
        {
            private int shadow = 3;

            private Color color;
            private int width;
            private int height;

            public ColorIcon(Color color, int width, int height)
            {
                this.color = color;
                this.width = width;
                this.height = height;
            }

            public int getIconWidth()
            {
                return width;
            }

            public int getIconHeight()
            {
                return height;
            }

            public void paintIcon(Component c, Graphics g, int x, int y)
            {
                g.setColor(color);
                g.fillRect(x, y, width - shadow, height);
                g.setColor(Color.GRAY);
                g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
            }
        }
        private  void createAndShowGUI()
        {
            addHistogramColumn("EFFECTIF", this.effectif, Color.CYAN);
            addHistogramColumn("ADMIS", this.admis, Color.GREEN);
            addHistogramColumn("REDOUBLANT", this.redoublant, Color.YELLOW);
            addHistogramColumn("EXCLU", this.exclu, Color.RED);
            layoutHistogram();
        }
    }
}
