/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import domen.DostupanTermin;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nemanja
 */
public class ModelTabeleDostupniTermini extends AbstractTableModel{
    
    private List<DostupanTermin> termini;
    private String[] kols = {"lekar","datum","vreme"};
    
    public ModelTabeleDostupniTermini(List<DostupanTermin> termini){
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
        DostupanTermin dt = termini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dt.getLekar();
            case 1:
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                return dt.getDatumVreme().toLocalDate().format(f);
            case 2:
                return dt.getDatumVreme().toLocalTime();
            default:
                throw new AssertionError();
        }
    }
    
    public DostupanTermin getTermin(int red){
        return termini.get(red);
    }

    @Override
    public String getColumnName(int column) {
        return kols[column];
    }
    
}
