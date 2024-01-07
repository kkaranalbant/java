/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;
import java.sql.SQLException; 

/**
 *
 * @author kaan
 */
public interface IStudentConvertingManager {
    void convertToWorkingStudent (Student student) throws SQLException;
    void convertToNormalStudent (WorkingStudent workingStudent) throws SQLException;
}
