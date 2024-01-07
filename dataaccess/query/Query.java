/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import com.kaan.schoolmanagementmaven.dataaccess.connection.Access;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kaan
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

    PreparedStatement getPreparedStatement() {
        return pStatement;
    }

    void setPreparedStatement(PreparedStatement pStatement) {
        this.pStatement = pStatement;
    }

}
