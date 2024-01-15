/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidBalanceException;
import com.kaan.schoolmanagementmaven.exception.InvalidDebtException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidSalaryException;
import com.kaan.schoolmanagementmaven.exception.InvalidUsernameLengthException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import com.kaan.schoolmanagementmaven.exception.NotUniquePhoneNumberException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueUsernameAndPassException;
import com.kaan.schoolmanagementmaven.person.IPersonChangingManager;
import com.kaan.schoolmanagementmaven.person.PersonManager;

/**
 *
 * @author kaan
 */
public class AdminSettingManager implements IAdminSettingManager{

    private static IAdminSettingManager adminSettingManager ;
    
    private IPersonChangingManager personManager ;
    
    static {
        adminSettingManager = null ;
    }
    
    private AdminSettingManager () throws SQLException{
        personManager = PersonManager.getInstanceForChangingManager();
    }
    
    static IAdminSettingManager getInstance () throws SQLException {
        if (adminSettingManager == null) adminSettingManager = new AdminSettingManager();
        return adminSettingManager ;
    }
    
    @Override
    public void setBalance(int uid, int value) throws SQLException, InvalidBalanceException {
        personManager.changeNormalStudentBalanceWithUID(uid, value);
        personManager.changeTeacherBalanceWithUID(uid, value);
        personManager.changeWorkingStudentBalanceWithUID(uid, value);
    }

    @Override
    public void setUsername(int uid, String newUsername) throws SQLException, NotUniqueUsernameAndPassException , InvalidUsernameLengthException{
        personManager.changeNormalStudentUserNameWithUID(uid, newUsername);
        personManager.changeTeacherUserNameWithUID(uid, newUsername);
        personManager.changeWorkingStudentUserNameWithUID(uid, newUsername);
    }

    @Override
    public void setPass(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException , InvalidPassLengthException {
        personManager.changeNormalStudentPassWithUID(uid, newPass);
        personManager.changeWorkingStudentPassWithUID(uid, newPass);
        personManager.changeTeacherPassWithUID(uid, newPass);
    }

    @Override
    public void setName(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        personManager.changeNormalStudentNameWithUID(uid, newName);
        personManager.changeWorkingStudentNameWithUID(uid, newName);
        personManager.changeTeacherNameWithUID(uid, newName);
    }
    
    @Override
    public void setLastName (int uid , String newLastname) throws SQLException , NotUniqueNameAndLastnameException {
        personManager.changeNormalStudentLastnameWithUID(uid, newLastname);
        personManager.changeWorkingStudentLastnameWithUID(uid, newLastname);
        personManager.changeTeacherLastnameWithUID(uid, newLastname);
    }

    @Override
    public void setDebt(int uid, int value) throws SQLException, InvalidDebtException {
        personManager.changeNormalStudentDebt(uid, value);
        personManager.changeWorkingStudentDebt(uid, value);
    }

    @Override
    public void setLessonCredit(int uid, int value) throws SQLException, InvalidLessonCreditException {
        personManager.changeNormalStudentLessonCredit(uid, value);
        personManager.changeWorkingStudentLessonCredit(uid, value);
    }

    @Override
    public void setSalary(int uid, int value) throws SQLException, InvalidSalaryException {
        personManager.changeTeacherSalary(uid, value);
    }

    @Override
    public void setBranch(int uid, int branchId) throws SQLException {
        personManager.changeTeacherBranch(uid, branchId);
    }
    
    @Override
    public void setPhoneNumber (int uid , String newPhoneNumber) throws SQLException , InvalidPhoneCountryCodeException , InvalidPhoneNumberLengthException , NotUniquePhoneNumberException {
        personManager.changeNormalStudentPhoneNumber(uid, newPhoneNumber);
        personManager.changeWorkingStudentPhoneNumber(uid, newPhoneNumber);
        personManager.changeTeacherPhoneNumber(uid, newPhoneNumber);
    }
    
    
}
