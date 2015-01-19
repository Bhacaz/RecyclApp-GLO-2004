/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.elements;

import java.awt.Color;
import java.awt.Graphics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bhacaz
 */
public class ConvoyeurTest {
    
    public ConvoyeurTest() {
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
     * Test of reqEntree method, of class Convoyeur.
     */
    @Test
    public void testReqEntree() {
        System.out.println("reqEntree");
        Convoyeur instance = new Convoyeur(new Station("a",2));
        Equipement expResult = new Station("b",2);
        instance.asgEntree(expResult);
        Equipement result = instance.reqEntree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqSortie method, of class Convoyeur.
     */
    @Test
    public void testReqSortie() {
        System.out.println("reqSortie");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        Equipement expResult = a;
        Equipement result = instance.reqSortie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqCapaciteMax method, of class Convoyeur.
     */
    @Test
    public void testReqCapaciteMax() {
        System.out.println("reqCapaciteMax");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        int expResult = 1;
        instance.asgCapaciteMax(1);
        int result = instance.reqCapaciteMax();
        assertEquals(expResult, result);
    }

    /**
     * Test of asgEntree method, of class Convoyeur.
     */
    @Test
    public void testAsgEntree() {
        System.out.println("asgEntree");
        Station a = new Station("a",2);
        Equipement p_Entree = new Station("b",2);
        Convoyeur instance = new Convoyeur(a);
        instance.asgEntree(p_Entree);
        assertEquals(p_Entree, instance.reqEntree());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgSortie method, of class Convoyeur.
     */
    @Test
    public void testAsgSortie() {
        System.out.println("asgSortie");
        Station a = new Station("a",2);
        Equipement p_Sortie = new Station("b",2);
        Convoyeur instance = new Convoyeur(a);
        instance.asgSortie(p_Sortie);
        assertEquals(p_Sortie, instance.reqSortie());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgCapaciteMax method, of class Convoyeur.
     */
    @Test
    public void testAsgCapaciteMax() {
        System.out.println("asgCapaciteMax");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        int expResult = 1;
        instance.asgCapaciteMax(1);
        int result = instance.reqCapaciteMax();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqPanier method, of class Convoyeur.
     */
    @Test
    public void testReqPanier() {
        System.out.println("reqPanier");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        Panier expResult = new Panier();
        expResult.ajouterProduit(new Produit("a", 100));
        instance.asgPanier(expResult);
        Panier result = instance.reqPanier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgPanier method, of class Convoyeur.
     */
    @Test
    public void testAsgPanier() {
        System.out.println("asgPanier");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        Panier expResult = new Panier();
        expResult.ajouterProduit(new Produit("a", 100));
        instance.asgPanier(expResult);
        Panier result = instance.reqPanier();
        assertEquals(expResult, result);
    }

    /**
     * Test of reqCouleur method, of class Convoyeur.
     */
    @Test
    public void testReqCouleur() {
        System.out.println("reqCouleur");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        Color expResult = Color.BLACK;
        instance.asgCouleur(expResult);
        Color result = instance.reqCouleur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgCouleur method, of class Convoyeur.
     */
    @Test
    public void testAsgCouleur() {
        System.out.println("asgCouleur");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        Color expResult = Color.BLACK;
        instance.asgCouleur(expResult);
        Color result = instance.reqCouleur();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Convoyeur.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Station a = new Station("a",2);
        Convoyeur instance = new Convoyeur(a);
        String expResult2 = "Convoyeur Sortie : a  --  Convoyeur Entree : null";
        assertEquals(expResult2, instance.toString());
        Station b = new Station("b",2);
        instance.asgEntree(b);
        String expResult = "Convoyeur Sortie : a  --  Convoyeur Entree : b";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
