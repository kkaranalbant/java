/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;
import java.sql.SQLException ;

/**
 *
 * @author kaan
 * 
 */
public interface ITeacherValueChangingQueries extends IPersonValueChangingQueries{
    public void setTeacherSalary (int uid , int value) throws SQLException ;
    public void setTeacherBranch (int uid , int branchId) throws SQLException ;
}
