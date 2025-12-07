/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komun;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Nemanja
 */
public class Posiljalac {
    
    private Socket skt;

    public Posiljalac(Socket skt) {
        this.skt = skt;
    }
    
    public void salji(Object obj){
        try {
            ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
            out.writeObject(obj);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
