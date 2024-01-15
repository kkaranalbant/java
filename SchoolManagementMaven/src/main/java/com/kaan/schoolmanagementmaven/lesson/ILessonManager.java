/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.lesson;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.OutOfQuotaException;

/**
 *
 * @author kaan
 * 
 */
public interface ILessonManager {

    void addLessonToNormalStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException, OutOfQuotaException;

    void addLessonToWorkingStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException;

    void removeLessonFromNormalStudentCourse(String lessonName, int studentUID) throws SQLException;

    void removeLessonFromWorkingStudentCourse(String lessonName, int studentUID) throws SQLException;

}
