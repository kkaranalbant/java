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
    
    public static IOTPGenerator getOTPGenerator () throws SQLException {
        return (IOTPGenerator) getUsernameAndPassGenerator() ;
    }

    private String generateUniqueUsernameAndPass() {
        StringBuilder sb = new StringBuilder();
        while (sb.length() != 16) {
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
        Map<String, String> resultMap = new HashMap();
        resultMap.put(userName, pass);
        return resultMap;
    }

    @Override
    public int generateUIDForNormalStudent() throws SQLException {
        int origin = defaultValuesQuery.getDefaultNormalStudentUIDOrigin();
        int bound = defaultValuesQuery.getDefaultNormalStudentUIDBound();
        int id = random.nextInt(origin, bound);
        while (!(isUniqueIDForWorkingStudent(id) && isUniqueIDForNormalStudent(id) && isUniqueIDForTeacher(id))) {
            id = random.nextInt(origin, bound);
        }
        return id;
    }

    private boolean isUniqueIDForNormalStudent(int id) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        while (resultSet.next()) {
            int uid = resultSet.getInt("normal_student_UID");
            if (uid == id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int generateUIDForWorkingStudent() throws SQLException {
        int origin = defaultValuesQuery.getDefaultWorkingStudentUIDOrigin();
        int bound = defaultValuesQuery.getDefaultWorkingStudentUIDBound();
        int id = random.nextInt(origin, bound);
        while (!(isUniqueIDForWorkingStudent(id) && isUniqueIDForNormalStudent(id) && isUniqueIDForTeacher(id))) {
            id = random.nextInt(origin, bound);
        }
        return id;
    }

    private boolean isUniqueIDForWorkingStudent(int id) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        while (resultSet.next()) {
            int uid = resultSet.getInt("working_student_UID");
            if (uid == id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int generateUIDForTeacher() throws SQLException {
        int origin = defaultValuesQuery.getDefaultTeacherUIDOrigin();
        int bound = defaultValuesQuery.getDefaultTeacherUIDBound();
        int id = random.nextInt(origin, bound);
        while (!(isUniqueIDForWorkingStudent(id) && isUniqueIDForNormalStudent(id) && isUniqueIDForTeacher(id))) {
            id = random.nextInt(origin, bound);
        }
        return id;
    }

    private boolean isUniqueIDForTeacher(int id) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        while (resultSet.next()) {
            int uid = resultSet.getInt("normal_student_UID");
            if (uid == id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int generatorUIDForLesson() throws SQLException {
        int origin = defaultValuesQuery.getDefaultLessonUIDOrigin();
        int bound = defaultValuesQuery.getDefaultLessonUIDBound();
        int id = random.nextInt(origin, bound);
        while (!isUniqueIDForLesson(id)) {
            id = random.nextInt(origin, bound);
        }
        return id;
    }

    private boolean isUniqueIDForLesson(int uid) throws SQLException {
        ResultSet resultSet = lessonFetcher.getLessonInfo(uid);
        return !resultSet.next();
    }

    private boolean isUniqueUserNameAndPassword(String userName, String password) throws SQLException {
        ResultSet resultSet1 = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        ResultSet resultSet2 = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        ResultSet resultSet3 = personLoginQuery.getAllTeacherUserNameAndPassword();
        while (resultSet1.next()) {
            String userNameInDb = resultSet1.getString("username");
            String passInDb = resultSet1.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                return false;
            }
        }
        while (resultSet2.next()) {
            String userNameInDb = resultSet2.getString("username");
            String passInDb = resultSet2.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                return false;
            }
        }
        while (resultSet3.next()) {
            String userNameInDb = resultSet3.getString("username");
            String passInDb = resultSet3.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int generateOTP() {
        return random.nextInt(100000,1000000) ;
    }

}
