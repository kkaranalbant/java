/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * @author kaan
 *
 */
public interface IPersonValidationQueries {

    ResultSet getAllNormalStudentNameAndLastname() throws SQLException;

    ResultSet getAllWorkingStudentNameAndLastname() throws SQLException;

    ResultSet getAllTeacherNameAndLastname() throws SQLException;

    Map<String, String> getAllNormalStudentUsernameAndPhoneNumber() throws SQLException;

    Map<String, String> getAllWorkingStudentUsernameAndPhoneNumber() throws SQLException;

    Map<String, String> getAllTeacherUsernameAndPhoneNumber() throws SQLException;

    boolean isValidUIDForNormalStudentTable(int uid) throws SQLException;

    boolean isValidUIDForWorkingStudentTable(int uid) throws SQLException;

    boolean isValidUIDForTeacherTable(int uid) throws SQLException;

}
