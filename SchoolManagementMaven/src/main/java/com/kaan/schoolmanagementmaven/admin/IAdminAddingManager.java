/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author kaan
 */
public interface IAdminAddingManager {

    Map<String, String> addNormalStudent(String name, String lastName , String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException , InvalidPhoneCountryCodeException , InvalidPhoneNumberLengthException;

    Map<String, String> addWorkingStudent(String name, String lastName,String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException , InvalidPhoneNumberLengthException;

    Map<String, String> addTeacher(String name, String lastName, String branchName , int salary,String phoneNumber) throws SQLException, NotUniqueNameAndLastnameException, InvalidPhoneCountryCodeException , InvalidPhoneNumberLengthException , InvalidBranchException , InvalidSalaryException;

}
