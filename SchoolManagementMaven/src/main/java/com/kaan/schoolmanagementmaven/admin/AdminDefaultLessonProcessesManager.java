/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.exception.BoundAndOriginRangeSmallerThanRowNumberException;
import com.kaan.schoolmanagementmaven.exception.InvalidBoundAndOriginPairException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public class AdminDefaultLessonProcessesManager implements IAdminDefaultLessonProcessesManager {

    private static IAdminDefaultLessonProcessesManager adminDefLessonProcessesObj;

    private IDefaultValuesQuery defValQuery;
    private ILessonFetchingQuery lessonFetcher ;

    private AdminDefaultLessonProcessesManager() throws SQLException {
        defValQuery = DefaultValuesQuery.getInstance();
        lessonFetcher = LessonFetchingQuery.getInstance() ;
    }

    public static IAdminDefaultLessonProcessesManager getInstance() throws SQLException {
        if (adminDefLessonProcessesObj == null) {
            adminDefLessonProcessesObj = new AdminDefaultLessonProcessesManager();
        }
        return adminDefLessonProcessesObj;
    }

    @Override
    public void setLessonUIDOrigin(int origin) throws BoundAndOriginRangeSmallerThanRowNumberException, InvalidBoundAndOriginPairException, SQLException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(origin, defValQuery.getDefaultLessonUIDBound(), lessonFetcher.getAllLessons());
        throwExceptionIfOriginCandidateEqualsOrGreaterThanBound(origin, defValQuery.getDefaultNormalStudentUIDBound());
        defValQuery.setDefaultLessonUIDOrigin(origin);
    }

    @Override
    public void setLessonUIDBound(int bound) throws BoundAndOriginRangeSmallerThanRowNumberException, InvalidBoundAndOriginPairException, SQLException {
        throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(defValQuery.getDefaultLessonUIDOrigin(), bound, lessonFetcher.getAllLessons());
        throwExceptionIfBoundCandidateEqualsOrSmallerThanOrigin(bound, defValQuery.getDefaultNormalStudentUIDOrigin());
        defValQuery.setDefaultLessonUIDBound(bound);
    }

    private void throwExceptionIfBoundAndOriginDifferenceSmallerThanRowNumber(int origin, int bound, ResultSet lessons) throws SQLException, BoundAndOriginRangeSmallerThanRowNumberException {
        int rowNumber = getRowNumber(lessons);
        if (isBoundAndOriginDifferenceSmallerThanRowNumber(origin, bound, rowNumber)) {
            throw new BoundAndOriginRangeSmallerThanRowNumberException();
        }
    }

    private int getRowNumber(ResultSet lessons) throws SQLException {
        int rowNumber = 0;
        while (lessons.next()) {
            rowNumber++;
        }
        return rowNumber;
    }

    private boolean isBoundAndOriginDifferenceSmallerThanRowNumber(int origin, int bound, int rowNumber) {
        return bound - origin < rowNumber;
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

}
