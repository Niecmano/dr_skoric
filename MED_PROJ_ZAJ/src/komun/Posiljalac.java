/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komun;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Posiljalac {
    private ObjectOutputStream out;

    public Posiljalac(Socket skt) {
        try {
            out = new ObjectOutputStream(skt.getOutputStream());
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public synchronized void salji(Object obj) {
        try {
            out.reset();
            out.writeObject(obj);
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
