/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recyclapp.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import recyclapp.elements.Produit;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jeffrey
 */
public class ModifierStation extends javax.swing.JDialog {

    private int sorties;
    private String hauteurS;
    private String largeurS;
    private String nom = null;
    private String description;
    private int hauteur;
    private int largeur;
    private Color couleur;
    private int capacite;
    private String capaciteS;
    private String cheminImage;
    private ArrayList<String> mesProduits = new ArrayList<String>();
    private ArrayList<ArrayList<Integer>> mesListesDeQt = new  ArrayList<ArrayList<Integer>>();
    private Map<Produit, ArrayList<Integer>> m_matrice = new HashMap<Produit, ArrayList<Integer>>();
   
    public String getNom() {
        return nom;
    }
    
    public ArrayList<String> getMesProduits(){
        return mesProduits;
    }
    
    public ArrayList<ArrayList<Integer>> getMesListesDeQt(){
        return mesListesDeQt;
    }

    
    public Map<Produit, ArrayList<Integer>> getMatrice() {
        return m_matrice;
    }
    
    public String getSortiesStr() {
        return Integer.toString(sorties);
    }
    
    public int getSorties() {
        return sorties;
    }
    
    public String getCapaciteStr() {
        return Integer.toString(capacite);
    }
    
    public String getDescription() {
        return description;
    }

    public int getHauteur() {
        return hauteur;
    }
    
    public String getHauteurStr() {
        return Integer.toString(hauteur);
    }
    
    public int getLargeur() {
        return largeur;
    }

    public String getLargeurStr() {
        return Integer.toString(largeur);
    }
    
    public Color getCouleur() {
        return couleur;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getCheminImage() {
        return cheminImage;
    }
    
    public ModifierStation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public ModifierStation(java.awt.Frame parent, boolean modal,int nbSortie, String p_nom, String des, Color c, Dimension d, int capM,Map<Produit, ArrayList<Integer>> matrice, String chemin) {
        super(parent, modal);
        sorties = nbSortie;
        nom = p_nom;
        description = des;
        couleur = c;
        hauteur = d.height;
        largeur = d.width;
        capacite = capM;
        m_matrice = matrice;
        hauteurS = Integer.toString(hauteur);
        largeurS = Integer.toString(largeur);
        cheminImage = chemin;
        
        
        initComponents();
        DefaultTableModel model =  (DefaultTableModel) this.TableMatrice.getModel();
        Iterator it = m_matrice.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry mapEntry = (Map.Entry) it.next();
            Produit keyValue = (Produit) mapEntry.getKey();
            ArrayList<Integer> value = (ArrayList<Integer>) mapEntry.getValue();
            if(value.size()==1)
                model.addRow(new Object[]{keyValue.reqNomProduit(),value.get(0)});
            else if(value.size()==2)
                model.addRow(new Object[]{keyValue.reqNomProduit(),value.get(0),value.get(1)});
            else if(value.size()==3)
                model.addRow(new Object[]{keyValue.reqNomProduit(),value.get(0),value.get(1),value.get(2)});
            else if(value.size()==4)
                model.addRow(new Object[]{keyValue.reqNomProduit(),value.get(0),value.get(1),value.get(2),value.get(3)});
            else if(value.size()==5)
                model.addRow(new Object[]{keyValue.reqNomProduit(),value.get(0),value.get(1),value.get(2),value.get(3),value.get(4)});
        }
        CouleurStation.setColor(couleur);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauConsole = new javax.swing.JPanel();
        ChoisirImageStation = new javax.swing.JFileChooser();
        Onglets = new javax.swing.JTabbedPane();
        PanneauMatrice = new javax.swing.JPanel();
        PanneauRecuperation = new javax.swing.JPanel();
        ConteneurProduitsEntree = new javax.swing.JScrollPane();
        TableMatrice = new javax.swing.JTable();
        LabelSorties = new javax.swing.JLabel();
        LabelProduits = new javax.swing.JLabel();
        LabelCapacite = new javax.swing.JLabel();
        LabelNbSortie = new javax.swing.JLabel();
        CapaciteStation = new javax.swing.JTextField();
        nbSortie = new javax.swing.JTextField();
        Format = new javax.swing.JPanel();
        LabelNom = new javax.swing.JLabel();
        NomStation = new javax.swing.JTextField();
        ConteneurDescription = new javax.swing.JScrollPane();
        DescriptionStation = new javax.swing.JTextPane();
        LabelDescription = new javax.swing.JLabel();
        Labelx = new javax.swing.JLabel();
        LargeurStation = new javax.swing.JTextField();
        HauteurStation = new javax.swing.JTextField();
        LabelDimensions = new javax.swing.JLabel();
        Labelmetres = new javax.swing.JLabel();
        LabelCouleur = new javax.swing.JLabel();
        CouleurStation = new javax.swing.JColorChooser();
        LabelImage = new javax.swing.JLabel();
        CheminImageStation = new javax.swing.JTextField();
        BoutonParcourirImage = new javax.swing.JButton();
        BoutonOK = new javax.swing.JButton();
        BoutonAnnuler = new javax.swing.JButton();

        PanneauConsole.setPreferredSize(new java.awt.Dimension(700, 191));

        javax.swing.GroupLayout PanneauConsoleLayout = new javax.swing.GroupLayout(PanneauConsole);
        PanneauConsole.setLayout(PanneauConsoleLayout);
        PanneauConsoleLayout.setHorizontalGroup(
            PanneauConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 817, Short.MAX_VALUE)
        );
        PanneauConsoleLayout.setVerticalGroup(
            PanneauConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        ChoisirImageStation.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 550));
        setResizable(false);

        Onglets.setPreferredSize(new java.awt.Dimension(900, 627));

        PanneauMatrice.setPreferredSize(new java.awt.Dimension(700, 399));

        PanneauRecuperation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TableMatrice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom du produit", "Sortie 1", "Sortie 2", "Sortie 3", "Sortie 4", "Sortie 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ConteneurProduitsEntree.setViewportView(TableMatrice);

        LabelSorties.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelSorties.setText("Sorties");

        LabelProduits.setText("Produits");
        LabelProduits.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout PanneauRecuperationLayout = new javax.swing.GroupLayout(PanneauRecuperation);
        PanneauRecuperation.setLayout(PanneauRecuperationLayout);
        PanneauRecuperationLayout.setHorizontalGroup(
            PanneauRecuperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanneauRecuperationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanneauRecuperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanneauRecuperationLayout.createSequentialGroup()
                        .addComponent(LabelProduits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ConteneurProduitsEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(LabelSorties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanneauRecuperationLayout.setVerticalGroup(
            PanneauRecuperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanneauRecuperationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelSorties)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanneauRecuperationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelProduits, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConteneurProduitsEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LabelCapacite.setText("Capacité");
        LabelCapacite.setToolTipText("");

        LabelNbSortie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelNbSortie.setText("Nombre sorties ");

        CapaciteStation.setText(getCapaciteStr());
        CapaciteStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapaciteStationActionPerformed(evt);
            }
        });

        nbSortie.setText(getSortiesStr());
        nbSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbSortieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanneauMatriceLayout = new javax.swing.GroupLayout(PanneauMatrice);
        PanneauMatrice.setLayout(PanneauMatriceLayout);
        PanneauMatriceLayout.setHorizontalGroup(
            PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanneauMatriceLayout.createSequentialGroup()
                .addGroup(PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanneauMatriceLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(PanneauRecuperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanneauMatriceLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelCapacite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LabelNbSortie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nbSortie, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                            .addComponent(CapaciteStation))))
                .addContainerGap())
        );
        PanneauMatriceLayout.setVerticalGroup(
            PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanneauMatriceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanneauRecuperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CapaciteStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelCapacite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanneauMatriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbSortie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNbSortie))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Onglets.addTab("Matrice de récupération", PanneauMatrice);

        LabelNom.setText("Nom");

        NomStation.setText(getNom());

        DescriptionStation.setText(getDescription());
        ConteneurDescription.setViewportView(DescriptionStation);

        LabelDescription.setText("Description");

        Labelx.setText("x");

        LargeurStation.setText(getHauteurStr());

        HauteurStation.setText(getLargeurStr());

        LabelDimensions.setText("Dimensions");

        Labelmetres.setText("mètres");

        LabelCouleur.setText("Couleur");

        LabelImage.setText("Image");

        CheminImageStation.setText(getCheminImage());
        CheminImageStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheminImageStationActionPerformed(evt);
            }
        });

        BoutonParcourirImage.setText("...");
        BoutonParcourirImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonParcourirImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FormatLayout = new javax.swing.GroupLayout(Format);
        Format.setLayout(FormatLayout);
        FormatLayout.setHorizontalGroup(
            FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormatLayout.createSequentialGroup()
                        .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelDimensions)
                            .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(LabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelCouleur)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FormatLayout.createSequentialGroup()
                                .addComponent(LargeurStation, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Labelx)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HauteurStation, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Labelmetres)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(FormatLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(CheminImageStation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BoutonParcourirImage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CouleurStation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(FormatLayout.createSequentialGroup()
                        .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNom)
                            .addComponent(LabelDescription))
                        .addGap(18, 18, 18)
                        .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NomStation)
                            .addComponent(ConteneurDescription))))
                .addContainerGap())
        );
        FormatLayout.setVerticalGroup(
            FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNom)
                    .addComponent(NomStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelDescription)
                    .addComponent(ConteneurDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LargeurStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelx)
                    .addComponent(LabelDimensions)
                    .addComponent(HauteurStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Labelmetres))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormatLayout.createSequentialGroup()
                        .addComponent(LabelCouleur)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CouleurStation, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonParcourirImage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheminImageStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelImage))
                .addGap(17, 17, 17))
        );

        Onglets.addTab("Format", Format);

        BoutonOK.setText("OK");
        BoutonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonOKActionPerformed(evt);
            }
        });

        BoutonAnnuler.setText("Annuler");
        BoutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BoutonOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BoutonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Onglets, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Onglets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoutonAnnuler)
                    .addComponent(BoutonOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonAnnulerActionPerformed
        nom = null;
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_BoutonAnnulerActionPerformed

    private void BoutonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonOKActionPerformed
       try {
        if(NomStation.getText().isEmpty()==false && parseInt(nbSortie.getText()) < 6 && matriceOK()== true) {
            ArrayList<Integer> maListeDeQt = new ArrayList<Integer>();
            mesProduits.clear();
            mesListesDeQt.clear();
            
                
            sorties = parseInt(nbSortie.getText());
            DefaultTableModel model =  (DefaultTableModel) this.TableMatrice.getModel();
            for(int i=0;i<model.getRowCount();i++)
            {
                maListeDeQt= new ArrayList<>();
                mesProduits.add(model.getValueAt(i, 0).toString());
                for(int j =1;j<=sorties;j++){
                    if(model.getValueAt(i, j) == null){
                        maListeDeQt.add(0);
                    }
                    else{
                        maListeDeQt.add(parseInt(model.getValueAt(i, j).toString()));
                    }
                }
                mesListesDeQt.add(maListeDeQt);
            }
            nom = NomStation.getText();
            description = DescriptionStation.getText();
            largeur = parseInt(LargeurStation.getText());
            hauteur = parseInt(HauteurStation.getText());
            capacite = parseInt(CapaciteStation.getText());
            cheminImage = CheminImageStation.getText();
            couleur = CouleurStation.getColor();
            this.setVisible(false);
        }
        else if(matriceOK()== false){
            BoutonOK.setToolTipText("La matrice doit être valide");
        }
        else if(NomStation.getText().isEmpty()==true){
            BoutonOK.setToolTipText("La station doit posséder un nom");
        }
        else if(parseInt(nbSortie.getText()) > 5){
            BoutonOK.setToolTipText("La station ne peut avoir plus de 5 sortie");
        }
        }
        catch(NumberFormatException e) {
            BoutonOK.setToolTipText("Les champs 'Sorties', Dimensions' et 'Capacité' doivent être des nombres entiers");
        }
    }//GEN-LAST:event_BoutonOKActionPerformed

    private void CapaciteStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapaciteStationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CapaciteStationActionPerformed
    
    private boolean matriceOK() {
        boolean matriceValide = true;
        DefaultTableModel model =  (DefaultTableModel) this.TableMatrice.getModel();
        for(int i=0;i<model.getRowCount();i++)
        {
            int qt = 0;
            for(int j =1;j<=sorties;j++){
                if(model.getValueAt(i, j) == null){}
                else{
                       qt += parseInt(model.getValueAt(i, j).toString());
                }
            }
            if(qt != 100)
                matriceValide = false;
            }
        return matriceValide;
    }
    
    private void nbSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbSortieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nbSortieActionPerformed

    private void CheminImageStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheminImageStationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheminImageStationActionPerformed

    private void BoutonParcourirImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonParcourirImageActionPerformed
        int returnVal = ChoisirImageStation.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = ChoisirImageStation.getSelectedFile();
            CheminImageStation.setText(ChoisirImageStation.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_BoutonParcourirImageActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModifierStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifierStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifierStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifierStation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModifierStation dialog = new ModifierStation(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonAnnuler;
    private javax.swing.JButton BoutonOK;
    private javax.swing.JButton BoutonParcourirImage;
    private javax.swing.JTextField CapaciteStation;
    private javax.swing.JTextField CheminImageStation;
    private javax.swing.JFileChooser ChoisirImageStation;
    private javax.swing.JScrollPane ConteneurDescription;
    private javax.swing.JScrollPane ConteneurProduitsEntree;
    private javax.swing.JColorChooser CouleurStation;
    private javax.swing.JTextPane DescriptionStation;
    private javax.swing.JPanel Format;
    private javax.swing.JTextField HauteurStation;
    private javax.swing.JLabel LabelCapacite;
    private javax.swing.JLabel LabelCouleur;
    private javax.swing.JLabel LabelDescription;
    private javax.swing.JLabel LabelDimensions;
    private javax.swing.JLabel LabelImage;
    private javax.swing.JLabel LabelNbSortie;
    private javax.swing.JLabel LabelNom;
    private javax.swing.JLabel LabelProduits;
    private javax.swing.JLabel LabelSorties;
    private javax.swing.JLabel Labelmetres;
    private javax.swing.JLabel Labelx;
    private javax.swing.JTextField LargeurStation;
    private javax.swing.JTextField NomStation;
    private javax.swing.JTabbedPane Onglets;
    private javax.swing.JPanel PanneauConsole;
    private javax.swing.JPanel PanneauMatrice;
    private javax.swing.JPanel PanneauRecuperation;
    private javax.swing.JTable TableMatrice;
    private javax.swing.JTextField nbSortie;
    // End of variables declaration//GEN-END:variables
}
