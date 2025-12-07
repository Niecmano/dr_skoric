/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
/**
 *
 * @author Nemanja
 */
public class Konekcija {
    private static Konekcija konekcija;
    private static Connection conn;
    private Konekcija(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MEDILEK_TIM?useSSL=false&serverTimezone=Europe/Belgrade","root","");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public static Konekcija getInstance(){
        if(konekcija==null) konekcija = new Konekcija();
        return konekcija;
    }
    public Connection getConn(){
        return conn;
    }
    public static void closeDBConn(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
