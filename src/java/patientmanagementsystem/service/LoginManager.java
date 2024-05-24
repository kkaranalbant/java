/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.service;

import java.sql.SQLException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import patientmanagementsystem.dao.LoginDao;
import patientmanagementsystem.exception.FailureLoginException;
import patientmanagementsystem.repository.LoginRepo;

/**
 *
 * @author kaan
 */
public class LoginManager {

    private static LoginManager loginManager;

    private LoginRepo loginRepo;

    private LoginManager() throws SQLException, ClassNotFoundException {
        loginRepo = LoginRepo.getInstance();
    }

    private enum UserRole {
        PATIENT,
        DOCTOR,
        ADMIN
    }

    public static LoginManager getInstance() throws SQLException, ClassNotFoundException {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public void authenticateUser(HttpServletRequest request, LoginDao loginDao) throws FailureLoginException, SQLException {
        if (loginRepo.doesAdminExist(loginDao.getUsername(), loginDao.getPassword())) {
            addRoleToHeader(request, UserRole.ADMIN);
            addCredentialsToHeader(request, loginDao);
        } else if (loginRepo.doesDoctorExist(loginDao.getUsername(), loginDao.getPassword())) {
            addRoleToHeader(request, UserRole.DOCTOR);
            addCredentialsToHeader(request, loginDao);
        } else if (loginRepo.doesPatientExist(loginDao.getUsername(), loginDao.getPassword())) {
            addRoleToHeader(request, UserRole.PATIENT);
            addCredentialsToHeader(request, loginDao);
        } else {
            throw new FailureLoginException();
        }
    }
    
    public void addDoctorLoginInfo (Long doctorId , String username , String password) throws SQLException {
        loginRepo.createDoctorLoginInfo(doctorId, username, password);
    }
    
    public void addPatientLoginInfo (Long patientId , String username , String password) throws SQLException {
        loginRepo.createPatientLoginInfo(patientId, username, password);
    }   

    private void addRoleToHeader(HttpServletRequest request, UserRole role) {
        HttpSession session = request.getSession(true);
        session.setAttribute("role", role.name());
    }

    private void addCredentialsToHeader(HttpServletRequest request, LoginDao loginDao) {
        HttpSession session = request.getSession(false);
        session.setAttribute("username", loginDao.getUsername());
        session.setAttribute("password", loginDao.getPassword());
    }

    public void removeRoleFromHeader(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("role");
        request.getSession(true);
    }
    
    public Long getPatientIdByUsernameAndPassword (String username , String password) throws SQLException {
        return loginRepo.getPatientIdByUsernameAndPassword(username, password);
    }
    
    public Long getDoctorIdByUsernameAndPassword (String username , String password) throws SQLException {
        return loginRepo.getDoctorIdByUsernameAndPassword(username, password);
    }
    
    public void removePatientLoginInfoByPatientId (Long patientId) throws SQLException {
        loginRepo.removePatientByPatientId(patientId);
    }
    public void removeDoctorLoginInfoByDoctorId (Long doctorId) throws SQLException {
        loginRepo.removeDoctorByDoctorId(doctorId);
    }
    
    public void updatePatientLoginInfoByPatientId (Long patientId , String username ,String password) throws SQLException {
        loginRepo.updatePatientByPatientId(patientId, username, password);
    }
    
    public void updateDoctorLoginInfoByDoctorId (Long doctorId , String username ,String password) throws SQLException {
        loginRepo.updateDoctorByDoctorId(doctorId, username, password);
    }
    
}
