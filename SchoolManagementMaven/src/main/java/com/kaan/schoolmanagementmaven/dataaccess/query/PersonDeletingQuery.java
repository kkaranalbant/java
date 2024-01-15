/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;

/**
 *
 * @author kaan
 * 
 */
public class PersonDeletingQuery extends Query implements IPersonDeletingQuery {

    private static IPersonDeletingQuery personDeletingObject;
    private IExamNoteRemovingQueries examTableRemovingObject;

    static {
        personDeletingObject = null;
    }

    private PersonDeletingQuery() throws SQLException {
        examTableRemovingObject = ExamNoteQueries.getInstanceForRemovingQueries();
    }

    public static IPersonDeletingQuery getInstance() throws SQLException {
        if (personDeletingObject == null) {
            personDeletingObject = new PersonDeletingQuery();
        }
        return personDeletingObject;
    }

    @Override
    public void deletePersonFromDb(int uid) throws SQLException, InvalidUIDException {
        int effectedRowNumber = deletePersonFromNormalStudentLoginTable(uid);
        if (effectedRowNumber != 0) {
            examTableRemovingObject.removeNormalStudentFromExamTable(uid);
            deletePersonFromNormalStudentsCourse(uid);
            deletePersonFromNormalStudentTable(uid);
            return;
        }
        effectedRowNumber = deletePersonFromWorkingStudentLoginTable(uid);
        if (effectedRowNumber != 0) {
            examTableRemovingObject.removeWorkingStudentFromExamTable(uid);
            deletePersonFromWorkingStudensCourse(uid);
            deletePersonFromWorkingStudentTable(uid);
            return;
        }
        effectedRowNumber = deletePersonFromTeacherLoginTable(uid);
        if (effectedRowNumber != 0) {
            deletePersonFromTeacherBranchTable(uid);
            deletePersonFromTeacherTable(uid);
            return;
        }
        throw new InvalidUIDException();
    }

    private void deletePersonFromNormalStudentTable(int uid) throws SQLException {
        String query = deleteNormalStudentQueryString(uid);
        super.runUpdatingQuery(query);
    }

    private void deletePersonFromWorkingStudentTable(int uid) throws SQLException {
        String query = deleteWorkingStudentQueryString(uid);
        super.runUpdatingQuery(query);
    }

    private void deletePersonFromTeacherTable(int uid) throws SQLException {
        String query = deleteTeacherQueryString(uid);
        super.runUpdatingQuery(query);
    }

    private String deleteNormalStudentQueryString(int uid) {
        return "delete from " + super.getAccess().getNormalStudentTable() + " where UID = " + uid;
    }

    private String deleteWorkingStudentQueryString(int uid) {
        return "delete from " + super.getAccess().getWorkingStudentTable() + " where UID = " + uid + "; ";
    }

    private String deleteTeacherQueryString(int uid) {
        return "delete from " + super.getAccess().getTeacherTable() + " where UID = " + uid;
    }

    private int deletePersonFromNormalStudentLoginTable(int uid) throws SQLException {
        String query = deletePersonFromNormalStudentLoginTableQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromNormalStudentLoginTableQueryString(int uid) {
        return "delete from " + super.getAccess().getNormalStudentLoginInfosTable() + " where normal_student_UID = " + uid + " ; ";
    }

    private int deletePersonFromWorkingStudentLoginTable(int uid) throws SQLException {
        String query = deletePersonFromWorkingStudentLoginTableQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromWorkingStudentLoginTableQueryString(int uid) {
        return "delete from " + super.getAccess().getWorkingStudentLoginInfosTable() + " where working_student_UID = " + uid + " ;";
    }

    private int deletePersonFromTeacherLoginTable(int uid) throws SQLException {
        String query = deletePersonFromTeacherLoginTableQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromTeacherLoginTableQueryString(int uid) {
        return "delete from " + super.getAccess().getTeacherLoginInfos() + " where teacher_UID = " + uid + " ;";
    }

    private int deletePersonFromNormalStudentsCourse(int uid) throws SQLException {
        String query = deletePersonFromNormalStudentsCourseQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromNormalStudentsCourseQueryString(int uid) {
        return "delete from " + super.getAccess().getNormalStudentCourseTable() + " where normal_student_UID = " + uid + " ;";
    }

    private int deletePersonFromWorkingStudensCourse(int uid) throws SQLException {
        String query = deletePersonFromWorkingStudensCourseQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromWorkingStudensCourseQueryString(int uid) {
        return "delete from " + super.getAccess().getWorkingStudentCourseTable() + " where working_student_UID = " + uid + " ;";
    }

    private int deletePersonFromTeacherBranchTable(int uid) throws SQLException {
        String query = deletePersonFromTeacherBranchTableQueryString(uid);
        return super.runUpdatingQuery(query);
    }

    private String deletePersonFromTeacherBranchTableQueryString(int uid) {
        return "delete from " + super.getAccess().getTeacherBranchTable() + " where teacher_UID = " + uid + " ;";
    }

}
