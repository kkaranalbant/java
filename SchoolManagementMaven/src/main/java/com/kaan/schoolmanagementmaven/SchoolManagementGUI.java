/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kaan.schoolmanagementmaven;

import java.io.IOException;
import com.kaan.schoolmanagementmaven.admin.Admin;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.kaan.schoolmanagementmaven.admin.AdminPanel;
import com.kaan.schoolmanagementmaven.admin.FirstTimeAdminAccessLoginPanel;
import com.kaan.schoolmanagementmaven.dataaccess.connection.AccessManager;
import com.kaan.schoolmanagementmaven.exception.EmptyConnectionFileException;
import com.kaan.schoolmanagementmaven.exception.IncompatibleUsernameAndPhoneNumberException;
import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidUserNameOrPassException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueUsernameAndPassException;
import com.kaan.schoolmanagementmaven.log.LogManager;
import com.kaan.schoolmanagementmaven.person.IPersonChangingManager;
import com.kaan.schoolmanagementmaven.person.PersonManager;
import com.kaan.schoolmanagementmaven.person.Student;
import com.kaan.schoolmanagementmaven.person.NormalStudentPanel;
import com.kaan.schoolmanagementmaven.person.Teacher;
import com.kaan.schoolmanagementmaven.person.TeacherPanel;
import com.kaan.schoolmanagementmaven.person.IPersonCreatorManager;
import com.kaan.schoolmanagementmaven.person.IPersonSMSManager;
import com.kaan.schoolmanagementmaven.person.WorkingStudent;
import com.kaan.schoolmanagementmaven.person.WorkingStudentPanel;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JPasswordField;

/**
 *
 * @author kaan
 */
public class SchoolManagementGUI extends javax.swing.JFrame {

    private IPersonSMSManager smsSender;
    private IPersonChangingManager personChanger;

    public SchoolManagementGUI() {
        initComponents();
        try {
            AccessManager.loadAccessObject();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (EmptyConnectionFileException | SQLException ex) {
            adminLoginButton1.setEnabled(false);
            studentLoginButton.setEnabled(false);
            teacherLoginButton.setEnabled(false);
            new FirstTimeAdminAccessLoginPanel(this).setVisible(true);
        }
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                try {
                    LogManager.closeAllLogFiles();
                    AccessManager.closeAllStreams();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        adminLoginInfo = new javax.swing.JLabel();
        normalStudentLoginInfo = new javax.swing.JLabel();
        TeacherLoginInfo = new javax.swing.JLabel();
        adminUserName = new javax.swing.JTextField();
        studentUserName = new javax.swing.JTextField();
        teacherUserName = new javax.swing.JTextField();
        teacherLoginButton = new javax.swing.JButton();
        studentLoginButton = new javax.swing.JButton();
        adminLoginButton1 = new javax.swing.JButton();
        studentPassField = new javax.swing.JPasswordField();
        teacherPasswordField = new javax.swing.JPasswordField();
        adminPassField = new javax.swing.JPasswordField();
        forgotPassButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 88, Short.MAX_VALUE)
        );

        Header.setText("School Management Application");

        adminLoginInfo.setText(" Admin Login Panel");

        normalStudentLoginInfo.setText("Student Login Panel");

        TeacherLoginInfo.setText("   Teacher Login Panel");

        adminUserName.setText("username");
        adminUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUserNameActionPerformed(evt);
            }
        });

        studentUserName.setText("username");
        studentUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentUserNameActionPerformed(evt);
            }
        });

        teacherUserName.setText("username");
        teacherUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherUserNameActionPerformed(evt);
            }
        });

        teacherLoginButton.setText("Login");
        teacherLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherLoginButtonActionPerformed(evt);
            }
        });

        studentLoginButton.setText("Login");
        studentLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentLoginButtonActionPerformed(evt);
            }
        });

        adminLoginButton1.setText("Login");
        adminLoginButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminLoginButton1ActionPerformed(evt);
            }
        });

        studentPassField.setText("password");
        studentPassField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentPassFieldActionPerformed(evt);
            }
        });

        teacherPasswordField.setText("password");
        teacherPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherPasswordFieldActionPerformed(evt);
            }
        });

        adminPassField.setText("password");
        adminPassField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPassFieldActionPerformed(evt);
            }
        });

        forgotPassButton.setText("Forgot My  Password");
        forgotPassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotPassButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(adminLoginInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TeacherLoginInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addComponent(adminLoginButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(studentLoginButton)
                                                .addGap(42, 42, 42))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(129, 129, 129)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(adminPassField)
                                                        .addComponent(adminUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                                .addGap(277, 277, 277)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(studentUserName)
                                                        .addComponent(studentPassField)
                                                        .addComponent(normalStudentLoginInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(teacherLoginButton)
                                                .addGap(184, 184, 184))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(319, 319, 319)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(teacherUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                                        .addComponent(teacherPasswordField))
                                                .addGap(168, 168, 168))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(502, 502, 502)
                                                .addComponent(forgotPassButton))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(471, 471, 471)
                                                .addComponent(Header)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(normalStudentLoginInfo)
                                        .addComponent(TeacherLoginInfo)
                                        .addComponent(adminLoginInfo))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(adminUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(teacherUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(teacherPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentLoginButton)
                                        .addComponent(teacherLoginButton)
                                        .addComponent(adminLoginButton1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(forgotPassButton)
                                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(515, 515, 515)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void teacherUserNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void studentUserNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    private void adminUserNameActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    } 

    private void teacherLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = teacherUserName.getText();
        String pass = getPassword(teacherPasswordField);
        IPersonCreatorManager personManager;
        try {
            personManager = PersonManager.getInstanceForCreatingManager();
            Teacher teacher = personManager.createTeacher(userName, pass);
            JOptionPane.showMessageDialog(null, "Welcome.");
            this.setVisible(false);
            new TeacherPanel(this, teacher).setVisible(true);
        } catch (InvalidUserNameOrPassException | SQLException ex) {
            showErrorMessage(ex);
        }
    }
    
    private void studentPassFieldActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    } 
    
    private void teacherPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    } 
    
    private void adminPassFieldActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    } 
    
    private void studentLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = studentUserName.getText();
        String pass = getPassword(studentPassField);
        IPersonCreatorManager personManager = null;
        try {
            personManager = PersonManager.getInstanceForCreatingManager();
            Student student = personManager.createNormalStudent(userName, pass);
            JOptionPane.showMessageDialog(null, "Welcome.");
            this.setVisible(false);
            new NormalStudentPanel(this, student).setVisible(true);
            return;
        }
        
        catch (InvalidUserNameOrPassException ex) {
            
        }
        catch (SQLException ex) {
            showErrorMessage(ex);
        }
        try {
            WorkingStudent wStudent = personManager.createWorkingStudent(userName, pass);
            JOptionPane.showMessageDialog(null, "Welcome.");
            this.setVisible(false);
            new WorkingStudentPanel(this, wStudent).setVisible(true);
        } catch (InvalidUserNameOrPassException | SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void adminLoginButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = adminUserName.getText();
        String pass = getPassword(adminPassField);
        Admin admin = null;
        try {
            admin = Admin.getInstanceIfValidUsernameAndPass(userName, pass);
            JOptionPane.showMessageDialog(null, "Welcome");
            this.setVisible(false);
            new AdminPanel(this, admin).setVisible(true);
        } catch (InvalidUserNameOrPassException | SQLException ex) {
            showErrorMessage(ex);
        }
    }

    private void forgotPassButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String userName = JOptionPane.showInputDialog("Username : ");
        String phoneNumber = JOptionPane.showInputDialog("Phone number : ");
        try {
            smsSender = PersonManager.getInstanceForSMSManager();
            int verificationCode = smsSender.sendRecoverySMS(userName, phoneNumber);
            int rightNumber = 3;
            while (rightNumber > 0) {
                String input = JOptionPane.showInputDialog("(" + rightNumber + " right left.)\nVerification Code : ");
                int inputInt = Integer.parseInt(input);
                if (inputInt == verificationCode) {
                    String newPass = JOptionPane.showInputDialog("New password : ");
                    personChanger = PersonManager.getInstanceForChangingManager();
                    try {
                        personChanger.changeForgottenPass(phoneNumber, newPass);
                        return;
                    } catch (NotUniqueUsernameAndPassException | InvalidPassLengthException ex) {
                        showErrorMessage(ex);
                    }
                } else {
                    rightNumber--;
                }
            }

        } catch (NumberFormatException | InputMismatchException | IncompatibleUsernameAndPhoneNumberException | SQLException ex) {
            showErrorMessage(ex);
        }
    }

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
            java.util.logging.Logger.getLogger(SchoolManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchoolManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchoolManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchoolManagementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchoolManagementGUI().setVisible(true);
            }
        });
    }

    public JButton getAdminLoginButton1() {
        return adminLoginButton1;
    }

    public JButton getStudentLoginButton() {
        return studentLoginButton;
    }

    public JButton getTeacherLoginButton() {
        return teacherLoginButton;
    }

    private String getPassword(JPasswordField passField) {
        char[] passData = passField.getPassword();
        return String.copyValueOf(passData);
    }

    private void showErrorMessage(Exception ex) {
        if (ex instanceof NumberFormatException || ex instanceof InputMismatchException) {
            JOptionPane.showMessageDialog(null, "Invalid Entry.");
        } else {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel Header;
    private javax.swing.JLabel TeacherLoginInfo;
    private javax.swing.JButton adminLoginButton1;
    private javax.swing.JLabel adminLoginInfo;
    private javax.swing.JPasswordField adminPassField;
    private javax.swing.JTextField adminUserName;
    private javax.swing.JButton forgotPassButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel normalStudentLoginInfo;
    private javax.swing.JButton studentLoginButton;
    private javax.swing.JPasswordField studentPassField;
    private javax.swing.JTextField studentUserName;
    private javax.swing.JButton teacherLoginButton;
    private javax.swing.JPasswordField teacherPasswordField;
    private javax.swing.JTextField teacherUserName;
    // End of variables declaration
}
