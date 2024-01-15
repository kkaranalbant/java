/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonDeletingManager {

    void deletePersonWithUID(int uid) throws SQLException, InvalidUIDException;

}
