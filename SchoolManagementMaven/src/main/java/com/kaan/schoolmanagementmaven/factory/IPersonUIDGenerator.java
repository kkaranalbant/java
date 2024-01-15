/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;
import java.sql.SQLException ;
/**
 *
 * @author kaan
 * 
 */
public interface IPersonUIDGenerator {
    public int generateUIDForNormalStudent() throws SQLException ;
    public int generateUIDForWorkingStudent() throws SQLException;
    public int generateUIDForTeacher() throws SQLException;
}
