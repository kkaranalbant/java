/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import com.kaan.schoolmanagementmaven.exception.IncompatibleUsernameAndPhoneNumberException;
import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonSMSManager {
    public int sendRecoverySMS(String userName, String phoneNumber) throws IncompatibleUsernameAndPhoneNumberException, SQLException ;
}
