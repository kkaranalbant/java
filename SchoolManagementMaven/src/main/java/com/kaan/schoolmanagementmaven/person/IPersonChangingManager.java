/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

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

/**
 *
 * @author kaan
 *
 */
public interface IPersonChangingManager {

    public void changeNormalStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeNormalStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeNormalStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException;

    public void changeNormalStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException;

    public void changeNormalStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException;

    public void changeWorkingStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeTeacherNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeWorkingStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeTeacherLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException;

    public void changeWorkingStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException;

    public void changeTeacherBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException;

    public void changeWorkingStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException;

    public void changeTeacherUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException;

    public void changeWorkingStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException;

    public void changeTeacherPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException;

    public void changeNormalStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException;

    public void changeWorkingStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException;

    public void changeNormalStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException;

    public void changeWorkingStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException;

    public void changeTeacherSalary(int uid, int newSalary) throws SQLException, InvalidSalaryException;

    public void changeTeacherBranch(int uid, int newBranchId) throws SQLException;

    public void changeNormalStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException;

    public void changeWorkingStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException;

    public void changeTeacherUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException;

    public void changeNormalStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException;

    public void changeWorkingStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException;

    public void changeTeacherPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException , SQLException ;

    public void changeForgottenPass (String phoneNumber , String pass) throws InvalidPassLengthException , SQLException , NotUniqueUsernameAndPassException ;
}
