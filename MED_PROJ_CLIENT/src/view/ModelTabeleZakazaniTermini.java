/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import domen.ZakazanTermin;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nemanja
 */
public class ModelTabeleZakazaniTermini extends AbstractTableModel{
    private List<ZakazanTermin> termini;
    private String[] kols = {"lekar","datum","vreme","pacijent"};
    
    public ModelTabeleZakazaniTermini(List<ZakazanTermin> termini){
        this.termini=termini;
    }
    @Override
    public int getRowCount() {
        return termini.size();
    }

    @Override
    public int getColumnCount() {
        return kols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ZakazanTermin dt = termini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dt.getLekar();
            case 1:
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return dt.getDatumVreme().toLocalDate().format(f);
            case 2:
                return dt.getDatumVreme().toLocalTime();
            case 3:
                return dt.getPac();
            default:
                throw new AssertionError();
        }
    }
    
    public ZakazanTermin getTermin(int red){
        return termini.get(red);
    }

    @Override
    public String getColumnName(int column) {
        return kols[column];
    }
}
