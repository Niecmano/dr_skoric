/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class Izvestaj extends OpstiDomenskiObjekat {
    private Pacijent pac;
    private Lekar lekar;
    private LocalDateTime datumVreme;
    private String anamneza;
    private String nalaz;
    private String dg;
    private String terapija;
    private String kontrola;

    public Izvestaj() {
    }
    
    public Izvestaj(Pacijent pac, Lekar lekar, LocalDateTime datumVreme, String anamneza, String dg, String terapija, String nalaz, String kontrola) {
        this.pac = pac;
        this.lekar = lekar;
        this.datumVreme = datumVreme;
        this.anamneza = anamneza;
        this.dg = dg;
        this.terapija = terapija;
        this.kontrola = kontrola;
        this.nalaz = nalaz;
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

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
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
     + "LEFT JOIN specijalizacija sp ON l.sifraSpec = sp.sifraSpec\n" +
"LEFT JOIN specijalizacija subsp ON l.sifraSubspec = subsp.sifraSpec";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String vrednostiUbacivanje() {
        return pac.getSifraPac()+",'" + datumVreme + "'," 
                + lekar.getSifraLekara()+",'"+anamneza+"','"+dg+"','"+terapija+"','"+nalaz+"','"+kontrola+"'";
    }

    //"sifraPac="+pac.getSifraPac()+
    
    @Override
    public String azuriranje() {
        return "sifraLekara="+lekar.getSifraLekara()+",anamneza='"+anamneza+"',dg='"+dg+"',terapija='"+terapija+"',nalaz='"+nalaz+"',kontrola='"+kontrola+
                "' WHERE datumVreme='"+datumVreme+"'";
    }

    @Override
    public String brisanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String filter() {
        if(pac==null) return "WHERE i.sifraLekara="+lekar.getSifraLekara(); 
        if(lekar == null || lekar.getImePrez()==null) return "WHERE i.sifraPac="+pac.getSifraPac(); 
        return "WHERE i.sifraPac="+pac.getSifraPac()+" AND i.sifraLekara="+lekar.getSifraLekara();
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListuObj(ResultSet rs) {
        List<OpstiDomenskiObjekat> izvs = new LinkedList<>();
        try {
            while (rs.next()) {
                Lekar le = new Lekar(rs.getInt("sifraLekara"), rs.getString("l.imePrez"), 
                    new Specijalizacija(rs.getInt("sp.sifraSpec"), rs.getString("sp.nazivSpec")),
                new Specijalizacija(rs.getInt("subsp.sifraSpec"), rs.getString("subsp.nazivSpec")));
                Pacijent p = new Pacijent(rs.getInt("p.sifraPac"), rs.getString("p.imePrez"), rs.getDate("datumRodj").toLocalDate());
                Izvestaj i = new Izvestaj(p, le, rs.getTimestamp("datumVreme").toInstant().atZone(ZoneId.of("Europe/Belgrade")).toLocalDateTime(),
                        rs.getString("anamneza"),rs.getString("dg"),rs.getString("terapija"),rs.getString("nalaz"),rs.getString("kontrola"));
                izvs.add(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return izvs;
    }
    
}
