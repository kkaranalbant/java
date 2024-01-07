/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidUserNameOrPassException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.kaan.schoolmanagementmaven.factory.IOTPGenerator;
import com.kaan.schoolmanagementmaven.factory.IPersonUIDGenerator;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import com.kaan.schoolmanagementmaven.sms.IMessageSendingManager;
import com.kaan.schoolmanagementmaven.sms.MessageSendingManager;

/**
 *
 * @author kaan
 */
public class PersonManager implements IPersonCreatorManager, IPersonDeletingManager, IPersonChangingManager, IStudentConvertingManager, IPersonSMSManager {

    private IPersonLoginQueries personLoginQuery;
    private static PersonManager personManager;
    private IPersonFetchingQueries personFetchingQuery;
    private ILessonFetchingQuery lessonFetchingQuery;
    private IPersonFactory personFactory;
    private IUsernameAndPassGenerator usernameAndPassGen;
    private IPersonUIDGenerator uidGen;
    private IOTPGenerator otpGen;
    private IPersonValidationQueries personValidation;
    private IPersonCreatingQuery personCreatingQuery;
    private IPersonDeletingQuery personDeletingQuery;
    private IStudentValueChangingQueries studentChangingQuery;
    private ITeacherValueChangingQueries teacherChangingQuery;
    private IPersonConvertingQuery converter;
    private ILessonCourseQuery lessonCourseQuery;
    private IMessageSendingManager messageSender;

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
        int uid = getUIDIfValidUserNameAndPassForNormalStudent(userName, pass);
        if (uid != -1) {
            return personFactory.createNormalStudentWhichExistsInDb(userName, pass, uid);
        } else {
            throw new InvalidUserNameOrPassException();
        }
    }

    private int getUIDIfValidUserNameAndPassForNormalStudent(String userName, String pass) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        while (resultSet.next()) {
            String currentUserName = resultSet.getString("username");
            String currentPass = resultSet.getString("pass");
            if (currentUserName.equals(userName) && currentPass.equals(pass)) {
                return resultSet.getInt("normal_student_UID");
            }
        }
        return -1;
    }

    @Override
    public WorkingStudent createWorkingStudent(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        int uid = getUIDIfValidUserNameAndPassForWorkingStudent(userName, pass);
        if (uid != -1) {
            return personFactory.createWorkingStudentWhichExistsInDb(userName, pass, uid);
        } else {
            throw new InvalidUserNameOrPassException();
        }
    }

    private int getUIDIfValidUserNameAndPassForWorkingStudent(String userName, String pass) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        while (resultSet.next()) {
            String currentUserName = resultSet.getString("username");
            String currentPass = resultSet.getString("pass");
            if (currentUserName.equals(userName) && currentPass.equals(pass)) {
                return resultSet.getInt("working_student_UID");
            }
        }
        return -1;
    }

    @Override
    public Teacher createTeacher(String userName, String pass) throws SQLException, InvalidUserNameOrPassException {
        int uid = getUIDIfValidUserNameAndPassForTeacher(userName, pass);
        if (uid != -1) {
            return personFactory.createTeacherWhichExistsInDb(userName, pass, uid);
        } else {
            throw new InvalidUserNameOrPassException();
        }
    }

    private int getUIDIfValidUserNameAndPassForTeacher(String userName, String pass) throws SQLException {
        ResultSet resultSet = personLoginQuery.getAllTeacherUserNameAndPassword();
        while (resultSet.next()) {
            String currentUserName = resultSet.getString("username");
            String currentPass = resultSet.getString("pass");
            if (currentUserName.equals(userName) && currentPass.equals(pass)) {
                return resultSet.getInt("teacher_UID");
            }
        }
        return -1;
    }

    @Override
    public Map<String, String> createNewNormalStudent(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException {
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (!phoneNumberValidation(phoneNumber)) {
            throw new NotUniquePhoneNumberException();
        }
        if (isUniqueNameAndLastname(resultSetForTeacher, name, lastName) && isUniqueNameAndLastname(resultSetForNormalStudent, name, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, lastName)) {
            Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
            String userName = null, pass = null;
            for (Entry<String, String> entrySet : usernameAndPass.entrySet()) {
                userName = entrySet.getKey();
                pass = entrySet.getValue();
            }
            int uid = uidGen.generateUIDForNormalStudent();
            personCreatingQuery.createNormalStudentInDb(name, lastName, userName, pass, uid, phoneNumber);
            return usernameAndPass;
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public Map<String, String> createNewWorkingStudent(String name, String lastName, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException {
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (!phoneNumberValidation(phoneNumber)) {
            throw new NotUniquePhoneNumberException();
        }
        if (isUniqueNameAndLastname(resultSetForTeacher, name, lastName) && isUniqueNameAndLastname(resultSetForNormalStudent, name, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, lastName)) {
            Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
            String userName = null, pass = null;
            for (Entry<String, String> entrySet : usernameAndPass.entrySet()) {
                userName = entrySet.getKey();
                pass = entrySet.getValue();
            }
            int uid = uidGen.generateUIDForWorkingStudent();
            personCreatingQuery.createWorkingStudentIndDb(name, lastName, userName, pass, uid, phoneNumber);
            return usernameAndPass;
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public Map<String, String> createNewTeacher(String name, String lastName, String branchName, int salary, String phoneNumber) throws NotUniqueNameAndLastnameException, SQLException, NotUniquePhoneNumberException {
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (!phoneNumberValidation(phoneNumber)) {
            throw new NotUniquePhoneNumberException();
        }
        if (isUniqueNameAndLastname(resultSetForTeacher, name, lastName) && isUniqueNameAndLastname(resultSetForNormalStudent, name, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, lastName)) {
            Map<String, String> usernameAndPass = usernameAndPassGen.generateUsernameAndPass();
            String userName = null, pass = null;
            for (Entry<String, String> entrySet : usernameAndPass.entrySet()) {
                userName = entrySet.getKey();
                pass = entrySet.getValue();
            }
            int uid = uidGen.generateUIDForTeacher();
            personCreatingQuery.createTeacherInDb(name, lastName, userName, pass, uid, branchName, salary, phoneNumber);
            return usernameAndPass;
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void deletePersonWithUID(int uid) throws SQLException, InvalidUIDException {
        personDeletingQuery.deletePersonFromDb(uid);
    }

    private boolean isUniqueNameAndLastname(ResultSet resultSet, String name, String lastName) throws SQLException {
        while (resultSet.next()) {
            String nameInDb = resultSet.getString("name");
            String lastNameInDb = resultSet.getString("last_name");
            if (name.equals(nameInDb) && lastName.equals(lastNameInDb)) {
                return false;
            }
        }
        return true;

    }

    private boolean isUniquePhoneNumber(ResultSet resultSet, String phoneNumber) throws SQLException {
        while (resultSet.next()) {
            if (resultSet.getString("phone_number").equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void changeNormalStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getNormalStudentInfo(uid);
        String lastName = null;
        while (resultSet.next()) {
            lastName = resultSet.getString("name");
        }
        if (lastName == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForTeacher, newName, lastName)) {
            studentChangingQuery.changeNormalStudentName(uid, newName);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeNormalStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getNormalStudentInfo(uid);
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("name");
        }
        if (name == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForTeacher, name, newLastname)) {
            studentChangingQuery.changeNormalStudentLastname(uid, newLastname);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeNormalStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        if (newBalance < 0) {
            throw new InvalidBalanceException();
        }
        studentChangingQuery.changeNormalStudentBalance(uid, newBalance);
    }

    @Override
    public void changeNormalStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        if (newUserName.length() < 8) {
            throw new InvalidUsernameLengthException();
        }
        String pass = personLoginQuery.getNormalStudentPassByUID(uid);
        if (pass == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(newUserName, pass)) {
            studentChangingQuery.changeNormalStudentUsername(uid, newUserName);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeNormalStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException {
        if (newPass.length() < 8) {
            throw new InvalidPassLengthException();
        }
        String userName = personLoginQuery.getNormalStudentUsernameByUID(uid);
        if (userName == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(userName, newPass)) {
            studentChangingQuery.changeNormalStudentPass(uid, newPass);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeWorkingStudentNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getWorkingStudentInfo(uid);
        String lastName = null;
        while (resultSet.next()) {
            lastName = resultSet.getString("last_name");
        }
        if (lastName == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForTeacher, newName, lastName)) {
            studentChangingQuery.changeWorkingStudentName(uid, newName);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeTeacherNameWithUID(int uid, String newName) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getTeacherInfo(uid);
        String lastName = null;
        while (resultSet.next()) {
            lastName = resultSet.getString("last_name");
        }
        if (lastName == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForWorkingStudent, newName, lastName) && isUniqueNameAndLastname(resultSetForTeacher, newName, lastName)) {
            teacherChangingQuery.changeTeacherName(uid, newName);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeWorkingStudentLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getWorkingStudentInfo(uid);
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("name");
        }
        if (name == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForTeacher, name, newLastname)) {
            studentChangingQuery.changeWorkingStudentLastname(uid, newLastname);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeTeacherLastnameWithUID(int uid, String newLastname) throws SQLException, NotUniqueNameAndLastnameException {
        ResultSet resultSet = personFetchingQuery.getTeacherInfo(uid);
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString("name");
        }
        if (name == null) {
            return;
        }
        ResultSet resultSetForNormalStudent = personValidation.getAllNormalStudentNameAndLastname();
        ResultSet resultSetForWorkingStudent = personValidation.getAllWorkingStudentNameAndLastname();
        ResultSet resultSetForTeacher = personValidation.getAllTeacherNameAndLastname();
        if (isUniqueNameAndLastname(resultSetForNormalStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForWorkingStudent, name, newLastname) && isUniqueNameAndLastname(resultSetForTeacher, name, newLastname)) {
            teacherChangingQuery.changeTeacherLastname(uid, newLastname);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeWorkingStudentBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        if (newBalance < 0) {
            throw new InvalidBalanceException();
        }
        studentChangingQuery.changeWorkingStudentBalance(uid, newBalance);
    }

    @Override
    public void changeTeacherBalanceWithUID(int uid, int newBalance) throws SQLException, InvalidBalanceException {
        if (newBalance < 0) {
            throw new InvalidBalanceException();
        }
        teacherChangingQuery.changeTeacherBalance(uid, newBalance);
    }

    @Override
    public void changeWorkingStudentUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        if (newUserName.length() < 8) {
            throw new InvalidUsernameLengthException();
        }
        String pass = personLoginQuery.getWorkingStudentPassByUID(uid);
        if (pass == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(newUserName, pass)) {
            studentChangingQuery.changeWorkingStudentUsername(uid, newUserName);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeWorkingStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        if (isUniqueUserNameAndPassword(newUsername, newPass)) {
            if (newUsername.length() < 8) {
                throw new InvalidUsernameLengthException();
            }
            if (newPass.length() < 8) {
                throw new InvalidPassLengthException();
            }
            studentChangingQuery.changeWorkingStudentUsername(uid, newUsername);
            studentChangingQuery.changeWorkingStudentPass(uid, newPass);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeNormalStudentUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        if (isUniqueUserNameAndPassword(newUsername, newPass)) {
            if (newUsername.length() < 8) {
                throw new InvalidUsernameLengthException();
            }
            if (newPass.length() < 8) {
                throw new InvalidPassLengthException();
            }
            studentChangingQuery.changeNormalStudentUsername(uid, newUsername);
            studentChangingQuery.changeNormalStudentPass(uid, newPass);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeTeacherUsernameAndPass(int uid, String newUsername, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException, InvalidUsernameLengthException {
        if (isUniqueUserNameAndPassword(newUsername, newPass)) {
            if (newUsername.length() < 8) {
                throw new InvalidUsernameLengthException();
            }
            if (newPass.length() < 8) {
                throw new InvalidPassLengthException();
            }
            teacherChangingQuery.changeTeacherUsername(uid, newUsername);
            teacherChangingQuery.changeTeacherPass(uid, newPass);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeTeacherUserNameWithUID(int uid, String newUserName) throws SQLException, NotUniqueUsernameAndPassException, InvalidUsernameLengthException {
        if (newUserName.length() < 8) {
            throw new InvalidUsernameLengthException();
        }
        String pass = personLoginQuery.getTeacherPassByUID(uid);
        if (pass == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(newUserName, pass)) {
            teacherChangingQuery.changeTeacherUsername(uid, newUserName);
        } else {
            throw new NotUniqueUsernameAndPassException();
        }
    }

    @Override
    public void changeWorkingStudentPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException, InvalidPassLengthException {
        if (newPass.length() < 8) {
            throw new InvalidPassLengthException();
        }

        String userName = personLoginQuery.getWorkingStudentUsernameByUID(uid);
        if (userName == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(userName, newPass)) {
            studentChangingQuery.changeWorkingStudentPass(uid, newPass);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeTeacherPassWithUID(int uid, String newPass) throws SQLException, NotUniqueUsernameAndPassException {
        if (newPass.length() < 8) {
            throw new InvalidPassLengthException();
        }
        String userName = personLoginQuery.getTeacherUsernameByUID(uid);
        if (userName == null) {
            return;
        }
        if (isUniqueUserNameAndPassword(userName, newPass)) {
            studentChangingQuery.changeTeacherPass(uid, newPass);
        } else {
            throw new NotUniqueNameAndLastnameException();
        }
    }

    @Override
    public void changeNormalStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException {
        if (newDebt < 0) {
            throw new InvalidDebtException();
        }
        studentChangingQuery.setNormalStudentDebt(uid, newDebt);
    }

    @Override
    public void changeWorkingStudentDebt(int uid, int newDebt) throws SQLException, InvalidDebtException {
        if (newDebt < 0) {
            throw new InvalidDebtException();
        }
        studentChangingQuery.setWorkingStudentDebt(uid, newDebt);
    }

    @Override
    public void changeNormalStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException {
        if (newCredit < 0) {
            throw new InvalidLessonCreditException();
        }
        studentChangingQuery.setNormalStudentLessonCredit(uid, newCredit);
    }

    @Override
    public void changeWorkingStudentLessonCredit(int uid, int newCredit) throws SQLException, InvalidLessonCreditException {
        if (newCredit < 0) {
            throw new InvalidLessonCreditException();
        }
        studentChangingQuery.setWorkingStudentLessonCredit(uid, newCredit);
    }

    @Override
    public void changeTeacherSalary(int uid, int newSalary) throws SQLException, InvalidSalaryException {
        if (newSalary <= 0) {
            throw new InvalidSalaryException();
        }
        teacherChangingQuery.setTeacherSalary(uid, newSalary);
    }

    @Override
    public void changeTeacherBranch(int uid, int newBranchId) throws SQLException {
        teacherChangingQuery.setTeacherBranch(uid, newBranchId);
    }

    @Override
    public void convertToWorkingStudent(Student student) throws SQLException {
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
        List<Lesson> lessonList = student.getLessonList();
        Map<Integer, Integer> lessonTeacherMap = new HashMap();
        for (Lesson currentLesson : lessonList) {
            int lessonUID = lessonFetchingQuery.getLessonUIDByLessonName(currentLesson.getName());
            int teacherUID = lessonCourseQuery.findTeacherUIDFromNormalStudentCourse(uid, lessonUID);
            lessonTeacherMap.put(lessonUID, teacherUID);
        }
        converter.convertToWorkingStudent(userName, pass, name, lastName, uid, newUID, balance, debt, lessonCredit, lessonTeacherMap, phoneNumber);
    }

    @Override
    public void convertToNormalStudent(WorkingStudent workingStudent) throws SQLException {
        int newUID = uidGen.generateUIDForWorkingStudent();
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
        Map<Integer, Integer> lessonTeacherMap = new HashMap();
        for (Lesson currentLesson : lessonList) {
            int lessonUID = lessonFetchingQuery.getLessonUIDByLessonName(currentLesson.getName());
            int teacherUID = lessonCourseQuery.findTeacherYUDFromWorkingStudentCourse(uid, lessonUID);
            lessonTeacherMap.put(lessonUID, teacherUID);
        }
        converter.convertToNormalStudent(userName, pass, name, lastName, uid, newUID, balance, debt, lessonCredit, lessonTeacherMap, phoneNumber);
    }

    private boolean isUniqueUserNameAndPassword(String userName, String password) throws SQLException {
        ResultSet resultSet1 = personLoginQuery.getAllNormalStudentUserNameAndPassword();
        ResultSet resultSet2 = personLoginQuery.getAllWorkingStudentUserNameAndPassword();
        ResultSet resultSet3 = personLoginQuery.getAllTeacherUserNameAndPassword();
        while (resultSet1.next()) {
            String userNameInDb = resultSet1.getString("username");
            String passInDb = resultSet1.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                System.out.println("False1");
                return false;
            }
        }
        while (resultSet2.next()) {
            String userNameInDb = resultSet2.getString("username");
            String passInDb = resultSet2.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                System.out.println("False2");
                return false;
            }
        }
        while (resultSet3.next()) {
            String userNameInDb = resultSet3.getString("username");
            String passInDb = resultSet3.getString("pass");
            if (userName.equals(userNameInDb) && password.equals(passInDb)) {
                System.out.println("False3");
                return false;
            }
        }
        return true;
    }

    @Override
    public int sendRecoverySMS(String userName, String phoneNumber) throws IncompatibleUsernameAndPhoneNumberException, SQLException {
        int verificationCode = 0;
        if (isValidUsernameAndPhoneNumber(userName, phoneNumber)) {
            otpGen = Generator.getOTPGenerator();
            verificationCode = otpGen.generateOTP();
            messageSender = MessageSendingManager.getInstance();
            messageSender.sendMessage(phoneNumber, verificationCode);
        }
        return verificationCode;
    }

    private boolean isValidUsernameAndPhoneNumber(String userName, String phoneNumber) throws IncompatibleUsernameAndPhoneNumberException, SQLException {
        Map<String, String> mapForNormalStudent = personValidation.getAllNormalStudentUsernameAndPhoneNumber();
        for (Map.Entry<String, String> entry : mapForNormalStudent.entrySet()) {
            String userNameInDb = entry.getValue();
            String phoneNumberInDb = entry.getKey();
            if (userNameInDb.equals(userName) && phoneNumberInDb.equals(phoneNumber)) {
                return true;
            }
        }
        Map<String, String> mapForWorkingStudent = personValidation.getAllWorkingStudentUsernameAndPhoneNumber();
        for (Map.Entry<String, String> entry : mapForWorkingStudent.entrySet()) {
            String userNameInDb = entry.getValue();
            String phoneNumberInDb = entry.getKey();
            if (userNameInDb.equals(userName) && phoneNumberInDb.equals(phoneNumber)) {
                return true;
            }
        }
        Map<String, String> mapForTeacher = personValidation.getAllTeacherUsernameAndPhoneNumber();
        for (Map.Entry<String, String> entry : mapForTeacher.entrySet()) {
            String userNameInDb = entry.getValue();
            String phoneNumberInDb = entry.getKey();
            if (userNameInDb.equals(userName) && phoneNumberInDb.equals(phoneNumber)) {
                return true;
            }
        }
        throw new IncompatibleUsernameAndPhoneNumberException();
    }

    private boolean phoneNumberValidation(String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        if (phoneNumber.length() != 13) {
            throw new InvalidPhoneNumberLengthException();
        }
        if (!phoneNumber.substring(0, 3).equals("+90")) {
            throw new InvalidPhoneCountryCodeException();
        }
        if (isUniquePhoneNumber(personFetchingQuery.getAllNormalStudentInfo(), phoneNumber) && isUniquePhoneNumber(personFetchingQuery.getAllWorkingStudentInfo(), phoneNumber) && isUniquePhoneNumber(personFetchingQuery.getAllTeacherInfo(), phoneNumber)) {
            return true;
        }
        throw new NotUniquePhoneNumberException();
    }

    @Override
    public void changeNormalStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        if (phoneNumberValidation(phoneNumber)) {
            studentChangingQuery.changeNormalStudentPhoneNumber(uid, phoneNumber);
        }
    }

    @Override
    public void changeWorkingStudentPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        if (phoneNumberValidation(phoneNumber)) {
            studentChangingQuery.changeWorkingStudentPhoneNumber(uid, phoneNumber);
        }
    }

    @Override
    public void changeTeacherPhoneNumber(int uid, String phoneNumber) throws InvalidPhoneNumberLengthException, InvalidPhoneCountryCodeException, NotUniquePhoneNumberException, SQLException {
        if (phoneNumberValidation(phoneNumber)) {
            studentChangingQuery.changeTeacherPhoneNumber(uid, phoneNumber);
        }
    }

    @Override
    public void changeForgottenPass(String phoneNumber, String pass) throws InvalidPassLengthException, SQLException {
        if (pass.length() < 8) {
            throw new InvalidPassLengthException();
        }
        studentChangingQuery.changeNormalStudentPassWithPhoneNumber(phoneNumber, pass);
        studentChangingQuery.changeWorkingStudentPassWithPhoneNumber(phoneNumber, pass);
        teacherChangingQuery.changeTeacherPassWithPhoneNumber(phoneNumber, pass);
    }

}
