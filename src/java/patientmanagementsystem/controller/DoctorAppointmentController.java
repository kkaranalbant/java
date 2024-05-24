/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.model.Appointment;
import patientmanagementsystem.service.AppointmentManager;
import patientmanagementsystem.service.LoginManager;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "DoctorAppointmentController", urlPatterns = {"/doctor-appointment"})
public class DoctorAppointmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!(((String) request.getSession(false).getAttribute("role")).equals("DOCTOR"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            StringBuilder sb = new StringBuilder();
            Long id = LoginManager.getInstance().getDoctorIdByUsernameAndPassword(username, password);
            List<Appointment> appointments = AppointmentManager.getInstance().getAppointmentsByDoctorId(id);
            for (Appointment appointment : appointments) {
                PatientInfoDao patientInfo = PatientManager.getInstance().showPatient(new ElementIdDao(appointment.getPatientId()));
                sb.append("Randevu ID: ").append(appointment.getId())
                        .append("\nHasta Isim: ").append(patientInfo.getName())
                        .append("\nHasta Soyisim: ").append(patientInfo.getLastname())
                        .append("\nRandevu Tarihi: ").append(appointment.getAppointmentTime().toLocalDate().toString())
                        .append("\nRandevu Saati: ").append(appointment.getAppointmentTime().toLocalTime().toString());
            }
            String infoString = sb.toString();
            request.setAttribute("infoString", infoString);
            request.getRequestDispatcher("/DoctorAppointmentPanel.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!(((String) request.getSession(false).getAttribute("role")).equals("DOCTOR"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
        if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(((String) request.getSession(false).getAttribute("role")).equals("DOCTOR"))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            Long id = LoginManager.getInstance().getDoctorIdByUsernameAndPassword(username, password);
            Long appointmentId = Long.valueOf(request.getParameter("appointmentID"));
            List<Appointment> appointments = AppointmentManager.getInstance().getAppointmentsByDoctorId(id);
            for (Appointment appointment : appointments) {
                if (appointmentId.longValue() == appointment.getId().longValue()) {
                    AppointmentManager.getInstance().removeAppointment(appointmentId);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Successful");
                    return;
                }
            }
        } catch (SQLException | ClassNotFoundException | InvalidIdException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
