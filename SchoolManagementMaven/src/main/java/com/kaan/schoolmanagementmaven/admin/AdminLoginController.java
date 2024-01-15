/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.IAdminValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.AdminValidationQueries;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kaan
 * 
 */


public class AdminLoginController implements IAdminLoginController {

    private static AdminLoginController controller = null;
    private IAdminValidationQueries adminQuery;

    private AdminLoginController() throws SQLException {
        adminQuery = AdminValidationQueries.getInstance();
    }

    static IAdminLoginController getInstance() throws SQLException {
        if (controller == null) {
            controller = new AdminLoginController();
        }
        return controller;
    }

    /*
        Admin sorgusu nesnesini kullanarak veritabanindaki admin verilerini alir ve parametredeki verilerle eslesme varsa true , hicbir eslesme yoksa false
     */
    public boolean isValidUserNameAndPass(String userName, String pass) throws SQLException {
        ResultSet resultSet = adminQuery.getAdminInformations();
        for (Map.Entry<String,String> adminUsernameAndPass: getAdminUsernameAndPassPairFrom(resultSet).entrySet()) {
            if (adminUsernameAndPass.getKey().equals(userName) && adminUsernameAndPass.getValue().equals(pass)) return true ;
        }
        return false;
    }
    
    private Map <String , String> getAdminUsernameAndPassPairFrom (ResultSet adminInfoResultSet) throws SQLException{
        Map <String,String> adminUsernameAndPass = new HashMap () ;
        while (adminInfoResultSet.next()) {
            String userName = null , pass = null ;
            userName = adminInfoResultSet.getString(("username")) ;
            pass = adminInfoResultSet.getString("pass"); 
            adminUsernameAndPass.put(userName , pass);
        }
        return adminUsernameAndPass ;
    }

}
