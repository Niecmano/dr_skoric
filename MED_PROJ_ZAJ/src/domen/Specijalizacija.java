/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Nemanja
 */
public class Specijalizacija implements Serializable{
    private int sifraSpec;
    private String nazivSpec;

    public Specijalizacija() {}

    public Specijalizacija(int sifraSpec, String nazivSpec) {
        this.sifraSpec = sifraSpec;
        this.nazivSpec = nazivSpec;
    }

    public int getSifraSpec() { return sifraSpec; }
    public void setSifraSpec(int sifraSpec) { this.sifraSpec = sifraSpec; }

    public String getNazivSpec() { return nazivSpec; }
    public void setNazivSpec(String nazivSpec) { this.nazivSpec = nazivSpec; }

    @Override
    public String toString() {
        return nazivSpec;
    }
}


