/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;
import java.sql.SQLException; 

/**
 *
 * @author kaan
 * 
 */
public interface IStudentConvertingManager {
    void convertToWorkingStudent (Student student) throws SQLException, ReachedMaximumRowNumberException;
    void convertToNormalStudent (WorkingStudent workingStudent) throws SQLException, ReachedMaximumRowNumberException;
}
