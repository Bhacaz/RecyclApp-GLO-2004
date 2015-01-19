//Important les Convoyeur et station vont toujours dans la meme direction
// 
//Soit de SORTIE --> ENTREE
//
// Un convoyeur est cree par default sur la SORTIE d'un equipement et
// Relier a lui meme

package recyclapp.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Convoyeur implements Serializable{
	private Equipement m_Entree;
	private Equipement m_Sortie;
	private int m_capaciteMax = 100000;
	private Color m_couleur = Color.BLACK;
	private Panier m_panier;
        private boolean m_red = false;

	public Convoyeur(Equipement p_Sortie, Equipement p_Entree) 
        {
		this.m_Entree = p_Entree;
                this.m_Sortie = p_Sortie;
                this.m_panier = new Panier();
	}
        
        public Convoyeur(Equipement s)
        {
            this.m_Entree = null;
            this.m_Sortie = s;
            this.m_panier = new Panier();
        }
        
        public Convoyeur()
        {
            this.m_Entree = null;
            this.m_Sortie = null;
            this.m_panier = new Panier();
        }
        
        public void erreurCapacite(boolean b)
        {
            m_red = b;
        }
        

	public Equipement reqEntree() 
        {
		return this.m_Entree;
	}

	public Equipement reqSortie() {
		return this.m_Sortie;
	}

	public int reqCapaciteMax() {
		return this.m_capaciteMax;
	}

	public void asgEntree(Equipement p_Entree) {
		this.m_Entree = p_Entree;
	}
        
	public void asgSortie(Equipement p_Sortie) {
		this.m_Sortie = p_Sortie;
	}

	public void asgCapaciteMax(int p_capaciteMax) 
        {
		this.m_capaciteMax= p_capaciteMax;
	}

        public Panier reqPanier()
        {
            return this.m_panier;
        }
        
        public void asgPanier(Panier p)
        {
            this.m_panier = p;
        }
        
        public Color reqCouleur()
        {
            if(m_red)
                return Color.RED;
            else
                return this.m_couleur;
        }
        
        public void asgCouleur(Color p_couleur)
        {
            this.m_couleur = p_couleur;
        }
        
        public String toString()
        {
            String str = "";
            if(this.m_Entree == null)
                str += "  -->  null";
            else
                str    += this.m_panier.toString();
            return str;
        }
}