/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.harryng.demo.grapher;

import org.harryng.demo.grapher.form.FrmGrapher;

import javax.swing.*;

/**
 *
 * @author hiep.nq
 */
public class GrapherApp {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGrapher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmGrapher().setVisible(true));
    }
}
