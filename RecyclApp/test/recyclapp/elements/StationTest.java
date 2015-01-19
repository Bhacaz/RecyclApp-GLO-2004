/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.elements;

import java.awt.Color;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import recyclapp.utilitaires.Usine;

/**
 *
 * @author bhacaz
 */
public class StationTest {
    
    private Station s;
    
    public StationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        s = new Station();
    }
    
    @After
    public void tearDown() {
        s = null;
    }

    /**
     * Test of reqNomStation method, of class Station.
     */
    @Test
    public void testReqNomStation() {
        System.out.println("reqNomStation");
        Station instance = new Station();
        String expResult = "";
        String result = instance.reqNomStation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of reqCapaciteMax method, of class Station.
     */
    @Test
    public void testReqCapaciteMax() {
        System.out.println("reqCapaciteMax");
        Station instance = new Station();
        instance.asgCapaciteMax(1);
        int expResult = 1;
        int result = instance.reqCapaciteMax();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgNomStation method, of class Station.
     */
    @Test
    public void testAsgNomStation() {
        System.out.println("asgNomStation");
        String p_nomStation = "a";
        Station instance = new Station();
        instance.asgNomStation(p_nomStation);
        assertEquals(instance.reqNomStation(), p_nomStation);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgCapaciteMax method, of class Station.
     */
    @Test
    public void testAsgCapaciteMax() {
        System.out.println("asgCapaciteMax");
        int p_CapaciteMax = 1;
        Station instance = new Station();
        instance.asgCapaciteMax(p_CapaciteMax);
        assertEquals(instance.reqCapaciteMax(), 1);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqCouleur method, of class Station.
     */
    @Test
    public void testReqCouleur() {
        System.out.println("reqCouleur");
        Station instance = new Station();
        Color expResult = Color.BLACK;
        instance.asgCouleur(expResult);
        Color result = instance.reqCouleur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgCouleur method, of class Station.
     */
    @Test
    public void testAsgCouleur() {
        System.out.println("asgCouleur");
        Station instance = new Station();
        Color expResult = Color.BLACK;
        instance.asgCouleur(expResult);
        Color result = instance.reqCouleur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of fonctionSurPanier method, of class Station.
     */
    @Test
    public void testFonctionSurPanier() {
        System.out.println("fonctionSurPanier");
        Station instance = new Station("a", 2);
        Panier p = new Panier();
        Produit a = new Produit("a", 100);
        Produit b = new Produit("b", 50);
        Produit c = new Produit("c", 25);
        p.ajouterProduit(a);
        p.ajouterProduit(b);
        p.ajouterProduit(c);
        instance.fonctionSurPanier(p);
        
        int q = instance.reqConvoyeurs().get(0).reqPanier().quantiteTotal();
        assertEquals(175, q);
        
        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(10);
        l.add(90);
        
        instance.asgALaMatrice(a, l);
        instance.fonctionSurPanier(p);
        q = instance.reqConvoyeurs().get(0).reqPanier().quantiteTotal();
        int q2 = instance.reqConvoyeurs().get(1).reqPanier().quantiteTotal();
        assertEquals(85, q);
        assertEquals(90, q2);
        
        l = new ArrayList<Integer>();
        l.add(50);
        l.add(50);
        instance.asgALaMatrice("a", l);
        instance.fonctionSurPanier(p);
        q = instance.reqConvoyeurs().get(0).reqPanier().quantiteTotal();
        q2 = instance.reqConvoyeurs().get(1).reqPanier().quantiteTotal();
        assertEquals(125, q);
        assertEquals(50, q2);
        
        instance.asgALaMatrice(b, l);
        instance.fonctionSurPanier(p);
        q = instance.reqConvoyeurs().get(0).reqPanier().quantiteTotal();
        q2 = instance.reqConvoyeurs().get(1).reqPanier().quantiteTotal();
        assertEquals(100, q);
        assertEquals(75, q2);

        
    }
    
}
