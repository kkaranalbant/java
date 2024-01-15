/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonValidationQueries;
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
 *
 */
public class AdminSettingManager implements IAdminSettingManager {

    private static IAdminSettingManager adminSettingManager;

    private IPersonChangingManager personManager;
    private IPersonValidationQueries personValidator;

    static {
        adminSettingManager = null;
    }

    private AdminSettingManager() throws SQLException {
        personManager = PersonManager.getInstanceForChangingManager();
        personValidator = PersonValidationQueries.getInstance();
    }

    static IAdminSettingManager getInstance() throws SQLException {
        if (adminSettingManager == null) {
            adminSettingManager = new AdminSettingManager();
        }
        return adminSettingManager;
    }

    @Override
    public void setBalance(int uid, int value) throws SQLException, InvalidBalanceException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentBalanceWithUID(uid, value);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentBalanceWithUID(uid, value);

        } else {
            personManager.changeTeacherBalanceWithUID(uid, value);
        }
    }

    @Override
    public void setUsername(int uid, String newUsername) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentUserNameWithUID(uid, newUsername);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentUserNameWithUID(uid, newUsername);

        } else {
            personManager.changeTeacherUserNameWithUID(uid, newUsername);
        }
    }

    @Override
    public void setPass(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentPassWithUID(uid, newPass);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentPassWithUID(uid, newPass);

        } else {
            personManager.changeTeacherPassWithUID(uid, newPass);
        }
    }

    @Override
    public void setName(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentNameWithUID(uid, newName);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentNameWithUID(uid, newName);

        } else {
            personManager.changeTeacherNameWithUID(uid, newName);
        }
    }

    @Override
    public void setLastName(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentLastnameWithUID(uid, newLastname);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentLastnameWithUID(uid, newLastname);

        } else {
            personManager.changeTeacherLastnameWithUID(uid, newLastname);
        }
    }

    @Override
    public void setDebt(int uid, int value) throws SQLException, InvalidDebtException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentDebt(uid, value);

        } else {
            personManager.changeWorkingStudentDebt(uid, value);
        }
    }

    @Override
    public void setLessonCredit(int uid, int value) throws SQLException, InvalidLessonCreditException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentLessonCredit(uid, value);

        } else {
            personManager.changeWorkingStudentLessonCredit(uid, value);
        }
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
    public void setPhoneNumber(int uid, String newPhoneNumber) throws SQLException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException, NotUniquePhoneNumberException {
        if (personValidator.isValidUIDForNormalStudentTable(uid)) {
            personManager.changeNormalStudentPhoneNumber(uid, newPhoneNumber);

        } else if (personValidator.isValidUIDForWorkingStudentTable(uid)) {
            personManager.changeWorkingStudentPhoneNumber(uid, newPhoneNumber);

        } else {
            personManager.changeTeacherPhoneNumber(uid, newPhoneNumber);

        }
    }
}
