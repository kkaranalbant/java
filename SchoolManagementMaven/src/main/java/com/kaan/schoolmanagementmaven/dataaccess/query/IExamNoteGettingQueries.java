/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public interface IExamNoteGettingQueries {
    int getNormalStudentMidtermValues (int studentUID , int lessonUID) throws SQLException;
    int getNormalStudentFinalValues (int studentUID , int lessonUID) throws SQLException;
    int getNormalStudentAverage (int studentUID , int lessonUID) throws SQLException;
    int getWorkingStudentMidtermValues (int studentUID , int lessonUID) throws SQLException;
    int getWorkingStudentFinalValues (int studentUID , int lessonUID) throws SQLException;
    int getWorkingStudentAverage (int studentUID , int lessonUID) throws SQLException;
}
