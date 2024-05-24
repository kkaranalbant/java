/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import patientmanagementsystem.dao.AppointmentDateTimeDao;
import patientmanagementsystem.exception.InvalidAppointmentException;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.model.Appointment;
import patientmanagementsystem.repository.AppointmentRepo;

/**
 *
 * @author kaan
 */
public class AppointmentManager {

    private static AppointmentManager appointmentManager;

    private AppointmentRepo appointmentRepo;
    private DoctorManager doctorManager;
    private LoginManager loginManager;

    private LocalDateTime originDateTime;
    private LocalDateTime boundDateTime;

    private static short dayBound;
    private static byte rangeMinute;
    private static byte startingHour;
    private static byte finishHour;

    static {
        dayBound = 7;
        rangeMinute = 10;
        startingHour = 9;
        finishHour = 17;
    }

    private AppointmentManager() throws SQLException, ClassNotFoundException {
        appointmentRepo = AppointmentRepo.getInstance();
        doctorManager = DoctorManager.getInstance();
        loginManager = LoginManager.getInstance();
    }

    public static AppointmentManager getInstance() throws SQLException, ClassNotFoundException {
        if (appointmentManager == null) {
            appointmentManager = new AppointmentManager();
        }
        return appointmentManager;
    }

    public void bookAppointment(Long patientId , Long doctorId ,LocalDateTime appointmentDateTime) throws SQLException, InvalidAppointmentException {
        Appointment appointment = new Appointment(patientId, doctorId, appointmentDateTime);
        appointmentRepo.createAppointment(appointment);
    }

    public void bookAppointmentForAdmin(Long doctorId, Long patientId, LocalDateTime appointmentDateTime) throws SQLException {
        if (appointmentRepo.isSuitableTime(doctorId, appointmentDateTime)) {
            Appointment appointment = new Appointment(patientId, doctorId, appointmentDateTime);
            appointmentRepo.createAppointment(appointment);
        } else {
            Long id = appointmentRepo.getAppointmentIdByDoctorIdAndAppointmentDate(doctorId, appointmentDateTime);
            Appointment appointment = appointmentRepo.getAppointmentById(id);
            appointment.setAppointmentTime(appointmentDateTime);
            appointment.setPatientId(patientId);
            appointmentRepo.updateAppointment(appointment);
        }
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentRepo.getAllAppointments();
    }

    public List<AppointmentDateTimeDao> getAllSuitableAppointmentTimes(Long id) throws SQLException {
        List<Appointment> appointments = appointmentRepo.getAppointmentByDoctorId(id);
        List<LocalDateTime> reservedTimes = extractAppointmentDates(appointments);
        List<LocalDateTime> allTimes = getAllAppointmentDateTimes();
        List<AppointmentDateTimeDao> result = new ArrayList();
        Long dateId = (long) 1;
        if (reservedTimes.isEmpty()) {
            for (LocalDateTime allTime : allTimes) {
                String year = Integer.toString(allTime.getYear());
                String month = Integer.toString(allTime.getMonthValue());
                String day = Integer.toString(allTime.getDayOfMonth());
                String hour = Integer.toString(allTime.getHour());
                String minute = Integer.toString(allTime.getMinute());
                result.add(new AppointmentDateTimeDao(dateId, year + "-" + month + "-" + day, hour + ":" + minute));
                dateId++;
            }
            return result;
        }
        for (LocalDateTime allTime : allTimes) {
            for (LocalDateTime reservedTime : reservedTimes) {
                if (!(allTime.getYear() == reservedTime.getYear() && allTime.getMonth() == reservedTime.getMonth() && allTime.getDayOfMonth() == reservedTime.getDayOfMonth() && allTime.getHour() == reservedTime.getHour() && allTime.getMinute() == reservedTime.getMinute())) {
                    String year = Integer.toString(allTime.getYear());
                    String month = Integer.toString(allTime.getMonthValue());
                    String day = Integer.toString(allTime.getDayOfMonth());
                    String hour = Integer.toString(allTime.getHour());
                    String minute = Integer.toString(allTime.getMinute());
                    result.add(new AppointmentDateTimeDao(dateId, year + "-" + month + "-" + day, hour + ":" + minute));
                    dateId++;
                    break;
                }
            }
        }
        return result;
    }

    private List<LocalDateTime> extractAppointmentDates(List<Appointment> appointments) {
        List<LocalDateTime> appointmentDateTimes = new ArrayList();
        for (Appointment appointment : appointments) {
            appointmentDateTimes.add(appointment.getAppointmentTime());
        }
        return appointmentDateTimes;
    }

    private List<LocalDateTime> getAllAppointmentDateTimes() {
        originDateTime = LocalDateTime.now();
        boundDateTime = originDateTime.plusDays(dayBound);
        List<LocalDateTime> appointmentDateTimes = new LinkedList<>();
        LocalDateTime currentDateTime = originDateTime;
        while (currentDateTime.isBefore(boundDateTime)) {
            if (currentDateTime.getHour() >= startingHour && currentDateTime.getHour() < finishHour) {
                for (int minute = 0; minute < 60; minute += rangeMinute) {
                    appointmentDateTimes.add(currentDateTime.withMinute(minute).withSecond(0));
                }
            }

            currentDateTime = currentDateTime.plusHours(1);
        }

        return appointmentDateTimes;
    }

    public List<Appointment> getAppointmentsByPatientId(Long patientId) throws SQLException {
        return appointmentRepo.getAppointmentByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) throws SQLException {
        return appointmentRepo.getAppointmentByDoctorId(doctorId);
    }

    public void removeAllAppointmentsByDoctorId(Long doctorId) throws SQLException, InvalidIdException {
        appointmentRepo.deleteAllAppointmentsByDoctorId(doctorId);
    }

    public void removeAllAppointmentsByPatientId(Long patientId) throws SQLException, InvalidIdException {
        appointmentRepo.deleteAllAppointmentsByPatientId(patientId);
    }

    public void removeAppointment(Long appointmentId) throws SQLException, InvalidIdException {
        appointmentRepo.deleteAppointment(appointmentId);
    }

}
