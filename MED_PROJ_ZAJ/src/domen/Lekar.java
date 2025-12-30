/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class Lekar extends OpstiDomenskiObjekat implements Serializable{
    private int sifraLekara;
    private String imePrez;
    private Specijalizacija spec;

    public Lekar() {}

    public Lekar(int sifraLekara, String imePrez, Specijalizacija spec) {
        this.sifraLekara = sifraLekara;
        this.imePrez = imePrez;
        this.spec = spec;
    }

    public int getSifraLekara() { return sifraLekara; }
    public void setSifraLekara(int sifraLekara) { this.sifraLekara = sifraLekara; }

    public String getImePrez() { return imePrez; }
    public void setImePrez(String imePrez) { this.imePrez = imePrez; }

    public Specijalizacija getSpec() {
        return spec;
    }

    public void setSpec(Specijalizacija spec) {
        this.spec = spec;
    }



    @Override
    public String toString() {
        return imePrez;
    }

    @Override
    public String nazivTabele() {
        return "lekar";
    }

    @Override
    public String kraciNazivTabele() {
        return "l";
    }

    @Override
    public String joinDeo() {
        return "JOIN specijalizacija sp ON l.sifraSpec=sp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String azuriranje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String brisanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String filter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs) {
        List<OpstiDomenskiObjekat> lekari = new LinkedList<>();
        try {
            while (rs.next()) {
                lekari.add(new Lekar(rs.getInt("sifraLekara"), rs.getString("imePrez"), 
                    new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lekari;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Lekar)) return false;
        return ((Lekar) obj).getSifraLekara()==sifraLekara;
    }
}
