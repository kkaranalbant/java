/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.AdminLoginInfoQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IAdminLoginInfoChangeQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidUsernameLengthException;
import java.awt.AWTEventMulticaster;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public class AdminInfoChangerImpl implements IAdminInfoChanger {
    
    private static IAdminInfoChanger adminInfoChanger;
    
    private IAdminLoginInfoChangeQuery adminLoginInfoChangingQuery;
    
    static {
        adminInfoChanger = null;
    }
    
    private AdminInfoChangerImpl() throws SQLException {
        adminLoginInfoChangingQuery = AdminLoginInfoQuery.getInstanceForChanging();
    }
    
    static IAdminInfoChanger getInstance() throws SQLException {
        if (adminInfoChanger == null) {
            adminInfoChanger = new AdminInfoChangerImpl();
        }
        return adminInfoChanger;
    }
    
    @Override
    public void setUsername(String userName) throws InvalidUsernameLengthException , SQLException {
        throwExceptionIfInvalidUsername(userName);
        adminLoginInfoChangingQuery.setUsername(userName);
    }
    
    private void throwExceptionIfInvalidUsername(String userName) throws InvalidUsernameLengthException {
        if (userName.length() < 8) {
            throw new InvalidUsernameLengthException();
        }
    }
    
    @Override
    public void setPassword(String pass) throws InvalidPassLengthException , SQLException{
        throwExceptionIfInvalidPassword(pass);
        adminLoginInfoChangingQuery.setPassword(pass);
    }
    
    private void throwExceptionIfInvalidPassword(String pass) throws InvalidPassLengthException {
        if (pass.length() < 8) {
            throw new InvalidPassLengthException();
        }
    }
    
}
