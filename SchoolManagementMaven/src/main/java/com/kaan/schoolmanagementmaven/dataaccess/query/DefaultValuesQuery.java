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
 */
public class DefaultValuesQuery extends Query implements IDefaultValuesQuery {

    private static DefaultValuesQuery defValuesQuery = null;
    private ResultSet defaultValueRow;

    private DefaultValuesQuery() throws SQLException {
        String gettingRowQuery = getDefaultValueRowQueryString();
        defaultValueRow = super.runGettingQuery(gettingRowQuery);
        defaultValueRow.next();
    }

    public static IDefaultValuesQuery getInstance() throws SQLException {
        if (defValuesQuery == null) {
            defValuesQuery = new DefaultValuesQuery();
        }
        return defValuesQuery;
    }

    @Override
    public int getDefaultLessonCredit() throws SQLException {
        return defaultValueRow.getInt("default_lesson_credit");
    }

    @Override
    public int getDefaultMaxDebtCredit() throws SQLException {
        return defaultValueRow.getInt("default_max_debt_credit");
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
        return defaultValueRow.getInt("default_balance");
    }

    @Override
    public void setDefaultDebt(int value) throws SQLException {
        String query = setColumnValueQuery("default_debt", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultDebt() throws SQLException {
        return defaultValueRow.getInt("default_debt");
    }

    @Override
    public void setDefaultNormalStudentUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_normal_student_UID_origin", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultNormalStudentUIDOrigin() throws SQLException {
        return defaultValueRow.getInt("default_normal_student_UID_origin");
    }

    @Override
    public void setDefaultNormalStudentUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_normal_student_UID_bound", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultNormalStudentUIDBound() throws SQLException {
        return defaultValueRow.getInt("default_normal_student_UID_bound");
    }

    @Override
    public void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_working_student_UID_origin", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultWorkingStudentUIDOrigin() throws SQLException {
        return defaultValueRow.getInt("default_working_student_UID_origin");
    }

    @Override
    public void setDefaultWorkingStudentUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_working_student_UID_bound", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultWorkingStudentUIDBound() throws SQLException {
        return defaultValueRow.getInt("default_working_student_UID_bound");
    }

    @Override
    public void setDefaultTeacherUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_teacher_UID_origin", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultTeacherUIDOrigin() throws SQLException {
        return defaultValueRow.getInt("default_teacher_UID_origin");
    }

    @Override
    public void setDefaultTeacherUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_teacher_UID_bound", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultTeacherUIDBound() throws SQLException {
        return defaultValueRow.getInt("default_teacher_UID_bound");
    }

    @Override
    public int getDefaultCostForPerHourForWorkingStudent() throws SQLException {
        return defaultValueRow.getInt("cost_for_per_hour");
    }

    @Override
    public void setDefaultCostForPerHourForWorkingStudent(int value) throws SQLException {
        String query = setColumnValueQuery("cost_for_per_hour", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultLessonUIDOrigin() throws SQLException {
        return defaultValueRow.getInt("default_lesson_UID_origin");
    }

    @Override
    public void setDefaultLessonUIDOrigin(int value) throws SQLException {
        String query = setColumnValueQuery("default_lesson_UID_origin", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultLessonUIDBound() throws SQLException {
        return defaultValueRow.getInt("default_lesson_UID_bound");
    }

    @Override
    public void setDefaultLessonUIDBound(int value) throws SQLException {
        String query = setColumnValueQuery("default_lesson_UID_bound", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultUsernameLength() throws SQLException {
        return defaultValueRow.getInt("default_username_length");
    }

    @Override
    public void setDefaultUsernameLength(int value) throws SQLException {
        String query = setColumnValueQuery("default_username_length", value);
        super.runUpdatingQuery(query);
    }

    @Override
    public int getDefaultPassLength() throws SQLException {
        return defaultValueRow.getInt("default_pass_length");
    }

    @Override
    public void setDefaultPassLength(int value) throws SQLException {
        String query = setColumnValueQuery("default_pass_length", value);
        super.runUpdatingQuery(query);
    }

    private String getDefaultValueRowQueryString() {
        return "select * from " + super.getAccess().getDefaultValuesTable() + " ;";
    }

    private String setColumnValueQuery(String column, int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set " + column + " = " + value + " ;";
    }

}
