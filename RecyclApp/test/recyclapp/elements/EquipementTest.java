/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.elements;

import java.awt.Graphics;
import java.awt.Point;
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
public class EquipementTest {
    
    private Equipement e;
    
    public EquipementTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        e = new Equipement();
    }
    
    @After
    public void tearDown() {
        e = null;
    }

    /**
     * Test of reqNbEntree method, of class Equipement.
     */
    @Test
    public void testReqNbEntree() {
        System.out.println("reqNbEntree");
        int expResult = 1;
        int result = e.reqNbEntree();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of reqNbSortie method, of class Equipement.
     */
    @Test
    public void testReqNbSortie() {
        System.out.println("reqNbSortie");
        int expResult = 1;
        int result = e.reqNbSortie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgNbEntree method, of class Equipement.
     */
    @Test
    public void testAsgNbEntree() {
        System.out.println("asgNbEntree");
        int p_nbEntree = 0;
        e.asgNbEntree(p_nbEntree);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(p_nbEntree, e.reqNbEntree());
    }

    /**
     * Test of asgNbSortie method, of class Equipement.
     */
    @Test
    public void testAsgNbSortie() {
        System.out.println("asgNbSortie");
        int p_nbSortie = 0;
        e.asgNbSortie(p_nbSortie);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(p_nbSortie, e.reqNbSortie());
    }

    /**
     * Test of reqPosition method, of class Equipement.
     */
    @Test
    public void testReqPosition() {
        System.out.println("reqPosition");
        Point expResult = null;
        Point result = e.reqPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of asgPosition method, of class Equipement.
     */
    @Test
    public void testAsgPosition() {
        System.out.println("asgPosition");
        Point p_position = new Point(2, 3);
        Equipement instance = new Equipement();
        e.asgPosition(p_position);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(p_position, e.reqPosition());
    }

    /**
     * Test of reqConvoyeurs method, of class Equipement.
     */
    @Test
    public void testReqConvoyeurs() {
        System.out.println("reqConvoyeurs");
        Equipement instance = new Equipement();
        ArrayList<Convoyeur> expResult = new ArrayList<Convoyeur>();
        expResult.add(new Convoyeur(instance));
        ArrayList<Convoyeur> result = instance.reqConvoyeurs();
        assertEquals(1, result.size());
        assertEquals(instance, result.get(0).reqSortie());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of link method, of class Equipement.
     */
    @Test
    public void testLink_Equipement() {
        System.out.println("link");
        Equipement entree = new Equipement();
        Equipement instance = new Equipement();
        instance.link(entree);
        Convoyeur c = e.reqConvoyeurs().get(0);
        assertEquals(c.reqSortie(), e);
        assertEquals(c.reqEntree(), null);
        
        Station sta1 = new Station("a", 2);
        Station sta2 = new Station("b", 2);
        Station sta3 = new Station("c", 2);
        sta1.link(sta2);
        sta1.link(sta3);
        assertEquals(sta2, sta1.reqConvoyeurs().get(0).reqEntree());
        assertEquals(sta3, sta1.reqConvoyeurs().get(1).reqEntree());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of link method, of class Equipement.
     */
    @Test
    public void testLink_int_Equipement() {
        System.out.println("link");
        int index = 0;
        Equipement entree = new Equipement();
        Equipement instance = new Equipement();
        instance.link(index, entree);
        assertEquals(instance.reqConvoyeurs().get(0).reqEntree(), entree);
        assertEquals(instance.reqConvoyeurs().get(0).reqSortie(), instance);
        assertEquals(entree.reqConvoyeurs().get(0).reqEntree(), null);
        assertEquals(entree.reqConvoyeurs().get(0).reqSortie(), entree);
    }

    
    /**
     * Test of asgNomEquipement method, of class Equipement.
     */
    @Test
    public void testasgNomEquipement()
    {
        System.out.println("asgNomEquipement");
        Equipement instance = new Equipement();
        String expResult = "";
        String result = instance.reqNomEquipement();
        assertEquals(expResult, result);
        instance.asgNomEquipement("allo");
        assertEquals("allo", instance.reqNomEquipement());
    }

    /**
     * Test of reqNomEquipement method, of class Equipement.
     */
    @Test
    public void testReqNomEquipement() {
        System.out.println("reqNomEquipement");
        Equipement instance = new Equipement();
        String expResult = "";
        String result = instance.reqNomEquipement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of fonctionSurPanier method, of class Equipement.
     */
    @Test
    public void testFonctionSurPanier() {
        System.out.println("fonctionSurPanier");
    }

    /**
     * Test of toString method, of class Equipement.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Equipement instance = new Equipement();
        String expResult = "";
        String result = instance.toString();
    }
    
}
