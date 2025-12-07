/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Nemanja
 */
import domen.Pacijent;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.util.*;


public class AutocompleteTextField extends JTextField {

    private final JPopupMenu popup;
    private final List<Pacijent> items;
    private Pacijent selectedPacijent = null;

    public AutocompleteTextField(List<Pacijent> items, int columns) {
        super(columns);
        this.items = new ArrayList<>(items);
        this.popup = new JPopupMenu();

        JTextComponent editor = this;
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = editor.getText();
                if (text.length() < 5) { 
                    popup.setVisible(false);
                    return;
                }
                showSuggestions(text);
            }
        });
    }

    private void showSuggestions(String text) {
        SwingUtilities.invokeLater(() -> {
            popup.removeAll();

            for (Pacijent p : items) {

                String prikaz = p.toString();

                if (prikaz.toLowerCase().contains(text.toLowerCase())) {

                    JMenuItem menuItem = new JMenuItem(prikaz);
                    menuItem.setFont(new Font("Arial", Font.PLAIN, 20));
                    menuItem.addActionListener(e -> {
                        setText(prikaz);
                        selectedPacijent = p; // čuvamo referencu na objekat
                        popup.setVisible(false);
                    });
                    popup.add(menuItem);
                }
            }

            if (popup.getComponentCount() > 0) {
                popup.show(this, 0, getHeight());
            } else {
                popup.setVisible(false);
            }
        });
    }

    public Pacijent getSelectedPacijent() {
        return selectedPacijent;
    }

}

