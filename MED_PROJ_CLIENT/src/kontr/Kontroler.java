/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontr;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import domen.*;
import java.time.LocalDate;
import komun.*;

/**
 *
 * @author Nemanja
 */
public class Kontroler {
    
    private static Kontroler kontroler;
    private Socket skt;
    private Primalac rcv;
    private Posiljalac sndr;
    
    private Kontroler(){
        try {
            skt = new Socket("localhost", 9009);
//            skt = new Socket("192.168.0.23", 9009);
            sndr = new Posiljalac(skt);
            rcv = new Primalac(skt);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static Kontroler getInstance(){
        if(kontroler==null) kontroler = new Kontroler();
        return kontroler;
    }

    public List<Pacijent> vratiPacijente() {
        Zahtev req = new Zahtev(Operacija.VRATI_PACIJENTA, null);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<Pacijent>) o.getOdg();
    }

    public void izbrisiPacijent(int id) {
        Zahtev req = new Zahtev(Operacija.OBRISI_PACIJENTA, id);
        sndr.salji(req);
        rcv.primi();
    }

    public Exception dodajPacijent(Pacijent p) {
        Zahtev req = new Zahtev(Operacija.DODAJ_PACIJENTA, p);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        if(o.getEx()!=null){
            return o.getEx();
        }
        return null;
    }

    public List<ZakazanTermin> vratiZakazaneT() {
        Zahtev req = new Zahtev(Operacija.VRATI_ZAKAZANE_TERMINE, null);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<ZakazanTermin>) o.getOdg();
    }

    public List<Lekar> vratiLekare() {
        Zahtev req = new Zahtev(Operacija.VRATI_LEKARA, null);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<Lekar>) o.getOdg();
    }

    public Exception dodajZakazanT(ZakazanTermin zt) {
        Zahtev req = new Zahtev(Operacija.DODAJ_ZAKAZAN_TERMIN, zt);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        if(o.getEx()!=null){
            return o.getEx();
        }
        return null; 
    }

    public List<ZakazanTermin> filtrirajZakazaneT(LocalDate ld) {
        Zahtev req = new Zahtev(Operacija.FILTER_ZAKAZANIH, ld);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<ZakazanTermin>) o.getOdg();
    }

    public void izbrisiZakazanT(ZakazanTermin zt) {
        Zahtev req = new Zahtev(Operacija.OBRISI_ZAKAZAN_TERMIN, zt);
        sndr.salji(req);
        rcv.primi();
    }

    public List<Izvestaj> filtrirajIzvestaje(ZakazanTermin zt) {
        Zahtev req = new Zahtev(Operacija.FILTER_IZVESTAJA, zt);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<Izvestaj>) o.getOdg();
    }

    public List<Izvestaj> vratiIzvestaje() {
        Zahtev req = new Zahtev(Operacija.VRATI_IZVESTAJE, null);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        System.out.println(((List<Izvestaj>) o.getOdg()).get(0).getDatumVreme());
        return (List<Izvestaj>) o.getOdg();
    }

    public Exception dodajIzvestaj(Izvestaj i) {
        Zahtev req = new Zahtev(Operacija.DODAJ_IZVESTAJ, i);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        if(o.getEx()!=null){
            return o.getEx();
        }
        return null; 
    }

    public List<Pacijent> filtrirajPacijente(Pacijent pac) {
        Zahtev req = new Zahtev(Operacija.FILTER_PACIJENATA, pac);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
        return (List<Pacijent>) o.getOdg();
    }

    public void izmeniPacijenta(Pacijent p) {
        Zahtev req = new Zahtev(Operacija.IZMENI_PACIJENTA, p);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
    }

    public void izmeniIzvestaj(Izvestaj i) {
        Zahtev req = new Zahtev(Operacija.IZMENI_IZVESTAJ, i);
        sndr.salji(req);
        Odgovor o = (Odgovor) rcv.primi();
    }
}

//    public Exception izmeniKlub(PlivackiKlub p) {
//        Zahtev req = new Zahtev(Operacija.IZMENI_KLUBOVE, p);
//        sndr.salji(req);
//        Odgovor o = (Odgovor) rcv.primi();
//        if(o.getEx()!=null){
//            return o.getEx();
//        }
//        return null;
//    }

//    public List<PlivackiKlub> filtrirajKlubove(String naziv, Mesto mesto) {
//        Object[] pom = {naziv,mesto};
//        Zahtev req = new Zahtev(Operacija.FILTER_KLUBOVE, pom);
//        sndr.salji(req);
//        Odgovor odg = (Odgovor) rcv.primi();
//        return (List<PlivackiKlub>) odg.getOdg();
//    }

