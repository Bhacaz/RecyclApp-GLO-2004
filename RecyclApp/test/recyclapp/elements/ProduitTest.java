/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.elements;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author bhacaz
 */
public class ProduitTest {
    
    private Produit p;
    
    public ProduitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * Test of reqNomProduit method, of class Produit.
     */
    @Test
    public void testReqNomProduit() {
        System.out.println("reqNomProduit");
        Produit instance = new Produit();
        String expResult = "";
        String result = instance.reqNomProduit();
        assertEquals(expResult, result);
        instance.asgNomProduit("allo");
        expResult =  "allo";
        result = instance.reqNomProduit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqQuantiteProduit method, of class Produit.
     */
    @Test
    public void testReqQuantiteProduit() {
        System.out.println("reqQuantiteProduit");
        Produit instance = new Produit();
        int expResult = 0;
        int result = instance.reqQuantiteProduit();
        assertEquals(expResult, result);
        expResult = 100;
        instance.asgQuantiteProduit(100);
        result = instance.reqQuantiteProduit();
        assertEquals(expResult, result);
    }

    /**
     * Test of asgNomProduit method, of class Produit.
     */
    @Test
    public void testAsgNomProduit() {
        System.out.println("asgNomProduit");
        String p_nomProduit = "";
        Produit instance = new Produit();
        instance.asgNomProduit(p_nomProduit);
        instance.asgNomProduit("allo");
        assertEquals("allo", instance.reqNomProduit());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgQuantiteProduit method, of class Produit.
     */
    @Test
    public void testAsgQuantiteProduit() {
        System.out.println("asgQuantiteProduit");
        int p_quantiteProduit = 0;
        Produit instance = new Produit();
        instance.asgQuantiteProduit(p_quantiteProduit);
        instance.asgQuantiteProduit(100);
        assertEquals(100, instance.reqQuantiteProduit());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of toString method, of class Produit.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Produit instance = new Produit("allo", 100);
        String expResult = "Nom: allo - Quantite : 100\n";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
