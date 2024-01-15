/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonConvertingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonCreatingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonLoginQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IStudentValueChangingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherValueChangingQueries;
import com.kaan.schoolmanagementmaven.factory.IOTPGenerator;
import com.kaan.schoolmanagementmaven.factory.IPersonFactory;
import com.kaan.schoolmanagementmaven.factory.IPersonUIDGenerator;
import com.kaan.schoolmanagementmaven.factory.IUsernameAndPassGenerator;
import com.kaan.schoolmanagementmaven.sms.IMessageSendingManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



/**
 *
 * @author kaan
 */
public class PersonManagerTest {
    private IPersonFactory personFactory;
    private IPersonFetchingQueries personFetchingQuery;
    private IPersonCreatingQuery personCreatingQuery;
    private IPersonDeletingQuery personDeletingQuery;
    private IPersonConvertingQuery converter;

    private IPersonLoginQueries personLoginQuery;
    private IUsernameAndPassGenerator usernameAndPassGen;
    private IPersonUIDGenerator uidGen;
    private IPersonValidationQueries personValidation;

    private IStudentValueChangingQueries studentChangingQuery;
    private ITeacherValueChangingQueries teacherChangingQuery;

    private ILessonCourseQuery lessonCourseQuery;
    private ILessonFetchingQuery lessonFetchingQuery;

    private IMessageSendingManager messageSender;
    private IOTPGenerator otpGen;
    
    
    @Before
    public void setUp () {
        personFactory = Mockito.mock(IPersonFactory.class) ;
        personFetchingQuery = Mockito.mock(IPersonFetchingQueries.class);
        personCreatingQuery = Mockito.mock(IPersonCreatingQuery.class);
        personDeletingQuery = Mockito.mock(IPersonDeletingQuery.class);
        converter = Mockito.mock(IPersonConvertingQuery.class);
        personLoginQuery = Mockito.mock(IPersonLoginQueries.class);
        usernameAndPassGen = Mockito.mock(IUsernameAndPassGenerator.class);
        uidGen = Mockito.mock(IPersonUIDGenerator.class);
        personValidation = Mockito.mock(IPersonValidationQueries.class);
        studentChangingQuery = Mockito.mock(IStudentValueChangingQueries.class);
        teacherChangingQuery = Mockito.mock(ITeacherValueChangingQueries.class);
        lessonCourseQuery = Mockito.mock(ILessonCourseQuery.class);
        lessonFetchingQuery = Mockito.mock(ILessonFetchingQuery.class);
        messageSender = Mockito.mock(IMessageSendingManager.class);
        otpGen = Mockito.mock(IOTPGenerator.class);
    }
    
    @Test
    public void createNormalStudentTest () throws SQLException {
        IPersonCreatorManager creatingManager = PersonManager.getInstanceForCreatingManager();
        
    }
    
}
