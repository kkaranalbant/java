/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;
import com.kaan.schoolmanagementmaven.person.PersonManager;
import java.sql.SQLException ;
import com.kaan.schoolmanagementmaven.person.IPersonDeletingManager;

/**
 *
 * @author kaan
 * 
 */
public class AdminPersonDeletingManager implements IAdminPersonDeletingManager {

    private static IAdminPersonDeletingManager personDeletingManager;
    private IPersonDeletingManager personManager ;

    static {
        personDeletingManager = null;
    }

    private AdminPersonDeletingManager() throws SQLException{
        personManager = PersonManager.getInstanceForDeletingManager() ;
    }

    public static IAdminPersonDeletingManager getInstance() throws SQLException{
        if (personDeletingManager == null) {
            personDeletingManager = new AdminPersonDeletingManager();
        }
        return personDeletingManager;
    }

    @Override
    public void deletePersonByUID(int uid) throws InvalidUIDException , SQLException {
        personManager.deletePersonWithUID(uid);
    }

}
