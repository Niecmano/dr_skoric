/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.util.List;
import domen.*;
import java.sql.*;

/**
 *
 * @author Nemanja
 */
public class DBBroker {

    private static Connection conn;
    private static DBBroker dbb;
    public static DBBroker getInstance(){
        if(dbb==null) dbb = new DBBroker();
        return dbb;
    }
    private DBBroker() {
        conn = Konekcija.getInstance().getConn();
    }

    public static Connection getConn() {
        return conn;
    }
    
    public List<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat opstiDO, boolean filter){
        try {
            String upit = "SELECT * FROM " + opstiDO.nazivTabele()+ " " + opstiDO.kraciNazivTabele()
                    + " " + opstiDO.joinDeo()+" "+opstiDO.uslov();
            if(filter==true) upit += opstiDO.filter();
            System.out.println(upit);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            return opstiDO.vratiListuObj(rs);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public void update(OpstiDomenskiObjekat opstiDO){
        try {
            String upit = "UPDATE " + opstiDO.nazivTabele()+ " SET " + opstiDO.azuriranje();
            System.out.println(upit);
            Statement st = conn.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void delete(OpstiDomenskiObjekat opstiDO){
        try {
            String upit = "DELETE FROM " + opstiDO.nazivTabele()+ " WHERE " + opstiDO.brisanje();
            System.out.println(upit);
            Statement st = conn.createStatement();
            st.executeUpdate(upit);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public int insert(OpstiDomenskiObjekat opstiDO){
        int generatedId = -1;
        try {
            String upit = "INSERT INTO " + opstiDO.nazivTabele()+ " VALUES(" + opstiDO.vrednostiUbacivanje()+")";
            System.out.println(upit);
        PreparedStatement ps = conn.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }

    } catch (SQLException ex) {
        System.out.println(ex);
    }

    return generatedId;
    }
}

