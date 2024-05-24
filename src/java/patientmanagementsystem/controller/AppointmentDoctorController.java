/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import patientmanagementsystem.dao.DoctorAppointmentDao;
import patientmanagementsystem.service.DoctorManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "AppointmentDoctorController", urlPatterns = {"/get-doctor"})
public class AppointmentDoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hospital = request.getParameter("hospital");
        String branch = request.getParameter("branch");

        // Adım 1: Parametre Kontrolü
        if (hospital == null || branch == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error: Hospital and branch parameters are required.");
            return;
        }

        try {
            DoctorManager doctorManager = DoctorManager.getInstance() ;
            List<DoctorAppointmentDao> results = doctorManager.getAllDoctorNamesAndLastnamesByWorkPlaceAndBranch(hospital, branch);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(results);
            response.getWriter().write(json);

        } catch (SQLException | ClassNotFoundException ex) {
            // Adım 4: Hata Kontrolü
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }


    @Override
    public String getServletInfo() {
        return "Servlet for getting doctors by hospital and branch data";
    }
}
