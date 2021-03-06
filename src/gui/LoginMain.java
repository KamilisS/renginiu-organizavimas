/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import common.Common;

/**
 *
 * @author gvt48
 */
public class LoginMain extends javax.swing.JFrame {

    /**
     * Creates new form LoginChoice
     */
    public LoginMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orgLoginBtn = new javax.swing.JButton();
        dalyvLoginBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        orgRegisterBtn = new javax.swing.JButton();
        dalyvRegisterBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orgLoginBtn.setText("Organizatoriaus prisijungimas");
        orgLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgLoginBtnActionPerformed(evt);
            }
        });

        dalyvLoginBtn.setText("Dalyvio prisijungimas");
        dalyvLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dalyvLoginBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Pasirinkite prisijungimo būdą:");

        orgRegisterBtn.setText("Organizatoriaus registracija");
        orgRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgRegisterBtnActionPerformed(evt);
            }
        });

        dalyvRegisterBtn.setText("Dalyvio registracija");
        dalyvRegisterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dalyvRegisterBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Pasirinkite registracijos būdą:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(orgLoginBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dalyvLoginBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dalyvRegisterBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(orgRegisterBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(134, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(orgLoginBtn)
                .addGap(18, 18, 18)
                .addComponent(dalyvLoginBtn)
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(orgRegisterBtn)
                .addGap(18, 18, 18)
                .addComponent(dalyvRegisterBtn)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orgLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgLoginBtnActionPerformed
        OrgLoginDialog dialog = new OrgLoginDialog(this, true);
        dialog.setTitle("Organizatoriaus prisijungimas");
        dialog.setModal(true);
        dialog.pack();
        Common.center(dialog);
        dialog.setResizable(false);
        dialog.setVisible(true);
        
    }//GEN-LAST:event_orgLoginBtnActionPerformed

    private void dalyvLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dalyvLoginBtnActionPerformed
        UserLoginDialog dialog = new UserLoginDialog(this, true);
        dialog.setTitle("Vartotojo prisijungimas");
        dialog.setModal(true);
        dialog.pack();
        Common.center(dialog);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_dalyvLoginBtnActionPerformed

    private void dalyvRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dalyvRegisterBtnActionPerformed
        UserRegisterDialog dialog = new UserRegisterDialog(null, true);
        dialog.setTitle("Vartotojo registracija");
        dialog.setModal(true);
        dialog.pack();
        Common.center(dialog);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_dalyvRegisterBtnActionPerformed

    private void orgRegisterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgRegisterBtnActionPerformed
        OrgRegisterDialog dialog = new OrgRegisterDialog(null, true);
        dialog.setTitle("Organizatoriaus registracija");
        dialog.setModal(true);
        dialog.pack();
        Common.center(dialog);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }//GEN-LAST:event_orgRegisterBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dalyvLoginBtn;
    private javax.swing.JButton dalyvRegisterBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton orgLoginBtn;
    private javax.swing.JButton orgRegisterBtn;
    // End of variables declaration//GEN-END:variables
}
