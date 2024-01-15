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
public interface IPersonLoginQueries {

    public ResultSet getAllNormalStudentUserNameAndPassword() throws SQLException;

    public ResultSet getAllWorkingStudentUserNameAndPassword() throws SQLException;

    public ResultSet getAllTeacherUserNameAndPassword() throws SQLException;

    public String getNormalStudentUsernameByUID(int uid) throws SQLException;

    public String getWorkingStudentUsernameByUID(int uid) throws SQLException;

    public String getTeacherUsernameByUID(int uid) throws SQLException;

    public String getNormalStudentPassByUID(int uid) throws SQLException;

    public String getWorkingStudentPassByUID(int uid) throws SQLException;

    public String getTeacherPassByUID(int uid) throws SQLException;
    
    public String getPersonUsernameByUID(int uid) throws SQLException;
    
}
