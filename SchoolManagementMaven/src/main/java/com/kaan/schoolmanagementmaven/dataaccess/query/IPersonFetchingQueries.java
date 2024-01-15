/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kaan
 *
 */
public interface IPersonFetchingQueries {

    public ResultSet getNormalStudentInfo(int uid) throws SQLException;

    public ResultSet getWorkingStudentInfo(int uid) throws SQLException;

    public ResultSet getTeacherInfo (int uid) throws SQLException;

    public ResultSet getAllNormalStudentInfo() throws SQLException;

    public ResultSet getAllWorkingStudentInfo() throws SQLException;

    public ResultSet getAllTeacherInfo () throws SQLException;

    public int getPersonUIDByNameAndLastname (String name , String lastName) throws SQLException ;

    public int getPersonUIDByPhoneNumber (String phoneNumber) throws SQLException ;



}
