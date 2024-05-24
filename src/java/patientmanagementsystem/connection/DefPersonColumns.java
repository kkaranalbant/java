/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.connection;

/**
 *
 * @author kaan
 */
public class DefPersonColumns {
    private static String patientId;
    private static String doctorId;
    private static String adminId;
    private static String labId;
    private static String medReportId;
    private static String appointmentId;
    private static String name ;
    private static String lastname;
    
    
    static {
        patientId = "HastaID" ;
        doctorId = "DoktorID" ;
        adminId = "YoneticiID" ;
        labId = "SonucID" ;
        medReportId = "RaporID" ;
        appointmentId = "RandevuID" ;
        name = "Ad" ;
        lastname = "Soyad" ;
    }

    public static String getPatientId() {
        return patientId;
    }

    public static String getDoctorId() {
        return doctorId;
    }

    public static String getAdminId() {
        return adminId;
    }

    public static String getLabId() {
        return labId;
    }

    public static String getMedReportId() {
        return medReportId;
    }

    public static String getAppointmentId() {
        return appointmentId;
    }

    public static String getName() {
        return name;
    }

    public static String getLastname() {
        return lastname;
    }
    
    
    
    
}
