/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.repository;

import java.sql.SQLException;
import patientmanagementsystem.connection.DefPersonColumns;
import patientmanagementsystem.connection.DefTableNames;
import patientmanagementsystem.model.Patient;
import java.sql.ResultSet;
import java.time.LocalDate;
import patientmanagementsystem.model.Gender;
import java.util.Optional;

/**
 *
 * @author kaan
 */
public class PatientRepo extends Repo {

    private static PatientRepo patientRepo;

    private PatientRepo() throws SQLException, ClassNotFoundException {

    }

    public static PatientRepo getInstance() throws SQLException, ClassNotFoundException {
        if (patientRepo == null) {
            patientRepo = new PatientRepo();
        }
        return patientRepo;
    }

    public Optional<Patient> getPatientById(Long id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String query = sb.append("select * from ").append(DefTableNames.getPatient()).append(" where ").append(DefPersonColumns.getPatientId()).append(" = ").append(id).append(" ;").toString();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        ResultSet patientResultSet = super.getPreparedStatement().executeQuery();
        return Optional.ofNullable(createPatientFromResultSet(patientResultSet));
    }

    public Patient getPatientByNameAndLastname(String name, String lastName) throws SQLException {
        String query = "Select * from Hasta where Ad = ? and Soyad = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, name);
        super.getPreparedStatement().setString(2, lastName);
        ResultSet patientResultSet = super.getPreparedStatement().executeQuery();
        return createPatientFromResultSet(patientResultSet);
    }

    public int savePatient(Patient patient) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String query = sb.append("insert into ").append(DefTableNames.getPatient())
                .append("(Ad , Soyad , DogumTarihi , Cinsiyet , TelefonNumarasi , Adres) values (")
                .append("'").append(patient.getName()).append("','")
                .append(patient.getLastName()).append("','").append(patient.getBirthDate().toString()).append("','")
                .append(patient.getGender().toString()).append("','").append(patient.getPhoneNumber()).append("','")
                .append(patient.getAddress()).append("') ;").toString();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeUpdate();
    }

    public int updatePatient(Patient patient) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String query = sb.append("UPDATE ")
                .append(DefTableNames.getPatient())
                .append(" SET Ad='").append(patient.getName())
                .append("', Soyad='").append(patient.getLastName())
                .append("', DogumTarihi='").append(patient.getBirthDate().toString())
                .append("', Cinsiyet='").append(patient.getGender().toString())
                .append("', TelefonNumarasi='").append(patient.getPhoneNumber())
                .append("', Adres='").append(patient.getAddress())
                .append("' WHERE ")
                .append(DefPersonColumns.getPatientId())
                .append(" = ")
                .append(patient.getId())
                .append(";")
                .toString();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeUpdate();
    }

    public int deletePatientById(Long id) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String query = sb.append("delete from ").append(DefTableNames.getPatient()).append(" where ").append(DefPersonColumns.getPatientId()).append(" = ").append(id).append(" ;").toString();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeUpdate();
    }

    public int deletePatientByNameAndLastname(String name, String lastName) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String query = sb.append("delete from ").append(DefTableNames.getPatient()).append(" where ").append(DefPersonColumns.getName()).append(" = '").append(name).append("' and ").append(DefPersonColumns.getLastname()).append(" = ' ").append(lastName).append(" ' ;").toString();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeUpdate();
    }

    private Patient createPatientFromResultSet(ResultSet patientResultSet) throws SQLException {
        Patient patient = null;
        while (patientResultSet.next()) {
            Long id = patientResultSet.getLong("HastaID");
            String name = patientResultSet.getString(DefPersonColumns.getName());
            String lastname = patientResultSet.getString(DefPersonColumns.getLastname());
            LocalDate birthDate = patientResultSet.getDate("DogumTarihi").toLocalDate();
            Gender gender = getGenderByString(patientResultSet.getString("Cinsiyet"));
            String phoneNumber = patientResultSet.getString("TelefonNumarasi");
            String address = patientResultSet.getString("Adres");
            patient = new Patient(id , name, lastname, phoneNumber, address, gender, birthDate);
        }
        return patient;
    }

    private Gender getGenderByString(String genderString) {
        for (Gender gender : Gender.values()) {
            if (gender.toString().equalsIgnoreCase(genderString)) {
                return gender;
            }
        }
        return null;
    }

}
