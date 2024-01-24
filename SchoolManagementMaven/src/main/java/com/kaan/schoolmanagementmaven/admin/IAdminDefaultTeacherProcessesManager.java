/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.exception.BoundAndOriginRangeSmallerThanRowNumberException;
import com.kaan.schoolmanagementmaven.exception.IntersectingUIDRangeException;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import java.sql.SQLException;

/**
 *
 * @author kaan
 *
 */
public interface IAdminDefaultTeacherProcessesManager extends IAdminDefaultPersonProccesesManager{

    void setTeacherUIDOrigin(int value) throws InvalidBoundAndOriginPairException, SQLException, BoundAndOriginRangeSmallerThanRowNumberException;

    void setTeacherUIDBound(int value) throws InvalidBoundAndOriginPairException, SQLException, BoundAndOriginRangeSmallerThanRowNumberException;

}
