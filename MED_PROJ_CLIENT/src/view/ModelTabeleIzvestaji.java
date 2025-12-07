/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import domen.Izvestaj;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nemanja
 */
public class ModelTabeleIzvestaji extends AbstractTableModel{
    private List<Izvestaj> izvestaji;
    private String[] kols = {"lekar","datum","vreme","pacijent"};

    public ModelTabeleIzvestaji(List<Izvestaj> izvestaji) {
        this.izvestaji = izvestaji;
    }
    
    @Override
    public int getRowCount() {
        return izvestaji.size();
    }

    @Override
    public int getColumnCount() {
        return kols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Izvestaj dt = izvestaji.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dt.getZt().getLekar();
            case 1:
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return dt.getZt().getDatumVreme().toLocalDate().format(f);
            case 2:
                return dt.getZt().getDatumVreme().toLocalTime();
            case 3:
                return dt.getZt().getPac();
            default:
                throw new AssertionError();
        }
    }
    
    public Izvestaj getIzvestaj(int red){
        return izvestaji.get(red);
    }

    @Override
    public String getColumnName(int column) {
        return kols[column];
    }
}
