/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import com.kaan.schoolmanagementmaven.dataaccess.connection.Access;
import com.kaan.schoolmanagementmaven.dataaccess.connection.IAccess;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public final class AdminValidationQueries extends Query implements IAdminValidationQueries {

    private static IAdminValidationQueries adminValidationQueryObject = null;

    private AdminValidationQueries() throws SQLException {
    }

    public static IAdminValidationQueries getInstance() throws SQLException {
        if (adminValidationQueryObject == null) {
            adminValidationQueryObject = new AdminValidationQueries();
        }
        return adminValidationQueryObject;
    }

    @Override
    public ResultSet getAdminInformations() throws SQLException{
        String query = AdminValidationQueryStringCreator.createQueryString(super.getAccess().getAdminTable());
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery() ;
    }

}
