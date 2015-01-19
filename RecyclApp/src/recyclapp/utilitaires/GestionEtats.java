/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package recyclapp.utilitaires;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class GestionEtats {
    private ArrayList<byte[]> m_etatPrec;
    private int m_etatCourantPrec;
    
 public GestionEtats()
{
    m_etatPrec = new ArrayList<byte[]>();
    m_etatCourantPrec = 0;
}
 
public void SaveEtat(Usine usine)
{
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    try
    {
        ObjectOutputStream out = new ObjectOutputStream(bOut);
        out.writeObject(usine);
        byte b[] = bOut.toByteArray();
        if(m_etatPrec.size()==0){
            m_etatPrec.add(m_etatCourantPrec, b);
        }
        else{
            m_etatCourantPrec += 1;
            m_etatPrec.add(m_etatCourantPrec, b);
        }
        
        if (m_etatPrec.size() > 20){
            m_etatPrec.remove(0);
            m_etatCourantPrec = m_etatPrec.size() - 1;
            out.close();
            bOut.close();
        }
    }
        catch(IOException e)
    {
        System.out.println(e.getMessage());
    }
}

 private Usine RefreshEtat()
{
    byte b[] = m_etatPrec.get(m_etatCourantPrec);
    ByteArrayInputStream bIn = new ByteArrayInputStream(b);
    try
    {
        Usine usine;
        ObjectInputStream in = new ObjectInputStream(bIn);
        usine = (Usine)in.readObject();
        in.close();
        bIn.close();
        return usine;
    }
        catch (IOException | ClassNotFoundException e)
    {
        return null;
    }
}
 public Usine Undo()
{
    if ( m_etatCourantPrec > 0)
    {
       m_etatCourantPrec --;
       return this.RefreshEtat();
    }
    else{
        return this.RefreshEtat();
    }
}
public Usine Redo()
{
    if ( m_etatCourantPrec < (m_etatPrec.size() - 1))
    {
       m_etatCourantPrec++;
       return this.RefreshEtat();
    }
    else{
        return this.RefreshEtat();
    }
}
}