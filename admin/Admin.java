/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import com.kaan.schoolmanagementmaven.exception.InvalidUserNameOrPassException;
import com.kaan.schoolmanagementmaven.log.ILogManager;
import com.kaan.schoolmanagementmaven.log.LogManager;

/**
 *
 * @author kaan
 */
public class Admin {

    private IAdminAddingManager addingManager;
    private static Admin admin ;
    private String userName;
    private String pass;
    private static IAdminLoginController loginController;
    private IAdminPersonDeletingManager personDeletingManager ;
    private IAdminDefaultStudentProcesses defStudentProcesses ;
    private static ILogManager logManager ;
    private static Optional<ILogManager> optionalLogManager ;
    
    static {
        admin = null ;
        loginController = null ;
        logManager = null ;
        optionalLogManager = Optional.ofNullable(logManager);
    }

    private Admin(String userName, String pass) throws SQLException{
        this.userName = userName;
        this.pass = pass;
        this.defStudentProcesses = AdminDefaultPersonProcesses.getInstance() ;
        addingManager = AdminAddingManager.getInstance() ;
        personDeletingManager = AdminPersonDeletingManager.getInstance() ;
    }

    public static ILogManager getLogManager () {
        return Admin.logManager ;
    }
    
    public static Optional<ILogManager> getOptionalLogManager() {
        return Admin.optionalLogManager;
    }

    public static void setLogManager(File logFile) throws IOException{
        Admin.logManager = new LogManager (logFile) ;
    }
    

    /*
        Admin daha onceden olusturulmamis ise ve parametredeki veriler veritabani ile dogru bilgilerse yeni bir admin ensnesi olusturur 
        dogrulama saglanmadiysa geriye null doner 
        dogrulama saglandiysa ve eger daha onceden admin nesnesi olusturulmus ise yeni nesne olusturulmaz var olan nesne geriye doner .
     */
    public static Admin getInstance(String userName, String pass) throws SQLException , InvalidUserNameOrPassException{
        loginController = AdminLoginController.getInstance();
        if (admin != null && !loginController.isValidUserNameAndPass(userName, pass)) throw new InvalidUserNameOrPassException() ;
        if (admin == null && loginController.isValidUserNameAndPass(userName, pass)) {
            admin = new Admin(userName, pass);
        }
        return admin;
    }

    public IAdminAddingManager getAdminAddingManager() {
        return addingManager;
    }
    
    IAdminDefaultStudentProcesses getAdminDefaultStudentProcessesObject () {
        return defStudentProcesses ;
    }
    
    IAdminDefaultTeacherProcesses getAdminDefaultTeacherProcesesObject () {
        return (IAdminDefaultTeacherProcesses) defStudentProcesses ;
    }
    IAdminPersonDeletingManager getAdminPersonDeletingManager () {
        return  personDeletingManager ;
    }

}
