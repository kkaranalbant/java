/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.connection;

/**
 *
 * @author kaan
 */
public class DefTableNames {

    private static String doctor;
    private static String patient;
    private static String labResult;
    private static String medReport;
    private static String appointment;
    private static String admin;
    private static String patientLoginInfo;
    private static String doctorLoginInfo;
    private static String adminLoginInfo;

    static {
        doctor = "Doktorlar";
        patient = "Hasta";
        labResult = "LaboratuvarSonuclari";
        medReport = "TibbiRaporlar";
        appointment = "Randevular";
        admin = "Yonetici";
        doctorLoginInfo = "DoctorLoginInfo";
        patientLoginInfo = "HastaLoginInfo";
        adminLoginInfo = "YoneticiLoginInfo";
    }

    public static String getPatientLoginInfo() {
        return patientLoginInfo;
    }

    public static String getDoctorLoginInfo() {
        return doctorLoginInfo;
    }

    public static String getAdminLoginInfo() {
        return adminLoginInfo;
    }

    public static String getDoctor() {
        return doctor;
    }

    public static String getPatient() {
        return patient;
    }

    public static String getLabResult() {
        return labResult;
    }

    public static String getMedReport() {
        return medReport;
    }

    public static String getAppointment() {
        return appointment;
    }

    public static String getAdmin() {
        return admin;
    }

}
