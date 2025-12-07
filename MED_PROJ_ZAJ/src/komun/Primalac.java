/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komun;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Nemanja
 */
public class Primalac {
    private Socket skt;

    public Primalac(Socket skt) {
        this.skt = skt;
    }
    
    public Object primi() {
        try {
            ObjectInputStream ois = new ObjectInputStream(skt.getInputStream());
            return ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        } 
        return null;
    }
}
