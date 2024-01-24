/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 *
 */
public class DefaultValuesQuery extends Query implements IDefaultValuesQuery {
    
    private static DefaultValuesQuery defValuesQuery = null;
    
    private DefaultValuesQuery() throws SQLException {
    }
    
    public static IDefaultValuesQuery getInstance() throws SQLException {
        if (defValuesQuery == null) {
            defValuesQuery = new DefaultValuesQuery();
        }
        return defValuesQuery;
    }
    
    @Override
    public int getDefaultLessonCredit() throws SQLException {
        return getAttributeFromRow("default_lesson_credit");
    }
    
    @Override
    public int getDefaultMaxDebtCredit() throws SQLException {
        return getAttributeFromRow("default_max_debt_credit");
    }
    
    @Override
    public void setDefaultLessonCredit(int value) throws SQLException {
        String query = setColumnValueQuery("default_lesson_credit", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public void setDefaultMaxDebtCredit(int value) throws SQLException {
        String query = setColumnValueQuery("default_max_debt_credit", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public void setDefaultBalance(int value) throws SQLException {
        String query = setColumnValueQuery("default_balance", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultBalance() throws SQLException {
        return getAttributeFromRow("default_balance");
    }
    
    @Override
    public void setDefaultDebt(int value) throws SQLException {
        String query = setColumnValueQuery("default_debt", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultDebt() throws SQLException {
        return getAttributeFromRow("default_debt");
    }
    
    @Override
    public void setDefaultNormalStudentUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_normal_student_UID_origin", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultNormalStudentUIDOrigin() throws SQLException {
        return getAttributeFromRow("default_normal_student_UID_origin");
    }
    
    @Override
    public void setDefaultNormalStudentUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_normal_student_UID_bound", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultNormalStudentUIDBound() throws SQLException {
        return getAttributeFromRow("default_normal_student_UID_bound");
    }
    
    @Override
    public void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_working_student_UID_origin", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultWorkingStudentUIDOrigin() throws SQLException {
        return getAttributeFromRow("default_working_student_UID_origin");
    }
    
    @Override
    public void setDefaultWorkingStudentUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_working_student_UID_bound", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultWorkingStudentUIDBound() throws SQLException {
        return getAttributeFromRow("default_working_student_UID_bound");
    }
    
    @Override
    public void setDefaultTeacherUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_teacher_UID_origin", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultTeacherUIDOrigin() throws SQLException {
        return getAttributeFromRow("default_teacher_UID_origin");
    }
    
    @Override
    public void setDefaultTeacherUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_teacher_UID_bound", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultTeacherUIDBound() throws SQLException {
        return getAttributeFromRow("default_teacher_UID_bound");
    }
    
    @Override
    public int getDefaultCostForPerHourForWorkingStudent() throws SQLException {
        return getAttributeFromRow("cost_for_per_hour");
    }
    
    @Override
    public void setDefaultCostForPerHourForWorkingStudent(int value) throws SQLException {
        String query = setColumnValueQuery("cost_for_per_hour", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultLessonUIDOrigin() throws SQLException {
        return getAttributeFromRow("default_lesson_UID_origin");
    }
    
    @Override
    public void setDefaultLessonUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_lesson_UID_origin", value);
        super.runUpdatingQuery(query);
    }
    
    @Override
    public int getDefaultLessonUIDBound() throws SQLException {
        return getAttributeFromRow("default_lesson_UID_bound");
    }
    
    @Override
    public void setDefaultLessonUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_lesson_UID_bound", value);
        super.runUpdatingQuery(query);
    }
    
    private String getDefaultValueRowQueryString() {
        return "select * from " + super.getAccess().getDefaultValuesTable() + " ;";
    }
    
    private int getAttributeFromRow(String columnName) throws SQLException {
        String query = getDefaultValueRowQueryString();
        ResultSet defaultValueRow = super.runGettingQuery(query);
        defaultValueRow.next();
        return defaultValueRow.getInt(columnName);
    }
    
    private String setColumnValueQuery(String column, int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set " + column + " = " + value + " ;";
    }
    
}
