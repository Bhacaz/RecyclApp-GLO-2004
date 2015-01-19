package recyclapp.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Station extends Equipement implements Serializable{
        private static int m_nbEntree = 1;
        private Map<Produit, ArrayList<Integer>> m_matrice = new HashMap<Produit, ArrayList<Integer>>();
        private String CheminImage;
        
        public Station()
        {    
            
        }
        
        public String reqCheminImage(){
            return CheminImage;
    }
        
        public void asgCheminImage(String chemin){
            CheminImage = chemin;
        }
     public Station(String nom, int s, int cMax)
        {
            this.m_nomEquipement = nom;
            this.m_capaciteMax = cMax;
            this.m_nbSortie = s;
            for(int i = 0; i < this.m_nbSortie - 1; i++)
                this.link(this);
        }
        
        public Station(String nom, int s)
        {
            this.m_nomEquipement = nom;
            if(s <= 0)
                throw new IllegalArgumentException("Une station doit avoir au moins 1 sortie.");
            else
                this.m_nbSortie = s;
            
            for(int i = 0; i < this.m_nbSortie - 1; i++)
                this.link(this);
        }
        
        public Station(int nbSortie, String nom, String des, Color c, Dimension d, Point p, int capM)
        {
            if(nbSortie <= 0)
                throw new IllegalArgumentException("Une station doit avoir au moins 1 sortie.");
            else
                this.m_nbSortie = nbSortie;
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_couleur = c;
            this.m_dimension = d;
            this.m_position = p;
            this.m_capaciteMax =capM;
            for(int x = 0; x < this.m_nbSortie - 1; x++)
            {
                this.link(this);
            }
                
        }
        
        public Station(int nbSortie, String nom, String des, Color c, Dimension d, Point p, int capM, Image image, String chemin)
        {
            if(nbSortie <= 0)
                throw new IllegalArgumentException("Une station doit avoir au moins 1 sortie.");
            else
                this.m_nbSortie = nbSortie;
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_couleur = c;
            this.m_dimension = d;
            this.m_position = p;
            this.m_capaciteMax =capM;
            this.m_image = image;
            this.CheminImage = chemin;
            for(int x = 0; x < this.m_nbSortie; x++)
                this.link(this);
        }        
        
        public void asgALaMatrice(Produit p, ArrayList<Integer> quan)
        {
            int sum = 0;
            for(int x : quan)
                sum += x;
            if(quan.size() != this.m_nbSortie)
                throw new IllegalArgumentException("La quantité d'argument de la matrice doit corresprondre au nombre de sortie de la station");
            else if(sum != 100)
                throw new IllegalArgumentException("La somme des pourcentage de produit doit donné 100%");
            else
            {
                if(m_matrice.containsKey(p))
                    this.m_matrice.remove(p);
                this.m_matrice.put(p, quan);
            }
        }
        
        public void asgALaMatrice(String pr, ArrayList<Integer> quan)
        {
            Produit p = new Produit();
            p.asgNomProduit(pr);
            
            int sum = 0;
            for(int x : quan)
                sum += x;
            if(quan.size() != this.m_nbSortie)
                throw new IllegalArgumentException("La quantité d'argument de la matrice doit corresprondre au nombre de sortie de la station");
            else if(sum != 100)
                throw new IllegalArgumentException("La somme des pourcentage de produit doit donné 100%");
            else
            {
                //parcours la liste et si un nom est pareil, on le supprime et on ajoute le nouveau
                Iterator it = m_matrice.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry mapEntry = (Map.Entry) it.next();
                    Produit keyValue = (Produit) mapEntry.getKey();
                    if(keyValue.reqNomProduit().equals(pr)){
                    it.remove();
                    }
                }
                this.m_matrice.put(p, quan);
            }
        }
        
        public Map<Produit, ArrayList<Integer>> reqMatrice()
        {
            return this.m_matrice;
        }

	public int reqCapaciteMax() {
		return this.m_capaciteMax;
	}

	public void asgNomStation(String p_nomStation) 
        {
		this.m_nomEquipement = p_nomStation;
	}

	public void asgCapaciteMax(int p_CapaciteMax) 
        {
		this.m_capaciteMax = p_CapaciteMax;
	}
        
        public String reqNomStation()
        {
           return super.reqNomEquipement();
        }
        
        public void fonctionSurPanier(Panier p)
        {
            Panier panierEntree = new Panier(p);
            Panier panierEntree2 = new Panier(p);
            //Initialisation des panier des convoyeurs
            for(int i = 0; i<this.reqConvoyeurs().size();i++)
                this.m_convoyeurs.get(i).asgPanier(new Panier());
            
            if(this.m_matrice.isEmpty())
            {
                this.m_convoyeurs.get(0).asgPanier(panierEntree);
                return;
            }
            
            for(Produit x : panierEntree.reqPanier())
            {
                Produit produitEntree = new Produit(x);
//                if(!this.m_matrice.isEmpty())
//                {
                    for(Produit y : this.m_matrice.keySet())
                    {
                        Produit produitMatrice = new Produit(y);
                        if(produitEntree.reqNomProduit().equals(produitMatrice.reqNomProduit()))
                        {
                            for(int i = 0; i < this.m_matrice.get(y).size(); i++)
                            {
                                int nouvelleQuantité = (int)produitEntree.reqQuantiteProduit()*this.m_matrice.get(y).get(i)/100;
                                Produit tmp = new Produit(produitEntree.reqNomProduit(), nouvelleQuantité);
                                if(tmp.reqQuantiteProduit()>0)
                                    this.m_convoyeurs.get(i).reqPanier().ajouterProduit(tmp);
                            }
                            panierEntree2.enleverProduit(y);
                        }
//                        else
//                            this.m_convoyeurs.get(0).reqPanier().ajouterProduit(produitEntree);
                    }
//                }
//                else
//                    this.m_convoyeurs.get(0).asgPanier(panierEntree);
            }
            this.m_convoyeurs.get(0).asgPanier(this.m_convoyeurs.get(0).reqPanier().additionerPanier(panierEntree2));
        }
        
        public void clearMaMatrice(){
            m_matrice.clear();
        }
        
        public void asgNbSortie(int p_nbSortie){
            //ajout convoyeurs
            if (p_nbSortie > m_nbSortie){
                for(int i = 0;i < (p_nbSortie - m_nbSortie);i++)
                    this.link(this);
            }
            // supression convoyeurs
            else if(p_nbSortie < m_nbSortie){
                for(int i = 0;i < (m_nbSortie - p_nbSortie);i++){
                    this.m_convoyeurs.remove(this.m_convoyeurs.size()-1);
                }
            }
            if(p_nbSortie <= 0)
                throw new IllegalArgumentException("Une station doit avoir au moins 1 sortie.");
            else
                this.m_nbSortie = p_nbSortie;
        }
        
        public Color reqVraiCouleur(){
            return this.m_couleur;
        }
        
        public int reqIndexConv(Convoyeur c){
            for(int i = 0; i < this.m_convoyeurs.size();i++)
                if (this.m_convoyeurs.get(i)== c){
                    return i;
                }
            return -1;
        }
        public String toString()
        {
            String str = "";
            str += "Nom : " + this.m_nomEquipement + "\n";
            str += "Description : " + this.m_description + "\n";
            str += "Nombre d'entree : " + this.m_nbEntree + "\n";
            str += "Nombre de sortie : " + this.m_nbSortie + "\n";
            str += "Couleur : " + "#" + Integer.toHexString(this.m_couleur.getRGB()).substring(2) + "\n";
            str += "Dimension : " + this.m_dimension.getHeight() + " x " + this.m_dimension.getWidth() + "\n";
            str += "Position : (" + this.m_position.getX() + ", " + this.m_position.getY() + ")\n";
            str += "Capacité : " + this.m_capaciteMax + "\n";
            return str;
        }
}