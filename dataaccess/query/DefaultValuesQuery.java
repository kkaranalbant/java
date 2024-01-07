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
        String query = getDefaultLessonCreditQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_lesson_credit");
        }
        return value;
    }

    @Override
    public int getDefaultMaxDebtCredit() throws SQLException {
        String query = getDefaultMaxDebtCreditQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_max_debt_credit");
        }
        return value;
    }

    @Override
    public void setDefaultLessonCredit(int value) throws SQLException {
        String query = setDefaultLessonCreditQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setDefaultMaxDebtCredit(int value) throws SQLException {
        String query = setDefaultMaxDebtCreditQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setDefaultBalance(int value) throws SQLException {
        String query = setDefaultBalanceQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultBalance() throws SQLException {
        String query = getDefaultBalanceQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_balance");
        }
        return value;
    }

    @Override
    public void setDefaultDebt(int value) throws SQLException {
        String query = setDefaultDebtQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultDebt() throws SQLException {
        String query = getDefaultDebtQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_debt");
        }
        return value;
    }

    private String getDefaultDebtQueryString() {
        return "select default_debt from " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultDebtQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set default_debt = " + value;
    }

    @Override
    public void setDefaultNormalStudentUIDOrigin(int value) throws SQLException {
        String query = setDefaultNormalStudentUIDOriginQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private String setDefaultNormalStudentUIDOriginQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set default_normal_student_UID_origin = " + value;
    }

    @Override
    public int getDefaultNormalStudentUIDOrigin() throws SQLException {
        String query = getDefaultNormalStudentUIDOriginQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_normal_student_UID_origin");
        }
        return value;
    }

    private String getDefaultNormalStudentUIDOriginQueryString() {
        return "select default_normal_student_UID_origin from " + super.getAccess().getDefaultValuesTable();
    }

    @Override
    public void setDefaultNormalStudentUIDBound(int value) throws SQLException {
        String query = setDefaultNormalStudentUIDBoundQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultNormalStudentUIDBound() throws SQLException {
        String query = getDefaultNormalStudentUIDBoundQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_normal_student_UID_bound");
        }
        return value;
    }

    private String getDefaultNormalStudentUIDBoundQueryString() {
        return "SELECT default_normal_student_UID_bound FROM " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultNormalStudentUIDBoundQueryString(int value) {
        return "UPDATE " + super.getAccess().getDefaultValuesTable() + " SET default_normal_student_UID_bound = " + value;
    }

    @Override
    public void setDefaultWorkingStudentUIDOrigin(int value) throws SQLException {
        String query = setDefaultWorkingStudentUIDOriginQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultWorkingStudentUIDOrigin() throws SQLException {
        String query = getDefaultWorkingStudentUIDOriginQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_working_student_UID_origin");
        }
        return value;
    }

    private String getDefaultWorkingStudentUIDOriginQueryString() {
        return "SELECT default_working_student_UID_origin FROM " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultWorkingStudentUIDOriginQueryString(int value) {
        return "UPDATE " + super.getAccess().getDefaultValuesTable() + " SET default_working_student_UID_origin = " + value;
    }

    @Override
    public void setDefaultWorkingStudentUIDBound(int value) throws SQLException {
        String query = setDefaultWorkingStudentUIDBoundQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultWorkingStudentUIDBound() throws SQLException {
        String query = getDefaultWorkingStudentUIDBoundQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_working_student_UID_bound");
        }
        return value;
    }

    private String getDefaultWorkingStudentUIDBoundQueryString() {
        return "SELECT default_working_student_UID_bound FROM " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultWorkingStudentUIDBoundQueryString(int value) {
        return "UPDATE " + super.getAccess().getDefaultValuesTable() + " SET default_working_student_UID_bound = " + value;
    }

    @Override
    public void setDefaultTeacherUIDOrigin(int value) throws SQLException {
        String query = setDefaultTeacherUIDOriginQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultTeacherUIDOrigin() throws SQLException {
        String query = getDefaultTeacherUIDOriginQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_teacher_UID_origin");
        }
        return value;
    }

    private String getDefaultTeacherUIDOriginQueryString() {
        return "SELECT default_teacher_UID_origin FROM " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultTeacherUIDOriginQueryString(int value) {
        return "UPDATE " + super.getAccess().getDefaultValuesTable() + " SET default_teacher_UID_origin = " + value;
    }

    @Override
    public void setDefaultTeacherUIDBound(int value) throws SQLException {
        String query = setDefaultTeacherUIDBoundQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultTeacherUIDBound() throws SQLException {
        String query = getDefaultTeacherUIDBoundQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_teacher_UID_bound");
        }
        return value;
    }

    @Override
    public int getDefaultCostForPerHourForWorkingStudent() throws SQLException {
        String query = getDefaultCostForPerHourForWorkingStudentQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("cost_for_per_hour");
        }
        return value;
    }

    @Override
    public void setDefaultCostForPerHourForWorkingStudent(int value) throws SQLException {
        String query = setDefaultCostForPerHourForWorkingStudentQueryString(value);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultLessonUIDOrigin() throws SQLException {
        String query = "select default_lesson_UID_origin from " + super.getAccess().getDefaultValuesTable() + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_lesson_UID_origin");
        }
        return value;
    }

    @Override
    public void setDefaultLessonUIDOrigin(int value) throws SQLException {
        String query = "update " + super.getAccess().getDefaultValuesTable() + " set default_lesson_UID_origin = " + value + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultLessonUIDBound() throws SQLException {
        String query = "select default_lesson_UID_bound from " + super.getAccess().getDefaultValuesTable() + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_lesson_UID_bound");
        }
        return value;
    }

    @Override
    public void setDefaultLessonUIDBound(int value) throws SQLException {
        String query = "update " + super.getAccess().getDefaultValuesTable() + " set default_lesson_UID_bound = " + value + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultUsernameLength() throws SQLException {
        String query = "select default_username_length from " + super.getAccess().getDefaultValuesTable();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_username_length");
        }
        return value;
    }

    @Override
    public void setDefaultUsernameLength(int value) throws SQLException {
        String query = "update " + super.getAccess().getDefaultValuesTable() + " set default_username_length = " + value;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getDefaultPassLength() throws SQLException {
        String query = "select default_pass_length from " + super.getAccess().getDefaultValuesTable();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int value = 0;
        while (resultSet.next()) {
            value = resultSet.getInt("default_pass_length");
        }
        return value;
    }

    @Override
    public void setDefaultPassLength(int value) throws SQLException {
        String query = "update " + super.getAccess().getDefaultValuesTable() + " set default_pass_length = " + value;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private String setDefaultCostForPerHourForWorkingStudentQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set cost_for_per_hour = " + value + " ;";
    }

    private String getDefaultCostForPerHourForWorkingStudentQueryString() {
        return "select cost_for_per_hour from " + super.getAccess().getDefaultValuesTable() + " ;";
    }

    private String getDefaultTeacherUIDBoundQueryString() {
        return "SELECT default_teacher_UID_bound FROM " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultTeacherUIDBoundQueryString(int value) {
        return "UPDATE " + super.getAccess().getDefaultValuesTable() + " SET default_teacher_UID_bound = " + value;
    }

    private String getDefaultBalanceQueryString() {
        return "select default_balance from " + super.getAccess().getDefaultValuesTable();
    }

    private String setDefaultBalanceQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set default_balance = " + value;
    }

    private String setDefaultLessonCreditQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set default_lesson_credit = " + value;
    }

    private String setDefaultMaxDebtCreditQueryString(int value) {
        return "update " + super.getAccess().getDefaultValuesTable() + " set default_max_debt_credit = " + value;
    }

    private String getDefaultLessonCreditQueryString() {
        return "select default_lesson_credit from " + super.getAccess().getDefaultValuesTable();
    }

    private String getDefaultMaxDebtCreditQueryString() {
        return "select default_max_debt_credit from " + super.getAccess().getDefaultValuesTable();
    }

}
