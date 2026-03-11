/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class Izvestaj extends OpstiDomenskiObjekat {
    private ZakazanTermin zt;
    private String anamneza;
    private String nalaz;
    private String dg;
    private String terapija;
    private String kontrola;

    public Izvestaj() {
    }
    
    public Izvestaj(ZakazanTermin zt, String anamneza, String dg, String terapija, String nalaz, String kontrola) {
        this.zt = zt;
        this.anamneza = anamneza;
        this.dg = dg;
        this.terapija = terapija;
        this.kontrola = kontrola;
        this.nalaz = nalaz;
    }

    public ZakazanTermin getZt() {
        return zt;
    }

    public void setZt(ZakazanTermin zt) {
        this.zt = zt;
    }

    public String getAnamneza() {
        return anamneza;
    }

    public void setAnamneza(String anamneza) {
        this.anamneza = anamneza;
    }

    public String getDg() {
        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
    }

    public String getTerapija() {
        return terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public String getNalaz() {
        return nalaz;
    }

    public void setNalaz(String nalaz) {
        this.nalaz = nalaz;
    }

    public String getKontrola() {
        return kontrola;
    }

    public void setKontrola(String kontrola) {
        this.kontrola = kontrola;
    }
    
    @Override
    public String nazivTabele() {
        return "izvestaj";
    }

    @Override
    public String kraciNazivTabele() {
        return "i";
    }

    @Override
    public String joinDeo() {
        return "JOIN pacijent p ON i.sifraPac = p.sifraPac "
     + "JOIN lekar l ON i.sifraLekara = l.sifraLekara "
     + "JOIN zakazantermin zt ON i.sifraPac = zt.sifraPac "
     + "AND i.datumVreme = zt.datumVreme "
     + "AND i.sifraLekara = zt.sifraLekara LEFT JOIN specijalizacija sp ON l.sifraSpec = sp.sifraSpec\n" +
"LEFT JOIN specijalizacija subsp ON l.sifraSubspec = subsp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        return zt.getPac().getSifraPac()+",'" + zt.getDatumVreme() + "'," 
                + zt.getLekar().getSifraLekara()+",'"+anamneza+"','"+dg+"','"+terapija+"','"+nalaz+"','"+kontrola+"'";
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
        if(zt.getLekar().getImePrez()==null) return "WHERE i.sifraPac="+zt.getPac().getSifraPac();
        if(zt.getPac().getImePrez()==null) return "WHERE i.sifraPac="+zt.getPac().getSifraPac();   
        return "WHERE i.sifraPac="+zt.getPac().getSifraPac()+" AND i.sifraLekara="+zt.getLekar().getSifraLekara();
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs) {
        List<OpstiDomenskiObjekat> izvs = new LinkedList<>();
        try {
            while (rs.next()) {
                Lekar le = new Lekar(rs.getInt("sifraLekara"), rs.getString("l.imePrez"), 
                    new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec")),
                new Specijalizacija(rs.getInt("subsp.sifraSpec"), rs.getString("subsp.nazivSpec")));
                Pacijent pac = new Pacijent(rs.getInt("p.sifraPac"), rs.getString("p.imePrez"), rs.getDate("datumRodj").toLocalDate());
                ZakazanTermin zt = new ZakazanTermin(pac,rs.getTimestamp("zt.datumVreme").toInstant().atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime(), le);
                Izvestaj i = new Izvestaj(zt, rs.getString("anamneza"),rs.getString("dg"),rs.getString("terapija"),
                rs.getString("nalaz"),rs.getString("kontrola"));
                izvs.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return izvs;
    }
    
}
