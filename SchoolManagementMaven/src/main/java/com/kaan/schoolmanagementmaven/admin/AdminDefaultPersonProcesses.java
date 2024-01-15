/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import com.kaan.schoolmanagementmaven.exception.InvalidCostForPerHourException;

/**
 *
 * @author kaan
 */
public class AdminDefaultPersonProcesses implements IAdminDefaultStudentProcesses , IAdminDefaultTeacherProcesses{

    private IDefaultValuesQuery defValQuery;

    private static AdminDefaultPersonProcesses defPersonProcesses;

    private AdminDefaultPersonProcesses() throws SQLException {
        defValQuery = DefaultValuesQuery.getInstance();
    }

    static IAdminDefaultStudentProcesses getInstanceForStudent() throws SQLException {
        if (defPersonProcesses == null) {
            defPersonProcesses = new AdminDefaultPersonProcesses();
        }
        return defPersonProcesses;
    }
    
    static IAdminDefaultTeacherProcesses getInstanceForTeacher () throws SQLException {
        return (IAdminDefaultTeacherProcesses) getInstanceForStudent() ;
    }
    @Override
    public void setDefaultMaxDebtCredit(int value) throws SQLException {
        defValQuery.setDefaultMaxDebtCredit(value);
    }

    @Override
    public void setDefaultLessonCredit(int value) throws SQLException {
        defValQuery.setDefaultLessonCredit(value);
    }

    @Override
    public void setDefaultNormalStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException {
        if (value < defValQuery.getDefaultNormalStudentUIDBound()) {
            defValQuery.setDefaultNormalStudentUIDOrigin(value);
        } else {
            throw new InvalidBoundAndOriginPairException();
        }

    }

    @Override
    public void setDefaultNormalStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException {
        if (value > defValQuery.getDefaultNormalStudentUIDOrigin()) {
            defValQuery.setDefaultNormalStudentUIDBound(value);
        } else {
            throw new InvalidBoundAndOriginPairException();
        }
    }

    @Override
    public void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException {
        if (value < defValQuery.getDefaultWorkingStudentUIDBound()) {
            defValQuery.setDefaultWorkingStudentUIDOrigin(value);
        } else {
            throw new InvalidBoundAndOriginPairException();
        }
    }

    @Override
    public void setDefaultWorkingStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException {
        if (value > defValQuery.getDefaultWorkingStudentUIDOrigin()) {
            defValQuery.setDefaultWorkingStudentUIDBound(value);
        } else {
            throw new InvalidBoundAndOriginPairException();
        }
    }

    @Override
    public void setTeacherUIDOrigin(int value) throws InvalidBoundAndOriginPairException , SQLException{
        if (value < defValQuery.getDefaultTeacherUIDBound()) {
            defValQuery.setDefaultTeacherUIDOrigin(value);
        }
        else {
            throw new InvalidBoundAndOriginPairException () ;
        }
    }

    @Override
    public void setTeacherUIDBound(int value) throws InvalidBoundAndOriginPairException , SQLException {
        if (value > defValQuery.getDefaultTeacherUIDOrigin()) {
            defValQuery.setDefaultTeacherUIDBound(value);
        }
        else {
            throw new InvalidBoundAndOriginPairException () ;
        }
    }

    @Override
    public void setCostForPerHourForWorkingStudents(int value) throws SQLException, InvalidCostForPerHourException {
        if (value <= 0) throw new InvalidCostForPerHourException();
        defValQuery.setDefaultCostForPerHourForWorkingStudent(value);
    }
    
    

}
