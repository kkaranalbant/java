/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.person.PersonManager;
import java.sql.SQLException;
import java.util.Map;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import com.kaan.schoolmanagementmaven.person.IPersonCreatorManager;

/**
 *
 * @author kaan
 */
public class AdminAddingManager implements IAdminAddingManager {

    IPersonCreatorManager personManager;

    private static IAdminAddingManager adminAddingManager;

    private AdminAddingManager() throws SQLException {
        personManager = PersonManager.getInstanceForCreatingManager();
    }

    public static IAdminAddingManager getInstance() throws SQLException {
        if (adminAddingManager == null) {
            adminAddingManager = new AdminAddingManager();
        }
        return adminAddingManager;
    }

    @Override
    public Map<String, String> addNormalStudent(String name, String lastName, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
        return personManager.createNewNormalStudent(name, lastName , phoneNumber);
    }

    @Override
    public Map<String, String> addWorkingStudent(String name, String lastName, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
        return personManager.createNewWorkingStudent(lastName, name , phoneNumber);
    }

    @Override
    public Map<String, String> addTeacher(String name, String lastName, String branchName, int salary, String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
        return personManager.createNewTeacher(name, lastName, branchName, salary,phoneNumber);
    }

}
