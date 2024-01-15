/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kaan
 * 
 */
public interface ITeacherInformationQueries {
    public ResultSet getAllTeacherNameLastnameAndUID() throws SQLException; 
    public ResultSet getAllTeacherUIDWith (int branchID) throws SQLException;
    public List<String> getAllTeacherNameWith (int branchID) throws SQLException;
    public List<String> getAllTeacherLastnameWith (int branchID) throws SQLException;
    public int getBranchId(int teacherUID) throws SQLException ;
}
