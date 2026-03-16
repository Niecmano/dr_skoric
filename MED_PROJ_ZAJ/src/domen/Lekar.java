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
    private Specijalizacija subspec;

    public Lekar() {}

    public Lekar(int sifraLekara, String imePrez, Specijalizacija spec, Specijalizacija subspec) {
        this.sifraLekara = sifraLekara;
        this.imePrez = imePrez;
        this.spec = spec;
        this.subspec = subspec;
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

    public Specijalizacija getSubspec() {
        return subspec;
    }

    public void setSubspec(Specijalizacija subspec) {
        this.subspec = subspec;
    }

    

    @Override
    public String toString() {
        String[] reci = imePrez.split(" ");
        String lekar="";
        for (String w : reci) {
            if(!w.toLowerCase().equals(w) || w.equals("dr")) lekar+=w+" ";
        }
        return lekar;
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
        return "LEFT JOIN specijalizacija sp ON l.sifraSpec = sp.sifraSpec\n" +
"LEFT JOIN specijalizacija subsp ON l.sifraSubspec = subsp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "ORDER BY TRIM(\n" +
"    CONCAT(\n" +
"        SUBSTRING_INDEX(SUBSTRING_INDEX(l.imePrez, ' ', -2), ' ', 1), ' ',\n" +
"        SUBSTRING_INDEX(l.imePrez, ' ', -1)\n" +
"    )\n" +
")";
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
                    new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec")),
                new Specijalizacija(rs.getInt("subsp.sifraSpec"), rs.getString("subsp.nazivSpec"))));
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
