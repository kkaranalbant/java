/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public class FirstTimeDefaultInfoQuery extends Query implements IFirstTimeDefaultInfoQuery {

    private static IFirstTimeDefaultInfoQuery firstTimeDefaultInfoObject;

    private FirstTimeDefaultInfoQuery() throws SQLException {
    }

    public static IFirstTimeDefaultInfoQuery getInstance() throws SQLException {
        if (firstTimeDefaultInfoObject == null) {
            firstTimeDefaultInfoObject = new FirstTimeDefaultInfoQuery();
        }
        return firstTimeDefaultInfoObject;
    }

    @Override
    public void addDefaultValuesRow() throws SQLException {
        String query = getAddingDefaultValuesRowStringQuery() ;
        super.runUpdatingQuery(query) ;
    }

    private String getAddingDefaultValuesRowStringQuery() {
        return "insert into " + super.getAccess().getDefaultValuesTable() + " values ("+0+","+0+","+0+","+0+","+0+","+1+","+2+","+3+","+4+","+5+","+6+","+7+","+0+") ;" ;
    }

}
