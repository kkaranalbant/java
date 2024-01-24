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
 *
 */
public class Admin {

    private static IAdminLoginController loginController;

    private static Admin admin;
    private String userName;
    private String pass;

    private IAdminPersonDeletingManager personDeletingManager;
    private IAdminDefaultStudentProcessesManager defStudentProcesses;
    private IAdminDefaultTeacherProcessesManager defTeacherProcesses;
    private IAdminAddingManager addingManager;
    private IAdminLessonManager lessonManager;
    private IAdminStudentInformationManager studentInformation;
    private IAdminTeacherInformationManager teacherInformation;
    private IAdminSettingManager adminSetter;
    private IAdminInfoChanger adminInfoChanger;

    private static ILogManager logManager;
    private static Optional<ILogManager> optionalLogManager;

    static {
        admin = null;
        loginController = null;
        logManager = null;
        optionalLogManager = Optional.ofNullable(logManager);
    }

    private Admin(String userName, String pass) throws SQLException {
        this.userName = userName;
        this.pass = pass;
        personDeletingManager = AdminPersonDeletingManager.getInstance();
        defStudentProcesses = AdminDefaultPersonProcessesManager.getInstanceForStudent();
        defTeacherProcesses = AdminDefaultPersonProcessesManager.getInstanceForTeacher();
        addingManager = AdminAddingManager.getInstance();
        lessonManager = AdminLessonManager.getInstance();
        studentInformation = AdminPersonInformationManager.getInstanceForStudent();
        teacherInformation = AdminPersonInformationManager.getInstanceForTeacher();
        adminInfoChanger = AdminInfoChangerImpl.getInstance() ;
    }

    public static ILogManager getLogManager() {
        return Admin.logManager;
    }

    public static Optional<ILogManager> getOptionalLogManager() {
        return Admin.optionalLogManager;
    }

    public static void setLogManager(File logFile) throws IOException {
        Admin.logManager = new LogManager(logFile);
    }

    public static Admin getInstanceIfValidUsernameAndPass(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        loginController = AdminLoginController.getInstance();
        throwExceptionIfInvalidUsernameOrPass(userName, pass);
        if (!isAdminCreated() && loginController.isValidUserNameAndPass(userName, pass)) {
            admin = new Admin(userName, pass);
        }
        return admin;
    }

    private static void throwExceptionIfInvalidUsernameOrPass(String userName, String pass) throws SQLException {
        if (!loginController.isValidUserNameAndPass(userName, pass)) {
            throw new InvalidUserNameOrPassException();
        }
    }

    private static boolean isAdminCreated() {
        if (admin == null) {
            return false;
        }
        return true;
    }

    public IAdminAddingManager getAdminAddingManager() {
        return addingManager;
    }

    IAdminDefaultStudentProcessesManager getAdminDefaultStudentProcessesObject() {
        return defStudentProcesses;
    }

    IAdminPersonDeletingManager getAdminPersonDeletingManager() {
        return personDeletingManager;
    }

    IAdminDefaultTeacherProcessesManager getAdminDefaultTeacherProcessesObject() {
        return defTeacherProcesses;
    }

    IAdminLessonManager getLessonManager() {
        return lessonManager;
    }

    IAdminStudentInformationManager getStudentInformation() {
        return studentInformation;
    }

    IAdminTeacherInformationManager getTeacherInformation() {
        return teacherInformation;
    }

    IAdminSettingManager getAdminSetter() {
        return adminSetter;
    }

    IAdminInfoChanger getAdminInfoChanger() {
        return adminInfoChanger;
    }

    String getUserName() {
        return userName;
    }

    String getPass() {
        return pass;
    }

}
