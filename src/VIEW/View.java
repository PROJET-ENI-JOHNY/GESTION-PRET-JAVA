/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CLASS.Banque;
import CLASS.Client;
import CLASS.Pret;
import CLASS.effectif_par_banque;
import CLASS.pret_par_banque;
import DAO.DaoBanque;
import DAO.DaoClient;
import DAO.DaoPret;
import DIAGRAMME.BarChart;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class View extends javax.swing.JFrame {

    public View() {
        initComponents();
        changeMenu(menuClient, labelClient);
        changeCorps(corpsClient);
        lister();
        jButton1.setVisible(false);
        btnModifBanque.setVisible(false);

        dateChooserCombo1.setVisible(false);
        dateChooserCombo2.setVisible(false);

      
        RemplirComboBox_banque(comboBoxBanque, new DAO.DaoBanque().Lister(null));
        RemplirComboBox_client(comboBoxClient, new DAO.DaoClient().ListerClient(null));
    }

    ArrayList<Banque> GridBanque;
    ArrayList<Client> GridClient;
    ArrayList<Pret> GridPret;
    ArrayList<pret_par_banque> Grid_pret_par_banque;
    ArrayList<effectif_par_banque> Grid_effectif_par_banque;

    public void lister() {
        ListerClient(new DAO.DaoClient().ListerClient(null));
        ListerBanque(new DAO.DaoBanque().Lister(null));
        ListerPret(new DAO.DaoPret().Lister(null));
        RemplirComboBox_banque(comboBoxBanque, new DAO.DaoBanque().Lister(null));
        RemplirComboBox_client(comboBoxClient, new DAO.DaoClient().ListerClient(null));
        RemplirComboBox_banque_pret(jComboBox3, new DAO.DaoBanque().Lister(null));
    }

    public void ListerClient(ArrayList<Client> All) {
        GridClient = All;
        if (!All.isEmpty()) {
            String[] header = new String[]{"Numéro Compte", "Nom", "Adresse"};
            Object[][] data = {{null, null, null}};
            data = new Object[All.size()][5];
            int i = 0;
            for (Client client : All) {
                data[i][0] = client.getN_compte();
                data[i][1] = client.getNom();
                data[i][2] = client.getAdresse();
                i++;
            }
            JTable Table = new JTable(data, header);
            tableClient.setModel(Table.getModel());
            tableClient.setEnabled(true);
        } else {
            String[] header = new String[]{"Numéro Compte", "Nom", "Adresse"};
            Object[][] data = {{"Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement"}};
            jLabel4.setText("                 Vous avez 0 Client");
            JTable Table = new JTable(data, header);
            tableClient.setModel(Table.getModel());
            tableClient.setEnabled(false);
        }
    }

    public void ListerBanque(ArrayList<Banque> All) {
        GridBanque = All;
        if (!All.isEmpty()) {
            String[] header = new String[]{"Numéro banque", "Désignation", "Taux (%)"};
            Object[][] data = {{null, null, null}};
            data = new Object[All.size()][5];
            int i = 0;
            for (Banque banque : All) {
                data[i][0] = banque.getN_banque();
                data[i][1] = banque.getDesignation();
                data[i][2] = banque.getTaux();
                i++;
            }
            JTable Table = new JTable(data, header);
            tableBanque.setModel(Table.getModel());
            tableBanque.setEnabled(true);
        } else {
            String[] header = new String[]{"Numéro banque", "Désignation", "Taux (%)"};
            Object[][] data = {{"Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement"}};
            JTable Table = new JTable(data, header);
            tableBanque.setModel(Table.getModel());
        }
    }

    public void ListerPret(ArrayList<Pret> All) {
        GridPret = All;
        if (!All.isEmpty()) {
            String[] header = new String[]{"Numéro prêt", "Numéro banque", "Numéro client", "Date prêt", "Montant (Ar)"};
            Object[][] data = {{null, null, null, null, null}};
            data = new Object[All.size()][5];
            int i = 0;
            for (Pret pret : All) {
                data[i][0] = pret.getId_pret();
                data[i][1] = pret.getN_banque();
                data[i][2] = pret.getN_client();
                data[i][3] = pret.getDate_pret();
                data[i][4] = pret.getMontant();
                i++;
            }
            JTable Table = new JTable(data, header);
            tablePret.setModel(Table.getModel());
            tablePret.setEnabled(true);
        } else {
            String[] header = new String[]{"Numéro prêt", "Numéro banque", "Numéro client", "Date prêt", "Montant (Ar)"};
            Object[][] data = {{"Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement"}};
            JTable Table = new JTable(data, header);
            tablePret.setModel(Table.getModel());
        }
    }

    public void ListerPretParBanque(ArrayList<pret_par_banque> All) {
        Grid_pret_par_banque = All;
        if (!All.isEmpty()) {
            String[] header = new String[]{"Client", "Date prêt", "Montant (Ar)", "Montant à payer (Ar)"};
            Object[][] data = {{null, null, null, null}};
            data = new Object[All.size() + 1][5];
            int i = 0;
            double totale = 0;
            for (pret_par_banque pret_banque : All) {
                data[i][0] = pret_banque.getNom();
                data[i][1] = pret_banque.getDate_pret();
                data[i][2] = pret_banque.getMontant();
                data[i][3] = pret_banque.getMontant_payer();
                totale += pret_banque.getMontant_payer();
                i++;
            }
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "TOTAL";
            data[i][3] = totale;
            JTable Table = new JTable(data, header);
            tablePret.setModel(Table.getModel());
            tablePret.setEnabled(true);
        } else {
            String[] header = new String[]{"Client", "Date prêt", "Montant (Ar)", "Montant à payer (Ar)"};
            Object[][] data = {{"Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement"}};
            JTable Table = new JTable(data, header);
            tablePret.setModel(Table.getModel());
        }
    }

    public void ListerPretParBanque_efectif(ArrayList<effectif_par_banque> All) {
        Grid_effectif_par_banque = All;
        if (!All.isEmpty()) {
            String[] header = new String[]{"Banque", "Taux", "Effectif", "Total prêt(Ar)", "Montant à payer (Ar)"};
            Object[][] data = {{null, null, null, null, null}};
            data = new Object[All.size()][5];
            int i = 0;
            for (effectif_par_banque effectif : All) {
                data[i][0] = effectif.getDesignation();
                data[i][1] = effectif.getTaux();
                data[i][2] = effectif.getEffectif();
                data[i][3] = effectif.getTotale();
                data[i][4] = effectif.getTotale_payer();
                i++;
            }
            JTable Table = new JTable(data, header);
            tablePret1.setModel(Table.getModel());
            tablePret1.setEnabled(true);
        } else {
            String[] header = new String[]{"Banque", "Taux", "Effectif", "Total prêt(Ar)", "Montant à payer (Ar)"};
            Object[][] data = {{"Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement", "Aucun enrengistrement"}};
            JTable Table = new JTable(data, header);
            tablePret1.setModel(Table.getModel());
        }
    }

    private void RemplirComboBox_banque(JComboBox combo, ArrayList<Banque> All) {
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{" n° compte"}));
        if (!All.isEmpty()) {
            int i = 0;
            for (Banque banque : All) {
                combo.addItem(banque.getN_banque());
                i++;
            }
        }
    }

    private void RemplirComboBox_banque_pret(JComboBox combo, ArrayList<Banque> All) {
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{" n° banque"}));
        if (!All.isEmpty()) {
            int i = 0;
            for (Banque banque : All) {
                combo.addItem(banque.getN_banque());
                i++;
            }
        }
    }

    private void RemplirComboBox_client(JComboBox combo, ArrayList<Client> All) {
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{" n° client"}));
        if (!All.isEmpty()) {
            int i = 0;
            for (Client client : All) {
                combo.addItem(client.getN_compte());
                i++;
            }
        }
    }

    private void configTableClient() {
        String titreClient[] = {"NUM", "NOM ET PRENOMS      ", "ADRESSE"};
        for (int t = 0; t < 3; t++) {
            tableClient.getColumnModel().getColumn(t).setHeaderValue(titreClient[t]);
            tableClient.getTableHeader().setFont(new java.awt.Font("Tahoma", 1, 12));
        }
        fixerColumnTable(tableClient, 0, 60);
        fixerColumnTable(tableClient, 1, 250);
        fixerColumnTable(tableClient, 2, 140);
        ModifierAllignementColumn(tableClient, 0, SwingConstants.RIGHT);
        ModifierAllignementColumn(tableClient, 1, SwingConstants.CENTER);
        ModifierAllignementColumn(tableClient, 2, SwingConstants.CENTER);
    }

    public void initMenu() {
        //couleur de menu tout vert
        menuBanque.setBackground(new Color(1, 102, 102));
        menuBilan.setBackground(new Color(1, 102, 102));
        menuClient.setBackground(new Color(1, 102, 102));
        menuGraphe.setBackground(new Color(1, 102, 102));
        menuPret.setBackground(new Color(1, 102, 102));
        // couleur de label du menu tout blanche
        labelBanque.setForeground(new Color(255, 255, 255));
        labelBilan.setForeground(new Color(255, 255, 255));
        labelClient.setForeground(new Color(255, 255, 255));
        labelGraphe.setForeground(new Color(255, 255, 255));
        labelPret.setForeground(new Color(255, 255, 255));
    }

    public void changeCorps(JPanel panel) {
        try {
            jPanelCorps.removeAll();
            jPanelCorps.repaint();
            jPanelCorps.revalidate();
            jPanelCorps.add(panel);
            jPanelCorps.repaint();
            jPanelCorps.revalidate();
        } catch (Exception e) {
        }
    }

    public String GetCurentDate() {
        String currentDate = String.format("%tF", new Date());
        return currentDate;
    }

    public void changeMenu(JPanel panel, JLabel label) {
        initMenu();
        panel.setBackground(new Color(250, 250, 250));
        label.setForeground(new Color(1, 102, 102));
    }

    private void fixerColumnTable(JTable table, int cell, int width) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col = table.getColumnModel().getColumn(cell);
        col.setPreferredWidth(width);
    }

    private void ModifierAllignementColumn(JTable table, int cl, int Alignement) {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(Alignement);
        TableColumn col = table.getColumnModel().getColumn(cl);
        col.setCellRenderer(render);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTete = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        menuClient = new javax.swing.JPanel();
        labelClient = new javax.swing.JLabel();
        menuBanque = new javax.swing.JPanel();
        labelBanque = new javax.swing.JLabel();
        menuPret = new javax.swing.JPanel();
        labelPret = new javax.swing.JLabel();
        menuBilan = new javax.swing.JPanel();
        labelBilan = new javax.swing.JLabel();
        menuGraphe = new javax.swing.JPanel();
        labelGraphe = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanelCorps = new javax.swing.JPanel();
        corpsClient = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClient = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanelFormClient = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumClient = new javax.swing.JTextField();
        txtNomClient = new javax.swing.JTextField();
        txtAdresseClient = new javax.swing.JTextField();
        btnAnnulerAddClient = new javax.swing.JButton();
        btnAddClient = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnPrepareDeleteClient = new javax.swing.JButton();
        btnPrepareUdateClient = new javax.swing.JButton();
        corpsBanque = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBanque = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jPanelFormBanque = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdBanque = new javax.swing.JTextField();
        txtDesignationBanque = new javax.swing.JTextField();
        btnAnnulerAddBanque = new javax.swing.JButton();
        btnAddBanque = new javax.swing.JButton();
        SpinnerTaux = new javax.swing.JSpinner();
        btnModifBanque = new javax.swing.JButton();
        btnPrepareDeleteBanque = new javax.swing.JButton();
        btnPrepareUdateBanque = new javax.swing.JButton();
        corpsPret = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePret = new javax.swing.JTable();
        jPanelFormBanque1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnAnnulerAddBanque1 = new javax.swing.JButton();
        btnAddpret = new javax.swing.JButton();
        comboBoxClient = new javax.swing.JComboBox<>();
        comboBoxBanque = new javax.swing.JComboBox<>();
        SpinnerMontantPretr = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        corpsBilan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePret1 = new javax.swing.JTable();
        jPanelFormBanque2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAddBanque2 = new javax.swing.JButton();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo4 = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        corpsGraphe = new javax.swing.JPanel();
        diagramme = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(850, 440));
        setMinimumSize(new java.awt.Dimension(850, 440));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTete.setBackground(new java.awt.Color(0, 102, 102));
        jPanelTete.setMinimumSize(new java.awt.Dimension(800, 100));
        jPanelTete.setPreferredSize(new java.awt.Dimension(850, 100));
        jPanelTete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 40));
        jPanelTete.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 690, 20));

        menuClient.setPreferredSize(new java.awt.Dimension(120, 20));
        menuClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuClientMouseClicked(evt);
            }
        });
        menuClient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelClient.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        labelClient.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelClient.setText("CLIENT");
        menuClient.add(labelClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 110, -1));

        jPanelTete.add(menuClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 40, 130, 30));

        menuBanque.setPreferredSize(new java.awt.Dimension(120, 20));
        menuBanque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBanqueMouseClicked(evt);
            }
        });
        menuBanque.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBanque.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        labelBanque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBanque.setText("BANQUE");
        menuBanque.add(labelBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 110, -1));

        jPanelTete.add(menuBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 130, 30));

        menuPret.setPreferredSize(new java.awt.Dimension(120, 20));
        menuPret.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPretMouseClicked(evt);
            }
        });
        menuPret.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelPret.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        labelPret.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPret.setText("PRET");
        menuPret.add(labelPret, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 110, -1));

        jPanelTete.add(menuPret, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 40, 130, 30));

        menuBilan.setPreferredSize(new java.awt.Dimension(120, 20));
        menuBilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuBilanMouseClicked(evt);
            }
        });
        menuBilan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelBilan.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        labelBilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBilan.setText("BILAN");
        menuBilan.add(labelBilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 110, -1));

        jPanelTete.add(menuBilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 130, 30));

        menuGraphe.setPreferredSize(new java.awt.Dimension(120, 20));
        menuGraphe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuGrapheMouseClicked(evt);
            }
        });
        menuGraphe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelGraphe.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        labelGraphe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGraphe.setText("GRAPHE");
        menuGraphe.add(labelGraphe, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 110, -1));

        jPanelTete.add(menuGraphe, new org.netbeans.lib.awtextra.AbsoluteConstraints(695, 40, 140, 30));

        jLabel17.setFont(new java.awt.Font("Traditional Arabic", 3, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Gestion de");
        jPanelTete.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 26, 110, 30));

        jLabel18.setFont(new java.awt.Font("Traditional Arabic", 3, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Pret Bancaire");
        jPanelTete.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 30));

        getContentPane().add(jPanelTete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 90));

        jPanelCorps.setPreferredSize(new java.awt.Dimension(852, 440));
        jPanelCorps.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        corpsClient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableClient.setRowHeight(20);
        tableClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClient);

        corpsClient.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 106, -1, 283));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField1PropertyChange(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jTextField1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jTextField1VetoableChange(evt);
            }
        });
        corpsClient.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 40, 200, 21));

        jPanelFormClient.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ajout et modification de client");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Numero:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Nom et prenoms: ");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Adresse:");

        btnAnnulerAddClient.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAnnulerAddClient.setText("Annuler");

        btnAddClient.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAddClient.setText("Ajouter");
        btnAddClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddClientMouseClicked(evt);
            }
        });

        jButton1.setText("Enregistrer");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormClientLayout = new javax.swing.GroupLayout(jPanelFormClient);
        jPanelFormClient.setLayout(jPanelFormClientLayout);
        jPanelFormClientLayout.setHorizontalGroup(
            jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormClientLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormClientLayout.createSequentialGroup()
                        .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAdresseClient)
                            .addComponent(txtNomClient)
                            .addComponent(txtNumClient, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(41, 41, 41))
                    .addGroup(jPanelFormClientLayout.createSequentialGroup()
                        .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormClientLayout.createSequentialGroup()
                                .addComponent(btnAnnulerAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))))
        );
        jPanelFormClientLayout.setVerticalGroup(
            jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormClientLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtAdresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelFormClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnulerAddClient)
                    .addComponent(btnAddClient)
                    .addComponent(jButton1))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        corpsClient.add(jPanelFormClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 106, 340, 280));

        btnPrepareDeleteClient.setText("Supprimer");
        btnPrepareDeleteClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrepareDeleteClientMouseClicked(evt);
            }
        });
        btnPrepareDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrepareDeleteClientActionPerformed(evt);
            }
        });
        corpsClient.add(btnPrepareDeleteClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        btnPrepareUdateClient.setText("Modifier");
        btnPrepareUdateClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrepareUdateClientMouseClicked(evt);
            }
        });
        corpsClient.add(btnPrepareUdateClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 90, -1));

        jPanelCorps.add(corpsClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 852, 440));

        corpsBanque.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableBanque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBanque.setRowHeight(20);
        jScrollPane2.setViewportView(tableBanque);

        corpsBanque.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 106, -1, 283));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        corpsBanque.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 40, 200, 21));

        jPanelFormBanque.setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ajout et modification de la banque");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel6.setText("Id banque:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel7.setText("Designation:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setText("Taux de pret");

        btnAnnulerAddBanque.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAnnulerAddBanque.setText("Annuler");
        btnAnnulerAddBanque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerAddBanqueActionPerformed(evt);
            }
        });

        btnAddBanque.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAddBanque.setText("Ajouter");
        btnAddBanque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBanqueActionPerformed(evt);
            }
        });

        btnModifBanque.setText("Enregistrer");
        btnModifBanque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifBanqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormBanqueLayout = new javax.swing.GroupLayout(jPanelFormBanque);
        jPanelFormBanque.setLayout(jPanelFormBanqueLayout);
        jPanelFormBanqueLayout.setHorizontalGroup(
            jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                        .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(43, 43, 43)
                        .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDesignationBanque)
                            .addComponent(txtIdBanque, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(41, 41, 41))
                    .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                        .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(41, 41, 41)
                                .addComponent(SpinnerTaux, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                                .addComponent(btnAnnulerAddBanque, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddBanque, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModifBanque)))
                        .addGap(9, 9, 9))))
        );
        jPanelFormBanqueLayout.setVerticalGroup(
            jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanqueLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIdBanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDesignationBanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(SpinnerTaux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanelFormBanqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnulerAddBanque)
                    .addComponent(btnAddBanque)
                    .addComponent(btnModifBanque))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        corpsBanque.add(jPanelFormBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 106, 340, 280));

        btnPrepareDeleteBanque.setText("Supprimer");
        btnPrepareDeleteBanque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrepareDeleteBanqueActionPerformed(evt);
            }
        });
        corpsBanque.add(btnPrepareDeleteBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        btnPrepareUdateBanque.setText("Modifier");
        btnPrepareUdateBanque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrepareUdateBanqueActionPerformed(evt);
            }
        });
        corpsBanque.add(btnPrepareUdateBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 90, -1));

        jPanelCorps.add(corpsBanque, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 852, 440));

        corpsPret.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablePret.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePret.setRowHeight(20);
        jScrollPane3.setViewportView(tablePret);

        corpsPret.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 106, -1, 283));

        jPanelFormBanque1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ajout de nouveau pret");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel10.setText("Id banque:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel11.setText("Id client:");

        btnAnnulerAddBanque1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAnnulerAddBanque1.setText("Annuler");

        btnAddpret.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAddpret.setText("Ajouter");
        btnAddpret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddpretActionPerformed(evt);
            }
        });

        comboBoxClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxClientActionPerformed(evt);
            }
        });

        comboBoxBanque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Montant: ");

        javax.swing.GroupLayout jPanelFormBanque1Layout = new javax.swing.GroupLayout(jPanelFormBanque1);
        jPanelFormBanque1.setLayout(jPanelFormBanque1Layout);
        jPanelFormBanque1Layout.setHorizontalGroup(
            jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanque1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFormBanque1Layout.createSequentialGroup()
                        .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnnulerAddBanque1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SpinnerMontantPretr)
                            .addComponent(btnAddpret, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(comboBoxClient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboBoxBanque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFormBanque1Layout.setVerticalGroup(
            jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanque1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addGap(41, 41, 41)
                .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(31, 31, 31)
                .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxBanque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpinnerMontantPretr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(25, 25, 25)
                .addGroup(jPanelFormBanque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnulerAddBanque1)
                    .addComponent(btnAddpret))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        corpsPret.add(jPanelFormBanque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 106, 340, 280));

        jCheckBox1.setText("Pret entre deux date");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        corpsPret.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 150, -1));
        corpsPret.add(dateChooserCombo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));
        corpsPret.add(dateChooserCombo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jComboBox3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox3PropertyChange(evt);
            }
        });
        corpsPret.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        corpsPret.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 160, 20));

        jPanelCorps.add(corpsPret, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 852, 440));

        corpsBilan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablePret1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePret1.setRowHeight(20);
        jScrollPane4.setViewportView(tablePret1);

        corpsBilan.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 106, -1, 283));

        jPanelFormBanque2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Pret dans deux date");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel13.setText("Id banque:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel14.setText("Designation:");

        btnAddBanque2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAddBanque2.setText("Afficher");
        btnAddBanque2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBanque2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormBanque2Layout = new javax.swing.GroupLayout(jPanelFormBanque2);
        jPanelFormBanque2.setLayout(jPanelFormBanque2Layout);
        jPanelFormBanque2Layout.setHorizontalGroup(
            jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanque2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelFormBanque2Layout.createSequentialGroup()
                            .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel13))
                            .addGap(44, 44, 44)
                            .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(dateChooserCombo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dateChooserCombo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelFormBanque2Layout.createSequentialGroup()
                        .addComponent(btnAddBanque2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFormBanque2Layout.setVerticalGroup(
            jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormBanque2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(38, 38, 38)
                .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelFormBanque2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(btnAddBanque2)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        corpsBilan.add(jPanelFormBanque2, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 106, 340, 280));

        jLabel15.setText("Mois:");
        corpsBilan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 40, 20));

        jLabel16.setText("Année:");
        corpsBilan.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 34, -1, 30));

        jCheckBox2.setText("Afficher l'activité dans deux date");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        corpsBilan.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 330, -1));

        jButton2.setText("Afficher");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        corpsBilan.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 33, 100, 30));
        corpsBilan.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 80, -1));
        corpsBilan.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 100, -1));

        jPanelCorps.add(corpsBilan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 852, 440));

        corpsGraphe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        diagramme.setLayout(new java.awt.CardLayout());
        corpsGraphe.add(diagramme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 440));

        jPanelCorps.add(corpsGraphe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 852, 440));

        getContentPane().add(jPanelCorps, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClientMouseClicked
        changeMenu(menuClient, labelClient);
        changeCorps(corpsClient);
    }//GEN-LAST:event_menuClientMouseClicked

    private void menuBanqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBanqueMouseClicked
        changeMenu(menuBanque, labelBanque);
        changeCorps(corpsBanque);
    }//GEN-LAST:event_menuBanqueMouseClicked

    private void menuPretMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPretMouseClicked
        changeMenu(menuPret, labelPret);
        changeCorps(corpsPret);
        RemplirComboBox_banque_pret(jComboBox3, new DAO.DaoBanque().Lister(null));

        ListerPret(new DAO.DaoPret().Lister(null));
    }//GEN-LAST:event_menuPretMouseClicked

    private void menuBilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBilanMouseClicked
        changeMenu(menuBilan, labelBilan);
        changeCorps(corpsBilan);
        ListerPretParBanque_efectif(new DAO.DaoPret().Lister_effectif_par_banque(null));
    }//GEN-LAST:event_menuBilanMouseClicked

    private void menuGrapheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuGrapheMouseClicked
        changeMenu(menuGraphe, labelGraphe);
        changeCorps(corpsGraphe);
        BarChart barChart = new BarChart("pieChart3dDEMO1");
        diagramme.removeAll();
        diagramme.repaint();
        diagramme.revalidate();
        diagramme.add(barChart.createDemoPanel());
        diagramme.repaint();
        diagramme.revalidate();
    }//GEN-LAST:event_menuGrapheMouseClicked

    private void btnAddClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddClientMouseClicked
        Client client = null;
        String n_banque = txtNumClient.getText();
        String nom = txtNomClient.getText();
        String adresse = txtAdresseClient.getText();

        client = new Client(n_banque, nom, adresse);

        DaoClient Dao = new DaoClient(client);

        Dao.AjouterClient();

        lister();

        viderChamp_client();

    }//GEN-LAST:event_btnAddClientMouseClicked


    private void tableClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientMouseClicked

    }//GEN-LAST:event_tableClientMouseClicked

    private void btnPrepareDeleteClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrepareDeleteClientMouseClicked
        Client client = GetClientOnTable();
        DaoClient Dao = new DaoClient(client);
        Dao.SupprimerClient();
        ListerClient(new DAO.DaoClient().ListerClient(null));
    }//GEN-LAST:event_btnPrepareDeleteClientMouseClicked

    private void btnPrepareUdateClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrepareUdateClientMouseClicked
        Client client = GetClientOnTable();
        txtNumClient.setText(client.getN_compte());
        txtNomClient.setText(client.getNom());
        txtAdresseClient.setText(client.getAdresse());
        btnAddClient.setVisible(false);
        jButton1.setVisible(true);
    }//GEN-LAST:event_btnPrepareUdateClientMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Client client = null;
        String n_banque = txtNumClient.getText();
        String nom = txtNomClient.getText();
        String adresse = txtAdresseClient.getText();

        client = new Client(n_banque, nom, adresse);

        DaoClient Dao = new DaoClient(client);

        Dao.EditerClient(client);

        lister();

        btnAddClient.setVisible(true);
        jButton1.setVisible(false);

        viderChamp_client();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnPrepareDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrepareDeleteClientActionPerformed

    }//GEN-LAST:event_btnPrepareDeleteClientActionPerformed

    private void btnPrepareDeleteBanqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrepareDeleteBanqueActionPerformed
        Banque banque = GetBanqueOnTable();
        DaoBanque Dao = new DaoBanque(banque);
        Dao.Supprimer();
        ListerBanque(new DAO.DaoBanque().Lister(null));
    }//GEN-LAST:event_btnPrepareDeleteBanqueActionPerformed

    private void btnPrepareUdateBanqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrepareUdateBanqueActionPerformed
        Banque banque = GetBanqueOnTable();
        txtIdBanque.setText(banque.getN_banque());
        txtDesignationBanque.setText(banque.getDesignation());
        SpinnerTaux.setValue((int) banque.getTaux());
        btnAddBanque.setVisible(false);
        btnModifBanque.setVisible(true);
    }//GEN-LAST:event_btnPrepareUdateBanqueActionPerformed

    private void btnModifBanqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifBanqueActionPerformed
        String n_banque = txtIdBanque.getText();
        String designation = txtDesignationBanque.getText();
        int Taux = (int) SpinnerTaux.getValue();
        Banque banque = null;

        banque = new Banque(n_banque, designation, Taux);

        DaoBanque Dao = new DaoBanque(banque);

        Dao.Editer(banque);

        lister();

        btnAddBanque.setVisible(true);
        btnModifBanque.setVisible(false);

        viderChamp_banque();
    }//GEN-LAST:event_btnModifBanqueActionPerformed

    private void btnAddBanqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBanqueActionPerformed
        String n_banque = txtIdBanque.getText();
        String designation = txtDesignationBanque.getText();
        int Taux = (int) SpinnerTaux.getValue();
        Banque banque = null;

        banque = new Banque(n_banque, designation, Taux);

        DaoBanque Dao = new DaoBanque(banque);

        Dao.Ajouter();

        lister();

        btnAddBanque.setVisible(true);
        btnModifBanque.setVisible(false);

        viderChamp_banque();
    }//GEN-LAST:event_btnAddBanqueActionPerformed

    private void btnAnnulerAddBanqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerAddBanqueActionPerformed
        btnAddBanque.setVisible(true);
        btnModifBanque.setVisible(false);
        viderChamp_banque();
    }//GEN-LAST:event_btnAnnulerAddBanqueActionPerformed

    private void comboBoxClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxClientActionPerformed

    private void btnAddpretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddpretActionPerformed
        String n_banque = (String) comboBoxBanque.getSelectedItem();
        String n_client = (String) comboBoxClient.getSelectedItem();
        int montant = (int) SpinnerMontantPretr.getValue();
        Pret pret = null;
        pret = new Pret(0, n_banque, n_client, montant, GetCurentDate());

        DaoPret dao = new DaoPret(pret);

        dao.Ajouter();

       lister();
    }//GEN-LAST:event_btnAddpretActionPerformed

    private void jComboBox3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox3PropertyChange
        // System.out.println("VIEW.View.jComboBox3PropertyChange()");
    }//GEN-LAST:event_jComboBox3PropertyChange

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        Banque banque = null;
        int Taux = new DAO.DaoPret().get_taux((String) jComboBox3.getSelectedItem());
        jLabel20.setText("Taux (%) : " + Taux);
        if (jCheckBox1.isSelected() == false) {
            ListerPretParBanque(new DAO.DaoPret().Lister_par_banque((String) jComboBox3.getSelectedItem()));
        } else {
            ListerPretParBanque(new DAO.DaoPret().Lister_par_banque((String) jComboBox3.getSelectedItem() + " and date_pret between '" + getDate(dateChooserCombo1) + "' and '" + getDate(dateChooserCombo2) + "'"));
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    public String getDate(DateChooserCombo date) {
        String Date = "";
        Calendar c = date.getSelectedDate();
        Date d = c.getTime();
        SimpleDateFormat fD = new SimpleDateFormat("yyyy-MM-dd");
        Date = fD.format(d);
        return Date;
    }


    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        System.err.println(jCheckBox1.isSelected());
        if (jCheckBox1.isSelected() == true) {
            dateChooserCombo1.setVisible(true);
            dateChooserCombo2.setVisible(true);
        } else {
            dateChooserCombo1.setVisible(false);
            dateChooserCombo2.setVisible(false);
            System.err.println(dateChooserCombo1.getText());
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int mois = (int) jSpinner1.getValue();
        int annee = (int) jSpinner2.getValue();

        ListerPretParBanque_efectif(new DAO.DaoPret().Lister_effectif_par_banque(" and MONTH(date_pret) = " + mois + " and YEAR(date_pret)=" + annee));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed

        if (jCheckBox2.isSelected() == false) {
            jCheckBox2.setText(" Afficher l'activité dans deux date");

            jLabel15.setVisible(true);
            jSpinner1.setVisible(true);
            jLabel16.setVisible(true);
            jSpinner2.setVisible(true);
            jButton2.setVisible(true);
        } else {

            jCheckBox2.setText("Dans un mois ");
            jLabel15.setVisible(false);
            jSpinner1.setVisible(false);
            jLabel16.setVisible(false);
            jSpinner2.setVisible(false);
            jButton2.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void btnAddBanque2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBanque2ActionPerformed

        ListerPretParBanque_efectif(new DAO.DaoPret().Lister_effectif_par_banque(" and date_pret between '" + getDate(dateChooserCombo3) + "' and '" + getDate(dateChooserCombo4) + "'"));

    }//GEN-LAST:event_btnAddBanque2ActionPerformed

    private void jTextField1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField1PropertyChange
        //System.out.println("VIEW.View.jTextField1PropertyChange()");
    }//GEN-LAST:event_jTextField1PropertyChange

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        System.out.println("VIEW.View.jTextField1PropertyChange()");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jTextField1VetoableChange
        System.out.println("VIEW.View.jTextField1PropertyChange()");
    }//GEN-LAST:event_jTextField1VetoableChange

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String cle = jTextField1.getText();
        if (cle == "") {
            ListerClient(new DAO.DaoClient().ListerClient(null));

        } else {
            ListerClient(new DAO.DaoClient().ListerClient(" nom like '%" + cle + "%' or adresse like '%" + cle + "%'"));
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        String cle = jTextField2.getText();
        if (cle == "") {
            ListerBanque(new DAO.DaoBanque().Lister(null));

        } else {
            ListerBanque(new DAO.DaoBanque().Lister(" designation like '%" + cle + "%' or taux like '%" + cle + "%'"));
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    public Client GetClientOnTable() {
        Client client = null;
        if (tableClient.getSelectedRow() >= 0) {
            int ligne = tableClient.getSelectedRow();
            client = GridClient.get(ligne);
        } else {
            JOptionPane.showMessageDialog(this, "Veillez cliquer une ligne");
        }
        return client;
    }

    public Banque GetBanqueOnTable() {
        Banque banque = null;
        if (tableBanque.getSelectedRow() >= 0) {
            int ligne = tableBanque.getSelectedRow();
            banque = GridBanque.get(ligne);
        } else {
            JOptionPane.showMessageDialog(this, "Veillez cliquer une ligne");
        }
        return banque;
    }

    public void viderChamp_client() {
        txtNumClient.setText("");
        txtNomClient.setText("");
        txtAdresseClient.setText("");
    }

    public void viderChamp_banque() {
        txtIdBanque.setText("");
        txtDesignationBanque.setText("");
        SpinnerTaux.setValue(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            /* for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/

            //UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpinnerMontantPretr;
    private javax.swing.JSpinner SpinnerTaux;
    private javax.swing.JButton btnAddBanque;
    private javax.swing.JButton btnAddBanque2;
    private javax.swing.JButton btnAddClient;
    private javax.swing.JButton btnAddpret;
    private javax.swing.JButton btnAnnulerAddBanque;
    private javax.swing.JButton btnAnnulerAddBanque1;
    private javax.swing.JButton btnAnnulerAddClient;
    private javax.swing.JButton btnModifBanque;
    private javax.swing.JButton btnPrepareDeleteBanque;
    private javax.swing.JButton btnPrepareDeleteClient;
    private javax.swing.JButton btnPrepareUdateBanque;
    private javax.swing.JButton btnPrepareUdateClient;
    private javax.swing.JComboBox<String> comboBoxBanque;
    private javax.swing.JComboBox<String> comboBoxClient;
    private javax.swing.JPanel corpsBanque;
    private javax.swing.JPanel corpsBilan;
    private javax.swing.JPanel corpsClient;
    private javax.swing.JPanel corpsGraphe;
    private javax.swing.JPanel corpsPret;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserCombo dateChooserCombo4;
    private javax.swing.JPanel diagramme;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelCorps;
    private javax.swing.JPanel jPanelFormBanque;
    private javax.swing.JPanel jPanelFormBanque1;
    private javax.swing.JPanel jPanelFormBanque2;
    private javax.swing.JPanel jPanelFormClient;
    private javax.swing.JPanel jPanelTete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel labelBanque;
    private javax.swing.JLabel labelBilan;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelGraphe;
    private javax.swing.JLabel labelPret;
    private javax.swing.JPanel menuBanque;
    private javax.swing.JPanel menuBilan;
    private javax.swing.JPanel menuClient;
    private javax.swing.JPanel menuGraphe;
    private javax.swing.JPanel menuPret;
    private javax.swing.JTable tableBanque;
    private javax.swing.JTable tableClient;
    private javax.swing.JTable tablePret;
    private javax.swing.JTable tablePret1;
    private javax.swing.JTextField txtAdresseClient;
    private javax.swing.JTextField txtDesignationBanque;
    private javax.swing.JTextField txtIdBanque;
    private javax.swing.JTextField txtNomClient;
    private javax.swing.JTextField txtNumClient;
    // End of variables declaration//GEN-END:variables

}
