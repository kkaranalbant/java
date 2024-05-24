/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import patientmanagementsystem.connection.Access;

/**
 *
 * @author kaan
 */
public abstract class Repo {

    private Connection connection;
    private PreparedStatement pStatement;
    
    Repo () throws SQLException , ClassNotFoundException {
        connection = Access.getInstance().createConnection() ;
    }

    void setPreparedStatement(PreparedStatement pStatement) {
        this.pStatement = pStatement;
    }

    PreparedStatement getPreparedStatement() {
        return pStatement;
    }
    
    Connection getConnection () {
        return connection ;
    }

}
