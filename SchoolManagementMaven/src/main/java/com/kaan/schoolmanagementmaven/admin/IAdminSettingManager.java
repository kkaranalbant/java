/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.InvalidBalanceException;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidDebtException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidSalaryException;
import com.kaan.schoolmanagementmaven.exception.InvalidUsernameLengthException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueUsernameAndPassException;

/**
 *
 * @author kaan
 * 
 */
public interface IAdminSettingManager {

    public void setBalance(int uid, int value) throws SQLException, InvalidBalanceException;

    public void setUsername(int uid, String newUsername) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException;

    public void setPass(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException;

    public void setName(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException;

    public void setLastName(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException;

    public void setDebt(int uid, int value) throws SQLException, InvalidDebtException;

    public void setLessonCredit(int uid, int value) throws SQLException, InvalidLessonCreditException;

    public void setSalary(int uid, int value) throws SQLException, InvalidSalaryException;

    public void setBranch(int uid, int branchId) throws SQLException;

    public void setPhoneNumber(int uid, String newPhoneNumber) throws SQLException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException ;
}
