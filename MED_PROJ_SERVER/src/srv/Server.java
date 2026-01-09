/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package srv;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nemanja
 */
public class Server extends Thread{

    private static ServerSocket srvskt;
    private static boolean radi = true; 

    @Override
    public void run() {     
        try {
            srvskt = new ServerSocket(9009, 5, InetAddress.getByName("192.168.0.19"));
//            srvskt = new ServerSocket(9009);
            while (radi) {
                Socket skt = srvskt.accept();
                KlijentskaNit kn = new KlijentskaNit(skt);
                kn.start();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void zaustavi() {
        radi = false;
        if (srvskt != null && !srvskt.isClosed()) {
            try {
                srvskt.close(); 
                System.out.println("Server zaustavljen.");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
