package recyclapp.elements;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;


public class Equipement implements Serializable {
	protected int m_nbEntree = 1;
	protected int m_nbSortie = 1;
	protected Point m_position;
	protected ArrayList<Convoyeur> m_convoyeurs = new ArrayList<Convoyeur>(); //Premier convoyeur est à l'entrer de la station
        protected String m_nomEquipement = "";
        protected String m_description = "";
        protected Color m_couleur;
        protected Dimension m_dimension = new Dimension(1, 1);
        protected Image  m_image;
        protected int m_capaciteMax;
        protected boolean m_red = false;

        public Equipement()
        {
            this.link(this);
        }
        
        public Equipement(Point p)
        {
            this.m_position = p;
            this.link(this);
        }
        
        public Equipement(int e, int s, Point p, String n, String d, Color c, Dimension di, Image i)
        {
            this.m_nbEntree = e;
            this.m_nbSortie = s;
            this.m_position = p;
            this.m_nomEquipement = n;
            this.m_description = d;
            this.m_couleur = c;
            this.m_dimension = di;
            this.m_image = i;
            this.link(this);
        }
        
        public int reqCapacite()
        {
            return m_capaciteMax;
        }
        
        public void asgCapacite(int c)
        {
            m_capaciteMax = c;
        }
        
        public void erreurCapacite(boolean b)
        {
            m_red = b;
        }
        
        public void asgImage(Image i)
        {
            this.m_image = i;
        }
        
        public Image reqImage()
        {
            return this.m_image;
        }
        
        public void asgDimension(Dimension d)
        {
            this.m_dimension = d;
        }
        
        public Dimension reqDimension()
        {
            return this.m_dimension;
        }
        
        public void asgCouleur(Color c)
        {
            this.m_couleur = c;
        }
        
        public Color reqCouleur()
        {
            if(m_red)
                return Color.RED;
            else
                return m_couleur;
        }
        
        public void asgDescription(String d)
        {
            this.m_description = d;
        }
        
        public String reqDescription()
        {
            return this.m_description;
        }

	public int reqNbEntree() 
        {
		return this.m_nbEntree;
	}

	public int reqNbSortie() 
        {
		return this.m_nbSortie;
	}

	public void asgNbEntree(int p_nbEntree) 
        {
		this.m_nbEntree = p_nbEntree;
	}

	public void asgNbSortie(int p_nbSortie) 
        {
		this.m_nbSortie = p_nbSortie;
	}

	public Point reqPosition() 
        {
		return this.m_position;
	}

	public void asgPosition(Point p_position) 
        {
		this.m_position = p_position;
	}
        
        public void asgNomEquipement(String n)
        {
            this.m_nomEquipement = n;
        }
	public ArrayList<Convoyeur> reqConvoyeurs() 
        {
		return this.m_convoyeurs;
	}
        
        public void link(Equipement entre)
        {
            Convoyeur n = new Convoyeur();
            if(entre == this)
            {
                n.asgSortie(this);
                this.m_convoyeurs.add(n);
            }
            else
            {
                for(Convoyeur x : this.m_convoyeurs)
                {
                    if(x.reqEntree() == null)
                    {
                        x.asgEntree(entre);
                        return;
                    }
                }
            }
            
        }

        
        public void link(int index, Equipement entree)
        {
            if(entree != this)
            {
                this.m_convoyeurs.get(index).asgEntree(entree);
            }
            else
                throw new IllegalArgumentException("Une station ne peut pas être lier a elle même");
        }
        
        
        public String reqNomEquipement()
        {
            return this.m_nomEquipement;
        }
        
        public void fonctionSurPanier(Panier p)
        {
            
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
        

     public String PanierToString()
     {
         String str = "";
         for(Convoyeur x : m_convoyeurs)
         {
             str += "\nSortie " + (m_convoyeurs.indexOf(x)+1) + " \n";
             str += x.reqPanier().toString();
         }
         
         return str;
     }
}