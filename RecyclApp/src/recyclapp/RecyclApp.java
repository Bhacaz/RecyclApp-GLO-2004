/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import recyclapp.elements.*;
import recyclapp.utilitaires.Controleur;
import static recyclapp.utilitaires.Controleur.ModesNoeuds.ENTREE;
import static recyclapp.utilitaires.Controleur.ModesNoeuds.JONCTION;
import static recyclapp.utilitaires.Controleur.ModesNoeuds.SORTIE;
import static recyclapp.utilitaires.Controleur.ModesNoeuds.STATION;
import recyclapp.utilitaires.Usine;

/**
 *
 * @author bhacaz
 */
public class RecyclApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        recyclapp.gui.MainWindow mainWindow = new recyclapp.gui.MainWindow();
        mainWindow.setVisible(true);
        
        Point p = new Point(0,300);
        
        boolean f;
        
        f = mainWindow.controller.ajouterEquipement(p, ENTREE);
        Panier pan = new Panier();
        Produit a = new Produit("P1", 1000);
        Produit b = new Produit("P2", 1000);
        pan.ajouterProduit(a);
        pan.ajouterProduit(b);
        
        mainWindow.controller.reqEquipements().get(0).reqConvoyeurs().get(0).asgPanier(pan);
        mainWindow.controller.reqEtats().SaveEtat(mainWindow.controller.reqUsine());
        
    }
}
