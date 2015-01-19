package recyclapp.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class SortieUsine extends Equipement {
    
        private final int m_nbEntree = 1;
        private Color m_couleur = Color.CYAN;
        
        public SortieUsine(String nom)
        {
            this.m_nomEquipement = nom;
            this.m_convoyeurs = new ArrayList<Convoyeur>();
        }
        
        public SortieUsine()
        {
            this.m_nomEquipement = "Sortie Usine1";
            this.m_convoyeurs = new ArrayList<Convoyeur>();
        }
        
        public SortieUsine(String nom, String des, Dimension d, Point p)
        {
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_dimension = d;
            this.m_position = p;
            this.link(this);
        }
        public SortieUsine(String nom, String des, Dimension d, Point p,Color c)
        {
            this.m_nomEquipement = nom;
            this.m_description = des;
            this.m_dimension = d;
            this.m_position = p;
            this.m_couleur = c;
            this.link(this);
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