/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.IAdminValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.AdminValidationQueries;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 */

/*
    Bu sinifin nesnesi veritabani kontrol nesnesi olacak bundan dolayi bir tane nesnesinin olusmasi yeterli.
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
        String realUserName = null, realPass = null;
        while (resultSet.next()) {
            realUserName = resultSet.getString("username");
            realPass = resultSet.getString("pass");
            if (realPass.equals(pass) && realUserName.equals(userName)) {
                return true;
            }

        }
        return false;
    }

}
