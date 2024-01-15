/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author kaan
 * 
 */
public interface ILessonCourseQuery {

    void addLessonAndStudentToNormalStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException;

    void removeLessonAndStudentFromNormalStudentCourse(String lessonName, int studentUID) throws SQLException;

    void addLessonAndStudentToWorkingStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException;

    void removeLessonAndStudentFromWorkingStudentCourse(String lessonName, int studentUID) throws SQLException;

    List<Integer> getAllNormalStudentUID(int teacherUID) throws SQLException;

    List<Integer> getAllWorkingStudentUID(int teacherUID) throws SQLException;

    List<ResultSet> getAllNormalStudentInfo(int teacherUID) throws SQLException;

    List<ResultSet> getAllWorkingStudentInfo(int teacherUID) throws SQLException;

    int findTeacherUIDFromNormalStudentCourse(int studentUID, int lessonUID) throws SQLException;

    int findTeacherUIDFromWorkingStudentCourse(int studentUID, int lessonUID) throws SQLException;

}
