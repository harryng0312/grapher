/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hiep
 */
public class TableFunctionInputModel extends DefaultTableModel {

    protected List<Object[]> tableColumnNameMappingList = null;
    protected List<FunctionInfo> lsFunc = null;

    public TableFunctionInputModel(List<Object[]> tableColumnNameMap) {
        this.tableColumnNameMappingList = tableColumnNameMap;
        if (tableColumnNameMap != null) {
            for (Object[] ent : tableColumnNameMap) {
                if (ent != null && ent.length == 3) {
                    super.addColumn(ent[1]);

                }
            }
        }
    }

    public void setDataList(List<FunctionInfo> ls) {
        this.lsFunc = ls;
        this.setRowCount(0);
        if (ls != null) {
            int i = 0;
            for (FunctionInfo inf : ls) {
                Vector vec = new Vector(tableColumnNameMappingList.size());
                for (Object[] ent : tableColumnNameMappingList) {
                    try {
                        vec.add(FunctionInfo.class.getMethod(
                                String.format("get%s%s", ent[0].toString().substring(0, 1).toUpperCase(),
                                ent[0].toString().substring(1)), null).invoke(inf, null));
                    } catch (Exception noS) {
                        try {
                            vec.add(FunctionInfo.class.getMethod(
                                    String.format("is%s%s", ent[0].toString().substring(0, 1).toUpperCase(),
                                    ent[0].toString().substring(1)), null).invoke(inf, null));
                        } catch (Exception ex) {
                            vec.add(null);
                        }
                    }

                }
                this.addRow(vec);
            }
        }
    }

    public List<FunctionInfo> getDataList() {
        List<FunctionInfo> rs = new ArrayList<FunctionInfo>();
        for (int i = 0; i < getRowCount(); i++) {
            if (!getValueAt(i, 0).equals("")) {
                FunctionInfo inf = new FunctionInfo(String.valueOf(getValueAt(i, 0)),
                        (Color) getValueAt(i, 1), Boolean.valueOf(String.valueOf(getValueAt(i, 2))));
                rs.add(inf);
            }
        }
        return rs;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        boolean rs = tableColumnNameMappingList.get(column)[2] == null ? false
                : (Boolean) tableColumnNameMappingList.get(column)[2];
        return rs;
    }
}
