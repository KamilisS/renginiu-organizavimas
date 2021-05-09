/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import common.Event;
import common.Session;
import database.MSSQL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gvt48
 */
public class ReviewEventDialog extends javax.swing.JDialog {

    
    private int eventId;
    private Session session;
    private Event event;
    /**
     * Creates new form ReviewEventDialog
     */
    public ReviewEventDialog(java.awt.Frame parent, boolean modal, Session session, int eventId) {
        super(parent, modal);
        initComponents();
        this.descField.setLineWrap(true);
        
        this.eventId = eventId;
        this.session = session;
        this.getEventFromDB(this.eventId);
        if (new MSSQL().isAttendant(eventId, this.session.getMyID())) {
            this.attendBtn.setEnabled(false);
            this.attendBtn.setText("Jau užsiregistruota!");
        } else if (event.getMaxAmount() - event.getAttendantsCount() <= 0) {
            this.attendBtn.setEnabled(false);
            this.attendBtn.setText("Renginys pilnas!");
        } else if (event.getStartsAt().compareTo(new Date()) < 0) {
            this.attendBtn.setEnabled(false);
            this.attendBtn.setText("Renginio pradžia jau praeityje!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        typeField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        startField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        endDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ageLimitField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        spotsLeft = new javax.swing.JLabel();
        attendBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descField = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        organiserNameField = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pavadinimas");

        nameField.setEditable(false);

        jLabel2.setText("Tipas");

        typeField.setEditable(false);
        typeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Pradžia");

        startField.setEditable(false);

        jLabel4.setText("Pabaiga");

        endDate.setEditable(false);

        jLabel5.setText("Amžiaus limitas");

        ageLimitField.setEditable(false);

        jLabel6.setText("Kaina");

        priceField.setEditable(false);

        jLabel7.setText("Laisvų vietų:");

        spotsLeft.setText("efwef");

        attendBtn.setText("Dalyvauti");
        attendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Grįžti");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Aprašymas");

        descField.setEditable(false);
        descField.setColumns(20);
        descField.setRows(5);
        jScrollPane1.setViewportView(descField);

        jLabel9.setText("Organizatorius");

        organiserNameField.setText("name surname");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(attendBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(ageLimitField, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(startField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                                        .addComponent(nameField, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addComponent(jLabel3)))
                                            .addComponent(jLabel5)))
                                    .addComponent(jLabel7)
                                    .addComponent(spotsLeft))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(organiserNameField)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel2)
                                        .addComponent(typeField)
                                        .addComponent(endDate)
                                        .addComponent(priceField)
                                        .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageLimitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spotsLeft)
                    .addComponent(organiserNameField))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(attendBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void typeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldActionPerformed

    private void attendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendBtnActionPerformed
        new MSSQL().createAttendant(this.eventId, this.session.getMyID());
        this.dispose();
    }//GEN-LAST:event_attendBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ageLimitField;
    private javax.swing.JButton attendBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextArea descField;
    private javax.swing.JTextField endDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel organiserNameField;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel spotsLeft;
    private javax.swing.JTextField startField;
    private javax.swing.JTextField typeField;
    // End of variables declaration//GEN-END:variables

    private void getEventFromDB(int eventId) {
        this.event = new MSSQL().getEventForUser(eventId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String attendantsLeft = this.event.getMaxAmount() - this.event.getAttendantsCount() + " / " + this.event.getMaxAmount();
        String organiserName = event.getOrganiserName() + " " + event.getOrganiserLastName();
        
        this.nameField.setText(this.event.getName());
        this.typeField.setText(this.event.getEventTypeName());
        this.startField.setText(dateFormat.format(this.event.getStartsAt()));
        this.endDate.setText(dateFormat.format(this.event.getEndsAt()));
        this.ageLimitField.setText(this.event.getAgeLimit() == null ? "" : Integer.toString(this.event.getAgeLimit()));
        this.priceField.setText(this.event.getPrice() == null ? "" : Double.toString(this.event.getPrice()));
        this.descField.setText(this.event.getDescription() == null ? "" : this.event.getDescription());
        this.spotsLeft.setText(attendantsLeft);
        this.organiserNameField.setText(organiserName);   
    }
    
}
