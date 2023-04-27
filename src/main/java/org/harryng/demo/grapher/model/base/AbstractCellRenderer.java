/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.model.base;

import java.awt.event.FocusEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author hiep
 */
public abstract class AbstractCellRenderer
    extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{
    
    public void onFocusLost(FocusEvent e){
        fireEditingStopped();
    }
}
