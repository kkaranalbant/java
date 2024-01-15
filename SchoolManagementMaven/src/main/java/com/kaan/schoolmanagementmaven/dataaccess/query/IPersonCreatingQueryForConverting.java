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
public interface IPersonCreatingQueryForConverting {

    void createStudentInfoInDb(String studentTableName, String name, String lastname, int uid, String phoneNumber) throws SQLException;

    void createPersonLoginInfoInDb(String userName, String pass, int uid, String studentLoginInfoTableName) throws SQLException;
}
