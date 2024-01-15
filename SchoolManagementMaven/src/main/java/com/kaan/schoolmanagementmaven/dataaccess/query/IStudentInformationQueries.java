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
public interface IStudentInformationQueries {

    public ResultSet getAllNormalStudentNameLastnameAndUID() throws SQLException;

    public ResultSet getAllWorkingStudentNameLastnameAndUID() throws SQLException;

}
