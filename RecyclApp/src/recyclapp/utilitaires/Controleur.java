package recyclapp.utilitaires;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import recyclapp.elements.*;


public class Controleur implements Serializable{
    private Usine m_usine;
    private Equipement m_eqSelectionner;
    private Equipement m_dernierEquipementSelectionnerClick;
    private Equipement m_EquipementClick;
    private Convoyeur m_ConvoyeurSelectionner;
    public final int PIXEL_PER_METER = 20;
    private int m_zoom;
    private GestionEtats m_etat;
    private String m_fichier; 

    public enum ModesNoeuds
    {
        ENTREE,
        SORTIE,
        JONCTION,
        STATION,
        CONVOYEUR1,
        CONVOYEUR2,
        RIEN
    }   

    public Controleur()
    {
        m_usine = new Usine(new Dimension(500,500));
        m_zoom = 1;
        m_etat = new GestionEtats();
    }
    
    public void asgZoom(int z)
    {
        if(z > 0)
            m_zoom = z;
    }
    
    public int reqZoom()
    {
        return m_zoom;
    }
    
    public void undo()
    {
        this.m_usine = this.m_etat.Undo();
    }
    
    public void redo()
    {
        this.m_usine = this.m_etat.Redo();
    }
    
    public GestionEtats reqEtats()
    {
        return this.m_etat;
    }
    
    public String reqFichier()
    {
        return this.m_fichier;
    }
    
    public boolean Save(String path)
    {
         try
        {
            FileOutputStream fOut = new FileOutputStream(path, false);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(this.m_usine);
            out.close();
            fOut.close();
            m_fichier = path;
            return true;
            }
            catch (IOException ex)
            {
            return false;
            }
    }
    
  public boolean Load(String path)
    {
    try
        {
            FileInputStream fIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fIn);
            this.m_usine = (Usine)in.readObject();
            in.close();
            fIn.close();
            this.m_fichier = path;
            m_etat = new GestionEtats();
            m_etat.SaveEtat(m_usine);
            m_zoom = 1;
            return true;
        }
    catch(IOException | ClassNotFoundException e)
        {
            return false;
        }
    }
    
    private Point convertirPointMetre(Point p)
    {
        Point pDomaine = new Point();
        pDomaine.x = (int)p.x/PIXEL_PER_METER/m_zoom;
        pDomaine.y = (int)p.y/PIXEL_PER_METER/m_zoom;

        return pDomaine;
    }

    public Usine reqUsine()
    {
        return this.m_usine;
    }

    public Dimension getDimensionCentreDeTri()
    {
        return m_usine.reqDimensions();
    }
    
    public ArrayList<Equipement> reqEquipements()
    {
        return m_usine.reqListeEquipement();
    }
    
    public boolean ajouterConvoyeur(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        Equipement destination = m_usine.reqEquipement(pDomaine);
        m_usine.ajouterConvoyeur(m_eqSelectionner, destination);
        return true;
    }
    
    public boolean enleverConvoyeur(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        Equipement destination = m_usine.reqEquipement(pDomaine);
        m_usine.supprimerConvoyer(m_eqSelectionner, destination);
        return true;
    }
    
    public boolean ajouterEquipement(Point p)
    {            
        ModesNoeuds t = reqTypeSelectionCourante();
        Point pLocationDomaine = convertirPointMetre(p);
                
        switch (t)
        {
            case ENTREE:
                return m_usine.ajouterEntreeUsine("Entree", "Ceci est une entree d'usine", new Dimension(3, 3), pLocationDomaine);
            case SORTIE:
                return m_usine.ajouterSortieUsine("Sortie", "Ceci est une sortie d'usine", new Dimension(3, 3), pLocationDomaine);
            case JONCTION:  
               return m_usine.ajouterJonction("Jonction", "Ceci est une jonction",new Dimension(3, 3), pLocationDomaine, 10000);
            case STATION:
                return m_usine.ajouterStation(2, "Station", "Ceci est une station", Color.MAGENTA, new Dimension(3, 3), pLocationDomaine, 10000);
        }
        return true;
    }
    
    public boolean ajouterEquipement(Point p, ModesNoeuds t)
    {
        Point pLocationDomaine = convertirPointMetre(p);
                
        switch (t)
        {
            case ENTREE:
                return m_usine.ajouterEntreeUsine("Entree", "Ceci est une entree d'usine", new Dimension(5, 5), pLocationDomaine);
            case SORTIE:
                return m_usine.ajouterSortieUsine("Sortie", "Ceci est une sortie d'usine", new Dimension(5,5), pLocationDomaine);
            case JONCTION:  
               return m_usine.ajouterJonction("Jonction", "Ceci est une jonction", new Dimension(3,3), pLocationDomaine, 100);
            case STATION:
                return m_usine.ajouterStation(2, "Station", "Ceci est une station", Color.MAGENTA, new Dimension(3,3), pLocationDomaine, 100);
        }
        return true;
    }
    
    public boolean ajouterEquipement(int s, String nom, String des, Color c, Dimension d, Point p, int capM, ModesNoeuds t)
    {            
        Point pLocationDomaine = convertirPointMetre(p);
        switch (t)
        {
            case ENTREE:
                return m_usine.ajouterEntreeUsine(nom, des, new Dimension(3,3), pLocationDomaine, c);
            case SORTIE:
                return m_usine.ajouterSortieUsine(nom, des, new Dimension(3,3), pLocationDomaine,c);
            case JONCTION:  
               return m_usine.ajouterJonction(nom,des,new Dimension(3,3), pLocationDomaine, capM, c);
            case STATION:
                return m_usine.ajouterStation(s, nom, des, c, d, pLocationDomaine, capM);
        }
        return true;
    }
    
        public boolean ajouterEquipement(int s, String nom, String des, Color c, Dimension d, Point p, int capM, ModesNoeuds t, Image image, String chemin)
    {            
        Point pLocationDomaine = convertirPointMetre(p);
        switch (t)
        {
            case ENTREE:
                return m_usine.ajouterEntreeUsine(nom, des, new Dimension(3,3), pLocationDomaine);
            case SORTIE:
                return m_usine.ajouterSortieUsine(nom, des, new Dimension(3,3), pLocationDomaine);
            case JONCTION:  
               return m_usine.ajouterJonction(nom,des,new Dimension(3,3), pLocationDomaine, capM);
            case STATION:
                return m_usine.ajouterStation(s, nom, des, c, d, pLocationDomaine, capM, image, chemin);
        }
        return true;
    }
        
    public void supprimerEquipementSelection()
    {
        m_usine.supprimerEquipement(m_EquipementClick);
        m_EquipementClick = null;
    }
    
    public boolean deplacerEquipmentSelection(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        return m_usine.deplacerEquipement(m_eqSelectionner, pDomaine);
    }
    
    public boolean equipementPresent(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        return (m_usine.reqEquipement(pDomaine) != null);
    }    
    
    public Equipement selectEquipement(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        return m_eqSelectionner = m_usine.reqEquipement(pDomaine);
    }
    
    public Equipement reqEquipementSelectionner()
    {
        return m_eqSelectionner;
    }
    
    public void asgEquipementSelectionnerClick(Point p)
    {
        Point pDomaine = convertirPointMetre(p);
        Equipement e = selectEquipement(p);
        if(e != null)
            m_dernierEquipementSelectionnerClick = m_EquipementClick;
        m_EquipementClick = m_usine.reqEquipement(pDomaine);
    }
    
    public void asgConvoyeurSelectionnerClick(Convoyeur c)
    {
        m_ConvoyeurSelectionner = c;
    }
    
    public Convoyeur reqConvoyeurSelectionnerClick()
    {
        return m_ConvoyeurSelectionner;
    }
    
    public Equipement reqEquipementSelectionnerClick()
    {
        return m_EquipementClick;
    }
    
    public Equipement reqDerneirEquipementSelectionnerClick()
    {
        return m_dernierEquipementSelectionnerClick;
    }

    
    public Equipement getSelectionCourante()
    {
        return m_eqSelectionner;
    }
    
    public ModesNoeuds reqTypeSelectionCourante()
    {
        if (m_eqSelectionner instanceof EntreeUsine)
            return ModesNoeuds.ENTREE;
        else if (m_eqSelectionner instanceof SortieUsine)
            return ModesNoeuds.SORTIE;
        else if (m_eqSelectionner instanceof Jonction)
            return ModesNoeuds.JONCTION;
        else if (m_eqSelectionner instanceof Station)
            return ModesNoeuds.STATION;
        else
            return null;
    }
    
    public ModesNoeuds reqTypeSelectionCourante(Point p)
    {
        m_eqSelectionner = m_usine.reqEquipement(p);
        if (m_eqSelectionner instanceof EntreeUsine)
            return ModesNoeuds.ENTREE;
        else if (m_eqSelectionner instanceof SortieUsine)
            return ModesNoeuds.SORTIE;
        else if (m_eqSelectionner instanceof Jonction)
            return ModesNoeuds.JONCTION;
        else if (m_eqSelectionner instanceof Station)
            return ModesNoeuds.STATION;
        else
            return null;
    }
    
    public void asgPanierEntree(JTable table, EntreeUsine e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Panier panier = new Panier();
        for(int i=0;i<table.getRowCount();i++) {
            String nom = table.getValueAt(i, 0).toString();
            int quantite = Integer.parseInt(table.getValueAt(i, 1).toString());
            Produit produit = new Produit(nom, quantite);
            panier.ajouterProduit(produit);
        }
        e.asgPanier(panier);
        e.asgPanierConvoyeur();
    }
    
    // fonction qui modifie une station
    public void modifierStation(Station s,String p_nom, String desc,int cap,Dimension d, int sortie,Color c, ArrayList<String> mesProduits,ArrayList<ArrayList<Integer>> mesListesDeQt, String chemin, Image image)
    {
        this.m_usine.reqStation(s).asgNomEquipement(p_nom);
        this.m_usine.reqStation(s).asgDescription(desc);
        this.m_usine.reqStation(s).asgCapaciteMax(cap);
        this.m_usine.reqStation(s).asgDimension(d);
        this.m_usine.reqStation(s).asgCouleur(c);
        this.m_usine.reqStation(s).asgNbSortie(sortie);
        this.m_usine.reqStation(s).asgCheminImage(chemin);
        this.m_usine.reqStation(s).asgImage(image);
        
        this.m_usine.reqStation(s).clearMaMatrice();
        // assigne la matrice à la station
        for (int i = 0; i < mesProduits.size();i++){
                this.m_usine.reqStation(s).asgALaMatrice(mesProduits.get(i), mesListesDeQt.get(i));
        }
        // ajout des calculs pour pousser aux convoyeurs
        this.m_usine.fonction();
    }
    
    public void ajouterMatriceStation(Station s, Panier p){
        ArrayList<Integer> malisteInit = new ArrayList<Integer>();
        for (int i = 0; i < this.m_usine.reqStation(s).reqNbSortie() ;i++){
            if(i==0)
                malisteInit.add(i, 100);
            else
                malisteInit.add(i, 0);
        }
        this.m_usine.reqStation(s).clearMaMatrice();
        for (int i = 0; i < p.reqPanier().size();i++){
            
            this.m_usine.reqStation(s).asgALaMatrice(p.reqPanier().get(i).reqNomProduit(),malisteInit);
        } 
    }
    
    public String equipementToString(Equipement e){
        String str = new String();
        if (e instanceof EntreeUsine){
            str += "Sortie : "+"\n";
            if(e.reqConvoyeurs().get(0).reqPanier().reqPanier().size() == 0){
                    str += "Vide"+"\n";
            }
            
            for(int i = 0; i < e.reqConvoyeurs().get(0).reqPanier().reqPanier().size(); i++){
                str += e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).toString();
                if(this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit() == 0)
                    str +="Taux de récupération : 0%";
                else if(this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()!= 0 )
                    str +="Taux de récupération : " + (e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100 / this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit())+ "%";
                if(e.reqConvoyeurs().get(0).reqPanier().quantiteTotal() == 0)
                    str +=" Taux de pureté : 0%"+ "\n";
                else if(e.reqConvoyeurs().get(0).reqPanier().quantiteTotal() != 0){
                    str +=" Taux de pureté : " + ((e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100/ e.reqConvoyeurs().get(0).reqPanier().quantiteTotal()))  + "%" + "\n";
                }
            }
            return str;
        }
        
        
        else if (e instanceof SortieUsine){
            if(this.m_usine.reqConvoyeurConnecterASonEntre(e) == null){}
            else if(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size()== 0)
                        str += "Entree : Vide "+"\n";
            else{
                str += "Entree : "+"\n";
                for(int i = 0; i < this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size(); i++){
                    str += this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).toString();
                    if(this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()== 0)
                        str +="Taux de récupération : 0%";
                    else if (this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()!=0)
                        str +="Taux de récupération : " + (this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqQuantiteProduit()* 100 /this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit())  + "%";                       
                    if(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal() == 0)
                        str +=" Taux de pureté : 0%"+ "\n";
                    else if (this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal() != 0){
                        str +=" Taux de pureté : " + ((this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100/ this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal()))  + "%" + "\n";
                    }
                }
            }
            return str;
        }
        
        
        else if (e instanceof Jonction){
            if(e.reqNbEntree() == 0){}
            else if(this.m_usine.reqConvoyeursJonction(e) == null){}
            else{
                for (int i = 0; i < this.m_usine.reqConvoyeursJonction(e).size(); i++) 
                    {
                     str += "\nEntree "+ (i+1)+"\n";
                        for (int j = 0; j < this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().size(); j++)
                        { 
                            str += this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).toString();
                            if(this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqNomProduit()).reqQuantiteProduit()==0)
                                str +="Taux de récupération : 0%";
                            else if(this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqNomProduit()).reqQuantiteProduit()!=0)
                                str +="Taux de récupération : " + (this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit()* 100 /this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqNomProduit()).reqQuantiteProduit() ) + "%";                                
                            if(this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().quantiteTotal()==0)
                                str +=" Taux de pureté : 0%"+ "\n";
                            else if (this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().quantiteTotal()!=0){
                                str +=" Taux de pureté : " + ((this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit() * 100/ this.m_usine.reqConvoyeursJonction(e).get(i).reqPanier().quantiteTotal()))  + "%" + "\n";
                            }
                        }
                    }
                }
            
            //sortie
            str += "\n"+ "Sortie : "+"\n";
            if(e.reqConvoyeurs().get(0).reqPanier().reqPanier().size() == 0)
                    str += "Vide"+"\n";
            else{
                
                for(int i = 0; i < e.reqConvoyeurs().get(0).reqPanier().reqPanier().size(); i++){
                    str += e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).toString();
                    if(this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit() == 0)
                         str +="Taux de récupération : 0%";
                    else if(this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()!=0)
                         str +="Taux de récupération : " + ( e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqQuantiteProduit()* 100 /this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()) + "%";
                    if(e.reqConvoyeurs().get(0).reqPanier().quantiteTotal()==0)
                         str +=" Taux de pureté : 0%"+ "\n";
                    else if (e.reqConvoyeurs().get(0).reqPanier().quantiteTotal()!=0){
                        str +=" Taux de pureté : " + ((e.reqConvoyeurs().get(0).reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100/ e.reqConvoyeurs().get(0).reqPanier().quantiteTotal()))  + "%" + "\n";
                    }
                }
            }
            return str;
        }
        else if (e instanceof Station){
            //entree
            if(this.m_usine.reqConvoyeurConnecterASonEntre(e) == null){}
            else if(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size()== 0)
                 str += "Entree : Vide ";
            else{
                str += "Entree : \n";
           
                for(int i = 0; i < this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size(); i++){
                    str += this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).toString();
                    if (this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit() == 0)
                        str +="Taux de récupération : 0 %";
                    else if(this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()!= 0)
                        str +="Taux de récupération : " + (this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqQuantiteProduit())* 100/this.m_usine.panierEntreeUsine().getProduit(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit() + "%";
                    if(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal() == 0){
                        str +=" Taux de pureté : 0 %" + "\n";
                    }
                    
                    else if(this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal() != 0)
                        str +=" Taux de pureté : " + ((this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100/ this.m_usine.reqConvoyeurConnecterASonEntre(e).reqPanier().quantiteTotal()))  + "%" + "\n";
                    
                }
            }
            // sorties
            for (int i = 0; i < e.reqNbSortie(); i++) {
                str += "\n"+ "\nSortie " + (i+1) +" : \n";
                if(e.reqConvoyeurs().get(i).reqPanier().reqPanier().isEmpty())
                    str += "Vide"+"\n";
                else{
                    for (int j = 0; j < e.reqConvoyeurs().get(i).reqPanier().reqPanier().size(); j++){ 
                        str += e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).toString();

                        if (e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit()==0)
                            str +="Taux de récupération : 0 %";
                        else if(e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit()!=0)
                            str +="Taux de récupération : " + (e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit()) * 100/ this.m_usine.panierEntreeUsine().getProduit(e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).reqNomProduit()).reqQuantiteProduit() + "%";
                        if(e.reqConvoyeurs().get(i).reqPanier().quantiteTotal()==0)
                            str +=" Taux de pureté : 0 %" + "\n";
                        else if (e.reqConvoyeurs().get(i).reqPanier().quantiteTotal()!=0){
                            
                            str +=" Taux de pureté : " + ((e.reqConvoyeurs().get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit() * 100/ e.reqConvoyeurs().get(i).reqPanier().quantiteTotal()))  + "%" + "\n";
                        }
                    }
                }
            }
            return str;
        }
        return str;
    }
    
    public String convoyeurToString(Convoyeur c){
        String str = new String();
        if(c.reqPanier().reqPanier().size() == 0)
                    str += "Vide"+"\n";
            
            
        for(int i = 0; i < c.reqPanier().reqPanier().size(); i++){
            str += c.reqPanier().reqPanier().get(i).toString();
            if(this.m_usine.panierEntreeUsine().getProduit(c.reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit() == 0)
                str +="Taux de récupération : 0%";
            else if(this.m_usine.panierEntreeUsine().getProduit(c.reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit()!= 0 )
                str +="Taux de récupération : " + (c.reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100 / this.m_usine.panierEntreeUsine().getProduit(c.reqPanier().reqPanier().get(i).reqNomProduit()).reqQuantiteProduit())+ "%";
            if(c.reqPanier().quantiteTotal() == 0)
                str +=" Taux de pureté : 0%"+ "\n";
            else if(c.reqPanier().quantiteTotal() != 0){
                str +=" Taux de pureté : " + ((c.reqPanier().reqPanier().get(i).reqQuantiteProduit() * 100/ c.reqPanier().quantiteTotal()))  + "%" + "\n";
            }
       }
        str += " Capacité : " + c.reqCapaciteMax();
        return str;
    }
}
