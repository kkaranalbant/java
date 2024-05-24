/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author kaan
 */
public class Access {

    private String host;
    private String dbName;
    private int port;
    private String userName;
    private String pass;

    private Connection connection;

    private static Access access;

    private Access() throws ClassNotFoundException {
        Class.forName(DefDbValues.getDriverClassName());
        this.host = DefDbValues.getHost();
        this.dbName = DefDbValues.getDbName();
        this.port = DefDbValues.getPort();
        this.userName = DefDbValues.getUsername();
        this.pass = DefDbValues.getPass();
    }

    public static Access getInstance() throws ClassNotFoundException {
        if (access == null) {
            access = new Access();
        }
        return access;
    }

    private String createAccessUrl() throws ClassNotFoundException  {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(dbName);
        return sb.toString();
    }

    public Connection createConnection() throws SQLException , ClassNotFoundException {
        if (connection == null) {
            connection = DriverManager.getConnection(createAccessUrl(), userName, pass);
        }
        return connection;
    }

}
