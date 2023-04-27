/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.model;

import org.harryng.demo.grapher.model.base.AbstractCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author hiep
 */
public class TextBoxCellRenderer extends AbstractCellRenderer {
    
    protected JTextField txtValue = new JTextField();
    protected String pre = null;
    
    public TextBoxCellRenderer(String pre) {
        this.pre = pre;
        txtValue.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                TextBoxCellRenderer.this.onFocusLost(e);
            }
        });
        
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel lb = new JLabel(String.format("%s%s", pre, value));
        return lb;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //super.getTableCellEditorComponent(table, value, isSelected, row, column);
        txtValue.setText(String.valueOf(value));
        return txtValue;
    }
    
    @Override
    public Object getCellEditorValue() {
        return txtValue.getText();
    }
}
