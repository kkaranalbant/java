/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

import java.util.Random;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonLoginQueries;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonLoginQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;

/**
 *
 * @author kaan
 *
 */
public class Generator implements IPersonUIDGenerator, IUsernameAndPassGenerator, ILessonUIDGenerator, IOTPGenerator {

    private static char[] specialCharacters;
    private Random random;
    private static Generator generator;
    private IPersonLoginQueries personLoginQuery;
    private IDefaultValuesQuery defaultValuesQuery;
    private ILessonFetchingQuery lessonFetcher;

    static {
        specialCharacters = new char[]{'}', '{', '!', '?', '_', '@', '#', '$', '&', '[', ']'};
        generator = null;
    }

    private Generator() throws SQLException {
        personLoginQuery = PersonLoginQueries.getInstance();
        defaultValuesQuery = DefaultValuesQuery.getInstance();
        random = new Random();
        lessonFetcher = LessonFetchingQuery.getInstance();
    }

    public static IUsernameAndPassGenerator getUsernameAndPassGenerator() throws SQLException {
        if (generator == null) {
            generator = new Generator();
        }
        return generator;
    }

    public static IPersonUIDGenerator getPersonUIDGenerator() throws SQLException {
        return (IPersonUIDGenerator) getUsernameAndPassGenerator();
    }

    public static ILessonUIDGenerator getLessonUIDGenerator() throws SQLException {
        return (ILessonUIDGenerator) getUsernameAndPassGenerator();
    }

    public static IOTPGenerator getOTPGenerator() throws SQLException {
        return (IOTPGenerator) getUsernameAndPassGenerator();
    }

    private String generateUniqueUsernameAndPass() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() != IUsernameAndPassGenerator.DEFAULT_USERNAME_AND_PASSWORD_LENGTH) {
            sb.append((char) random.nextInt(48, 58));
            sb.append((char) random.nextInt(65, 91));
            sb.append(specialCharacters[random.nextInt(0, specialCharacters.length)]);
            sb.append((char) random.nextInt(97, 123));
        }
        return sb.toString();
    }

    @Override
    public Map<String, String> generateUsernameAndPass() throws SQLException {
        String userName = generateUniqueUsernameAndPass();
        String pass = generateUniqueUsernameAndPass();
        while (!isUniqueUserNameAndPassword(userName, pass)) {
            userName = generateUniqueUsernameAndPass();
            pass = generateUniqueUsernameAndPass();
        }
        Map<String, String> userNameAndPassPair = new HashMap();
        userNameAndPassPair.put(userName, pass);
        return userNameAndPassPair;
    }

    @Override
    public int generateUIDForNormalStudent() throws SQLException {
        int origin = defaultValuesQuery.getDefaultNormalStudentUIDOrigin();
        int bound = defaultValuesQuery.getDefaultNormalStudentUIDBound();
        return generateUIDForAllPersons(origin, bound);
    }

    @Override
    public int generateUIDForWorkingStudent() throws SQLException {
        int origin = defaultValuesQuery.getDefaultWorkingStudentUIDOrigin();
        int bound = defaultValuesQuery.getDefaultWorkingStudentUIDBound();
        return generateUIDForAllPersons(origin, bound);
    }

    @Override
    public int generateUIDForTeacher() throws SQLException {
        int origin = defaultValuesQuery.getDefaultTeacherUIDOrigin();
        int bound = defaultValuesQuery.getDefaultTeacherUIDBound();
        return generateUIDForAllPersons(origin, bound);
    }

    private boolean isUniqueIDForNormalStudent(int id) throws SQLException {
        ResultSet normalStudentUsernamesAndPasswords = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        return isUniqueIDForPerson(id, "normal_student_UID", normalStudentUsernamesAndPasswords);
    }

    private boolean isUniqueIDForWorkingStudent(int id) throws SQLException {
        ResultSet workingStudentUsernamesAndPasswords = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        return isUniqueIDForPerson(id, "working_student_UID", workingStudentUsernamesAndPasswords);
    }

    private boolean isUniqueIDForTeacher(int id) throws SQLException {
        ResultSet teacherUsernamesAndPasswords = personLoginQuery.getAllTeacherUserNameAndPassword();
        return isUniqueIDForPerson(id, "teacher_UID", teacherUsernamesAndPasswords);
    }

    private boolean isUniqueIDForPerson(int id, String column, ResultSet userNamesAndPasswords) throws SQLException {
        while (userNamesAndPasswords.next()) {
            int uid = userNamesAndPasswords.getInt(column);
            if (uid == id) {
                return false;
            }
        }
        return true;
    }

    private int generateUIDForAllPersons(int origin, int bound) throws SQLException {
        int id = random.nextInt(origin, bound + 1);
        while (!(isUniqueIDForWorkingStudent(id) && isUniqueIDForNormalStudent(id) && isUniqueIDForTeacher(id))) {
            id = random.nextInt(origin, bound + 1);
        }
        return id;
    }

    @Override
    public int generateUIDForLesson() throws SQLException {
        int origin = defaultValuesQuery.getDefaultLessonUIDOrigin();
        int bound = defaultValuesQuery.getDefaultLessonUIDBound();
        int id = random.nextInt(origin, bound+1);
        while (!isUniqueIDForLesson(id)) {
            id = random.nextInt(origin, bound+1);
        }
        return id;
    }

    private boolean isUniqueIDForLesson(int uid) throws SQLException {
        ResultSet lesson = lessonFetcher.getLessonInfo(uid);
        boolean isEmptyResultSet = !lesson.next();
        if (isEmptyResultSet) {
            return true;
        }
        return false;
    }

    private boolean isUniqueUserNameAndPassword(String userName, String password) throws SQLException {
        ResultSet normalStudents = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        ResultSet workingStudents = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        ResultSet teachers = personLoginQuery.getAllTeacherUserNameAndPassword();
        return isUniqueUsernameAndPassword(teachers, userName, password) && isUniqueUsernameAndPassword(normalStudents, userName, password) && isUniqueUsernameAndPassword(workingStudents, userName, password);
    }

    private boolean isUniqueUsernameAndPassword(ResultSet persons, String userName, String pass) throws SQLException {
        while (persons.next()) {
            if (userName.equals(persons.getString("username")) && pass.equals(persons.getString("pass"))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int generateOTP() {
        return random.nextInt(IOTPGenerator.OTP_ORIGIN, IOTPGenerator.OTP_BOUND);
    }

}
