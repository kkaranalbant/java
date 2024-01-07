/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;
import java.sql.SQLException ;

/**
 *
 * @author kaan
 */
public interface IStudentValueChangingQueries extends IPersonValueChangingQueries{
    void setNormalStudentDebt (int uid , int value) throws SQLException ;
    void setWorkingStudentDebt (int uid , int value) throws SQLException ;
    void setNormalStudentLessonCredit (int uid , int value) throws SQLException ;
    void setWorkingStudentLessonCredit (int uid , int value) throws SQLException ;
}
