package dataaccess;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import queries.Queries;

public class Access {

    private String userName ;
    private String pass ;
    private String dbName ;
    private String host ;
    private int port ;

    private Connection connection = null;

    private Access(String userName , String pass , String dbName , String host , int port) {
        
        this.userName = userName ;
        this.pass = pass ;
        this.dbName = dbName ;
        this.host = host ;
        this.port = port ;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName; // 

        try {
            connection = DriverManager.getConnection(url, userName, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void setDbInfo (String userName , String pass , String dbName , String host , int port) {
               
        Access access = new Access (userName , pass , dbName , host ,port) ;
        
        Queries.access = access ;
        
    }
    

    public Connection getConnection() {
        return connection;
    }
}
