/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class DostupanTermin extends OpstiDomenskiObjekat implements Serializable {

    private LocalDateTime datumVreme;
    private Lekar lekar;

    public DostupanTermin() {
    }

    public DostupanTermin(LocalDateTime datumVreme, Lekar lekar) {
        this.datumVreme = datumVreme;
        this.lekar = lekar;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    @Override
    public String nazivTabele() {
        return "dostupantermin";
    }

    @Override
    public String kraciNazivTabele() {
        return "dt";
    }

    @Override
    public String joinDeo() {
        return "JOIN lekar l ON dt.sifraLekara=l.sifraLekara LEFT JOIN specijalizacija sp ON l.sifraSpec = sp.sifraSpec\n" +
"LEFT JOIN specijalizacija subsp ON l.sifraSubspec = subsp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        return "'" + datumVreme + "'," + lekar.getSifraLekara();
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
                Lekar le = new Lekar(rs.getInt("sifraLekara"), rs.getString("imePrez"), 
                    new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec")),
                new Specijalizacija(rs.getInt("subsp.sifraSpec"), rs.getString("subsp.nazivSpec")));
                termini.add(new DostupanTermin(rs.getTimestamp("datumVreme").toInstant().atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime(), le));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return termini;
    }
}
