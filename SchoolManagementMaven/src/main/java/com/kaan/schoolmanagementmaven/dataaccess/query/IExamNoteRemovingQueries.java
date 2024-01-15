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
public interface IExamNoteRemovingQueries {
    void removeNormalStudentFromExamTable (int studentUID) throws SQLException ;
    void removeWorkingStudentFromExamTable (int studentUID) throws SQLException ;
    void removeNormalStudentFromExamTable (int studentUID , int lessonUID) throws SQLException ;
    void removeWorkingStudentFromExamTable (int studentUID , int lessonUID) throws SQLException ;
}
