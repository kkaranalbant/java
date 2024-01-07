/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import com.kaan.schoolmanagementmaven.exception.InvalidCostForPerHourException;

/**
 *
 * @author kaan
 */
public interface IAdminDefaultStudentProcesses {

    void setDefaultMaxDebtCredit(int value) throws SQLException;

    void setDefaultLessonCredit(int value) throws SQLException;
    
    void setDefaultNormalStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException;
    
    void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException ;
    
    void setDefaultWorkingStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException;
    
    void setDefaultNormalStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException ;
    
    void setCostForPerHourForWorkingStudents (int value) throws SQLException , InvalidCostForPerHourException ;
    
}
