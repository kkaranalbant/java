package patientmanagementsystem.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import patientmanagementsystem.dao.DoctorAppointmentDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.DoctorRegisterDao;
import patientmanagementsystem.dao.DoctorUpdatingDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.exception.UnsavedPersonException;
import patientmanagementsystem.model.Doctor;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.repository.DoctorRepo;

public class DoctorManager {

    private static DoctorManager doctorManager;
    private final DoctorRepo doctorRepo;
    private final LoginManager loginManager ;

    private DoctorManager() throws SQLException, ClassNotFoundException {
        doctorRepo = DoctorRepo.getInstance();
        loginManager = LoginManager.getInstance() ;
    }

    public static DoctorManager getInstance() throws SQLException, ClassNotFoundException {
        if (doctorManager == null) {
            doctorManager = new DoctorManager();
        }
        return doctorManager;
    }

    public void saveDoctor(DoctorRegisterDao doctorRegisterDao) throws UnsavedPersonException  , ClassNotFoundException{
        String name = doctorRegisterDao.getName();
        String lastname = doctorRegisterDao.getLastname();
        String phoneNumber = doctorRegisterDao.getPhoneNumber();
        String address = doctorRegisterDao.getAddress();
        String branch = doctorRegisterDao.getBranch();
        String workingPlace = doctorRegisterDao.getWorkingPlace();
        Gender gender = doctorRegisterDao.getGender();
        LocalDate birthDate = doctorRegisterDao.getBirthDate();
        String username = doctorRegisterDao.getUsername() ;
        String password = doctorRegisterDao.getPassword() ;
        Doctor doctor = new Doctor(branch, workingPlace, name, lastname, phoneNumber, address, gender, birthDate);
        try {
            doctorRepo.saveDoctor(doctor);
            Doctor doctorWithId = doctorRepo.getDoctorByNameAndLastname(name, lastname);
            Long doctorId = doctorWithId.getId() ;
            loginManager.addDoctorLoginInfo(doctorId, username, password);
        } catch (SQLException ex) {
            throw new UnsavedPersonException();
        }
    }

    public void updateDoctor(DoctorUpdatingDao doctorUpdatingDao) throws SQLException, FailureUpdatingException {
        Long id = doctorUpdatingDao.getId();
        Doctor doctor = doctorRepo.getDoctorById(id);
        if (doctor != null) {
            doctor.setName(doctorUpdatingDao.getName());
            doctor.setLastName(doctorUpdatingDao.getLastname());
            doctor.setBirthDate(doctorUpdatingDao.getBirthDate());
            doctor.setAddress(doctorUpdatingDao.getAddress());
            doctor.setGender(doctorUpdatingDao.getGender());
            doctor.setPhoneNumber(doctorUpdatingDao.getPhoneNumber());
            doctor.setBranch(doctorUpdatingDao.getBranch());
            doctor.setWorkPlace(doctorUpdatingDao.getWorkingPlace());
            doctorRepo.updateDoctor(doctor);
            String username = doctorUpdatingDao.getUsername() ;
            String password = doctorUpdatingDao.getPassword() ;
            loginManager.updateDoctorLoginInfoByDoctorId(id, username, password);
        } else {
            throw new FailureUpdatingException();
        }

    }

    public void removeDoctor(ElementIdDao doctorIdDao) throws FailureDeletingException , ClassNotFoundException {
        Long id = doctorIdDao.getId();
        try {
            loginManager.removeDoctorLoginInfoByDoctorId(id);
            AppointmentManager appointmentManager = AppointmentManager.getInstance() ;
            appointmentManager.removeAllAppointmentsByDoctorId(id);
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            medicalReportManager.deleteAllMedicalReportsByDoctorId(id);
            doctorRepo.deleteDoctorById(id);
        } catch (SQLException ex) {
            throw new FailureDeletingException();
        }
    }

    public DoctorInfoDao showDoctor(ElementIdDao doctorIdDao) throws InvalidIdException , SQLException{
        Long id = doctorIdDao.getId();
        DoctorInfoDao doctorInfoDao = null;
        Doctor doctor = doctorRepo.getDoctorById(id);
        if (doctor != null) {
            doctorInfoDao = new DoctorInfoDao(id, doctor.getName(), doctor.getLastName(), doctor.getBirthDate(), doctor.getGender(), doctor.getAddress(), doctor.getPhoneNumber(), doctor.getBranch(), doctor.getWorkPlace());
            return doctorInfoDao;
        }
        throw new InvalidIdException () ;
    }
    
    public Doctor getDoctorByNameAndLastname (String name , String lastname) throws SQLException {
        return doctorRepo.getDoctorByNameAndLastname(name, lastname) ;
    }
    
    public List <DoctorAppointmentDao> getAllDoctorNamesAndLastnamesByWorkPlaceAndBranch (String workPlace , String branch) throws SQLException {
        List <Doctor> doctors = doctorRepo.getAllDoctorsByWorkPlaceAndBranch(workPlace, branch) ;
        List <DoctorAppointmentDao> results = new ArrayList () ; 
        for (Doctor doctor : doctors) {
            results.add(new DoctorAppointmentDao(doctor.getId(),doctor.getName(),doctor.getLastName()));
        }
        return results ;
    }
    
    public List <String> getAllWorkPlaces () throws SQLException {
        return doctorRepo.getAllWorkPlaces() ;
    }
    
    public List <String> getAllBranchs () throws SQLException {
        return doctorRepo.getAllBranchs() ;
    }
}
