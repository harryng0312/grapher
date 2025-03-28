/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmFunctionInputDialog.java
 *
 * Created on Dec 7, 2011, 9:22:01 AM
 */
package org.harryng.demo.grapher.form;

import org.harryng.demo.grapher.model.*;

import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author hiep
 */
public class FrmFunctionInputDialog extends javax.swing.JDialog {

    protected Observable observable = new Observable();
    protected Frame parent = null;
    List<FunctionInfo> lsFunc = null;

    /** Creates new form FrmFunctionInputDialog */
    public FrmFunctionInputDialog(Frame parent, List<FunctionInfo> lsFunc) {
        super(parent, true);
        this.parent = parent;
        this.lsFunc = lsFunc;
        initComponents();
        init();
        setLocationRelativeTo(parent);
    }

    protected void init() {
        List<Object[]> colMap = new ArrayList<Object[]>();
        colMap.add(new Object[]{"functionContent", "Content", true});
        colMap.add(new Object[]{"color", "Color", true});
        colMap.add(new Object[]{"drawed", "Drawed", true});
        TableFunctionInputModel model = new TableFunctionInputModel(colMap);

        tblFunction.setModel(model);
        tblFunction.setRowHeight(27);
        
        TableColumn col = tblFunction.getColumnModel().getColumn(2);
        CheckBoxCellRenderer chkColRender = new CheckBoxCellRenderer();
        col.setCellRenderer(chkColRender);
        col.setCellEditor(chkColRender);

        col = tblFunction.getColumnModel().getColumn(0);
        col.setMinWidth(400);
        TextBoxCellRenderer txtColRender =new TextBoxCellRenderer("y=");
        col.setCellEditor(txtColRender);
        col.setCellRenderer(txtColRender);
        
        col = tblFunction.getColumnModel().getColumn(1);
        ColorChooserCellRenderer colorColRender = new ColorChooserCellRenderer();
        col.setCellRenderer(colorColRender);
        col.setCellEditor(colorColRender);

        if(lsFunc==null){
            lsFunc = new ArrayList<FunctionInfo>();
            lsFunc.add(new FunctionInfo("x^2", Color.RED, true));
        }

        model.setDataList(lsFunc);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFunction = new javax.swing.JTable();
        btnAddFunction = new javax.swing.JButton();
        btnRemoveLast = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setViewportView(tblFunction);

        btnAddFunction.setText("Add");
        btnAddFunction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFunctionActionPerformed(evt);
            }
        });

        btnRemoveLast.setText("Remove last");
        btnRemoveLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLastActionPerformed(evt);
            }
        });

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddFunction, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveLast)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFunction)
                    .addComponent(btnRemoveLast)
                    .addComponent(btnApply))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFunctionActionPerformed
        TableFunctionInputModel model = (TableFunctionInputModel)tblFunction.getModel();
        List<FunctionInfo> rs = getFunctions();
//        for(FunctionInfo inf:rs){
//            System.out.println(inf.isDrawed());
//        }
        Random rand =new Random();
        if( rs.size()==0 || rs.get(rs.size()-1).getFunctionContent().trim().length()>0 ){
            rs.add(new FunctionInfo("", new Color(rand.nextInt()), true));
        }
        model.setDataList(rs);
    }//GEN-LAST:event_btnAddFunctionActionPerformed

    private void btnRemoveLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLastActionPerformed
        TableFunctionInputModel model = (TableFunctionInputModel)tblFunction.getModel();
        List<FunctionInfo> rs = getFunctions();
        
//        for(FunctionInfo inf:rs){
//            System.out.println(inf.isDrawed());
//        }
        if(rs.size()>0 && rs.get(rs.size()-1).getFunctionContent().trim().length() ==0 ){
            rs.remove(rs.size()-1);
        }
        model.setDataList(rs);
    }//GEN-LAST:event_btnRemoveLastActionPerformed

    public List<FunctionInfo> getFunctions(){
        TableFunctionInputModel model = (TableFunctionInputModel)tblFunction.getModel();
        
        return model.getDataList();
    }
    
    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
//        btnApply.f
        observable.notifyObservers(getFunctions());
        this.dispose();
    }//GEN-LAST:event_btnApplyActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        //observable.notifyObservers(getFunctions());
    }//GEN-LAST:event_formWindowClosed

    public Observable getObservable(){
        return this.observable;
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmFunctionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmFunctionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmFunctionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmFunctionInputDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new FrmFunctionInputDialog(null).setVisible(true);
//
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnAddFunction;
    protected javax.swing.JButton btnApply;
    protected javax.swing.JButton btnRemoveLast;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTable tblFunction;
    // End of variables declaration//GEN-END:variables
}
