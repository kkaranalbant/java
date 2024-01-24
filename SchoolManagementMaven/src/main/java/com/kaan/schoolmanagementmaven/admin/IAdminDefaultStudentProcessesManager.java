/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.BoundAndOriginRangeSmallerThanRowNumberException;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import com.kaan.schoolmanagementmaven.exception.InvalidCostForPerHourException;
import com.kaan.schoolmanagementmaven.exception.InvalidDebtException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidMaxDebtCreditException;

/**
 *
 * @author kaan
 *
 */
public interface IAdminDefaultStudentProcessesManager extends IAdminDefaultPersonProccesesManager {

    void setDefaultMaxDebtCredit(int value) throws SQLException, InvalidMaxDebtCreditException;

    void setDefaultLessonCredit(int value) throws SQLException, InvalidLessonCreditException;

    void setDefaultDebt(int value) throws SQLException , InvalidDebtException ;

    void setDefaultNormalStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException;

    void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException;

    void setDefaultWorkingStudentUIDBound(int value) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException;

    void setDefaultNormalStudentUIDOrigin(int value) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException;

    void setCostForPerHourForWorkingStudents(int value) throws SQLException, InvalidCostForPerHourException;

}
