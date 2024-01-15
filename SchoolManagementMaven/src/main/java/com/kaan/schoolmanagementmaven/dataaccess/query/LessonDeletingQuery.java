/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
/**
 *
 * @author kaan
 * 
 */
public class LessonDeletingQuery extends Query implements ILessonDeletingQuery {

    private static ILessonDeletingQuery lessonDeletor;

    public static ILessonDeletingQuery getInstance() throws SQLException{
        if (lessonDeletor == null) {
            lessonDeletor = new LessonDeletingQuery();
        }
        return lessonDeletor;
    }

    private LessonDeletingQuery() throws SQLException {
    }

    @Override
    public boolean deleteLesson(int uid) throws SQLException {
        deleteLesson(uid, super.getAccess().getNormalStudentCourseTable());
        deleteLesson(uid, super.getAccess().getWorkingStudentCourseTable());
        deleteLesson(uid, super.getAccess().getTeacherBranchTable());
        deleteLesson(uid, super.getAccess().getNormalStudentCourseTable());
        int updatedRowNumber = deleteLesson(uid, super.getAccess().getLessonTable());
        if (updatedRowNumber != 0) return true ;
        return false ;
    }

    private int deleteLesson(int uid, String tableName) throws SQLException {
        String query = getDeletingLessonQueryString(uid, tableName);
        return super.runUpdatingQuery(query);
    }
    
    private String getDeletingLessonQueryString (int uid , String tableName) {
        return "delete from " + tableName + " where lesson_UID = " + uid + " ;";
    }

}
