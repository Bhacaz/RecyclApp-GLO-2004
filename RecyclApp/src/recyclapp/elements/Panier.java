package recyclapp.elements;

import java.io.Serializable;
import java.util.ArrayList;



public class Panier implements Serializable{
	private ArrayList<Produit> m_panierProduit;

    /**
     * Constructeur par défaut de Panier, qui crée un panier par défaut avec aucun élément
     */
        public Panier() 
        {
            this.m_panierProduit = new ArrayList<Produit>();
	}
        
        public Panier(Panier p)
        {
            this.m_panierProduit = new ArrayList<Produit>();
            for(Produit x : p.reqPanier())
            {
                if(x != null)
                this.m_panierProduit.add(new Produit(x));
            }
        }

	public void ajouterProduit(Produit p) 
        {
            int i = this.getProduitIndex(p.reqNomProduit());
            int q = p.reqQuantiteProduit();
            if(i != -1)
            {
                this.modifierQuantiteProduit(p.reqNomProduit(), q);
                return;
            }
            this.m_panierProduit.add(p);
	}

	public int quantiteTotal() 
        {
		int quantite = 0;
                for(Produit x : this.m_panierProduit)
                {
                    quantite += x.reqQuantiteProduit();
                }
                return quantite;
	}

	public void enleverProduit(Produit p) 
        {
		this.m_panierProduit.remove(this.getProduitIndex(p));
	}
        
        public void asgPanier(ArrayList<Produit> p)
        {
            this.m_panierProduit = p;
        }

	public ArrayList<Produit> reqPanier() 
        {
		return this.m_panierProduit;
	}
        
        public Produit getProduit(Produit p)
        {
            return m_panierProduit.get(this.getProduitIndex(p));
        }
        
        public Produit getProduit(String p)
        {
            return m_panierProduit.get(this.getProduitIndex(p));
        }
        
        public int getProduitIndex(Produit p)
        {
            return this.getProduitIndex(p.reqNomProduit());
        }
        
        public int getProduitIndex(String p)
        {
            for(int i = 0; i < this.m_panierProduit.size(); i++)
            {
                if(p.equals(this.m_panierProduit.get(i).reqNomProduit()))
                {
                    return i;
                }   
            }
            return -1;
        }
        
        public void modifierQuantiteProduit(Produit p, int quantite)
        {
            Produit pGet = this.getProduit(p);
            int index = this.getProduitIndex(p);
            this.enleverProduit(p);
            
            int qIni = pGet.reqQuantiteProduit();
            int qNew = qIni + quantite;
            
            pGet.asgQuantiteProduit(qNew);
            
            if(qNew  > 0)
            {
                this.m_panierProduit.add(index, pGet);
            }
        }
        
        public void modifierQuantiteProduit(String p, int quantite)
        {
            Produit pGet = this.getProduit(p);
            int index = this.getProduitIndex(p);
            this.enleverProduit(pGet);
            
            int qIni = pGet.reqQuantiteProduit();
            int qNew = qIni + quantite;
            
            pGet.asgQuantiteProduit(qNew);
            
            if(qNew  > 0)
            {
                this.m_panierProduit.add(index, pGet);
            }
        }
        
        public Panier additionerPanier(Panier p)
        {
            Panier tmp = new Panier();
            for(Produit x : p.reqPanier())
                tmp.ajouterProduit(x);
            for(Produit x : this.reqPanier())
                tmp.ajouterProduit(x);
            return tmp;
        }
        
        public Panier soustrairePanier(Panier p1, Panier p2)
        {
            Panier p2tmp = new Panier(p2);
            Panier p3 = new Panier();
            for(Produit p : p1.reqPanier()){
                Produit res = new Produit(p);
                for(Produit r : p2tmp.reqPanier()) {
                    res.substract(r);
                    if (r.reqNomProduit().equals(p.reqNomProduit())) { // Si r a bien été soustrait, on peut l'enlever pour accélérer le processus (+ Safeguard contre 2 produits aux noms identique dans p1)
                        p2tmp.enleverProduit(r);
                    }
                }
                p3.ajouterProduit(res);
            }
            return p3;
        }
        
        public String toString()
        {
            String str = "";
            for(Produit x : this.m_panierProduit)
            {
                str += x.toString();
            }
            return str;
        }
}