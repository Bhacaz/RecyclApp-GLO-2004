/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.utilitaires;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recyclapp.elements.Convoyeur;
import recyclapp.elements.EntreeUsine;
import recyclapp.elements.Equipement;
import recyclapp.elements.Jonction;
import recyclapp.elements.Panier;
import recyclapp.elements.Produit;
import recyclapp.elements.SortieUsine;
import recyclapp.elements.Station;

/**
 *
 * @author bhacaz
 */
public class UsineTest {
    
    public UsineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ajouterConvoyeur method, of class Usine.
     */
    @Test
    public void testAjouterConvoyeur_3args() {
        System.out.println("ajouterConvoyeur");
        Station sta1 = new Station("Sta1", 2);
        Station sta2 = new Station("Sta2",2);
        Usine instance = new Usine();
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(sta1);
        
        instance.ajouterConvoyeur(sta1, sta2);
        assertEquals(sta2, instance.reqEquipement(sta1).reqConvoyeurs().get(0).reqEntree());
        
        Jonction j = new Jonction();
        instance.ajouterConvoyeur(sta2, j);
        assertEquals(j, instance.reqEquipement(sta2).reqConvoyeurs().get(0).reqEntree());
    }

    /**
     * Test of supprimerConvoyer method, of class Usine.
     */
    @Test
    public void testSupprimerConvoyer() {
        System.out.println("supprimerConvoyer");
        Station sta1 = new Station("Sta1", 2);
        int noSortie = 0;
        Station sta2 = new Station("Sta2",2);
        Station sta3 = new Station("Sta3",2);
        Usine instance = new Usine();
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(sta1);
        instance.ajouterEquipement(sta3);
        instance.ajouterConvoyeur(sta1, sta2);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(sta2, sta1.reqConvoyeurs().get(0).reqEntree());
        Jonction j = new Jonction();
        instance.ajouterEquipement(j);
        instance.ajouterConvoyeur(sta2, j);
        assertEquals(j, instance.reqEquipement(sta2).reqConvoyeurs().get(0).reqEntree());
        instance.supprimerConvoyer(sta2, j);
        assertEquals(null, sta2.reqConvoyeurs().get(0).reqEntree());
    }

    /**
     * Test of reqConvoyeur method, of class Usine.
     */
    @Test
    public void testReqConvoyeur() {
        System.out.println("reqConvoyeur");
        Station sta1 = new Station("Sta1", 2);
        Station sta2 = new Station("Sta2",2);
        Usine instance = new Usine();
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(sta1);
        instance.ajouterConvoyeur(sta1, sta2);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(sta2, sta1.reqConvoyeurs().get(0).reqEntree());
        Jonction j = new Jonction();
        instance.ajouterEquipement(j);
        instance.ajouterConvoyeur(sta2, j);
        assertEquals(j, instance.reqEquipement(sta2).reqConvoyeurs().get(0).reqEntree());
        assertEquals(sta2.reqConvoyeurs().get(0), instance.reqConvoyeur(sta2, j));
        assertEquals(sta1.reqConvoyeurs().get(0), instance.reqConvoyeur(sta1, sta2));
    }

    /**
     * Test of ajouterEquipement method, of class Usine.
     */
    @Test
    public void testAjouterEquipement() {
        System.out.println("ajouterEquipement");
        System.out.println("ajouterStation");
        Equipement p_station = new Station("Sta1", 2);
        Usine instance = new Usine();
        instance.ajouterEquipement(p_station);
        assertEquals(instance.reqEquipement(p_station), p_station);
    }

    /**
     * Test of reqListeEquipement method, of class Usine.
     */
    @Test
    public void testReqListeEquipement() {
        System.out.println("reqListeEquipement");
        Station sta1 = new Station("Sta1", 2);
        Station sta2 = new Station("Sta2",2);
        Usine instance = new Usine();
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(sta1);
        assertEquals(2, instance.reqListeEquipement().size());
    }

    /**
     * Test of reqEquipement method, of class Usine.
     */
    @Test
    public void testReqEquipement() {
        System.out.println("reqEquipement");
        Station sta1 = new Station("Sta1", 2);
        Station sta2 = new Station("Sta2",2);
        Usine instance = new Usine();
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(sta1);
        assertEquals(sta1, instance.reqEquipement(sta1));
    }

    /**
     * Test of fonction method, of class Usine.
     */
    @Test
    public void testFonction() {
        System.out.println("fonction");
        Produit a1 = new Produit("a", 100);
        Produit a2 = new Produit("a", 200);
        Produit b1 = new Produit("b", 10);
        Panier p = new Panier();
        p.ajouterProduit(a1);
        p.ajouterProduit(a2);
        p.ajouterProduit(b1);
        
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(10);
        l.add(90);
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l2.add(10);
        l2.add(90);
        
        EntreeUsine e = new EntreeUsine();
        SortieUsine s = new SortieUsine();
        Jonction j = new Jonction();
        Station sta1 = new Station("sta1", 2);
        sta1.asgALaMatrice(a1, l);
        Station sta2 = new Station("sta2",2);
        sta2.asgALaMatrice(b1, l2);
        
        Usine instance = new Usine();
        instance.ajouterEquipement(e);
        instance.ajouterEquipement(s);
        instance.ajouterEquipement(sta1);
        instance.ajouterEquipement(sta2);
        instance.ajouterEquipement(j);
        
        e.reqConvoyeurs().get(0).asgPanier(p);
        
        instance.ajouterConvoyeur(e, sta1);
        instance.ajouterConvoyeur(sta1, sta2);
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(270, sta1.reqConvoyeurs().get(1).reqPanier().getProduit("a").reqQuantiteProduit());
        assertEquals(1, sta2.reqConvoyeurs().get(0).reqPanier().getProduit("b").reqQuantiteProduit());
        assertEquals(30, sta2.reqConvoyeurs().get(0).reqPanier().getProduit("a").reqQuantiteProduit());
        
        instance.supprimerConvoyer(sta1, sta2);
        assertEquals(270, sta1.reqConvoyeurs().get(1).reqPanier().getProduit("a").reqQuantiteProduit());
        assertEquals(true, sta2.reqConvoyeurs().get(0).reqPanier().reqPanier().isEmpty());
        
        instance.ajouterConvoyeur(sta1, sta2);
        assertEquals(270, sta1.reqConvoyeurs().get(1).reqPanier().getProduit("a").reqQuantiteProduit());
        assertEquals(1, sta2.reqConvoyeurs().get(0).reqPanier().getProduit("b").reqQuantiteProduit());
        assertEquals(30, sta2.reqConvoyeurs().get(0).reqPanier().getProduit("a").reqQuantiteProduit());
        
        instance.supprimerConvoyer(sta1, sta2);
        instance.supprimerConvoyer(e, sta1);
        
        
        instance.ajouterConvoyeur(e, sta2);
        instance.ajouterConvoyeur(sta1, j);
        instance.ajouterConvoyeur(sta2,  sta1);
        instance.ajouterConvoyeur(sta2, j);
        instance.ajouterConvoyeur(j,  s);
        
        assertEquals(10, j.reqConvoyeurs().get(0).reqPanier().getProduit("b").reqQuantiteProduit());
        assertEquals(30, j.reqConvoyeurs().get(0).reqPanier().getProduit("a").reqQuantiteProduit());

        instance.supprimerEquipement(sta2);
        assertEquals(true, sta1.reqConvoyeurs().get(0).reqPanier().reqPanier().isEmpty());

    }
    
}
