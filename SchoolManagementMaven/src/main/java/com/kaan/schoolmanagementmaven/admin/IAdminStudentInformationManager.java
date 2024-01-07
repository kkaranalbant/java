/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public interface IAdminStudentInformationManager {
    public String normalStudentInfo() throws SQLException ;
    public String workingStudentInfo() throws SQLException;
}
