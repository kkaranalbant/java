/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.log;

import java.io.IOException;

/**
 *
 * @author kaan
 * 
 */
public interface ILogManager {
    public void saveMessage (String logMessage) throws IOException ;
    public void close () throws IOException ;
}
