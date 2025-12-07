/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Nemanja
 */
public abstract class OpstiDomenskiObjekat implements Serializable {
    
    public abstract String nazivTabele();
    public abstract String kraciNazivTabele();
    
    public abstract String joinDeo();
    public abstract String uslov();
    
    public abstract String vrednostiUbacivanje();
    public abstract String azuriranje();
    public abstract String brisanje();
    public abstract String filter();
    
    public abstract List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs);
}
