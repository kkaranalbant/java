/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidUserNameOrPassException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import java.util.Map;
import com.kaan.schoolmanagementmaven.exception.NotUniquePhoneNumberException;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonCreatorManager {

    Student createNormalStudent(String userName, String pass) throws SQLException, InvalidUserNameOrPassException;

    WorkingStudent createWorkingStudent(String userName, String pass) throws SQLException, InvalidUserNameOrPassException;

    Teacher createTeacher(String userName, String pass) throws SQLException, InvalidUserNameOrPassException;

    Map<String, String> createNewNormalStudentAndReturnLoginInfo(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException , NotUniquePhoneNumberException;

    Map<String, String> createNewWorkingStudentAndReturnLoginInfo(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException , NotUniquePhoneNumberException;

    Map<String, String> createNewTeacherAndReturnLoginInfo(String name, String lastName, String branchName, int salary, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException , NotUniquePhoneNumberException;
    
}
