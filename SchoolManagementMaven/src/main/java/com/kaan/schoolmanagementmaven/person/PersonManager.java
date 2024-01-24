/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidUserNameOrPassException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonCreatingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonLoginQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonValidationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonFetchingQueries;
import com.kaan.schoolmanagementmaven.exception.NotUniqueNameAndLastnameException;
import com.kaan.schoolmanagementmaven.factory.Generator;
import com.kaan.schoolmanagementmaven.factory.IPersonFactory;
import com.kaan.schoolmanagementmaven.factory.IUsernameAndPassGenerator;
import com.kaan.schoolmanagementmaven.factory.PersonFactory;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonCreatingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonLoginQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IStudentValueChangingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherValueChangingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonValueChangingQueries;
import com.kaan.schoolmanagementmaven.exception.InvalidBalanceException;
import com.kaan.schoolmanagementmaven.exception.InvalidDebtException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidSalaryException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueUsernameAndPassException;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonConvertingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonConvertingQuery;
import com.kaan.schoolmanagementmaven.exception.IncompatibleUsernameAndPhoneNumberException;
import com.kaan.schoolmanagementmaven.exception.InvalidPassLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneCountryCodeException;
import com.kaan.schoolmanagementmaven.exception.InvalidPhoneNumberLengthException;
import com.kaan.schoolmanagementmaven.exception.InvalidUsernameLengthException;
import com.kaan.schoolmanagementmaven.exception.NotUniquePhoneNumberException;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;
import com.kaan.schoolmanagementmaven.factory.IOTPGenerator;
import com.kaan.schoolmanagementmaven.factory.IPersonUIDGenerator;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import com.kaan.schoolmanagementmaven.sms.IMessageSendingManager;
import com.kaan.schoolmanagementmaven.sms.MessageSendingManager;

/**
 *
 * @author kaan
 *
 */
public class PersonManager implements IPersonCreatorManager, IPersonDeletingManager, IPersonChangingManager, IStudentConvertingManager, IPersonSMSManager {

    private static PersonManager personManager;
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

    private IDefaultValuesQuery defValQuery;

    private static final int INVALID_UID_NUMBER;

    static {
        personManager = null;
        INVALID_UID_NUMBER = -1;
    }

    private PersonManager() throws SQLException {
        personLoginQuery = PersonLoginQueries.getInstance();
        personFetchingQuery = PersonFetchingQueries.getInstance();
        lessonFetchingQuery = LessonFetchingQuery.getInstance();
        personFactory = PersonFactory.getInstance();
        usernameAndPassGen = Generator.getUsernameAndPassGenerator();
        uidGen = Generator.getPersonUIDGenerator();
        personValidation = PersonValidationQueries.getInstance();
        personCreatingQuery = PersonCreatingQuery.getInstance();
        personDeletingQuery = PersonDeletingQuery.getInstance();
        studentChangingQuery = PersonValueChangingQueries.getInstanceForStudent();
        teacherChangingQuery = PersonValueChangingQueries.getInstanceForTeacher();
        converter = PersonConvertingQuery.getInstance();
        lessonCourseQuery = LessonCourseQuery.getInstance();
        defValQuery = DefaultValuesQuery.getInstance();
    }

    public static IPersonDeletingManager getInstanceForDeletingManager() throws SQLException {
        if (personManager == null) {
            personManager = new PersonManager();
        }
        return personManager;
    }

    public static IPersonCreatorManager getInstanceForCreatingManager() throws SQLException {
        return (IPersonCreatorManager) getInstanceForDeletingManager();
    }

    public static IPersonChangingManager getInstanceForChangingManager() throws SQLException {
        return (IPersonChangingManager) getInstanceForDeletingManager();
    }

    public static IStudentConvertingManager getInstanceForConvertingManager() throws SQLException {
        return (IStudentConvertingManager) getInstanceForDeletingManager();
    }

    public static IPersonSMSManager getInstanceForSMSManager() throws SQLException {
        return (IPersonSMSManager) getInstanceForDeletingManager();
    }

    @Override
    public Student createNormalStudent(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        int uid = getUIDOfNormalStudent(userName, pass);
        throwExceptionIfInvalidUID(uid);
        return personFactory.createNormalStudentWhichExistsInDb(userName, pass, uid);
    }

    @Override
    public WorkingStudent createWorkingStudent(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        int uid = getUIDOfWorkingStudent(userName, pass);
        throwExceptionIfInvalidUID(uid);
        return personFactory.createWorkingStudentWhichExistsInDb(userName, pass, uid);
    }

    @Override
    public Teacher createTeacher(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        int uid = getUIDOfTeacher(userName, pass);
        throwExceptionIfInvalidUID(uid);
        return personFactory.createTeacherWhichExistsInDb(userName, pass, uid);
    }

    private void throwExceptionIfInvalidUID(int uid) {
        if (uid == INVALID_UID_NUMBER) {
            throw new InvalidUserNameOrPassException();
        }
    }

    private int getUIDOfNormalStudent(String userName, String pass) throws SQLException {
        ResultSet normalStudentLoginInfos = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        return getUIDOfPerson(normalStudentLoginInfos, userName, pass, "normal_student_UID");
    }

    private int getUIDOfWorkingStudent(String userName, String pass) throws SQLException {
        ResultSet workingStudentLoginInfos = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        return getUIDOfPerson(workingStudentLoginInfos, userName, pass, "working_student_UID");
    }

    private int getUIDOfTeacher(String userName, String pass) throws SQLException {
        ResultSet teacherLoginInfos = personLoginQuery.getAllTeacherUserNameAndPassword();
        return getUIDOfPerson(teacherLoginInfos, userName, pass, "teacher_UID");
    }

    private int getUIDOfPerson(ResultSet personLoginInfos, String userName, String pass, String column) throws SQLException {
        while (personLoginInfos.next()) {
            String currentUserName = personLoginInfos.getString("username");
            String currentPass = personLoginInfos.getString("pass");
            if (currentUserName.equals(userName) && currentPass.equals(pass)) {
                return personLoginInfos.getInt(column);
            }
        }
        return INVALID_UID_NUMBER;
    }

    @Override
    public Map<String, String> createNewNormalStudentAndReturnLoginInfo(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInNormalStudentTable();
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniqueNameAndLastname(name, lastName);
        Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
        String userName = extractUsernameFromMap(usernameAndPass);
        String pass = extractPasswordFromMap(usernameAndPass);
        int uid = uidGen.generateUIDForNormalStudent();
        personCreatingQuery.createNormalStudentInDb(name, lastName, userName, pass, uid, phoneNumber);
        return usernameAndPass;

    }

    @Override
    public Map<String, String> createNewWorkingStudentAndReturnLoginInfo(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInWorkingStudentTable();
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniqueNameAndLastname(name, lastName);
        Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
        String userName = extractUsernameFromMap(usernameAndPass);
        String pass = extractPasswordFromMap(usernameAndPass);
        int uid = uidGen.generateUIDForWorkingStudent();
        personCreatingQuery.createWorkingStudentIndDb(name, lastName, userName, pass, uid, phoneNumber);
        return usernameAndPass;
    }

    @Override
    public Map<String, String> createNewTeacherAndReturnLoginInfo(String name, String lastName, String branchName, int salary, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException, InvalidPhoneCountryCodeException, InvalidPhoneNumberLengthException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInTeacherTable();
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniqueNameAndLastname(name, lastName);
        Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
        String userName = extractUsernameFromMap(usernameAndPass);
        String pass = extractPasswordFromMap(usernameAndPass);
        int uid = uidGen.generateUIDForTeacher();
        personCreatingQuery.createTeacherInDb(name, lastName, userName, pass, uid, branchName, salary, phoneNumber);
        return usernameAndPass;

    }

    private String extractUsernameFromMap(Map<String, String> userNameAndPass) {
        String userName = null;
        for (Map.Entry<String, String> userNameAndPassEntry : userNameAndPass.entrySet()) {
            userName = userNameAndPassEntry.getKey();
        }
        return userName;
    }

    private String extractPasswordFromMap(Map<String, String> userNameAndPass) {
        String pass = null;
        for (Map.Entry<String, String> userNameAndPassEntry : userNameAndPass.entrySet()) {
            pass = userNameAndPassEntry.getValue();
        }
        return pass;
    }

    @Override
    public void deletePersonWithUID(int uid) throws SQLException, InvalidUIDException {
        personDeletingQuery.deletePersonFromDb(uid);
    }

    private boolean isUniqueNameAndLastname(ResultSet resultSet, String name, String lastName) throws SQLException {
        while (resultSet.next()) {
            String nameInDb = resultSet.getString("name");
            String lastNameInDb = resultSet.getString("last_name");
            System.out.println("Name:"+nameInDb+"\nLastname: "+lastNameInDb);
            if (name.equals(nameInDb) && lastName.equals(lastNameInDb)) {
                return false;
            }
        }
        return true;

    }

    private void throwExceptionIfNotUniqueNameAndLastname(String name, String lastName) throws NotUniqueNameAndLastnameException, SQLException {
        ResultSet normalStudentNamesAndLastnames = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet workingStudentNamesAndLastnames = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet teacherStudentNamesAndLastnames = personValidation.getAllTeacherNameAndLastname();

        boolean isUniqueForNormalStudent = isUniqueNameAndLastname(normalStudentNamesAndLastnames, name, lastName);
        boolean isUniqueForWorkingStudent = isUniqueNameAndLastname(workingStudentNamesAndLastnames, name, lastName);
        boolean isUniqueForTeacher = isUniqueNameAndLastname(teacherStudentNamesAndLastnames, name, lastName);

        if (!(isUniqueForNormalStudent && isUniqueForWorkingStudent && isUniqueForTeacher)) {
            throw new NotUniqueNameAndLastnameException();
        }

    }

    @Override
    public void changeNormalStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet normalStudent = personFetchingQuery.getNormalStudentInfo(uid);
        String lastName = getPersonAttributeString(normalStudent, "lastname");
        throwExceptionIfNotUniqueNameAndLastname(newName, lastName);
        studentChangingQuery.changeNormalStudentName(uid, newName);
    }

    @Override
    public void changeWorkingStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet workingStudent = personFetchingQuery.getWorkingStudentInfo(uid);
        String lastName = getPersonAttributeString(workingStudent, "lastname");
        throwExceptionIfNotUniqueNameAndLastname(newName, lastName);
        studentChangingQuery.changeWorkingStudentName(uid, newName);
    }

    @Override
    public void changeTeacherNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet teacher = personFetchingQuery.getTeacherInfo(uid);
        String lastName = getPersonAttributeString(teacher, "lastname");
        throwExceptionIfNotUniqueNameAndLastname(newName, lastName);
        teacherChangingQuery.changeTeacherName(uid, newName);
    }

    @Override
    public void changeNormalStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet normalStudent = personFetchingQuery.getNormalStudentInfo(uid);
        String name = getPersonAttributeString(normalStudent, "name");
        throwExceptionIfNotUniqueNameAndLastname(name, newLastname);
        studentChangingQuery.changeNormalStudentLastname(uid, newLastname);

    }

    @Override
    public void changeWorkingStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet workingStudent = personFetchingQuery.getWorkingStudentInfo(uid);
        String name = getPersonAttributeString(workingStudent, "name");
        throwExceptionIfNotUniqueNameAndLastname(name, newLastname);
        studentChangingQuery.changeWorkingStudentLastname(uid, newLastname);
    }

    @Override
    public void changeTeacherLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet teacher = personFetchingQuery.getTeacherInfo(uid);
        String name = getPersonAttributeString(teacher, "name");
        throwExceptionIfNotUniqueNameAndLastname(name, newLastname);
        teacherChangingQuery.changeTeacherLastname(uid, newLastname);
    }

    private String getPersonAttributeString(ResultSet person, String column) throws SQLException {
        person.next();
        return person.getString(column);
    }

    @Override
    public void changeNormalStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUserName);
        String pass = personLoginQuery.getNormalStudentPassByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(newUserName, pass);
        studentChangingQuery.changeNormalStudentUsername(uid, newUserName);
    }

    @Override
    public void changeWorkingStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUserName);
        String pass = personLoginQuery.getWorkingStudentPassByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(newUserName, pass);
        studentChangingQuery.changeWorkingStudentUsername(uid, newUserName);
    }

    @Override
    public void changeTeacherUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUserName);
        String pass = personLoginQuery.getTeacherPassByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(newUserName, pass);
        teacherChangingQuery.changeTeacherUsername(uid, newUserName);
    }

    private void throwExceptionIfInvalidUsernameLength(String userName) {
        if (userName.length() < 8) {
            throw new InvalidUsernameLengthException();
        }
    }

    @Override
    public void changeNormalStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException {
        throwExceptionIfInvalidPasswordLength(newPass);
        String userName = personLoginQuery.getNormalStudentUsernameByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(userName, newPass);
        studentChangingQuery.changeNormalStudentPass(uid, newPass);
    }

    @Override
    public void changeWorkingStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException {
        throwExceptionIfInvalidPasswordLength(newPass);
        String userName = personLoginQuery.getWorkingStudentUsernameByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(userName, newPass);
        studentChangingQuery.changeWorkingStudentPass(uid, newPass);
    }

    @Override
    public void changeTeacherPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException {
        throwExceptionIfInvalidPasswordLength(newPass);
        String userName = personLoginQuery.getTeacherUsernameByUID(uid);
        throwExceptionIfNotUniqueUsernameAndPassword(userName, newPass);
        teacherChangingQuery.changeTeacherPass(uid, newPass);
    }

    private void throwExceptionIfInvalidPasswordLength(String password) {
        if (password.length() < 8) {
            throw new InvalidPassLengthException();
        }
    }

    @Override
    public void changeNormalStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUsername);
        throwExceptionIfInvalidPasswordLength(newPass);
        throwExceptionIfNotUniqueUsernameAndPassword(newUsername, newPass);
        studentChangingQuery.changeNormalStudentUsername(uid, newUsername);
        studentChangingQuery.changeNormalStudentPass(uid, newPass);
    }

    @Override
    public void changeWorkingStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUsername);
        throwExceptionIfInvalidPasswordLength(newPass);
        throwExceptionIfNotUniqueUsernameAndPassword(newUsername, newPass);
        studentChangingQuery.changeWorkingStudentUsername(uid, newUsername);
        studentChangingQuery.changeWorkingStudentPass(uid, newPass);
    }

    @Override
    public void changeTeacherUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        throwExceptionIfInvalidUsernameLength(newUsername);
        throwExceptionIfInvalidPasswordLength(newPass);
        throwExceptionIfNotUniqueUsernameAndPassword(newUsername, newPass);
        teacherChangingQuery.changeTeacherUsername(uid, newUsername);
        teacherChangingQuery.changeTeacherPass(uid, newPass);
    }

    private boolean isUniqueUserNameAndPassword(ResultSet loginInfo, String userName, String password) throws SQLException {
        while (loginInfo.next()) {
            String userNameInDb = loginInfo.getString("username");
            String passInDb = loginInfo.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                return false;
            }
        }
        return true;
    }

    private void throwExceptionIfNotUniqueUsernameAndPassword(String userName, String password) throws NotUniqueUsernameAndPassException, SQLException {
        ResultSet normalStudentLoginInfos = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        ResultSet workingStudentLoginInfos = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        ResultSet teacherLoginInfos = personLoginQuery.getAllNormalStudentUserNameAndPassword();

        boolean isUniqueForNormalStudent = isUniqueUserNameAndPassword(normalStudentLoginInfos, userName, password);
        boolean isUniqueForWorkingStudent = isUniqueUserNameAndPassword(workingStudentLoginInfos, userName, password);
        boolean isUniqueForTeacher = isUniqueUserNameAndPassword(teacherLoginInfos, userName, password);

        if (!(isUniqueForNormalStudent && isUniqueForWorkingStudent && isUniqueForTeacher)) {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeNormalStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        throwExceptionIfInvalidBalance(newBalance);
        studentChangingQuery.changeNormalStudentBalance(uid, newBalance);
    }

    @Override
    public void changeWorkingStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        throwExceptionIfInvalidBalance(newBalance);
        studentChangingQuery.changeWorkingStudentBalance(uid, newBalance);
    }

    @Override
    public void changeTeacherBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        throwExceptionIfInvalidBalance(newBalance);
        teacherChangingQuery.changeTeacherBalance(uid, newBalance);
    }

    private void throwExceptionIfInvalidBalance(int balance) throws InvalidBalanceException {
        if (balance < 0) {
            throw new InvalidBalanceException();
        }
    }

    @Override
    public void changeNormalStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException {
        throwExceptionIfInvalidDebt(newDebt);
        studentChangingQuery.changeNormalStudentDebt(uid, newDebt);
    }

    @Override
    public void changeWorkingStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException {
        throwExceptionIfInvalidDebt(newDebt);
        studentChangingQuery.changeWorkingStudentDebt(uid, newDebt);
    }

    private void throwExceptionIfInvalidDebt(int debt) throws InvalidDebtException {
        if (debt < 0) {
            throw new InvalidDebtException();
        }
    }

    @Override
    public void changeNormalStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException {
        throwExceptionIfInvalidLessonCredit(newCredit);
        studentChangingQuery.changeNormalStudentLessonCredit(uid, newCredit);
    }

    @Override
    public void changeWorkingStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException {
        throwExceptionIfInvalidLessonCredit(newCredit);
        studentChangingQuery.changeWorkingStudentLessonCredit(uid, newCredit);
    }

    private void throwExceptionIfInvalidLessonCredit(int lessonCredit) throws InvalidLessonCreditException {
        if (lessonCredit < 0) {
            throw new InvalidLessonCreditException();
        }
    }

    @Override
    public void changeTeacherSalary(int uid, int newSalary) throws SQLException, InvalidSalaryException {
        throwExceptionIfInvalidSalary(newSalary);
        teacherChangingQuery.setTeacherSalary(uid, newSalary);
    }

    private void throwExceptionIfInvalidSalary(int salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException();
        }
    }

    @Override
    public void changeTeacherBranch(int uid, int newBranchId) throws SQLException {
        teacherChangingQuery.setTeacherBranch(uid, newBranchId);
    }

    @Override
    public void convertToNormalStudent(WorkingStudent workingStudent) throws SQLException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInNormalStudentTable();
        int newUID = uidGen.generateUIDForNormalStudent();
        String userName = workingStudent.getUserName();
        String pass = workingStudent.getPass();
        String name = workingStudent.getName();
        String lastName = workingStudent.getLastName();
        int uid = personFetchingQuery.getPersonUIDByNameAndLastname(name, lastName);
        int debt = workingStudent.getDebt();
        int balance = workingStudent.getBalance();
        int lessonCredit = workingStudent.getLessonCredit();
        String phoneNumber = workingStudent.getPhoneNumber();
        List<Lesson> lessonList = workingStudent.getLessonList();
        Map<Integer, Integer> lessonsAndTeachersForStudent = new HashMap();
        putLessonUIDAndTeacherUIDToMap(lessonsAndTeachersForStudent, lessonList, uid);
        converter.convertToNormalStudent(userName, pass, name, lastName, uid, newUID, balance, debt, lessonCredit, lessonsAndTeachersForStudent, phoneNumber);
    }

    @Override
    public void convertToWorkingStudent(Student student) throws SQLException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInWorkingStudentTable();
        int newUID = uidGen.generateUIDForWorkingStudent();
        String userName = student.getUserName();
        String pass = student.getPass();
        String name = student.getName();
        String lastName = student.getLastName();
        String phoneNumber = student.getPhoneNumber();
        int uid = personFetchingQuery.getPersonUIDByNameAndLastname(name, lastName);
        int debt = student.getDebt();
        int balance = student.getBalance();
        int lessonCredit = student.getLessonCredit();
        List<Lesson> lessons = student.getLessonList();
        for(Lesson lesson : lessons) {
            System.out.println(lesson.getName());
        }
        Map<Integer, Integer> lessonsAndTeachersForStudent = new HashMap();
        putLessonUIDAndTeacherUIDToMap(lessonsAndTeachersForStudent, lessons, uid);
        converter.convertToWorkingStudent(userName, pass, name, lastName, uid, newUID, balance, debt, lessonCredit, lessonsAndTeachersForStudent, phoneNumber);
    }

    private void putLessonUIDAndTeacherUIDToMap(Map<Integer, Integer> lessonsAndTeachers, List<Lesson> lessons, int studentUID) throws SQLException {
        for (Lesson currentLesson : lessons) {
            int lessonUID = lessonFetchingQuery.getLessonUIDByLessonName(currentLesson.getName());
            int teacherUID = lessonCourseQuery.findTeacherUIDFromNormalStudentCourse(studentUID, lessonUID);
            System.out.println("Teacher UID : "+teacherUID);
            if (teacherUID == INVALID_UID_NUMBER) {
                teacherUID = lessonCourseQuery.findTeacherUIDFromWorkingStudentCourse(studentUID, lessonUID) ;
                System.out.println("Teacher UID : "+teacherUID);
            }
            lessonsAndTeachers.put(lessonUID, teacherUID);
        }
    }

    @Override
    public int sendRecoverySMS(String userName, String phoneNumber) throws IncompatibleUsernameAndPhoneNumberException, SQLException {
        int verificationCode = 0;
        throwExceptionIfInvalidUsernameAndPhoneNumber(userName, phoneNumber);
        otpGen = Generator.getOTPGenerator();
        verificationCode = otpGen.generateOTP();
        messageSender = MessageSendingManager.getInstance();
        messageSender.sendMessage(phoneNumber, verificationCode);
        return verificationCode;
    }

    private void throwExceptionIfInvalidUsernameAndPhoneNumber(String userName, String phoneNumber) throws SQLException, IncompatibleUsernameAndPhoneNumberException {
        Map<String, String> normalStudentUsernamesAndPhoneNumbers = personValidation.getAllNormalStudentUsernameAndPhoneNumber();
        Map<String, String> workingStudentUsernamesAndPhoneNumbers = personValidation.getAllWorkingStudentUsernameAndPhoneNumber();
        Map<String, String> teacherUsernamesAndPhoneNumbers = personValidation.getAllTeacherUsernameAndPhoneNumber();

        boolean isValidForNormalStudent = isValidUsernameAndPhoneNumber(userName, phoneNumber, normalStudentUsernamesAndPhoneNumbers);
        boolean isValidForWorkingStudent = isValidUsernameAndPhoneNumber(userName, phoneNumber, workingStudentUsernamesAndPhoneNumbers);
        boolean isValidForTeacher = isValidUsernameAndPhoneNumber(userName, phoneNumber, teacherUsernamesAndPhoneNumbers);

        if (!(isValidForNormalStudent || isValidForWorkingStudent || isValidForTeacher)) {
            throw new IncompatibleUsernameAndPhoneNumberException();
        }
    }

    private boolean isValidUsernameAndPhoneNumber(String userName, String phoneNumber, Map<String, String> personUsernamesAndPhoneNumbers) throws IncompatibleUsernameAndPhoneNumberException {
        for (Map.Entry<String, String> entry : personUsernamesAndPhoneNumbers.entrySet()) {
            String userNameInDb = entry.getValue();
            String phoneNumberInDb = entry.getKey();
            if (userNameInDb.equals(userName) && phoneNumberInDb.equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private void throwExceptionIfInvalidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
    }

    private void throwExceptionIfNotUniquePhoneNumber(String phoneNumber) throws NotUniquePhoneNumberException, SQLException {
        boolean isUniqueForNormalStudents = isUniquePhoneNumber(personFetchingQuery.getAllNormalStudentInfo(), phoneNumber);
        boolean isUniqueForWorkingStudents = isUniquePhoneNumber(personFetchingQuery.getAllWorkingStudentInfo(), phoneNumber);
        boolean isUniqueForTeacher = isUniquePhoneNumber(personFetchingQuery.getAllTeacherInfo(), phoneNumber);
        if (!(isUniqueForNormalStudents && isUniqueForWorkingStudents && isUniqueForTeacher)) {
            throw new NotUniquePhoneNumberException();
        }
    }

    private boolean isUniquePhoneNumber(ResultSet resultSet, String phoneNumber) throws SQLException {
        boolean isUnique = true;
        while (resultSet.next()) {
            if (resultSet.getString("phone_number").equals(phoneNumber)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    private void phoneNumberControl(String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        throwExceptionIfInvalidPhoneNumber(phoneNumber);
        throwExceptionIfNotUniquePhoneNumber(phoneNumber);
    }

    private int getRowNumberOfTable(ResultSet table) throws SQLException {
        int counter = 0;
        while (table.next()) {
            counter++;
        }
        return counter;
    }

    private void throwExceptionIfReachedMaxRowNumberInNormalStudentTable() throws ReachedMaximumRowNumberException, SQLException {
        boolean isReached = isReachedMaxRowNumberInNormalStudentTable(personFetchingQuery.getAllNormalStudentInfo());
        if (isReached) {
            throw new ReachedMaximumRowNumberException();
        }
    }

    private boolean isReachedMaxRowNumberInNormalStudentTable(ResultSet normalStudents) throws SQLException {
        int rowNumber = getRowNumberOfTable(normalStudents);
        int range = defValQuery.getDefaultNormalStudentUIDBound() - defValQuery.getDefaultNormalStudentUIDOrigin() + 1;
        return rowNumber == range;
    }

    private void throwExceptionIfReachedMaxRowNumberInWorkingStudentTable() throws ReachedMaximumRowNumberException, SQLException {
        boolean isReached = isReachedMaxRowNumberInWorkingStudentTable(personFetchingQuery.getAllWorkingStudentInfo());
        if (isReached) {
            throw new ReachedMaximumRowNumberException();
        }
    }

    private boolean isReachedMaxRowNumberInWorkingStudentTable(ResultSet workingStudents) throws SQLException {
        int rowNumber = getRowNumberOfTable(workingStudents);
        int range = defValQuery.getDefaultWorkingStudentUIDBound() - defValQuery.getDefaultWorkingStudentUIDOrigin() + 1;
        return rowNumber == range;
    }

    private void throwExceptionIfReachedMaxRowNumberInTeacherTable() throws ReachedMaximumRowNumberException, SQLException {
        boolean isReached = isReachedMaxRowNumberInTeacherTable(personFetchingQuery.getAllTeacherInfo());
        if (isReached) {
            throw new ReachedMaximumRowNumberException();
        }
    }

    private boolean isReachedMaxRowNumberInTeacherTable(ResultSet teachers) throws SQLException {
        int rowNumber = getRowNumberOfTable(personFetchingQuery.getAllTeacherInfo());
        int range = defValQuery.getDefaultTeacherUIDBound() - defValQuery.getDefaultTeacherUIDOrigin() + 1;
        return rowNumber == range;
    }

    @Override
    public void changeNormalStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniquePhoneNumber(phoneNumber);
        studentChangingQuery.changeNormalStudentPhoneNumber(uid, phoneNumber);

    }

    @Override
    public void changeWorkingStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniquePhoneNumber(phoneNumber);
        studentChangingQuery.changeWorkingStudentPhoneNumber(uid, phoneNumber);
    }

    @Override
    public void changeTeacherPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        phoneNumberControl(phoneNumber);
        throwExceptionIfNotUniquePhoneNumber(phoneNumber);
        teacherChangingQuery.changeTeacherPhoneNumber(uid, phoneNumber);
    }

    @Override
    public void changeForgottenPass(String phoneNumber, String pass) throws InvalidPassLengthException, SQLException, NotUniqueUsernameAndPassException {
        throwExceptionIfInvalidPasswordLength(pass);
        int personUID = personFetchingQuery.getPersonUIDByPhoneNumber(phoneNumber);
        String personUsername = personLoginQuery.getPersonUsernameByUID(personUID);
        throwExceptionIfNotUniqueUsernameAndPassword(personUsername, pass);
        studentChangingQuery.changeNormalStudentPassWithPhoneNumber(phoneNumber, pass);
        studentChangingQuery.changeWorkingStudentPassWithPhoneNumber(phoneNumber, pass);
        teacherChangingQuery.changeTeacherPassWithPhoneNumber(phoneNumber, pass);
    }

}
