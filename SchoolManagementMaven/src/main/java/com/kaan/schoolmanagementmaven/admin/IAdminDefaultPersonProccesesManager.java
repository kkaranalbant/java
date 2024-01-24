/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.IntersectingUIDRangeException;
import com.kaan.schoolmanagementmaven.exception.InvalidBalanceException;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public interface IAdminDefaultPersonProccesesManager {

    public void setDefaultBalance(int value) throws SQLException , InvalidBalanceException ;

    void throwExceptionIfUIDsIntersectsOtherWithOtherUIDs() throws SQLException, IntersectingUIDRangeException;

}
