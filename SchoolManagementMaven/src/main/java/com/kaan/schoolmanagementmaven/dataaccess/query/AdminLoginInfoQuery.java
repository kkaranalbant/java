/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public class AdminLoginInfoQuery extends Query implements IAdminLoginInfoChangeQuery, IAdminLoginInfoAddingQuery {

    private static AdminLoginInfoQuery adminInfoChanger;

    private AdminLoginInfoQuery() throws SQLException {

    }

    public static IAdminLoginInfoChangeQuery getInstanceForChanging() throws SQLException {
        if (adminInfoChanger == null) {
            adminInfoChanger = new AdminLoginInfoQuery();
        }
        return adminInfoChanger;
    }

    public static IAdminLoginInfoAddingQuery getInstanceForAdding() throws SQLException {
        if (adminInfoChanger == null) {
            adminInfoChanger = new AdminLoginInfoQuery();
        }
        return adminInfoChanger;
    }

    @Override
    public void setUsername(String userName) throws SQLException {
        String query = "update " + super.getAccess().getAdminTable() + " set username = '" + userName + "' ;";
        super.runUpdatingQuery(query) ;
    }

    @Override
    public void setPassword(String pass) throws SQLException {
        String query = "update " + super.getAccess().getAdminTable() + " set pass = '" + pass + "' ;";
        super.runUpdatingQuery(query);
    }

    @Override
    public void addAdmin(String userName, String pass) throws SQLException {
        String query = "insert into " + super.getAccess().getAdminTable() + " values ('" + userName + "','" + pass + "') ;";
        super.runUpdatingQuery(query) ;
    }

}
