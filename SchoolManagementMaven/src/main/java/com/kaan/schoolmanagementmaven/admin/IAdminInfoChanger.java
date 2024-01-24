/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidUsernameLengthException;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public interface IAdminInfoChanger {

    void setUsername(String userName) throws InvalidUsernameLengthException , SQLException;

    void setPassword(String pass) throws InvalidPassLengthException , SQLException ;
}
