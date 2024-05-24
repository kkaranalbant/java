/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import patientmanagementsystem.dao.PatientRegisterDao;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "RegistrationController", urlPatterns = {"/register"})
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (role == null) {
            request.getRequestDispatcher("/Registration.jsp").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (role == null) {
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            PatientRegisterDao patientRegisterDao = new PatientRegisterDao(name, lastname, birthDate, gender, address, phoneNumber, username, password);

            try {
                PatientManager patientManager = PatientManager.getInstance();
                patientManager.savePatient(patientRegisterDao);
                response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_OK);
                response.getWriter().write("Successful !");
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(ex.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
