/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonValueChangingQueries {

    void changeNormalStudentName(int uid ,String newName) throws SQLException;

    void changeNormalStudentLastname(int uid ,String newName) throws SQLException;
    

    void changeWorkingStudentName(int uid ,String newName) throws SQLException;

    void changeWorkingStudentLastname(int uid ,String newName) throws SQLException;

    void changeTeacherName(int uid ,String newName) throws SQLException;

    void changeTeacherLastname(int uid ,String newName) throws SQLException;

    void changeNormalStudentBalance(int uid ,int newBalance) throws SQLException;

    void changeWorkingStudentBalance(int uid ,int newBalance) throws SQLException;

    void changeTeacherBalance(int uid ,int newBalance) throws SQLException;

    void changeNormalStudentUsername(int uid ,String newUserName) throws SQLException;

    void changeWorkingStudentUsername(int uid ,String newUserName) throws SQLException;

    void changeTeacherUsername(int uid ,String newUserName) throws SQLException;

    void changeNormalStudentPass(int uid ,String newPass) throws SQLException;

    void changeWorkingStudentPass(int uid ,String newPass) throws SQLException;

    void changeTeacherPass(int uid ,String newPass) throws SQLException;
    
    void changeNormalStudentPhoneNumber (int uid , String newPhoneNumber) throws SQLException ;
    
    void changeWorkingStudentPhoneNumber (int uid , String newPhoneNumber) throws SQLException ;
    
    void changeTeacherPhoneNumber (int uid , String newPhoneNumber) throws SQLException ;
    
    void changeNormalStudentPassWithPhoneNumber (String phoneNumber , String newPass) throws SQLException ;

    public void changeWorkingStudentPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException ;
    
    public void changeTeacherPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException ;
}
