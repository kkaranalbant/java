package patientmanagementsystem.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.dao.PatientRegisterDao;
import patientmanagementsystem.dao.PatientUpdatingDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.exception.InvalidNameOrLastnameException;
import patientmanagementsystem.exception.UnsavedPersonException;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.model.Patient;
import patientmanagementsystem.repository.PatientRepo;

public class PatientManager {

    private static PatientManager patientManager;
    private final PatientRepo patientRepo;
    private final LoginManager loginManager ;

    private PatientManager() throws SQLException, ClassNotFoundException {
        patientRepo = PatientRepo.getInstance();
        loginManager = LoginManager.getInstance() ;
    }

    public static PatientManager getInstance() throws SQLException, ClassNotFoundException {
        if (patientManager == null) {
            patientManager = new PatientManager();
        }
        return patientManager;
    }

    public void savePatient(PatientRegisterDao patientRegisterDao) throws UnsavedPersonException , ClassNotFoundException {
        String name = patientRegisterDao.getName();
        String lastname = patientRegisterDao.getLastname();
        String phoneNumber = patientRegisterDao.getPhoneNumber();
        String address = patientRegisterDao.getAddress();
        Gender gender = patientRegisterDao.getGender();
        LocalDate birthDate = patientRegisterDao.getBirthDate();
        String username = patientRegisterDao.getUsername() ;
        String password = patientRegisterDao.getPassword() ;
        Patient patient = new Patient(name, lastname, phoneNumber, address, gender, birthDate);
        try {
            patientRepo.savePatient(patient);
            Patient patientWithId = patientRepo.getPatientByNameAndLastname(name, lastname);
            Long patientId = patientWithId.getId() ;
            loginManager.addPatientLoginInfo(patientId, username, password);
        } catch (SQLException ex) {
            throw new UnsavedPersonException(name,lastname);
        }
    }

    public void updatePatient(PatientUpdatingDao patientUpdatingDao) throws SQLException, FailureUpdatingException {
        Long id = patientUpdatingDao.getId();
        Optional<Patient> patientOptional = patientRepo.getPatientById(id);
        if (patientOptional.get() != null) {
            Patient patient = patientOptional.get();
            patient.setName(patientUpdatingDao.getName());
            patient.setLastName(patientUpdatingDao.getLastname());
            patient.setBirthDate(patientUpdatingDao.getBirthDate());
            patient.setAddress(patientUpdatingDao.getAddress());
            patient.setGender(patientUpdatingDao.getGender());
            patient.setPhoneNumber(patientUpdatingDao.getPhoneNumber());
            patientRepo.updatePatient(patient);
            String username = patientUpdatingDao.getUsername() ;
            String password = patientUpdatingDao.getPassword() ;
            loginManager.updatePatientLoginInfoByPatientId(id, username, password);
        } else {
            throw new FailureUpdatingException();
        }
    }

    public void removePatient(ElementIdDao patientIdDao) throws FailureDeletingException , ClassNotFoundException {
        Long id = patientIdDao.getId();
        try {
            loginManager.removePatientLoginInfoByPatientId(id);
            AppointmentManager appointmentManager = AppointmentManager.getInstance() ;
            appointmentManager.removeAllAppointmentsByPatientId(id);
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance() ;
            medicalReportManager.deleteAllMedicalReportsByPatientId(id);
            patientRepo.deletePatientById(id);
        } catch (SQLException ex) {
            throw new FailureDeletingException();
        }
    }

    public PatientInfoDao showPatient(ElementIdDao patientIdDao) throws InvalidIdException, SQLException {
        Long id = patientIdDao.getId();
        PatientInfoDao patientInfoDao = null;
        Optional<Patient> patientOptional = patientRepo.getPatientById(id);
        if (patientOptional.get() != null) {
            Patient patient = patientOptional.get();
            patientInfoDao = new PatientInfoDao(id, patient.getName(), patient.getLastName(), patient.getBirthDate(), patient.getGender(), patient.getAddress(), patient.getPhoneNumber());
            return patientInfoDao;
        }
        throw new InvalidIdException();
    }
    
    public Patient getPatientByNameAndLastname (String name , String lastname) throws SQLException , InvalidNameOrLastnameException{
        Patient patient = patientRepo.getPatientByNameAndLastname(name, lastname) ;
        if (patient != null) {
            return patient ;
        }
        throw new InvalidNameOrLastnameException () ;
    }

}
