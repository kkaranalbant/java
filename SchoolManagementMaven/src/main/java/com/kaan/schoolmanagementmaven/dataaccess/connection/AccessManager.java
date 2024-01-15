/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.connection;

import com.kaan.schoolmanagementmaven.dataaccess.query.ITableCreatingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.TableCreatingQuery;
import com.kaan.schoolmanagementmaven.exception.EmptyConnectionFileException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kaan
 * 
 */
public class AccessManager implements IAccessManager {

    private String host;
    private String dbName;
    private int port;
    private String userName;
    private String pass;
    private Access access;
    private static File connectionFile;
    private static BufferedReader bufferedReader;
    private static FileReader fileReader;
    private ITableCreatingQuery tableCreator;

    static {
        connectionFile = new File("databaseinfo.txt");
        try {
            fileReader = new FileReader(connectionFile);
            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.mark(1000);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private AccessManager(String hostName, int port, String dbName, String userName, String pass) throws SQLException {
        this.host = hostName;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
        access = Access.getInstance();
        String url = getConnectionURLString(host, port, dbName);
        access.setConnection(DriverManager.getConnection(url, userName, pass));
        tableCreator = TableCreatingQuery.getInstance();
    }

    public static IAccessManager changeAccessInformations(String hostName, int port, String dbName, String userName, String pass) throws SQLException, IOException {
        throwExceptionIfInvalidConnectionInformationsOrElseCreateConnection(hostName, port, dbName, userName, pass);
        changeDefaultDatabaseInfo(hostName, port, dbName, userName, pass);
        changeConnectionFile(hostName, port, dbName, userName, pass);
        return new AccessManager(hostName, port, dbName, userName, pass);
    }

    public static Access loadAccessObject() throws SQLException, IOException, EmptyConnectionFileException {
        List<String> resultList = throwExceptionIfEmptyConnectionFileOrElseReturnInformations();
        Connection connection = throwExceptionIfInvalidConnectionInformationsOrElseCreateConnection(resultList.get(0), Integer.parseInt(resultList.get(1)), resultList.get(2), resultList.get(3), resultList.get(4));
        changeDefaultDatabaseInfo(resultList.get(0), Integer.parseInt(resultList.get(1)), resultList.get(2), resultList.get(3), resultList.get(4));
        Access.getInstance().setConnection(connection);
        return Access.getInstance();
    }

    private static Connection throwExceptionIfInvalidConnectionInformationsOrElseCreateConnection(String host, int port, String dbName, String userName, String pass) throws SQLException {
        String url = getConnectionURLString(host, port, dbName);
        return DriverManager.getConnection(url, userName, pass);
    }

    private static String getConnectionURLString(String host, int port, String dbName) {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }

    private static List<String> throwExceptionIfEmptyConnectionFileOrElseReturnInformations() throws EmptyConnectionFileException, IOException {
        List<String> resultList = getConnectionInformations();
        if (resultList == null) {
            throw new EmptyConnectionFileException();
        }
        return resultList;
    }

    private static void changeDefaultDatabaseInfo(String hostName, int port, String dbName, String userName, String pass) {
        DefaultDatabaseInfos.dbName = dbName;
        DefaultDatabaseInfos.userName = userName;
        DefaultDatabaseInfos.port = port;
        DefaultDatabaseInfos.pass = pass;
        DefaultDatabaseInfos.host = hostName;
    }

    public static void changeConnectionFile(String host, int port, String dbName, String userName, String pass) throws IOException {
        resetConnectionFile();
        BufferedWriter bufferedWriter = createNewBufferedWriterForConnectionFile();
        printInformationsToConnectionFile(host, port, dbName, userName, pass, bufferedWriter);
        bufferedWriter.close();
        bufferedWriter = null;
    }

    private static void resetConnectionFile() throws IOException {
        connectionFile.delete();
        connectionFile.createNewFile();
    }

    private static BufferedWriter createNewBufferedWriterForConnectionFile() throws IOException {
        FileWriter fileWriter = new FileWriter(connectionFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        return bufferedWriter;
    }

    private static void printInformationsToConnectionFile(String host, int port, String dbName, String userName, String pass, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(host);
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(port));
        bufferedWriter.newLine();
        bufferedWriter.write(dbName);
        bufferedWriter.newLine();
        bufferedWriter.write(userName);
        bufferedWriter.newLine();
        bufferedWriter.write(pass);
        bufferedWriter.flush();
    }

    private static boolean isEmptyConnectionFile() throws IOException {
        if (bufferedReader.readLine() == null) {

            return true;
        }
        bufferedReader.reset();
        return false;
    }

    private static List<String> getConnectionInformations() throws IOException {
        if (isEmptyConnectionFile()) {
            return null;
        }
        List<String> informations = new ArrayList();
        String accessInformation = null;
        while ((accessInformation = bufferedReader.readLine()) != null) {
            informations.add(accessInformation);
        }
        bufferedReader.reset();
        return informations;
    }
    
    @Override
    public void createTables() throws SQLException {
        tableCreator.addAllTables();
    }

    public static void closeAllStreams() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }

    public static File getConnectionFile() {
        return connectionFile;
    }

}
