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
public interface IDefaultValuesQuery {

    public int getDefaultLessonCredit() throws SQLException;

    public int getDefaultMaxDebtCredit() throws SQLException;

    public void setDefaultLessonCredit(int value) throws SQLException;

    public void setDefaultMaxDebtCredit(int value) throws SQLException;

    public void setDefaultBalance(int value) throws SQLException;

    public void setDefaultDebt(int value) throws SQLException;

    public int getDefaultBalance() throws SQLException;

    public int getDefaultDebt() throws SQLException;

    public void setDefaultNormalStudentUIDBound(int value) throws SQLException;

    public int getDefaultNormalStudentUIDBound() throws SQLException;

    public void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException;

    public int getDefaultWorkingStudentUIDOrigin() throws SQLException;

    public void setDefaultWorkingStudentUIDBound(int value) throws SQLException;

    public int getDefaultWorkingStudentUIDBound() throws SQLException;

    public void setDefaultTeacherUIDOrigin(int value) throws SQLException;

    public int getDefaultTeacherUIDOrigin() throws SQLException;

    public void setDefaultTeacherUIDBound(int value) throws SQLException;

    public int getDefaultTeacherUIDBound() throws SQLException;

    public void setDefaultNormalStudentUIDOrigin(int value) throws SQLException;

    public int getDefaultNormalStudentUIDOrigin() throws SQLException;

    public int getDefaultCostForPerHourForWorkingStudent() throws SQLException;

    public void setDefaultCostForPerHourForWorkingStudent(int value) throws SQLException;

    public int getDefaultLessonUIDOrigin() throws SQLException;

    public void setDefaultLessonUIDOrigin(int value) throws SQLException;

    public int getDefaultLessonUIDBound() throws SQLException;

    public void setDefaultLessonUIDBound(int value) throws SQLException;

}
