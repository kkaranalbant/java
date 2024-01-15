/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;
import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public interface IAdminLoginController {
    boolean isValidUserNameAndPass (String userName , String pass) throws SQLException;
}
