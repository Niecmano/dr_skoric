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
public class Zahtev implements Serializable{
    private Operacija op;
    private Object argum;

    public Zahtev(Operacija op, Object argum) {
        this.op = op;
        this.argum = argum;
    }

    public Operacija getOp() {
        return op;
    }

    public void setOp(Operacija op) {
        this.op = op;
    }

    public Object getArgum() {
        return argum;
    }

    public void setArgum(Object argum) {
        this.argum = argum;
    }
}
