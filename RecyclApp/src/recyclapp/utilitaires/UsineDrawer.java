/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.utilitaires;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import recyclapp.elements.EntreeUsine;
import recyclapp.elements.Equipement;
import recyclapp.elements.Jonction;
import recyclapp.elements.SortieUsine;
import recyclapp.elements.Station;

/**
 *
 * @author David
 */
public class UsineDrawer {
    
    private Controleur controleur;
    private Dimension dimension;
    private int rayon;
    
    public UsineDrawer(Controleur p_cont, Dimension p_dim)
    {
        this.controleur = p_cont;
        this.dimension = p_dim;
    }
    
//    public void draw(Graphics g)
//    {
//    
//    }
    
    public void drawEquipement(Graphics g)
    {
        ArrayList<Equipement> eq = controleur.reqUsine().reqListeEquipement();
        
        for(Equipement x : eq)
        {
            if(x instanceof Station)
            {
                Point statPt = x.reqPosition();
                Color couleur = x.reqCouleur();
                g.setColor(couleur);
                g.drawRect((int) statPt.getX(), (int) statPt.getY(), 5, 5);
            }
            if(x instanceof EntreeUsine)
            {
                Point entPt = x.reqPosition();
                Color couleur = Color.BLACK;
                g.setColor(couleur);
                g.drawOval((int) entPt.getX()- rayon, (int) entPt.getY() - rayon, rayon*2, rayon*2);
            }
            if(x instanceof SortieUsine)
            {
                Point sorPt = x.reqPosition();
                Color couleur = Color.BLACK;
                g.setColor(couleur);
                g.drawOval((int) sorPt.getX()- rayon, (int) sorPt.getY() - rayon, rayon*2, rayon*2);
            }
            if(x instanceof Jonction)
            {
                Point jctPt = x.reqPosition();
                Color couleur = Color.BLACK;
                g.setColor(couleur);
                g.drawOval((int) jctPt.getX()- rayon, (int) jctPt.getY() - rayon, rayon*2, rayon*2);
            }
                
        }
    }
}