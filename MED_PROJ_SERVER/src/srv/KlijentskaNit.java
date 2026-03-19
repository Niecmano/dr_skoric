/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package srv;

import baza.DBBroker;
import domen.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import komun.*;

/**
 *
 * @author Nemanja
 */
public class KlijentskaNit extends Thread {
    
    private static DBBroker dbb;
    private Socket skt;

    public KlijentskaNit(Socket skt) {
        this.skt = skt;
    }

    @Override
    public void run() {
        try {
            Posiljalac sndr = new Posiljalac(skt);
            Primalac rcv = new Primalac(skt);
            dbb = DBBroker.getInstance();
            while (true) {
                Zahtev req = (Zahtev) rcv.primi(); Odgovor o;
                switch (req.getOp()) {
                    case VRATI_PACIJENTA:
                        o = new Odgovor(dbb.select(new Pacijent(), false),null);
                        break;
                    case DODAJ_PACIJENTA:
                        dbb.insert((Pacijent)req.getArgum());
                        o=new Odgovor(null, null);
                        break;
                    case OBRISI_PACIJENTA:
                        Pacijent p = new Pacijent();
                        p.setSifraPac((int) req.getArgum());
                        dbb.delete(p);
                        o=new Odgovor(null, null);
                        break;
                    case VRATI_DOSTUPNE_PREGLEDE:
                        o = new Odgovor(dbb.select(new DostupanTermin(), false),null);
                        break;
                    case DODAJ_DOSTUPNI_PREGLED:
                        dbb.insert((DostupanTermin)req.getArgum());
                        o=new Odgovor(null, null);
                        break;
                    case VRATI_LEKARA:
                        o = new Odgovor(dbb.select(new Lekar(), false),null);
                        break;
                    case OBRISI_DOSTUPNI_PREGLED:
                        dbb.delete((DostupanTermin)req.getArgum());
                        o=new Odgovor(null, null);
                        break;
                    case FILTER_DOSTUPNIH:
                        LocalDate ld = (LocalDate) req.getArgum();
                        DostupanTermin dter = new DostupanTermin();
                        dter.setDatumVreme(ld.atTime(12, 30));
                        o = new Odgovor(dbb.select(dter, true),null);
                        break;
                    case VRATI_ZAKAZANE_TERMINE:
                        o = new Odgovor(dbb.select(new ZakazanTermin(), false),null);
                        break;
                    case DODAJ_ZAKAZAN_TERMIN:
                        dbb.insert((ZakazanTermin)req.getArgum());
                        o=new Odgovor(null, null);
                        break;  
                    case FILTER_ZAKAZANIH:
                        LocalDate datum = (LocalDate) req.getArgum();
                        ZakazanTermin zt = new ZakazanTermin();
                        zt.setDatumVreme(datum.atTime(12, 30));
                        o = new Odgovor(dbb.select(zt, true),null);
                        break;
                    case OBRISI_ZAKAZAN_TERMIN:
                        dbb.delete((ZakazanTermin) req.getArgum());
                        o=new Odgovor(null, null);
                        break;
                    case FILTER_IZVESTAJA:
                        ZakazanTermin z = (ZakazanTermin) req.getArgum();
                        Izvestaj i = new Izvestaj(z, null, null, null, null, null);
                        o = new Odgovor(dbb.select(i, true),null);
                        break;
                    case VRATI_IZVESTAJE:
                        Izvestaj iz = new Izvestaj();
                        o = new Odgovor(dbb.select(iz, false),null);
                        break;
                    case DODAJ_IZVESTAJ:
                        dbb.insert((Izvestaj)req.getArgum());
                        o=new Odgovor(null, null);
                        break;  
                    case FILTER_PACIJENATA:
                        Pacijent pa = (Pacijent) req.getArgum();
                        o = new Odgovor(dbb.select(pa, true),null);
                        break;
                    case IZMENI_PACIJENTA:
                        dbb.update((Pacijent)req.getArgum());
                        o=new Odgovor(null, null);
                        break;
                    default:
                        throw new RuntimeException("Operacija ne postoji");
                }
                sndr.salji(o);
            }
        } catch (Exception ex) {
            System.out.println("Kraj rada");
        }
    }
}
