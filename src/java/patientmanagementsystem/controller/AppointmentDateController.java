/* * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template */package patientmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import patientmanagementsystem.dao.AppointmentDateTimeDao;
import patientmanagementsystem.service.AppointmentManager;

/**
 * * * @author kaan
 */
@WebServlet(name = "AppointmentDateController", urlPatterns = {"/date"})
public class AppointmentDateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("doctor"));
        try {
            AppointmentManager appointmentManager = AppointmentManager.getInstance();
            List<AppointmentDateTimeDao> appointmentDates = appointmentManager.getAllSuitableAppointmentTimes(id);
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = mapper.writeValueAsString(appointmentDates);
            response.getWriter().write(json);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Getting suitable appointment dates from from doctor data.";
    }
}
