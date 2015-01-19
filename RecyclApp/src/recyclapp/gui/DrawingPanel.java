/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recyclapp.gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import recyclapp.elements.Convoyeur;
import recyclapp.elements.EntreeUsine;
import recyclapp.elements.Equipement;
import recyclapp.elements.Jonction;
import recyclapp.elements.SortieUsine;
import recyclapp.elements.Station;
import recyclapp.gui.listeners.mouse.ModeHandleListener;
import recyclapp.gui.listeners.mouse.MotionListener;
import recyclapp.gui.listeners.mouse.WheelListener;
import recyclapp.utilitaires.Controleur;
import recyclapp.utilitaires.UsineDrawer;
 
 public class DrawingPanel extends JPanel implements Serializable 
 {
     public Dimension initialDimension;
     public MainWindow mainWindow;
     private UsineDrawer usineDrawer;
     public Controleur monControleur;
     private boolean grilleActivee = false;
     int pointUsine;
     int taille = 0;
     private static final int HIT_BOX_SIZE = 30;
     ArrayList<Line2D> mesConvoyeurs;
     Map<Line2D, Convoyeur> map;
 
 
     public DrawingPanel() 
     {
         monControleur = null;
         setBackground(Color.white);
         ArrayList<Line2D> mesConvoyeurs = new ArrayList<Line2D>();
         map = new HashMap<Line2D, Convoyeur>();
     }
     
     public void asgActiveGrille()
     {
         grilleActivee = !grilleActivee;
     }
     

     public void asgControleur(Controleur c)
     {
         monControleur = c;
     }
     
     public int reqZoom()
     {
         return monControleur.reqZoom()*monControleur.PIXEL_PER_METER;
     }
     
     public Controleur reqControleur()
     {
         return monControleur;
     }
     
     @Override
     protected void paintComponent(Graphics g)
     {
        if (monControleur != null)
        {
            super.paintComponent(g);     
           //afficher la grille si actif(g);
            if (grilleActivee == true)
            {
                dessineGrille(g,this.taille);
            }
            dessineConvoyeur(g);
            dessineEquipements(g);
            
        }
     }
 
     public void dessineEquipements(Graphics g)
     {
         // ajout du zoom à implémenter
         ArrayList<Equipement> m_listeEquipement = monControleur.reqUsine().reqListeEquipement();
         Iterator<Equipement> it = m_listeEquipement.iterator();
         
         while(it.hasNext()){
             Equipement equi = it.next();
             
             if(equi == monControleur.getSelectionCourante())
                 SelectEquipement(equi.reqPosition(), equi.reqDimension(), g);
             
             if (equi instanceof EntreeUsine ) {
                 double hauteur = equi.reqDimension().height*reqZoom();
                 double largeur = equi.reqDimension().width*reqZoom();
                 double x = equi.reqPosition().x*reqZoom();
                 double y = equi.reqPosition().y*reqZoom();
                 double xCenter = x+largeur/2;
                 double yCenter = y+hauteur/2;
                 //dessine le carré
                 g.setColor(equi.reqCouleur());
                 g.fillRect((int)x,(int)y, (int)largeur, (int)hauteur);
                 // si sélectionné, mettre un autre couleur à ajouter
                 //dessine le nom de l'équipement au millieu
                 FontMetrics fm = g.getFontMetrics();
                 double textWidth = fm.getStringBounds(equi.reqNomEquipement(), g).getWidth();
                 g.setColor(Color.BLACK);
                 g.drawString(equi.reqNomEquipement(), (int) (xCenter - textWidth/2), (int) (yCenter - fm.getMaxAscent()/2));
                 
                 
                 
             }
             else if(equi instanceof SortieUsine){
                 double hauteur = equi.reqDimension().height*reqZoom();
                 double largeur = equi.reqDimension().width*reqZoom();
                 double x = equi.reqPosition().x*reqZoom();
                 double y = equi.reqPosition().y*reqZoom();
                 double xCenter = x+largeur/2;
                 double yCenter = y+hauteur/2;
                 // si sélectionné, mettre un autre couleur à ajouter
                 
                 //dessine le carré
                 g.setColor(equi.reqCouleur());
                 g.fillRect((int)x,(int)y, (int)largeur, (int)hauteur);
                 //dessine le nom de l'équipement au millieu
                 FontMetrics fm = g.getFontMetrics();
                 double textWidth = fm.getStringBounds(equi.reqNomEquipement(), g).getWidth();
                 g.setColor(Color.BLACK);
                 g.drawString(equi.reqNomEquipement(), (int) (xCenter - textWidth/2), (int) (yCenter - fm.getMaxAscent()/2));
                 
                 
             }
             else if(equi instanceof Jonction){
                 double hauteur = equi.reqDimension().height*reqZoom();
                 double largeur = equi.reqDimension().width*reqZoom();
                 double x = equi.reqPosition().x*reqZoom();
                 double y = equi.reqPosition().y*reqZoom();
                 double xCenter = x+largeur/2;
                 double yCenter = y+hauteur/2;
                 //dessine le rond
                 g.setColor(equi.reqCouleur());
                 g.fillOval((int)(xCenter-largeur/2),(int)(yCenter-hauteur/2), (int)largeur, (int)hauteur);
                 // si sélectionné, mettre un autre couleur à ajouter
                 //dessine le nom de l'équipement au millieu
                 FontMetrics fm = g.getFontMetrics();
                 double textWidth = fm.getStringBounds(equi.reqNomEquipement(), g).getWidth();
                 g.setColor(Color.BLACK);
                 g.drawString(equi.reqNomEquipement(), (int) (xCenter - textWidth/2), (int) (yCenter - fm.getMaxAscent()/25));
                 
                 
             }
             else if(equi instanceof Station){
                 double hauteur = equi.reqDimension().height*reqZoom();
                 double largeur = equi.reqDimension().width*reqZoom();
                 double x = equi.reqPosition().x*reqZoom();
                 double y = equi.reqPosition().y*reqZoom();
                 double xCenter = x+largeur/2;
                 double yCenter = y+hauteur/2;
                 // si sélectionné, mettre un autre couleur à ajouter
                 //dessine le carré
                 if(equi.reqImage()== null){
                     g.setColor(equi.reqCouleur());
                     g.fillRect((int)x,(int)y, (int)largeur, (int)hauteur);
                 } 
                 else {
                     Image img = equi.reqImage();
                     BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                     Graphics2D bGr = bimage.createGraphics();
                     bGr.drawImage(img, (int)x, (int)y, null);
                     bGr.dispose();
                     BufferedImage resizedImage = new BufferedImage((int)largeur, (int)hauteur, BufferedImage.TYPE_INT_ARGB);
                     Graphics2D gr = resizedImage.createGraphics();
                     gr.drawImage(img, (int)x, (int)y, (int)x+(int)largeur, (int)y+(int)hauteur, null);
                     gr.dispose();
                     boolean b = g.drawImage(equi.reqImage(), (int)x, (int)y, (int)largeur, (int)hauteur, equi.reqCouleur(), null);
                 }
                 //dessine le nom de l'équipement au millieu
                 FontMetrics fm = g.getFontMetrics();
                 double textWidth = fm.getStringBounds(equi.reqNomEquipement(), g).getWidth();
                 g.setColor(Color.BLACK);
                 g.drawString(equi.reqNomEquipement(), (int) (xCenter - textWidth/2), (int) (yCenter - fm.getMaxAscent()/2));
                 
                 
             }
                 
             
             } 
     }
     
     private void SelectEquipement(Point p, Dimension d, Graphics g)
    {
        double originX = (p.getX()*reqZoom()) - 2;
        double originY = (p.getY()*reqZoom()) - 2;
        double rectWidth = d.width*reqZoom() + 3;
        double rectHeight = d.height*reqZoom() + 3;
        
        g.setColor(Color.black);
        Point tlc = new Point((int)originX, (int)originY);
        Dimension selectionDim = new Dimension((int)rectWidth, (int)rectHeight);
        
        g.drawRect(tlc.x, tlc.y, selectionDim.width, selectionDim.height);
    }
     
     public void dessineConvoyeur(Graphics g)
     {
         ArrayList<Convoyeur> m_listeConvoyeur = monControleur.reqUsine().reqListeConvoyeur();
         Iterator<Convoyeur> it = m_listeConvoyeur.iterator();
         Equipement e;
         map = new HashMap<Line2D, Convoyeur>();
         while(it.hasNext()){
             Convoyeur conv = it.next();
             if(conv.reqEntree()!=null)
             {
                 
                 int xSortie = (conv.reqSortie().reqPosition().x *reqZoom()) + (conv.reqSortie().reqDimension().width*reqZoom()/2);
                 int ySortie = (conv.reqSortie().reqPosition().y *reqZoom()) + (conv.reqSortie().reqDimension().height*reqZoom()/2);
                 // Entree de gauche
                 int xEntree = conv.reqEntree().reqPosition().x *reqZoom();
                 int yEntree = conv.reqEntree().reqPosition().y *reqZoom() + (conv.reqEntree().reqDimension().height*reqZoom()/2);

                 //Entree nord
                 int xNordEntree = (conv.reqEntree().reqPosition().x *reqZoom()) + (conv.reqEntree().reqDimension().width*reqZoom()/2);
                 int yNordEntree = conv.reqEntree().reqPosition().y *reqZoom();
                 //Entree sud
                 int xSudEntree = (conv.reqEntree().reqPosition().x *reqZoom()) + (conv.reqEntree().reqDimension().width*reqZoom()/2);
                 int ySudEntree = (conv.reqEntree().reqPosition().y *reqZoom()) + (conv.reqEntree().reqDimension().height*reqZoom());
                 
                 if(conv.reqSortie() instanceof Station){
                    Station s = (Station) conv.reqSortie();
                    xSortie = (conv.reqSortie().reqPosition().x *reqZoom()) + (conv.reqSortie().reqDimension().width*reqZoom());
                    ySortie = (conv.reqSortie().reqPosition().y *reqZoom()) + ((conv.reqSortie().reqDimension().height*reqZoom()/ (conv.reqSortie().reqNbSortie()+1)) * (s.reqIndexConv(conv) +1));
                    
                 }
                 //Si xSortie  est >= a mon equipement entree, et y + petit -> nord
                 if(xSortie>=(conv.reqEntree().reqPosition().x *reqZoom())&& ySortie <(conv.reqEntree().reqPosition().y *reqZoom())){
                     if(conv.reqSortie() instanceof Station){
                        Station s = (Station) conv.reqSortie();
                        xSortie = (conv.reqSortie().reqPosition().x *reqZoom()) + (conv.reqSortie().reqDimension().width*reqZoom())/ (conv.reqSortie().reqNbSortie()+1) * (s.reqIndexConv(conv) +1);
                        ySortie = (conv.reqSortie().reqPosition().y *reqZoom()) + (conv.reqSortie().reqDimension().height*reqZoom());
                     }
                     g.setColor(conv.reqCouleur());
                     g.drawLine(xSortie, ySortie, xNordEntree, yNordEntree);
                     Line2D line = new Line2D.Double(xSortie, ySortie, xNordEntree, yNordEntree);
                     map.put(line, conv);
                     if(conv == monControleur.reqConvoyeurSelectionnerClick()){
                         g.setColor(Color.BLUE);
                         g.drawLine(xSortie+ 2, ySortie , xNordEntree+ 2, yNordEntree );
                         g.drawLine(xSortie-2, ySortie , xNordEntree- 2, yNordEntree );
                     }   
                     Point tip = new Point(xNordEntree, yNordEntree);
                     Point tail = new Point(xSortie, ySortie);
                     dessineFleche(g,tip, tail,conv.reqCouleur());
                     // si le convoyeur sort d'une station, met un numéro
                     if(conv.reqSortie() instanceof Station){
                         e = monControleur.reqUsine().reqEquipement(conv.reqSortie());
                         int numero = e.reqConvoyeurs().indexOf(conv) + 1;
                         FontMetrics fm = g.getFontMetrics();
                         g.setColor(Color.BLACK);
                         g.drawString(String.valueOf(numero), (int) ((xSortie+xNordEntree)/2), (int) ((ySortie+yNordEntree)/2));
                     }
                     
                     
                 }
                 //Si Sortie x est >= a mon equipement entree, et y + grand -> sud
                 else if(xSortie>=(conv.reqEntree().reqPosition().x *reqZoom())&& ySortie>(conv.reqEntree().reqPosition().y *reqZoom())){
                     if(conv.reqSortie() instanceof Station){
                        Station s = (Station) conv.reqSortie();
                        xSortie = (conv.reqSortie().reqPosition().x *reqZoom()) + (conv.reqSortie().reqDimension().width*reqZoom())/ (conv.reqSortie().reqNbSortie()+1) * (s.reqIndexConv(conv) +1);
                        ySortie = (conv.reqSortie().reqPosition().y *reqZoom()) ;
                     }
                     g.setColor(conv.reqCouleur());
                     g.drawLine(xSortie, ySortie, xSudEntree, ySudEntree);
                     Line2D line = new Line2D.Double(xSortie, ySortie, xSudEntree, ySudEntree);
                     map.put(line, conv);
                     if(conv == monControleur.reqConvoyeurSelectionnerClick()){
                         g.setColor(Color.BLUE);
                         g.drawLine(xSortie+ 2, ySortie , xSudEntree + 2, ySudEntree );
                         g.drawLine(xSortie-2, ySortie , xSudEntree - 2, ySudEntree );
                     } 
                     Point tip = new Point(xSudEntree, ySudEntree);
                     Point tail = new Point(xSortie, ySortie);
                     dessineFleche(g,tip, tail,conv.reqCouleur());
                     if(conv.reqSortie() instanceof Station){
                         e = monControleur.reqUsine().reqEquipement(conv.reqSortie());
                         int numero = e.reqConvoyeurs().indexOf(conv) + 1;
                         FontMetrics fm = g.getFontMetrics();
                         g.setColor(Color.BLACK);
                         g.drawString(String.valueOf(numero), (int) ((xSortie+xSudEntree)/2), (int) ((ySortie+ySudEntree)/2));
                     }
                 }
                 // Fleche se fait sur le point milieu du coté gauche
                 else{
                     g.setColor(conv.reqCouleur());
                     g.drawLine(xSortie, ySortie, xEntree, yEntree);
                     Line2D line = new Line2D.Double(xSortie, ySortie, xEntree, yEntree);
                     map.put(line, conv);
                     if(conv == monControleur.reqConvoyeurSelectionnerClick()){
                         g.setColor(Color.BLUE);
                         g.drawLine(xSortie, ySortie + 2, xEntree, yEntree + 2);
                         g.drawLine(xSortie, ySortie -2, xEntree, yEntree - 2);
                     } 
                     Point tip = new Point(xEntree, yEntree);
                     Point tail = new Point(xSortie, ySortie);
                     dessineFleche(g,tip, tail,conv.reqCouleur());
                     if(conv.reqSortie() instanceof Station){
                         e = monControleur.reqUsine().reqEquipement(conv.reqSortie());
                         int numero = e.reqConvoyeurs().indexOf(conv) + 1;
                         FontMetrics fm = g.getFontMetrics();
                         g.setColor(Color.BLACK);
                         g.drawString(String.valueOf(numero), (int) ((xSortie+xEntree)/2), (int) ((ySortie+yEntree)/2));
                     }
                 }
             }
         }
         
     }
     
     private void dessineFleche(Graphics g, Point tip, Point tail, Color couleur)  
    {  
        Graphics2D g2 = (Graphics2D)g;
        double phi;  
        int barb;
        phi = Math.toRadians(40);  
        barb = 20;
        g.setColor(couleur);  
        double dy = tip.y - tail.y;  
        double dx = tip.x - tail.x;  
        double theta = Math.atan2(dy, dx);  
        double x, y, rho = theta + phi;
        
        int[] pointsX = new int[3];
        int[] pointsY = new int[3];
        pointsX[0] = (int)tip.x;
        pointsY[0] = (int)tip.y;
        for(int j = 1; j < 3; j++)  
        {  
            x = tip.x - barb * Math.cos(rho);  
            y = tip.y - barb * Math.sin(rho);
            pointsX[j] = (int)x;
            pointsY[j] = (int)y;  
            rho = theta - phi;
        }  
        g.fillPolygon(pointsX, pointsY, 3);
        
    } 
     public void dessineGrille(Graphics g, int espacement)
     {
         int esp = (espacement * this.reqZoom());
         int height = this.getHeight();
         int width = this.getWidth();
         
         g.setColor(Color.LIGHT_GRAY);
         
         for(int i=0; i<= width/esp && i<= height; i++)
         {
            g.drawLine(esp*i, 0, esp*i, height);
            g.drawLine(0, esp*i, width, esp*i);
         }
     }
     
     public Convoyeur getLigneConvoyeur(int x, int y) {
        int boxX = x - HIT_BOX_SIZE / 2;
        int boxY = y - HIT_BOX_SIZE / 2;

        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;
        
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Line2D line = (Line2D) pairs.getKey();
            if (line.intersects(boxX, boxY, width, height)) {
		return (Convoyeur) pairs.getValue();}
        }
        return null;
    }
     
     public MainWindow getMainWindow()
     {
         return mainWindow;
     }
 
     public void setMainWindow(MainWindow mainWindow)
     {
         this.mainWindow = mainWindow;
     }
 
     public Dimension getInitialDimension()
     {
         return initialDimension;
     }
 
     public void setInitialDimension()
     {
     }
     
     public void asgGrille(boolean active)
     {
         this.grilleActivee = active;
     }
     
     public void asgGrille(boolean active, int taille)
     {
         this.grilleActivee = active;
         this.taille = taille;
     }
     public boolean reqGrille()
     {
         return this.grilleActivee;
     }

 }
