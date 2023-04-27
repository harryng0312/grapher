/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.model;

import org.harryng.demo.grapher.model.base.AbstractCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author hiep
 */
public class ColorChooserCellRenderer extends AbstractCellRenderer {

//    protected Color lastColor = null;
    protected JButton btnColorChooser = new JButton();
    protected JPanel pnl = new JPanel(new GridBagLayout());
    protected static JColorChooser colorChooser = new JColorChooser();
    protected static JDialog dialog = null;
//    protected JTable table = null;

    public ColorChooserCellRenderer() {
        pnl.setBackground(Color.WHITE);
//        btnColorChooser.setFocusable(false);
        btnColorChooser.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                lastColor = btnColorChooser.getBackground();
                dialog.setVisible(true);
            }
        });

        dialog = JColorChooser.createDialog(this.pnl,
                "Pick a Color",
                true, //modal
                colorChooser,
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ColorChooserCellRenderer.this.onColorChosen(e);
                    }
                }, //OK button handler
                null);

        pnl.add(btnColorChooser);
    }
    
    protected void onColorChosen(ActionEvent e){
        btnColorChooser.setBackground(colorChooser.getColor());
        onFocusLost(null);
    }
    
    protected void onColorCancel(ActionEvent e){
//        btnColorChooser.setBackground(lastColor);
        onFocusLost(null);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
//        this.table = table;
        if (value != null && value instanceof Color) {
            Color c = (Color) value;
//            btnColorChooser.setBackground(c);
            btnColorChooser.setBackground(c);
        } else {
//            btnColorChooser.setBackground(Color.WHITE);
            btnColorChooser.setBackground(Color.WHITE);
        }
        return pnl;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        this.table = table;
        //colorChooser.setColor((Color)value);
//        dialog.setVisible(true);
        //if(dialog.get)
//        value = colorChooser.getColor();

//        Component c =getTableCellRendererComponent(table, value, isSelected, isSelected, row, column);
        
        return pnl;
    }

    @Override
    public Object getCellEditorValue() {
        return btnColorChooser.getBackground();
    }
}
