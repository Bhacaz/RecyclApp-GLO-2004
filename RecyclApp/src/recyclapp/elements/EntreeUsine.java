package recyclapp.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class EntreeUsine extends Equipement 
{
        private final int m_nbEntree = 0;
        private final int m_nbSortie = 1;
        private Color m_couleur = Color.GREEN;
        private Panier m_panier;
        public EntreeUsine(String nom)
        {

            this.m_nomEquipement = nom;
//            this.link(this);
        }
        
        public EntreeUsine()
        {
            this.m_nomEquipement = "Entre usine1";
//            this.link(this);
        }
        
        public EntreeUsine(String nom, String des, Dimension d, Point p)
        {
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_dimension = d;
            this.m_position = p;
//            this.link(this);
        }
        
        public EntreeUsine(String nom, String des, Dimension d, Point p, Color c)
        {
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_dimension = d;
            this.m_position = p;
            this.m_couleur = c;
//            this.link(this);
        }
        
        public void asgPanier(Panier p)
        {
            this.m_panier = p;
        }
        
        public void asgPanierConvoyeur() 
        {
            this.m_convoyeurs.get(0).asgPanier(m_panier);
        }
        
        public void asgCouleur(Color c)
        {
                this.m_couleur = c;
        }
        
            
        public Color reqCouleur()
        {
            return this.m_couleur;
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