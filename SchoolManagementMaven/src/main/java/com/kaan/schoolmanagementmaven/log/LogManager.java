/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.log;

import com.kaan.schoolmanagementmaven.admin.Admin;
import com.kaan.schoolmanagementmaven.person.Student;
import com.kaan.schoolmanagementmaven.person.Teacher;
import com.kaan.schoolmanagementmaven.person.WorkingStudent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author kaan
 * 
 */
public class LogManager implements ILogManager{

    private File logFile;
    private BufferedWriter bufferedWriter ;
    private FileWriter fileWriter ;

    public LogManager(File logFile) throws IOException {
        this.logFile = logFile ;
        this.logFile.setWritable(true) ;
        logFile.createNewFile() ;
        fileWriter = new FileWriter(logFile,true) ;
        bufferedWriter = new BufferedWriter (fileWriter) ;
    }
    
    @Override
    public void saveMessage (String logMessage) throws IOException{
        bufferedWriter.newLine(); 
        bufferedWriter.write(logMessage);
        bufferedWriter.flush();
    }

    @Override
    public void close() throws IOException {
        bufferedWriter.close();
        fileWriter.close();
    }

    public static void closeAllLogFiles () throws IOException {
        if (isLogManagerExists(Admin.getLogManager())) {
            Admin.getLogManager().close();
        }
        if (isLogManagerExists(Student.getLogManager())) {
            Student.getLogManager().close();
        }
        if (isLogManagerExists(WorkingStudent.getLogManager())) {
            WorkingStudent.getLogManager().close();
        }
        if (isLogManagerExists(Teacher.getLogManager())) {
            Teacher.getLogManager().close(); 
        }       
    }
    
    private static boolean isLogManagerExists (ILogManager logManager) {
        return logManager != null ;
    }

    
    
}
