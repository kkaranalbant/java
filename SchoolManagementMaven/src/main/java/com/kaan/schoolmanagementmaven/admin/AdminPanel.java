/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.SchoolManagementGUI;
import java.sql.SQLException;

/**
 *
 * @author kaan
 *
 */
public class AdminPanel extends javax.swing.JFrame {

    private static Admin admin;

    /**
     * Creates new form AdminPanel
     *
     * @param admin
     */
    public AdminPanel(SchoolManagementGUI mainPanel, Admin admin) {
        initComponents();
        AdminPanel.admin = admin;
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                mainPanel.setVisible(true); // İkinci JFrame kapatıldığında ilk JFrame'i görünür yap
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        studentAddingButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        teacherAddingButton = new javax.swing.JButton();
        personDeletingButton = new javax.swing.JButton();
        personChangingButton = new javax.swing.JButton();
        lessonProcessButton = new javax.swing.JButton();
        dbProcess = new javax.swing.JButton();
        logFileProcessPanel = new javax.swing.JButton();
        accountSettingsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        studentAddingButton.setText("Student Adding Processes");
        studentAddingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentAddingButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Admin Panel");

        teacherAddingButton.setText("Teacher Adding Processes");
        teacherAddingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherAddingButtonActionPerformed(evt);
            }
        });

        personDeletingButton.setText("Person Deleting Panel");
        personDeletingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personDeletingButtonActionPerformed(evt);
            }
        });

        personChangingButton.setText("Person Changing Panel");
        personChangingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personChangingButtonActionPerformed(evt);
            }
        });

        lessonProcessButton.setText("Lesson Process Panel");
        lessonProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessonProcessButtonActionPerformed(evt);
            }
        });

        dbProcess.setText("Database Process Panel");
        dbProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbProcessActionPerformed(evt);
            }
        });

        logFileProcessPanel.setText("Log File Process Panel");
        logFileProcessPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logFileProcessPanelActionPerformed(evt);
            }
        });

        accountSettingsButton.setText("Account Settings");
        accountSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountSettingsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(studentAddingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(personChangingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lessonProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(logFileProcessPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(personDeletingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(teacherAddingButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dbProcess)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(accountSettingsButton)))
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentAddingButton)
                            .addComponent(teacherAddingButton))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(personDeletingButton)
                            .addComponent(personChangingButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dbProcess)
                            .addComponent(lessonProcessButton))
                        .addGap(61, 61, 61)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logFileProcessPanel)
                    .addComponent(accountSettingsButton))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentAddingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentAddingButtonActionPerformed
        this.setVisible(false);
        new AdminStudentAddingPanel(this, admin).setVisible(true);
    }//GEN-LAST:event_studentAddingButtonActionPerformed

    private void teacherAddingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherAddingButtonActionPerformed
        this.setVisible(false);
        try {
            new AdminTeacherAddingPanel(this, admin).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_teacherAddingButtonActionPerformed

    private void personDeletingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personDeletingButtonActionPerformed
        this.setVisible(false);
        new AdminPersonDeletingPanel(this, admin).setVisible(true);
    }//GEN-LAST:event_personDeletingButtonActionPerformed

    private void personChangingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personChangingButtonActionPerformed
        this.setVisible(false);
        try {
            new AdminPersonChangingMainPanel(this).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_personChangingButtonActionPerformed

    private void lessonProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessonProcessButtonActionPerformed
        this.setVisible(false);
        try {
            new AdminLessonProcessPanel(this).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lessonProcessButtonActionPerformed

    private void dbProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbProcessActionPerformed
        try {
            this.setVisible(false);
            new DatabaseProcessPanel(this).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_dbProcessActionPerformed

    private void logFileProcessPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logFileProcessPanelActionPerformed
        this.setVisible(false);
        new AdminLogFileProcessPanel(this).setVisible(true);
    }//GEN-LAST:event_logFileProcessPanelActionPerformed

    private void accountSettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountSettingsButtonActionPerformed
        this.setVisible(false);
        try {
            new AdminAccountSettingsPanel(this, admin).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_accountSettingsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountSettingsButton;
    private javax.swing.JButton dbProcess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lessonProcessButton;
    private javax.swing.JButton logFileProcessPanel;
    private javax.swing.JButton personChangingButton;
    private javax.swing.JButton personDeletingButton;
    private javax.swing.JButton studentAddingButton;
    private javax.swing.JButton teacherAddingButton;
    // End of variables declaration//GEN-END:variables
}
