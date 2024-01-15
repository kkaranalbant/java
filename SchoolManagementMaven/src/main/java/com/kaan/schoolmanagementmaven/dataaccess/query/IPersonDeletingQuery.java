/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;
import java.sql.SQLException ;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonDeletingQuery {
    void deletePersonFromDb (int uid) throws SQLException , InvalidUIDException;
}
