
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

import java.util.Map;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public interface IUsernameAndPassGenerator {

    public Map <String , String> generateUsernameAndPass() throws SQLException;
}
