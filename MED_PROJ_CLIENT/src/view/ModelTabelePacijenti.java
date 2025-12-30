/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import domen.Pacijent;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nemanja
 */
public class ModelTabelePacijenti extends AbstractTableModel {
    private List<Pacijent> pacijenti;
    private String[] kols = {"sifra pacijenta","ime i prezime","datum rodjenja","telefon"};
    public ModelTabelePacijenti(List<Pacijent> pacijenti) {
        this.pacijenti = pacijenti;
    }
    
    @Override
    public int getRowCount() {
        return pacijenti.size();
    }

    @Override
    public int getColumnCount() {
        return kols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pacijent p = pacijenti.get(rowIndex);
        switch (columnIndex) {
            case 1:
                return p.getImePrez();
            case 0:
                return p.getSifraPac();
            case 2:
                return p.getDatumRodj().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            case 3:
                return p.getTelefon();
            default:
                throw new AssertionError();
        }
    }
    
    public Pacijent getPacijent(int red){
        return pacijenti.get(red);
    }

    @Override
    public String getColumnName(int column) {
        return kols[column];
    }
}
