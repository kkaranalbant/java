/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;
import java.sql.SQLException ;
/**
 *
 * @author kaan
 * 
 */
public interface ILessonAttendanceQuery {
    void increaseAttendance (String lessonName) throws SQLException ;
    void decreaseAttendance (String lessonName) throws SQLException ;
    int getLessonAttendanceAmount(String lessonName) throws SQLException ;
    void addLessonAttendanceToDb (String lessonName) throws SQLException ;
    void removeLessonAttendanceFromDb (String lessonName) throws SQLException ;
}
