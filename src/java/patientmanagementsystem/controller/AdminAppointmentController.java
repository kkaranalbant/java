/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.model.Appointment;
import patientmanagementsystem.service.AppointmentManager;
import patientmanagementsystem.service.DoctorManager;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "AdminAppointmentController", urlPatterns = {"/admin-appointment"})
public class AdminAppointmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (!role.equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        } else {
            StringBuilder sb = new StringBuilder();
            try {
                List<Appointment> appointments = AppointmentManager.getInstance().getAllAppointments();
                for (Appointment appointment : appointments) {
                    DoctorInfoDao doctorInfo = DoctorManager.getInstance().showDoctor(new ElementIdDao(appointment.getDoctorId()));
                    PatientInfoDao patientInfo = PatientManager.getInstance().showPatient(new ElementIdDao(appointment.getPatientId()));
                    sb.append("Randevu ID: ").append(appointment.getId())
                            .append("\nDoktor ID: ").append(appointment.getDoctorId())
                            .append("\nDoktor: ").append(doctorInfo.getName()).append(" ")
                            .append(doctorInfo.getLastname()).append("\nHasta ID: ")
                            .append(appointment.getPatientId()).append("\nHasta: ")
                            .append(patientInfo.getName()).append(" ")
                            .append(patientInfo.getLastname())
                            .append("\nRandevu Tarihi: ").append(appointment.getAppointmentTime().toLocalDate().toString())
                            .append("\nRandevu Saati: ").append(appointment.getAppointmentTime().toLocalTime().toString())
                            .append("\n\n");
                }
                String infoString = sb.toString();
                request.setAttribute("infoString", infoString);
                request.getRequestDispatcher("/AdminAppointmentPanel.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(ex.getMessage());
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (!role.equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
        if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
            return;
        }
        Long doctorId = Long.valueOf(request.getParameter("doctorID"));
        Long patientId = Long.valueOf(request.getParameter("patientId"));
        LocalDateTime appointmentDateTime = LocalDateTime.parse(request.getParameter("appointmentDateTime"));
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Successful");
        try {
            AppointmentManager.getInstance().bookAppointmentForAdmin(doctorId, patientId, appointmentDateTime);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (!role.equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
        try {
            Long appointmentId = Long.valueOf(request.getParameter("appointmentId"));
            AppointmentManager.getInstance().removeAppointment(appointmentId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful");
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        } catch (FailureDeletingException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
