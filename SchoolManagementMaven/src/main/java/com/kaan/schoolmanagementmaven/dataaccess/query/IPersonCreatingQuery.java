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
public interface IPersonCreatingQuery {

    void createNormalStudentInDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException;

    void createWorkingStudentIndDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException;

    void createTeacherInDb(String name, String lastname, String username, String pass, int uid, String branchName, int salary, String phoneNumber) throws SQLException;
}
