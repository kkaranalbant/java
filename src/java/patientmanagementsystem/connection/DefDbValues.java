/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.connection;

/**
 *
 * @author kaan
 */
public class DefDbValues {
    
    private static String driverClassName ;
    private static String host ;
    private static String dbName ;
    private static String username ;
    private static String pass ;
    private static int port ;
    
    static {
        driverClassName = "com.mysql.jdbc.Driver" ;
        host = "localhost";
        port = 3306;
        dbName = "PatientManagement1";
        username = "root";
        pass = "ssssss";
    }

    public static String getHost() {
        return host;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPass() {
        return pass;
    }

    public static int getPort() {
        return port;
    }

    public static String getDriverClassName() {
        return driverClassName;
    }
    
    
    
}
