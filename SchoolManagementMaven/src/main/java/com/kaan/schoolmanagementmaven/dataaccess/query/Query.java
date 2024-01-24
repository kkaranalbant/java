/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import com.kaan.schoolmanagementmaven.dataaccess.connection.Access;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet ;

/**
 *
 * @author kaan
 * 
 */
public abstract class Query {

    private Access access;
    private PreparedStatement pStatement;

    Query() throws SQLException {
        this.access = Access.getInstance();
    }

    Access getAccess() {
        return access;
    }

    private void setPreparedStatement(String query) throws SQLException{
        pStatement = access.getConnection().prepareStatement(query);
    }
    
    int runUpdatingQuery (String query) throws SQLException{
        setPreparedStatement(query);
        return pStatement.executeUpdate() ;
    }

    ResultSet runGettingQuery (String query) throws SQLException{
        setPreparedStatement(query);
        return pStatement.executeQuery() ;
    }
    
    boolean isEmptyResultSet (ResultSet resultSet) throws SQLException{
        
        return !resultSet.next();
    }
    
}
