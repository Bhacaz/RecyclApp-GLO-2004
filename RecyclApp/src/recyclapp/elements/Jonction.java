package recyclapp.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Jonction extends Equipement {
    private final int m_nbSortie = 1;
    private int m_nbEntree = 0;
    private String m_nomEquipement = "Jonction";
    private Color m_couleur;

    public Jonction()
    {
//        this.link(this);
    }
    
    public Jonction(String nom, String des, Dimension d, Point p, int m)
    {
        this.m_nomEquipement = nom;
        this.m_description = des;
        this.m_dimension = d;
        this.m_position = p;
        this.m_capaciteMax = m;
//        this.link(this);
    }

     public Jonction(String nom, String des, Dimension d, Point p, int m,Color c)
    {
        this.m_nomEquipement = nom;
        this.m_description = des;
        this.m_dimension = d;
        this.m_position = p;
        this.m_capaciteMax = m;
        this.m_couleur = c;
//        this.link(this);
    }

    public void fonctionSurPanier(Panier p)
    {
        Panier tmp = this.m_convoyeurs.get(0).reqPanier();
        Panier p2 = new Panier(p);
        tmp = tmp.additionerPanier(p2);
        this.m_convoyeurs.get(0).asgPanier(tmp);
    }
    public void asgCouleur(Color c)
        {
                this.m_couleur = c;
        }
        
            
        public Color reqCouleur()
        {
            return this.m_couleur;
        }
        
         public String reqNomEquipement()
        {
            return this.m_nomEquipement;
        }
         
         public int reqNbEntree() 
        {
		return this.m_nbEntree;
	}

	public void asgNbEntree(int p_nbEntree) 
        {
		this.m_nbEntree = p_nbEntree;
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