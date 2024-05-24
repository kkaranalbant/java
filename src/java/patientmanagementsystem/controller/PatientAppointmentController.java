/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import patientmanagementsystem.dao.AppointmentDateTimeDao;
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.exception.InvalidAppointmentException;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.model.Appointment;
import patientmanagementsystem.service.AppointmentManager;
import patientmanagementsystem.service.DoctorManager;
import patientmanagementsystem.service.LoginManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "PatientAppointmentController", urlPatterns = {"/book-appointment"})
public class PatientAppointmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("ADMIN") || request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed !");
        }
        try {
            DoctorManager doctorManager = DoctorManager.getInstance();
            List<String> hospitals = doctorManager.getAllWorkPlaces();
            List<String> branches = doctorManager.getAllBranchs();

            request.setAttribute("hospital", hospitals);
            request.setAttribute("branch", branches);

            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            Long id = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
            List<Appointment> appointments = AppointmentManager.getInstance().getAppointmentsByPatientId(id);
            StringBuilder sb = new StringBuilder();
            for (Appointment appointment : appointments) {
                DoctorInfoDao doctorInfo = DoctorManager.getInstance().showDoctor(new ElementIdDao(appointment.getDoctorId()));
                sb.append("Doktor Isim: ").append(doctorInfo.getName())
                        .append("\nDoktor Soyisim: ").append(doctorInfo.getLastname())
                        .append("\nRandevu Tarihi: ").append(appointment.getAppointmentTime().toLocalDate().toString())
                        .append("\nRandevu Saati: ").append(appointment.getAppointmentTime().toLocalTime().toString())
                        .append("\nRandevu ID: ").append(appointment.getId());
            }
            String appointmentInfo = sb.toString();
            request.setAttribute("appointmentInfo", appointmentInfo);
            request.getRequestDispatcher("/appointment.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
            return;
        }
        if (request.getSession(false).getAttribute("role").equals("ADMIN") || request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed !");
            return;
        }

        try {
            AppointmentManager appointmentManager = AppointmentManager.getInstance();
            Long doctorId = Long.valueOf(request.getParameter("doctor"));
            List<AppointmentDateTimeDao> appointments = AppointmentManager.getInstance().getAllSuitableAppointmentTimes(doctorId);
            Long appointmentDateId = Long.valueOf(request.getParameter("date"));
            String appointmentDateString = null;
            String appointmentTimeString = null;
            for (AppointmentDateTimeDao appointment : appointments) {
                if (appointment.getId().longValue() == appointmentDateId.longValue()) {
                    appointmentDateString = appointment.getDate();
                    appointmentTimeString = appointment.getTime();
                }
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
            LocalDate localDate = LocalDate.parse(appointmentDateString, dateFormatter);
            LocalTime localTime = LocalTime.parse(appointmentTimeString, timeFormatter);
            LocalDateTime appointmentDateTime = LocalDateTime.of(localDate, localTime);
            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            Long patientId = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
            appointmentManager.bookAppointment(patientId, doctorId, appointmentDateTime);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Appointment booked successfully!");
        } catch (IOException | SQLException | ClassNotFoundException | InvalidAppointmentException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("ADMIN") || request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed !");
            return;
        }
        Long appointmentId = Long.valueOf(request.getParameter("appointmentId"));
        try {
            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            Long id = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
            List<Appointment> appointments = AppointmentManager.getInstance().getAppointmentsByPatientId(id);
            for (Appointment appointment : appointments) {
                if (appointment.getId().longValue() == appointmentId.longValue()) {
                    AppointmentManager.getInstance().removeAppointment(appointmentId);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Successful");
                    return;
                }
            }
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid id");
        } catch (SQLException | ClassNotFoundException | InvalidIdException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Patient Appointment Controller";
    }// </editor-fold>

}
