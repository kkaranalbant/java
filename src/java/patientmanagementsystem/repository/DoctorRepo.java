package patientmanagementsystem.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import patientmanagementsystem.connection.DefTableNames;
import patientmanagementsystem.model.Doctor;
import patientmanagementsystem.model.Gender;

public class DoctorRepo extends Repo {

    private static DoctorRepo doctorRepo;

    private DoctorRepo() throws SQLException, ClassNotFoundException {
    }

    public static DoctorRepo getInstance() throws SQLException, ClassNotFoundException {
        if (doctorRepo == null) {
            doctorRepo = new DoctorRepo();
        }
        return doctorRepo;
    }

    public Doctor getDoctorById(Long id) throws SQLException {
        String query = "SELECT * FROM " + DefTableNames.getDoctor() + " WHERE DoktorID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, id);
        ResultSet doctorResultSet = super.getPreparedStatement().executeQuery();
        return createDoctorFromResultSet(doctorResultSet);
    }

    public Doctor getDoctorByNameAndLastname(String name, String lastName) throws SQLException {
        String query = "SELECT * FROM " + DefTableNames.getDoctor() + " WHERE Ad = ? AND Soyad = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, name);
        super.getPreparedStatement().setString(2, lastName);
        ResultSet doctorResultSet = super.getPreparedStatement().executeQuery();
        return createDoctorFromResultSet(doctorResultSet);
    }

    public int saveDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO " + DefTableNames.getDoctor()
                + "(Ad, Soyad, DogumTarihi, Cinsiyet, TelefonNumarasi, Adres, Brans, CalistigiYer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, doctor.getName());
        super.getPreparedStatement().setString(2, doctor.getLastName());
        super.getPreparedStatement().setDate(3, java.sql.Date.valueOf(doctor.getBirthDate()));
        super.getPreparedStatement().setString(4, doctor.getGender().toString());
        super.getPreparedStatement().setString(5, doctor.getPhoneNumber());
        super.getPreparedStatement().setString(6, doctor.getAddress());
        super.getPreparedStatement().setString(7, doctor.getBranch());
        super.getPreparedStatement().setString(8, doctor.getWorkPlace());
        return super.getPreparedStatement().executeUpdate();
    }

    public int updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE " + DefTableNames.getDoctor()
                + " SET Ad=?, Soyad=?, DogumTarihi=?, Cinsiyet=?, TelefonNumarasi=?, Adres=?, Brans=?, CalistigiYer=? WHERE DoktorID=?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, doctor.getName());
        super.getPreparedStatement().setString(2, doctor.getLastName());
        super.getPreparedStatement().setDate(3, java.sql.Date.valueOf(doctor.getBirthDate()));
        super.getPreparedStatement().setString(4, doctor.getGender().toString());
        super.getPreparedStatement().setString(5, doctor.getPhoneNumber());
        super.getPreparedStatement().setString(6, doctor.getAddress());
        super.getPreparedStatement().setString(7, doctor.getBranch());
        super.getPreparedStatement().setString(8, doctor.getWorkPlace());
        super.getPreparedStatement().setLong(9, doctor.getId());
        return super.getPreparedStatement().executeUpdate();
    }

    public int deleteDoctorById(Long id) throws SQLException {
        String query = "DELETE FROM " + DefTableNames.getDoctor() + " WHERE DoktorID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, id);
        return super.getPreparedStatement().executeUpdate();
    }

    private List<Doctor> createDoctorsFromResultSet(ResultSet doctorResultSet) throws SQLException {
        List<Doctor> doctors = new ArrayList();
        while (doctorResultSet.next()) {
            Doctor doctor = null;
            Long id = doctorResultSet.getLong("DoktorID");
            String name = doctorResultSet.getString("Ad");
            String lastname = doctorResultSet.getString("Soyad");
            String phoneNumber = doctorResultSet.getString("TelefonNumarasi");
            String address = doctorResultSet.getString("Adres");
            String branch = doctorResultSet.getString("Brans");
            String workPlace = doctorResultSet.getString("CalistigiYer");
            LocalDate birthDate = doctorResultSet.getDate("DogumTarihi").toLocalDate();
            String genderString = doctorResultSet.getString("Cinsiyet");
            Gender gender = getGenderByString(genderString);
            doctor = new Doctor(id, branch, workPlace, name, lastname, phoneNumber, address, gender, birthDate);
            doctors.add(doctor);
        }
        return doctors;
    }

    private Doctor createDoctorFromResultSet (ResultSet doctorResultSet) throws SQLException {
        Doctor doctor = null;
        doctorResultSet.next();
        Long id = doctorResultSet.getLong("DoktorID");
        String name = doctorResultSet.getString("Ad");
        String lastname = doctorResultSet.getString("Soyad");
        String phoneNumber = doctorResultSet.getString("TelefonNumarasi");
        String address = doctorResultSet.getString("Adres");
        String branch = doctorResultSet.getString("Brans");
        String workPlace = doctorResultSet.getString("CalistigiYer");
        LocalDate birthDate = doctorResultSet.getDate("DogumTarihi").toLocalDate();
        String genderString = doctorResultSet.getString("Cinsiyet");
        Gender gender = getGenderByString(genderString);
        doctor = new Doctor(id, branch, workPlace, name, lastname, phoneNumber, address, gender, birthDate);
        return doctor;
    }

//    public List<Doctor> getAllDoctorsByBranch(String branch) throws SQLException {
//        String query = "SELECT * FROM " + DefTableNames.getDoctor() + " WHERE Brans = ?";
//        super.setPreparedStatement(super.getConnection().prepareStatement(query));
//        super.getPreparedStatement().setString(1, branch);
//        ResultSet doctorResultSet = super.getPreparedStatement().executeQuery();
//        List<Doctor> doctors = new ArrayList();
//        while (doctorResultSet.next()) {
//            Optional<Doctor> doctorOptional = createDoctorFromResultSet(doctorResultSet);
//            if (doctorOptional.isPresent()) {
//                doctors.add(doctorOptional.get());
//            }
//        }
//        return doctors;
//    }
//    
//    public List <Doctor> getAllDoctorsByWorkPlace (String workPlace) throws SQLException {
//        String query = "SELECT * FROM " + DefTableNames.getDoctor() + " WHERE Brans = ?";
//        super.setPreparedStatement(super.getConnection().prepareStatement(query));
//        super.getPreparedStatement().setString(1, branch);
//        ResultSet doctorResultSet = super.getPreparedStatement().executeQuery();
//        List<Doctor> doctors = new ArrayList();
//        while (doctorResultSet.next()) {
//            Optional<Doctor> doctorOptional = createDoctorFromResultSet(doctorResultSet);
//            if (doctorOptional.isPresent()) {
//                doctors.add(doctorOptional.get());
//            }
//        }
//        return doctors;
//    }
    public List<Doctor> getAllDoctorsByWorkPlaceAndBranch(String workPlace, String branch) throws SQLException {
        String query = "SELECT * FROM Doktorlar WHERE CalistigiYer = '" + workPlace + "' and Brans = '" + branch + "' ;";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        ResultSet doctorResultSet = super.getPreparedStatement().executeQuery();
        List<Doctor> doctors = createDoctorsFromResultSet(doctorResultSet);
        return doctors;
    }

    public List<String> getAllBranchs() throws SQLException {
        return getAllDistinctStringColumn("Brans");
    }

    public List<String> getAllWorkPlaces() throws SQLException {
        return getAllDistinctStringColumn("CalistigiYer");
    }

    private List<String> getAllDistinctStringColumn(String columnName) throws SQLException {
        String query = "SELECT DISTINCT " + columnName + " FROM " + DefTableNames.getDoctor();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(columnName));
        }
        return list;
    }

    private Gender getGenderByString(String genderString) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(genderString)) {
                return gender;
            }
        }
        return null;
    }
}
