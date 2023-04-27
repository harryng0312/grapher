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
public class CheckBoxCellRenderer  extends AbstractCellRenderer{

    protected JCheckBox chkBox = new JCheckBox();
    protected JPanel pnl = new JPanel(new GridBagLayout());
    
    public CheckBoxCellRenderer(){
        pnl.setBackground(Color.WHITE);
        chkBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                CheckBoxCellRenderer.this.onFocusLost(e);
            }
        });
        pnl.add(chkBox);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null && value instanceof Boolean) {
            chkBox.setSelected((Boolean) value);
            fireEditingStopped();
        }
        return pnl;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
        boolean isSelected, int row, int column) {
        
        return getTableCellRendererComponent(table, value, isSelected, isSelected, row, column);
    }

    @Override
    public Object getCellEditorValue() {
        return chkBox.isSelected();
    }

    
}
