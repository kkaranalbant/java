/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonFetchingQueries;
import com.kaan.schoolmanagementmaven.exception.BoundAndOriginRangeSmallerThanRowNumberException;
import com.kaan.schoolmanagementmaven.exception.IntersectingUIDRangeException;
import com.kaan.schoolmanagementmaven.exception.InvalidBalanceException;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import com.kaan.schoolmanagementmaven.exception.InvalidCostForPerHourException;
import com.kaan.schoolmanagementmaven.exception.InvalidDebtException;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 *
 */
public class AdminDefaultPersonProcessesManager implements IAdminDefaultStudentProcessesManager, IAdminDefaultTeacherProcessesManager {

    private IDefaultValuesQuery defValQuery;
    private IPersonFetchingQueries personFetcher;

    private static AdminDefaultPersonProcessesManager defPersonProcesses;

    private AdminDefaultPersonProcessesManager() throws SQLException {
        defValQuery = DefaultValuesQuery.getInstance();
        personFetcher = PersonFetchingQueries.getInstance();
    }

    static IAdminDefaultStudentProcessesManager getInstanceForStudent() throws SQLException {
        if (defPersonProcesses == null) {
            defPersonProcesses = new AdminDefaultPersonProcessesManager();
        }
        return defPersonProcesses;
    }

    static IAdminDefaultTeacherProcessesManager getInstanceForTeacher() throws SQLException {
        return (IAdminDefaultTeacherProcessesManager) getInstanceForStudent();
    }

    @Override
    public void setDefaultMaxDebtCredit(int value) throws SQLException {
        defValQuery.setDefaultMaxDebtCredit(value);
    }

    @Override
    public void setDefaultLessonCredit(int value) throws SQLException {
        defValQuery.setDefaultLessonCredit(value);
    }

    @Override
    public void setDefaultNormalStudentUIDOrigin(int normalStudentUIDOrigin) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(normalStudentUIDOrigin, defValQuery.getDefaultNormalStudentUIDBound(), personFetcher.getAllNormalStudentInfo());
        throwExceptionIfOriginCandidateEqualsOrGreaterThanBound(normalStudentUIDOrigin, defValQuery.getDefaultNormalStudentUIDBound());
        defValQuery.setDefaultNormalStudentUIDOrigin(normalStudentUIDOrigin);
    }

    @Override
    public void setDefaultNormalStudentUIDBound(int normalStudentUIDBound) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(defValQuery.getDefaultNormalStudentUIDOrigin(), normalStudentUIDBound, personFetcher.getAllNormalStudentInfo());
        throwExceptionIfBoundCandidateEqualsOrSmallerThanOrigin(normalStudentUIDBound, defValQuery.getDefaultNormalStudentUIDOrigin());
        defValQuery.setDefaultNormalStudentUIDBound(normalStudentUIDBound);
    }

    @Override
    public void setDefaultWorkingStudentUIDOrigin(int workingStudentUIDOrigin) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(workingStudentUIDOrigin, defValQuery.getDefaultWorkingStudentUIDBound(), personFetcher.getAllWorkingStudentInfo());
        throwExceptionIfOriginCandidateEqualsOrGreaterThanBound(workingStudentUIDOrigin, defValQuery.getDefaultWorkingStudentUIDBound());
        defValQuery.setDefaultWorkingStudentUIDOrigin(workingStudentUIDOrigin);
    }

    @Override
    public void setDefaultWorkingStudentUIDBound(int workingStudentUIDBound) throws SQLException, InvalidBoundAndOriginPairException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(defValQuery.getDefaultWorkingStudentUIDBound(), workingStudentUIDBound, personFetcher.getAllWorkingStudentInfo());
        throwExceptionIfBoundCandidateEqualsOrSmallerThanOrigin(workingStudentUIDBound, defValQuery.getDefaultWorkingStudentUIDOrigin());
        defValQuery.setDefaultWorkingStudentUIDBound(workingStudentUIDBound);
    }

    @Override
    public void setTeacherUIDOrigin(int teacherUIDOrigin) throws InvalidBoundAndOriginPairException, SQLException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(teacherUIDOrigin, defValQuery.getDefaultTeacherUIDBound(), personFetcher.getAllTeacherInfo());
        throwExceptionIfOriginCandidateEqualsOrGreaterThanBound(teacherUIDOrigin, defValQuery.getDefaultTeacherUIDBound());
        defValQuery.setDefaultTeacherUIDOrigin(teacherUIDOrigin);
    }

    @Override
    public void setTeacherUIDBound(int teacherUIDBound) throws InvalidBoundAndOriginPairException, SQLException, BoundAndOriginRangeSmallerThanRowNumberException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(defValQuery.getDefaultTeacherUIDOrigin(), teacherUIDBound, personFetcher.getAllTeacherInfo());
        throwExceptionIfBoundCandidateEqualsOrSmallerThanOrigin(teacherUIDBound, defValQuery.getDefaultTeacherUIDOrigin());
        defValQuery.setDefaultTeacherUIDBound(teacherUIDBound);
    }

    @Override
    public void setCostForPerHourForWorkingStudents(int costForPerHour) throws SQLException {
        throwExceptionIfInvalidCostForPerHour(costForPerHour);
        defValQuery.setDefaultCostForPerHourForWorkingStudent(costForPerHour);
    }

    private void throwExceptionIfInvalidCostForPerHour(int costForPerHour) throws InvalidCostForPerHourException {
        if (costForPerHour <= 0) {
            throw new InvalidCostForPerHourException();
        }
    }

    @Override
    public void throwExceptionIfUIDsIntersectsOtherWithOtherUIDs() throws SQLException, IntersectingUIDRangeException {
        boolean isNormalStudentUIDIntersectsWithWorkingStudentUIDRange = !((defValQuery.getDefaultWorkingStudentUIDOrigin() < defValQuery.getDefaultNormalStudentUIDOrigin() && defValQuery.getDefaultWorkingStudentUIDBound() < defValQuery.getDefaultNormalStudentUIDOrigin()) || defValQuery.getDefaultWorkingStudentUIDOrigin() > defValQuery.getDefaultNormalStudentUIDBound());
        boolean isNormalStudentUIDIntersectsWithTeacherUIDRange = !((defValQuery.getDefaultTeacherUIDOrigin() < defValQuery.getDefaultNormalStudentUIDOrigin() && defValQuery.getDefaultWorkingStudentUIDBound() < defValQuery.getDefaultNormalStudentUIDOrigin()) || defValQuery.getDefaultTeacherUIDOrigin() > defValQuery.getDefaultNormalStudentUIDBound());
        boolean isWorkingStudentUIDIntersectsWithTeacherUIDRange = !((defValQuery.getDefaultTeacherUIDOrigin() < defValQuery.getDefaultWorkingStudentUIDOrigin() && defValQuery.getDefaultTeacherUIDBound() < defValQuery.getDefaultWorkingStudentUIDOrigin()) || defValQuery.getDefaultTeacherUIDOrigin() > defValQuery.getDefaultWorkingStudentUIDBound());
        if (isNormalStudentUIDIntersectsWithTeacherUIDRange || isNormalStudentUIDIntersectsWithWorkingStudentUIDRange || isWorkingStudentUIDIntersectsWithTeacherUIDRange) {
            throw new IntersectingUIDRangeException();
        }
    }

    private void throwExceptionIfOriginCandidateEqualsOrGreaterThanBound(int originCandidate, int bound) throws InvalidBoundAndOriginPairException {
        if (isOriginCandidateEqualsOrGreaterThanBound(originCandidate, bound)) {
            throw new InvalidBoundAndOriginPairException();
        }
    }

    private boolean isOriginCandidateEqualsOrGreaterThanBound(int originCandidate, int bound) {
        return originCandidate >= bound;
    }

    private void throwExceptionIfBoundCandidateEqualsOrSmallerThanOrigin(int boundCandidate, int origin) throws InvalidBoundAndOriginPairException {
        if (isBoundCandidateEqualsOrSmallerThanOrigin(boundCandidate, origin)) {
            throw new InvalidBoundAndOriginPairException();
        }
    }

    private boolean isBoundCandidateEqualsOrSmallerThanOrigin(int boundCandidate, int origin) {
        return boundCandidate <= origin;
    }

    private void throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(int origin, int bound, ResultSet persons) throws SQLException, BoundAndOriginRangeSmallerThanRowNumberException {
        int rowNumber = getRowNumber(persons);
        if (isBoundAndOriginDifferenceSmallerThanRowNumber(origin, bound, rowNumber)) {
            throw new BoundAndOriginRangeSmallerThanRowNumberException();
        }
    }

    private boolean isBoundAndOriginDifferenceSmallerThanRowNumber(int origin, int bound, int rowNumber) {
        return bound - origin < rowNumber;
    }

    private int getRowNumber(ResultSet persons) throws SQLException {
        int rowNumber = 0;
        while (persons.next()) {
            rowNumber++;
        }
        return rowNumber;
    }

    @Override
    public void setDefaultBalance(int value) throws SQLException, InvalidBalanceException {
        throwExceptionIfInvalidDefaultBalance(value);
        defValQuery.setDefaultBalance(value);
    }

    private void throwExceptionIfInvalidDefaultBalance(int value) throws InvalidBalanceException {
        if (value < 0) {
            throw new InvalidBalanceException();
        }
    }

    @Override
    public void setDefaultDebt(int value) throws SQLException, InvalidDebtException {
        throwExceptionIfInvalidDefaultDebt(value);
        defValQuery.setDefaultDebt(value);
    }

    private void throwExceptionIfInvalidDefaultDebt(int value) throws InvalidDebtException {
        if (value < 0) {
            throw new InvalidDebtException();
        }
    }

}
