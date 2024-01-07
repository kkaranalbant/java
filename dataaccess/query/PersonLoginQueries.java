/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet ;

/**
 *
 * @author kaan
 */
public class PersonLoginQueries extends Query implements IPersonLoginQueries{

    private static IPersonLoginQueries normalStudentLoginQuery ;
    
    private PersonLoginQueries() throws SQLException {
    }
    
    public static IPersonLoginQueries getInstance () throws SQLException {
        if (normalStudentLoginQuery == null) normalStudentLoginQuery = new PersonLoginQueries();
        return normalStudentLoginQuery ;
    }
    
    @Override
    public ResultSet getAllNormalStudentUserNameAndPassword () throws SQLException {
        String query = getAllNormalStudentUserNameAndPasswordQueryString() ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery() ;
    }
    
    private String getAllNormalStudentUserNameAndPasswordQueryString () {
        return "select * from "+super.getAccess().getNormalStudentLoginInfosTable() ;
    }
    
    @Override
    public ResultSet getAllWorkingStudentUserNameAndPassword () throws SQLException {
        String query = getAllWorkingStudentUserNameAndPasswordQueryString() ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }
    
    private String getAllWorkingStudentUserNameAndPasswordQueryString () {
        return "select * from "+super.getAccess().getWorkingStudentLoginInfosTable();
    }
    
    @Override
    public ResultSet getAllTeacherUserNameAndPassword () throws SQLException {
        String query = getAllTeacherUserNameAndPasswordQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }
    
    private String getAllTeacherUserNameAndPasswordQueryString () {
        return "select * from "+super.getAccess().getTeacherLoginInfos();
    }

    @Override
    public String getNormalStudentUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID") ;
    }

    @Override
    public String getWorkingStudentUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID") ;
    }

    @Override
    public String getTeacherUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getTeacherLoginInfos(), "teacher_UID") ;
    }

    @Override
    public String getNormalStudentPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID") ;
    }

    @Override
    public String getWorkingStudentPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID") ;
    }

    @Override
    public String getTeacherPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getTeacherLoginInfos(), "teacher_UID") ;
    }

    private String getPersonUsernameByUID (int uid , String tableName , String columnName) throws SQLException{
        String query = "SELECT username FROM " + tableName + " WHERE " + columnName + " = "+uid+" ;"; 
        System.out.println(query);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        String userName = null ;
        while (resultSet.next()) {
            userName = resultSet.getString("username"); 
        }
        return userName ;
    }
    
    private String getPersonPassByUID (int uid , String tableName , String columnName) throws SQLException{
        String query = "select pass from "+tableName + " where "+columnName+" = "+uid + " ;"; 
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        String pass = null ;
        while (resultSet.next()) {
            pass = resultSet.getString("pass"); 
        }
        return pass ;
    }
    
}
