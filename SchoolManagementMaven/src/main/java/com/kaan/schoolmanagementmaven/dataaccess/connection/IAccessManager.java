/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.connection;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public interface IAccessManager {
    void createTables () throws SQLException;
}
