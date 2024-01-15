/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;
import java.sql.SQLException ;

/**
 *
 * @author kaan
 * 
 */
public interface ILessonChangingQuery {
    void changeLessonName (String newName , int lessonUID) throws SQLException ;
    void changeLessonCredit (int newCredit , int lessonUID) throws SQLException ;
    void changeLessonHour (int newLessonHour , int lessonUID) throws SQLException ;
    void changeLessonQuota (int newQutoa , int lessonUID) throws SQLException ;
    void changeLessonMidtermRate (int newMidtermRate , int lessonUID) throws SQLException ;
    void changeLessonFinalRate (int newFinalRate , int lessonUID) throws SQLException ;
}
