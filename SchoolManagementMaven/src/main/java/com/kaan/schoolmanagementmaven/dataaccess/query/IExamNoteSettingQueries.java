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
public interface IExamNoteSettingQueries {

    void setMidtermNote(int studentUID, int lessonUID ,int value) throws SQLException;

    void setFinalNote(int studentUID, int lessonUID, int value) throws SQLException;
    
    void setAverage (int studentUID , int lessonUID , int value) throws SQLException ;
}
