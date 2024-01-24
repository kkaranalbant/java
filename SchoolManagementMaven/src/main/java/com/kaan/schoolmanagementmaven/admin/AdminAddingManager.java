/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.exception.IntersectingUIDRangeException;
import com.kaan.schoolmanagementmaven.exception.InvalidBranchException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidSalaryException;
import com.kaan.schoolmanagementmaven.person.PersonManager;
import java.sql.SQLException;
import java.util.Map;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;
import com.kaan.schoolmanagementmaven.person.IPersonCreatorManager;

/**
 *
 * @author kaan
 *
 */
public class AdminAddingManager implements IAdminAddingManager {

    IPersonCreatorManager personManager;

    private IAdminDefaultStudentProcessesManager defValManagerForStudent;

    private IAdminDefaultTeacherProcessesManager defaultValuesManagerForTeacher;

    private static IAdminAddingManager adminAddingManager;

    private AdminAddingManager() throws SQLException {
        personManager = PersonManager.getInstanceForCreatingManager();
        defValManagerForStudent = AdminDefaultPersonProcessesManager.getInstanceForStudent();
        defaultValuesManagerForTeacher = AdminDefaultPersonProcessesManager.getInstanceForTeacher();
    }

    public static IAdminAddingManager getInstance() throws SQLException {
        if (adminAddingManager == null) {
            adminAddingManager = new AdminAddingManager();
        }
        return adminAddingManager;
    }

    @Override
    public Map<String, String> addNormalStudent(String name, String lastName, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException, ReachedMaximumRowNumberException, IntersectingUIDRangeException {
        defValManagerForStudent.throwExceptionIfUIDsIntersectsOtherWithOtherUIDs();
        throwExceptionIfInvalidPhoneNumberLengthOrPhoneCountryCode(phoneNumber);
        return personManager.createNewNormalStudentAndReturnLoginInfo(name, lastName, phoneNumber);
    }

    private void throwExceptionIfInvalidPhoneNumberLengthOrPhoneCountryCode(String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException {
        throwExceptionIfInvalidNumberLength(phoneNumber);
        throwExceptionIfInvalidCountryCode(phoneNumber);
    }

    private void throwExceptionIfInvalidNumberLength(String phoneNumber) throws InvalidPhoneNumberLengthException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
    }

    private void throwExceptionIfInvalidCountryCode(String phoneNumber) throws InvalidPhoneCountryCodeException {
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
    }

    @Override
    public Map<String, String> addWorkingStudent(String name, String lastName, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException, ReachedMaximumRowNumberException, IntersectingUIDRangeException {
        defValManagerForStudent.throwExceptionIfUIDsIntersectsOtherWithOtherUIDs();
        throwExceptionIfInvalidPhoneNumberLengthOrPhoneCountryCode(phoneNumber);
        return personManager.createNewWorkingStudentAndReturnLoginInfo(lastName, name, phoneNumber);
    }

    @Override
    public Map<String, String> addTeacher(String name, String lastName, String branchName, int salary, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException, InvalidBranchException, InvalidSalaryException, ReachedMaximumRowNumberException, IntersectingUIDRangeException {
        defaultValuesManagerForTeacher.throwExceptionIfUIDsIntersectsOtherWithOtherUIDs();
        throwExceptionIfInvalidBranchNameOrInvalidSalaryAmount(branchName, salary);
        throwExceptionIfInvalidPhoneNumberLengthOrPhoneCountryCode(phoneNumber);
        return personManager.createNewTeacherAndReturnLoginInfo(name, lastName, branchName, salary, phoneNumber);
    }

    private void throwExceptionIfInvalidBranchNameOrInvalidSalaryAmount(String branchName, int salary) throws InvalidBranchException, InvalidSalaryException {
        throwExceptionIfInvalidBranchName(branchName);
        throwExceptionIfInvalidSalaryAmount(salary);
    }

    private void throwExceptionIfInvalidSalaryAmount(int salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException();
        }
    }

    private void throwExceptionIfInvalidBranchName(String branchName) throws InvalidBranchException {
        if (branchName == null) {
            throw new InvalidBranchException();
        }
    }
    
}
