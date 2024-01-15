/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.person.Student;
import com.kaan.schoolmanagementmaven.person.Teacher;
import com.kaan.schoolmanagementmaven.person.WorkingStudent;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonFactory {
    public Student createNormalStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException;
    public WorkingStudent createWorkingStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException;
    public Teacher createTeacherWhichExistsInDb(String userName, String pass, int uid) throws SQLException;
    
}
