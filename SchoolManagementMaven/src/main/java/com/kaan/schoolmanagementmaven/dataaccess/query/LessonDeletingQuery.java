/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet ;
/**
 *
 * @author kaan
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
    public int deleteLesson(int uid) throws SQLException {
        deleteLesson(uid, super.getAccess().getNormalStudentCourseTable());
        deleteLesson(uid, super.getAccess().getWorkingStudentCourseTable());
        deleteLesson(uid, super.getAccess().getTeacherBranchTable());
        deleteLesson(uid, super.getAccess().getNormalStudentCourseTable());
        return deleteLesson(uid, super.getAccess().getLessonTable());


    }

    private int deleteLesson(int uid, String tableName) throws SQLException {
        String query = "delete from " + tableName + " where lesson_UID = " + uid + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeUpdate();
    }

}
