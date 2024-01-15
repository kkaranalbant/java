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
public interface IStudentValueChangingQueries extends IPersonValueChangingQueries{
    void changeNormalStudentDebt (int uid , int value) throws SQLException ;
    void changeWorkingStudentDebt (int uid , int value) throws SQLException ;
    void changeNormalStudentLessonCredit (int uid , int value) throws SQLException ;
    void changeWorkingStudentLessonCredit (int uid , int value) throws SQLException ;
}
