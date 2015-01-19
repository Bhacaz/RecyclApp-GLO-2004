package recyclapp.utilitaires;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import recyclapp.elements.*;

/**
 *
 * Classe usine qui contient des listes d'équipements et de convoyeur, ainsi qu'un panier de produit.
 * Cette classe est utilisé pour afficher les éléments dans le schéma.
 */
public class Usine implements Serializable{
        private ArrayList<Equipement> m_listeEquipement = new ArrayList<Equipement>();
        private Dimension m_dimensions;
        
        
        public Usine()
        {
        }
        
        public Usine(Dimension d)
        {
            m_dimensions =d;
        }
        
        public Dimension reqDimensions()
        {
            return m_dimensions;
        } 

	public ArrayList<Convoyeur> reqListeConvoyeur() {
            ArrayList<Convoyeur> c = new ArrayList<Convoyeur>();
            for(Equipement x : this.m_listeEquipement)
            {
                for(Convoyeur y : x.reqConvoyeurs())
                    c.add(y);
            }
		return c;
	}
        
        public void ajouterConvoyeur(Equipement sortie, Equipement entre)
        {
            if(entre instanceof EntreeUsine)
                throw new IllegalArgumentException("Une entree d'usine n'a pas d'entree");
            else if(sortie instanceof SortieUsine)
                throw  new IllegalArgumentException("Une sortie d'usine n'a pas de sortie");
            else if(entre instanceof Station || entre instanceof SortieUsine)
            {
                for(Convoyeur x : this.reqListeConvoyeur())
                {
                    if(x.reqEntree() == entre)
                        throw new IllegalArgumentException("Il y a déja un convoyeur a l'entre de cette station");

                }
            }
            if(entre instanceof Jonction)
            {
                Jonction j = (Jonction)entre;
                j.asgNbEntree(j.reqNbEntree() + 1);
            }
            sortie.link(entre);
            this.fonction();
        }

        
        public void supprimerConvoyer(Equipement s, Equipement e)
        {
            this.reqConvoyeur(s, e).asgEntree(null);
            this.fonction();
        }

        public void supprimerConvoyeur(Convoyeur c)
        {
            if(c.reqSortie() instanceof Station){
                Station maStation = (Station) c.reqSortie();
                c.reqSortie().reqConvoyeurs().get(maStation.reqIndexConv(c)).asgEntree(null);
            }
            else{this.reqConvoyeur(c.reqSortie(), c.reqEntree()).asgEntree(null);}
            this.fonction();
        }
        
        public Convoyeur reqConvoyeur(Equipement s, Equipement e)
        {
            for(Equipement x : this.m_listeEquipement)
            {
                for(Convoyeur y : x.reqConvoyeurs())
                {
                    if(y.reqSortie() == s && y.reqEntree() == e)
                        return y;
                }
            }
            return null;
        }

        public void ajouterEquipement(Equipement e)
        {
            boolean b = this.m_listeEquipement.add(e);
            if(!b)
                throw new IllegalArgumentException("Erreur dans l'ajout de l'équipement");
        }
        
        public boolean ajouterStation(int nbSortie, String nom, String des, Color c, Dimension d, Point p, int capM)
        {
            if(validerPosition(p, d, null))
            {
                Station station = new Station(nbSortie, nom, des, c, d, p, capM);
                ajouterEquipement(station);
                return true;
            }
            else
            {
                return false;
            }
        }
   
        public boolean ajouterStation(int nbSortie, String nom, String des, Color c, Dimension d, Point p, int capM, Image image,String chemin)
        {
            if(validerPosition(p, d, null))
            {
                Station station = new Station(nbSortie, nom, des, c, d, p, capM, image, chemin);
                ajouterEquipement(station);
                return true;
            }
            else
            {
                return false;
            }
        }        
        
        public boolean ajouterEntreeUsine(String nom, String des, Dimension d, Point p)
        {
            if(validerPosition(p, d, null))
            {
                EntreeUsine entree = new EntreeUsine(nom, des, d, p);
                ajouterEquipement(entree);
                return true;
            }
            else
            {
                return false;
            }
        }
        
        public boolean ajouterEntreeUsine(String nom, String des, Dimension d, Point p,Color c)
        {
            if(validerPosition(p, d, null))
            {
                EntreeUsine entree = new EntreeUsine(nom, des, d, p, c);
                ajouterEquipement(entree);
                return true;
            }
            else
            {
                return false;
            }
        }
        
        public boolean ajouterJonction(String nom, String des,Dimension d, Point p, int m)
        {
            if(validerPosition(p,d, null))
            {
                Jonction jonction = new Jonction(nom,des,d, p, m);
                ajouterEquipement(jonction);
                return true;
            }
            else
            {
                return false;
            }
        }
        
        public boolean ajouterJonction(String nom, String des,Dimension d, Point p, int m, Color c)
        {
            if(validerPosition(p,d, null))
            {
                Jonction jonction = new Jonction(nom,des,d, p, m, c);
                ajouterEquipement(jonction);
                return true;
            }
            else
            {
                return false;
            }
        }
        public boolean ajouterSortieUsine(String nom, String des, Dimension d, Point p)
        {
            if(validerPosition(p, d, null))
            {
                SortieUsine sortie = new SortieUsine(nom, des, d, p);
                ajouterEquipement(sortie);
                return true;
            }
            else
            {
                return false;
            }
        }
        
        public boolean ajouterSortieUsine(String nom, String des, Dimension d, Point p, Color c)
        {
            if(validerPosition(p, d, null))
            {
                SortieUsine sortie = new SortieUsine(nom, des, d, p, c);
                ajouterEquipement(sortie);
                return true;
            }
            else
            {
                return false;
            }
        }
        
        public void supprimerEquipement(Equipement e)
        {
            boolean b;
            b = this.m_listeEquipement.remove(this.reqEquipement(e));
            if(b)
            {
                if (e instanceof Jonction){
                    for (Convoyeur c : this.reqConvoyeursJonction((Jonction)e)){
                        this.supprimerConvoyeur(c);
                    }
                }
                
                if(this.reqEquipementConnecterASonEntre(e) != null)
                    this.supprimerConvoyer(this.reqEquipementConnecterASonEntre(e), e);
                this.fonction();
            }
            else
                throw new IllegalArgumentException("Impossible de supprimer Equipement");
        }
        
        public ArrayList<Equipement> reqListeEquipement()
        {
            return this.m_listeEquipement;
        }
        
        public Equipement reqEquipement(Equipement e)
        {
            for(Equipement x : this.m_listeEquipement)
            {
                if(e == x)
                {
                    return x;
                }
            }
            throw new IllegalArgumentException("L'équipement n'est pas dans l'usine.");
        }
        
        public Station reqStation(Station s)
        {
            for(Equipement x : this.m_listeEquipement)
            {
                if(s == x)
                {
                    return s;
                }
            }
            throw new IllegalArgumentException("La station n'est pas dans l'usine.");
        }
        
        public Equipement reqEquipement(Point point)
        {
            for(Equipement equipement : this.m_listeEquipement)
            {
                double minX = equipement.reqPosition().getX();
                double maxX = minX + equipement.reqDimension().width-1;
                double minY = equipement.reqPosition().getY();
                double maxY = minY + equipement.reqDimension().height-1;

                if(minX <= point.getX() && point.getX() <= maxX && minY <= point.getY() && point.getY() <= maxY)
                    return equipement;
            }
            // Il n'y a pas d'équipement au point donnée
            return null;
        }
        
        
        
        public void fonction()
        {
            String str = "";
            for(Equipement x : this.m_listeEquipement)
            {
                if(!(x instanceof EntreeUsine))
                {
                    for(Convoyeur a : x.reqConvoyeurs())
                        a.asgPanier(new Panier());
                }
                for(Equipement y : this.m_listeEquipement)
                {
                    for(Convoyeur z : y.reqConvoyeurs())
                    {
                        if(z.reqEntree() == x)
                        {
                            x.fonctionSurPanier(z.reqPanier());
                        }
                    }
                }
            }
                
       //Détecter des erreur de capacite
                
            for(Equipement x : reqListeEquipement())
            {
                if(x instanceof Station || x instanceof Jonction)
                {
                   if(reqConvoyeurConnecterASonEntre(x) != null)
                    {
                        if(reqConvoyeurConnecterASonEntre(x).reqPanier().quantiteTotal() > x.reqCapacite())
                        {
                            str += "Erreur capacite Equipement : " + x.reqNomEquipement() + "\n";
                            x.erreurCapacite(true);
                        }
                        else
                        {
                            x.erreurCapacite(false);
                        }
                    }
                    else
                        x.erreurCapacite(false); 
                }
                
            }
            
            
            for(Convoyeur x : reqListeConvoyeur())
            {
                if(x.reqEntree() != null)
                {
                   if(x.reqPanier().quantiteTotal() > x.reqCapaciteMax())
                    {
                        str += "Erreur capacite Convoyeur : " + x.toString() + "\n";
                        x.erreurCapacite(true);
                    }
                    else
                        x.erreurCapacite(false); 
                }
            }
            System.out.println(str);
        }
        
        
        
        private boolean validerPosition(Point p, Dimension d, Equipement e)
        {
            d = new Dimension(d.height-1, d.width-1);
            // On renvoie false si une des coordonnées est négative
            if(p.getX() < 0 || p.getY() < 0)
                return false;
            // On renvoie false si la largeur ou la longueur passé en arg. est plus 
            // grande que la largeur ou la longueur du centre de tri
            if(d.height > reqDimensions().height || d.width > reqDimensions().width)
                return false;
            for(Equipement equipement : reqListeEquipement())
            {
                if(equipement != e)
                {
                    boolean chevauchementHorizontal = false;
                    boolean chevauchementVertical = false;
                    Point currentCoord = equipement.reqPosition();
                    Dimension currentDimensions = equipement.reqDimension();
                    // Vérification si la position donnée chevauche horizontalement
                    // la position de l'équipement courrant
                    if( (p.getX() >= currentCoord.getX() && p.getX() <= currentCoord.getX() + currentDimensions.width) 
                            || (p.getX() + d.width >= currentCoord.getX() && p.getX() + d.width <= currentCoord.getX() + currentDimensions.width)
                            || (p.getX() <= currentCoord.getX() && p.getX() + d.width >= currentCoord.getX() + currentDimensions.width))
                        chevauchementHorizontal = true;
                    // Vérification si la position donnée chevauche verticalement la
                    // position de l'équipement courrant
                    if( (p.getY() >= currentCoord.getY() && p.getY() <= currentCoord.getY() + currentDimensions.height)
                            || (p.getY() + d.height >= currentCoord.getY() && p.getY() + d.height <= currentCoord.getY() + currentDimensions.height)
                            || (p.getY() <= currentCoord.getY() && p.getY() + d.height >= currentCoord.getY() + currentDimensions.height))
                        chevauchementVertical = true;
                    // S'il y a chevauchement vertical et horizontal, la position n'est pas valide
                    if(chevauchementHorizontal && chevauchementVertical)
                        return false;
                }
            }
            // la position donnée ne chevauche aucun équipement
            return true;
         }
        
        public boolean deplacerEquipement(Equipement equipement, Point p)
        {
            // on valide la nouvelle position avec la dimension de l'équipement et le nouveau point.
            if(validerPosition(p, equipement.reqDimension(), equipement))
            {
                equipement.asgPosition(p);
                return true;
            }
            return false;
        }
        
        public Equipement reqEquipementConnecterASonEntre(Equipement e)
        {
            for(Equipement x : m_listeEquipement)
            {
                for(Convoyeur y : x.reqConvoyeurs())
                {
                    if(y.reqEntree() == e)
                        return x;
                }
                
            }
            return null;
        }
        
        public Convoyeur reqConvoyeurConnecterASonEntre(Equipement e)
        {
            for(Equipement x : m_listeEquipement)
            {
                for(Convoyeur y : x.reqConvoyeurs())
                {
                    if(y.reqEntree() == e)
                        return y;
                }
                
            }
            return null;
        }
        
        public String toString()
        {
            String str = "";
            for(Equipement x : this.m_listeEquipement)
            {
                str += x + "\n";
            }
            return str;
            
        }
        
        public ArrayList<Convoyeur> reqConvoyeursJonction(Equipement j)
        {
            ArrayList<Convoyeur> c = new ArrayList<Convoyeur>();
            for(Equipement x : m_listeEquipement)
            {
                for(Convoyeur y : x.reqConvoyeurs())
                {
                    if(y.reqEntree() == j)
                        c.add(y);
                }
                
            }
            if(c.isEmpty()){
                return null;
            }
            else{
               return c; 
            }
            
        }
        // retourne les produits en entrée d'un équipement
        public String produitEntree(Equipement e)
        {
            String str = "";
            if(e instanceof EntreeUsine)
            {
                return str;
            }
            else if(e instanceof Jonction)
            {
                if(e.reqNbEntree() == 0)
                {
                    return str;
                }
                else if(this.reqConvoyeursJonction(e) == null){
                    return str;
                }
                else{
                    for (int i = 0; i < this.reqConvoyeursJonction(e).size(); i++) 
                    {

                        str += "\nEntree "+ (i+1)+"\n";

                        for (int j = 0; j < this.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().size(); j++)
                        { 
                            str += "Nom Produit : " + this.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqNomProduit();
                            str += " Quantité : " + this.reqConvoyeursJonction(e).get(i).reqPanier().reqPanier().get(j).reqQuantiteProduit()+" kg/h"+ "\n";
                        }

                    }
                }
                return str;
                
            }
            else
            {
                    if(this.reqConvoyeurConnecterASonEntre(e) == null){
                        
                    }
                    else if(this.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size()== 0){
                        str += "Entree : Vide ";
                    }
                    else{
                        str += "Entree : \n";
                        
                        for (int j = 0; j < this.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().size(); j++)
                        { 
                            str += "Nom Produit : " + this.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(j).reqNomProduit();
                            str += " Quantité : " + this.reqConvoyeurConnecterASonEntre(e).reqPanier().reqPanier().get(j).reqQuantiteProduit()+" kg/h"+ "\n";
                        }
                    }
                    
                return str;
            }
            
        }
        
        //Fonction qui calcul si tout est connecté
        public boolean toutConnecte()
        {
            boolean calculOK = true;
            //Parcours la liste d'équipement, si on trouve un convoyeur non connecté, le calcul est false.
            for(int i=0;i < this.reqListeConvoyeur().size();i++)
            {
                if (this.reqListeConvoyeur().get(i).reqEntree() == null)
                {
                    calculOK = false;
                }
            }
            return calculOK;
        }
        //Fonction qui retourne le panier de produit des entrées
        public Panier panierEntreeUsine()
        {
            ArrayList<Equipement> m_listeEquipement = this.reqListeEquipement();
            Iterator<Equipement> it = m_listeEquipement.iterator();
            Panier p = new Panier();
            while(it.hasNext()){
                Equipement equi = it.next();
                if (equi instanceof EntreeUsine ) {
                    if(equi.reqConvoyeurs().get(0).reqPanier().reqPanier().isEmpty()){}
                    else{
                        for(int j = 0; j < equi.reqConvoyeurs().get(0).reqPanier().reqPanier().size(); j++){
                            Produit monProduit = new Produit(equi.reqConvoyeurs().get(0).reqPanier().reqPanier().get(j));
                            p.ajouterProduit(monProduit);
                        }
                    }
                }
            }
            return p;
        }
        
        public String PanierEntreeUsineToString()
        {
            return "Entree usine \n" + panierEntreeUsine().toString() + "\n\n";
        }
        
        public String PanierSortieUsineToString()
        {
            return "Sortie Usine \n" + panierSortieUsine().toString();
        }
        
        //Fonction qui retourne le panier de produit des sorties
        public Panier panierSortieUsine()
        {
            ArrayList<Equipement> m_listeEquipement = this.reqListeEquipement();
            Iterator<Equipement> it = m_listeEquipement.iterator();
            Panier p = new Panier();
            while(it.hasNext()){
                Equipement equi = it.next();
                if (equi instanceof SortieUsine ) {
                    // si la sortied'usine n'a pas de convoyeur à son entrée 
                    if(this.reqConvoyeurConnecterASonEntre(equi) == null){}
                    // si le panier est vide
                    else if(this.reqConvoyeurConnecterASonEntre(equi).reqPanier().reqPanier().isEmpty()){}
                    else{
                        for(int j = 0; j < this.reqConvoyeurConnecterASonEntre(equi).reqPanier().reqPanier().size(); j++){
                            Produit monProduit = new Produit(this.reqConvoyeurConnecterASonEntre(equi).reqPanier().reqPanier().get(j));
                            p.ajouterProduit(monProduit);
                        }
                    }
                }
            }
            return p;
        }
}