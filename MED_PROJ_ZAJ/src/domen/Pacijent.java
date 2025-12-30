/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class Pacijent extends OpstiDomenskiObjekat implements Serializable{
    private int sifraPac;
    private String imePrez;
    private LocalDate datumRodj;
    private String telefon;

    public Pacijent() {}

    public Pacijent(int sifraPac, String imePrez, LocalDate datumRodj) {
        this.sifraPac = sifraPac;
        this.imePrez = imePrez;
        this.datumRodj = datumRodj;
    }

    public Pacijent(int sifraPac, String imePrez, LocalDate datumRodj, String telefon) {
        this.sifraPac = sifraPac;
        this.imePrez = imePrez;
        this.datumRodj = datumRodj;
        this.telefon = telefon;
    }
    
    public int getSifraPac() {
        return sifraPac;
    }

    public void setSifraPac(int sifraPac) {
        this.sifraPac = sifraPac;
    }

    public String getImePrez() {
        return imePrez;
    }

    public void setImePrez(String imePrez) {
        this.imePrez = imePrez;
    }

    public LocalDate getDatumRodj() {
        return datumRodj;
    }

    public void setDatumRodj(LocalDate datumRodj) {
        this.datumRodj = datumRodj;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    
    @Override
    public String toString() {
        return imePrez +" - "+sifraPac;
    }

    @Override
    public String nazivTabele() {
        return "pacijent";
    }

    @Override
    public String kraciNazivTabele() {
        return "p";
    }

    @Override
    public String joinDeo() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        return "0,'"+imePrez+"','"+java.sql.Date.valueOf(datumRodj)+"',"+telefon;
    }

    @Override
    public String azuriranje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String brisanje() {
        return "sifraPac="+sifraPac;
    }

    @Override
    public String filter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs) {
        List<OpstiDomenskiObjekat> lt = new LinkedList<>();
        try {
            while (rs.next()) {
                lt.add(new Pacijent(rs.getInt("sifraPac"),rs.getString("imePrez"), 
                rs.getDate("datumRodj").toLocalDate(),rs.getString("telefon")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lt;
    }
}

