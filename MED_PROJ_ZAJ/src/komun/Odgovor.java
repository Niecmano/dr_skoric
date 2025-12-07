/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komun;

import java.io.Serializable;

/**
 *
 * @author Nemanja
 */
public class Odgovor implements Serializable{
    
    private Object odg;
    private Exception ex;

    public Odgovor(Object odg, Exception ex) {
        this.odg = odg;
        this.ex = ex;
    }
    
    public Object getOdg() {
        return odg;
    }

    public void setOdg(Object odg) {
        this.odg = odg;
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
    
}
