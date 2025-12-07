/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class ZakazanTermin extends OpstiDomenskiObjekat implements Serializable{
    private Pacijent pac;
    private LocalDateTime datumVreme;
    private Lekar lekar;

    public ZakazanTermin() {}

    public ZakazanTermin(Pacijent pac, LocalDateTime datumVreme, Lekar lekar) {
        this.pac = pac;
        this.datumVreme = datumVreme;
        this.lekar = lekar;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Pacijent getPac() {
        return pac;
    }

    public void setPac(Pacijent pac) {
        this.pac = pac;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    @Override
    public String nazivTabele() {
        return "zakazantermin";
    }

    @Override
    public String kraciNazivTabele() {
        return "zt";
    }

    @Override
    public String joinDeo() {
        return "JOIN lekar l ON zt.sifraLekara=l.sifraLekara JOIN pacijent p ON zt.sifraPac=p.sifraPac JOIN specijalizacija sp ON l.sifraSpec=sp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        return pac.getSifraPac()+",'" + datumVreme + "'," + lekar.getSifraLekara();
    }

    @Override
    public String azuriranje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String brisanje() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datumStr = datumVreme.format(formatter);
        return "sifraLekara=" + lekar.getSifraLekara() + " AND datumVreme='" + datumStr + "'";
    }

    @Override
    public String filter() {
        return "WHERE DATE(datumVreme)='"+datumVreme.toLocalDate()+"'";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs) {
        List<OpstiDomenskiObjekat> termini = new LinkedList<>();
        try {
            while (rs.next()) {
                Lekar le = new Lekar(rs.getInt("l.sifraLekara"), rs.getString("l.imePrez"), new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec")));
                Pacijent pac = new Pacijent(rs.getInt("p.sifraPac"), rs.getString("p.imePrez"), null);
                termini.add(new ZakazanTermin(pac,rs.getTimestamp("datumVreme").toInstant().atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime(), le));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return termini;
    }
}

