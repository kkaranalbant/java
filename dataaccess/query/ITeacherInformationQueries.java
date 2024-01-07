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
 */
public interface ITeacherInformationQueries {
    public ResultSet getAllTeacherNameLastnameAndUID() throws SQLException; 
    public ResultSet getAllTeacherUIDForBranchID(int branchID) throws SQLException;
    public List<String> getAllTeacherNameForBranchID (int branchID) throws SQLException;
    public List<String> getAllTeacherLastnameForBranchID (int branchID) throws SQLException;
}
