/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.elements;

import java.util.ArrayList;
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
public class PanierTest {
    
    public PanierTest() {
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
     * Test of ajouterProduit method, of class Panier.
     */
    @Test
    public void testAjouterProduit() {
        System.out.println("ajouterProduit");
        Produit p = new Produit("a", 100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(p, instance.reqPanier().get(0));
        Produit p2 = new Produit("a",1);
        instance.ajouterProduit(p2);
        assertEquals(101, instance.reqPanier().get(0).reqQuantiteProduit());
    }

    /**
     * Test of quantiteTotal method, of class Panier.
     */
    @Test
    public void testQuantiteTotal() {
        System.out.println("quantiteTotal");
        Panier instance = new Panier();
        int expResult = 4;
        Produit p1 = new Produit("a", 1);
        Produit p2  = new Produit("b", 3);
        instance.ajouterProduit(p1);
        instance.ajouterProduit(p2);
        int result = instance.quantiteTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of enleverProduit method, of class Panier.
     */
    @Test
    public void testEnleverProduit() {
        System.out.println("enleverProduit");
        Produit p1 = new Produit("a",1);
        Produit p2 = new Produit("b", 3);
        Panier instance = new Panier();
        instance.ajouterProduit(p1);
        instance.ajouterProduit(p2);
        assertEquals(instance.reqPanier().size(), 2);
        instance.enleverProduit(p1);
        assertEquals(instance.reqPanier().size(), 1);
        assertEquals(instance.reqPanier().get(0), p2);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgPanier method, of class Panier.
     */
    @Test
    public void testAsgPanier() {
        System.out.println("asgPanier");
        ArrayList<Produit> p = new ArrayList<Produit>();
        p.add(new Produit("a", 100));
        Panier instance = new Panier();
        instance.asgPanier(p);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(p, instance.reqPanier());
    }

    /**
     * Test of reqPanier method, of class Panier.
     */
    @Test
    public void testReqPanier() {
        System.out.println("reqPanier");
        Panier instance = new Panier();
        Produit p = new Produit("a", 100);
        ArrayList<Produit> expResult = new ArrayList<Produit>();
        ArrayList<Produit> result = instance.reqPanier();
        instance.ajouterProduit(p);
        expResult.add(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getProduit method, of class Panier.
     */
    @Test
    public void testGetProduit_Produit() {
        System.out.println("getProduit");
        Produit p = new Produit("a", 100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        Produit expResult = p;
        Produit result = instance.getProduit(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getProduit method, of class Panier.
     */
    @Test
    public void testGetProduit_String() {
        System.out.println("getProduit");
        Produit p = new Produit("a", 100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        Produit result = instance.getProduit(p);
        assertEquals(p, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getProduitIndex method, of class Panier.
     */
    @Test
    public void testGetProduitIndex_Produit() {
        System.out.println("getProduitIndex");
        Produit p = new Produit("a",100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        int expResult = 0;
        int result = instance.getProduitIndex(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getProduitIndex method, of class Panier.
     */
    @Test
    public void testGetProduitIndex_String() {
        System.out.println("getProduitIndex");
        Produit p = new Produit("a",100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        int expResult = 0;
        int result = instance.getProduitIndex("a");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of modifierQuantiteProduit method, of class Panier.
     */
    @Test
    public void testModifierQuantiteProduit_Produit_int() {
        System.out.println("modifierQuantiteProduit");
        Produit p = new Produit("a", 100);
        int quantite = 101;
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        instance.modifierQuantiteProduit(p, 1);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(quantite, instance.getProduit(p).reqQuantiteProduit());
    }

    /**
     * Test of modifierQuantiteProduit method, of class Panier.
     */
    @Test
    public void testModifierQuantiteProduit_String_int() {
        System.out.println("modifierQuantiteProduit");
        Produit p = new Produit("a", 100);
        int quantite = 101;
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        instance.modifierQuantiteProduit("a", 1);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(quantite, instance.getProduit("a").reqQuantiteProduit());
    }
    
    /**
     * Test of testAdditionnerPanier method, of class Panier.
     */
    @Test
    public void testAdditionnerPanier() {
        System.out.println("additionnerPanier");
        Produit a1 = new Produit("a", 100);
        Produit a2 = new Produit("a", 1);
        Produit b = new Produit("b",3);
        Panier p1 = new Panier();
        Panier p2 = new Panier();
        p1.ajouterProduit(a1);
        p1.ajouterProduit(b);
        p2.ajouterProduit(a2);
        p1 = p1.additionerPanier(p2);
        assertEquals(101, p1.reqPanier().get(0).reqQuantiteProduit());
    }

    /**
     * Test of toString method, of class Panier.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Produit p = new Produit("allo", 100);
        Panier instance = new Panier();
        instance.ajouterProduit(p);
        String expResult = "Nom: allo - Quantite : 100\n";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
