/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.connection;

import com.kaan.schoolmanagementmaven.dataaccess.query.FirstTimeDefaultInfoQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IFirstTimeDefaultInfoQuery;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kaan
 */
public class AccessManager implements IAccessManager {

    private String host;
    private String dbName;
    private int port;
    private String userName;
    private String pass;
    private Access access;
    private static File connectionFile;
    private static BufferedReader bufferedReader;
    private static FileReader fileReader;

    /*
    Bu sinif yuklendigi zaman connectionFile referansi databaseInfo.txt isimli dosyayi gosterecek . 
     */
    static {
        connectionFile = new File("databaseinfo.txt");
        try {

            fileReader = new FileReader(connectionFile);
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private AccessManager(String hostName, int port, String dbName, String userName, String pass) throws SQLException {
        this.host = hostName;
        this.port = port;
        this.dbName = dbName;
        this.userName = userName;
        this.pass = pass;
        access = Access.getInstance();
        access.setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, pass));
    }

    /*
    Metot once verilen baglanti bilgilerinin dogrulugunu kontrol eden baska bir metodu cagiriyor . 
    Kontrol yapildiktan sonra eger bilgiler gecerli ise DefaultDatabaseInfo sinifindaki degerleri degistiren baska bir metot cagriliyor .
    Daha sonra baglanti dosyasina verileri yazan baska bir metot cagiriyor .
    En sonunda AccessMAnager nesnesini olusturuyor ve geriye donuyor .
     */
    public static IAccessManager changeAccessInformations(String hostName, int port, String dbName, String userName, String pass) throws SQLException, IOException {
        isValidDatabaseInformations(hostName, port, dbName, userName, pass);
        changeDefaultDatabaseInfo(hostName, port, dbName, userName, pass);
        changeConnectionFile(hostName, port, dbName, userName, pass);
        return new AccessManager(hostName, port, dbName, userName, pass);
    }


    /*
    Bu metot eger databaseinfo dosyasi dolu ise dogrulugunu kontrol ederek access nesnesi olusturmaya yariyor . 
     */
    public static Access loadAccessObject() throws SQLException, IOException {
        List<String> resultList = getConnectionInformations();
        if (resultList == null) {
            throw new SQLException();
        }
        isValidDatabaseInformations(resultList.get(0), Integer.parseInt(resultList.get(1)), resultList.get(2), resultList.get(3), resultList.get(4));
        changeDefaultDatabaseInfo(resultList.get(0), Integer.parseInt(resultList.get(1)), resultList.get(2), resultList.get(3), resultList.get(4));
        String url = "jdbc:mysql://" + DefaultDatabaseInfos.host + ":" + DefaultDatabaseInfos.port + "/" + DefaultDatabaseInfos.dbName;
        Access.getInstance().setConnection(DriverManager.getConnection(url, DefaultDatabaseInfos.userName, DefaultDatabaseInfos.pass));
        return Access.getInstance();
    }

    private static boolean isValidDatabaseInformations(String host, int port, String dbName, String userName, String pass) throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        DriverManager.getConnection(url, userName, pass); // burada exception firlatilirsa verilen bilgiler yanlis demek
        return true;
    }

    private static void changeDefaultDatabaseInfo(String hostName, int port, String dbName, String userName, String pass) {
        DefaultDatabaseInfos.dbName = dbName;
        DefaultDatabaseInfos.userName = userName;
        DefaultDatabaseInfos.port = port;
        DefaultDatabaseInfos.pass = pass;
        DefaultDatabaseInfos.host = hostName;
    }

    public static void changeConnectionFile(String host, int port, String dbName, String userName, String pass) throws IOException {
        connectionFile.delete();
        connectionFile.createNewFile(); // dosyanin icerisindeki daha onceden olan verileri silmek icin
        FileWriter fileWriter = new FileWriter(connectionFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(host);
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(port));
        bufferedWriter.newLine();
        bufferedWriter.write(dbName);
        bufferedWriter.newLine();
        bufferedWriter.write(userName);
        bufferedWriter.newLine();
        bufferedWriter.write(pass);
        bufferedWriter.flush(); // close etmedigimiz icin ve metot bittiginden dolayi tampondaki verileri dosyaya kaydetmeye yariyor . 
        bufferedWriter.close();
        fileWriter.close(); 
        bufferedWriter = null ;
        fileWriter = null ;
    }

    private static boolean isEmptyConnectionFile() throws IOException {
        FileReader fileReader = new FileReader(connectionFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        if (bufferedReader.readLine() == null) {
            return true;
        }
        return false;
    }

    private static List<String> getConnectionInformations() throws IOException {
        if (isEmptyConnectionFile()) {
            return null;
        }
        List<String> resultList = new ArrayList();
        FileReader fileReader = new FileReader(connectionFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String value = null;
        while ((value = bufferedReader.readLine()) != null) {
            resultList.add(value);
        }
        bufferedReader.mark(0); // birdaha veriler istenirse readerin basa donmesi icin isaretledim .
        bufferedReader.reset(); // isaretlenen yere geri dondurmek icin .
        return resultList;
    }

    @Override
    public void createTables() throws SQLException {
        addAdminTable();
        addNormalStudentsTable();
        addWorkingStudentsTable();
        addTeacherTable();
        addLessonTable();
        addNormalStudentCourse();
        addWorkingStudentCourse();
        addNormalStudentsLoginInfo();
        addWorkingStudentsLoginInfo();
        addTeacherLoginInfo();
        addNormalStudentExamNote();
        addWorkingStudentExamNote();
        addDefaultValues();
        addCourseAttendance();
        addTeacherBranch();

    }

    public static void closeAllStreams() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }

    private void addAdminTable() throws SQLException {
        String query = "create table admin ("
                + "username varchar(25),"
                + "pass varchar(25)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentsTable() throws SQLException {
        String query = "create table normal_students ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "debt int,"
                + "student_lesson_credit int,"
                + "phone_number char (13)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentsTable() throws SQLException {
        String query = "create table working_students ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "debt int,"
                + "student_lesson_credit int,"
                + "phone_number char (13)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherTable() throws SQLException {
        String query = "create table teachers ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "salary int,"
                + "phone_number char (13)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addLessonTable() throws SQLException {
        String query = "create table lesson ("
                + "lesson_name varchar(20),"
                + "lesson_credit int,"
                + "lesson_hour int,"
                + "lesson_UID int primary key,"
                + "quota int,"
                + "average_midterm_rate int ,"
                + "average_final_rate int"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentCourse() throws SQLException {
        String query = "create table normal_students_course("
                + "normal_student_UID int,"
                + "lesson_UID int ,"
                + "teacher_UID int ,"
                + "foreign key (normal_student_UID) references normal_students(UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentCourse() throws SQLException {
        String query = "create table working_students_course("
                + "working_student_UID int,"
                + "lesson_UID int ,"
                + "teacher_UID int ,"
                + "foreign key (working_student_UID) references working_students(UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentsLoginInfo() throws SQLException {
        String query = "create table normal_students_login_infos ("
                + "normal_student_UID int primary key,"
                + "username varchar (25) ,"
                + "pass varchar (25),"
                + "foreign key (normal_student_UID) references normal_students (UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentsLoginInfo() throws SQLException {
        String query = "create table working_students_login_infos ("
                + "working_student_UID int primary key,"
                + "username varchar (25) ,"
                + "pass varchar (25),"
                + "foreign key (working_student_UID) references working_students (UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherLoginInfo() throws SQLException {
        String query = "create table teacher_login_infos ("
                + "teacher_UID int primary key ,"
                + "username varchar (25),"
                + "pass varchar(25),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherBranch() throws SQLException {
        String query = "create table teacher_branch ("
                + "teacher_UID int primary key,"
                + "lesson_UID int,"
                + "foreign key (teacher_UID) references teachers (UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentExamNote() throws SQLException {
        String query = "create table normal_student_exam_notes ("
                + "student_UID int ,"
                + "lesson_UID int ,"
                + "primary key (student_UID , lesson_UID) ,"
                + "foreign key (student_UID) references normal_students (UID) ,"
                + "foreign key (lesson_UID) references lesson (lesson_UID),"
                + "midterm int ,"
                + "final int ,"
                + "average int "
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentExamNote() throws SQLException {
        String query = "create table working_student_exam_notes ("
                + "student_UID int ,"
                + "lesson_UID int ,"
                + "primary key (student_UID , lesson_UID) ,"
                + "foreign key (student_UID) references working_students (UID) ,"
                + "foreign key (lesson_UID) references lesson (lesson_UID),"
                + "midterm int ,"
                + "final int ,"
                + "average int "
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addCourseAttendance() throws SQLException {
        String query = "create table course_attendance ("
                + "lesson_UID int primary key,"
                + "attendance int,"
                + "foreign key (lesson_UID) references lesson(lesson_UID)"
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
    }

    private void addDefaultValues() throws SQLException {
        String query = "create table default_values ("
                + "default_lesson_credit int,"
                + "default_max_debt_credit int,"
                + "default_balance int ,"
                + "default_debt int ,"
                + "default_normal_student_UID_origin int ,"
                + "default_normal_student_UID_bound int ,"
                + "default_working_student_UID_origin int ,"
                + "default_working_student_UID_bound int ,"
                + "default_teacher_UID_origin int ,"
                + "default_teacher_UID_bound int ,"
                + "cost_for_per_hour int ,"
                + "default_lesson_UID_origin int ,"
                + "default_lesson_UID_bound int "
                + ");";
        access.getConnection().prepareStatement(query).executeUpdate();
        IFirstTimeDefaultInfoQuery rowAdder = FirstTimeDefaultInfoQuery.getInstance();
        rowAdder.addDefaultValuesRow();
    }

    public static File getConnectionFile() {
        return connectionFile;
    }

}
