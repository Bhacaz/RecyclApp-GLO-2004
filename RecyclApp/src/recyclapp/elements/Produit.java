package recyclapp.elements;

import java.io.Serializable;

public class Produit implements Serializable{
	private String m_nomProduit;
	private int m_quantiteProduit;
        
        public Produit()
        {this.m_quantiteProduit = 0;
        this.m_nomProduit = "";}
        
        public Produit(String s)
        {
            this.m_nomProduit = s;
            this.m_quantiteProduit = 0;
        }
        
        public Produit(String n, int q)
        {
            this.m_nomProduit = n;
            this.m_quantiteProduit = q;
        }
        
        public Produit(Produit p)
        {
            this.m_nomProduit = p.reqNomProduit();
            this.m_quantiteProduit = p.reqQuantiteProduit();
        }

	public String reqNomProduit() {
		return this.m_nomProduit;
	}

	public int reqQuantiteProduit() {
		return this.m_quantiteProduit;
	}

	public void asgNomProduit(String p_nomProduit) {
		this.m_nomProduit = p_nomProduit;
	}

	public void asgQuantiteProduit(int p_quantiteProduit) {
		this.m_quantiteProduit = p_quantiteProduit;
	}
        
        public String toString()
        {
            String str = "Nom: " + this.m_nomProduit + " - Quantite : " + this.m_quantiteProduit + " kg/h\n";
            return str;
        }
        
        // Pour création de la table "Produits disponibles" dans AjouterEntree
        public void substract(Produit p1) {
            if ((this.m_nomProduit).equals(p1.reqNomProduit())) {
                int quantite = this.m_quantiteProduit - p1.reqQuantiteProduit();
                if (quantite < 0){quantite = 0;}
                this.m_quantiteProduit=quantite;
            }
        }
}