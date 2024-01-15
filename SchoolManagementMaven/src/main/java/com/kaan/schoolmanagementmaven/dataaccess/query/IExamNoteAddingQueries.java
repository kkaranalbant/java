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
public interface IExamNoteAddingQueries {

    public void addNormalStudentToExamNoteTable(int studentUID, int lessonUID) throws SQLException;

    public void addWorkingStudentToExamNoteTable(int studentUID, int lessonUID) throws SQLException ;
}
