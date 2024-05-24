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
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.DoctorUpdatingDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.service.DoctorManager;
import patientmanagementsystem.service.LoginManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "DoctorSelfProcessesController", urlPatterns = {"/doctor-self-processes"})
public class DoctorSelfProcessesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("DOCTOR")) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            Long id = LoginManager.getInstance().getDoctorIdByUsernameAndPassword(username, password);
            DoctorInfoDao info = DoctorManager.getInstance().showDoctor(new ElementIdDao(id));
            StringBuilder sb = new StringBuilder();
            sb.append("Id: ").append(info.getId()).append("\nIsim: ")
                    .append(info.getName()).append("\nSoyisim: ")
                    .append(info.getLastname()).append("\nDogum Tarihi: ")
                    .append(info.getBirthDate().toString()).append("\nCinsiyet: ")
                    .append(info.getGender().name()).append("\nAdres:")
                    .append(info.getAddress()).append("\nTelefon no: ")
                    .append(info.getPhoneNumber()).append("\nKullanici adi: ")
                    .append(username).append("\nSifre: ").append(password);
            String infoString = sb.toString();
            request.setAttribute("infoString", infoString);
            request.getRequestDispatcher("/DoctorSelfProcessesPanel.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("DOCTOR")) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            Long id = LoginManager.getInstance().getDoctorIdByUsernameAndPassword(username, password);
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String usernameNew = request.getParameter("username");
            String passwordNew = request.getParameter("password");
            DoctorInfoDao info = DoctorManager.getInstance().showDoctor(new ElementIdDao(id));
            DoctorUpdatingDao doctorUpdatingDao = new DoctorUpdatingDao(id, name, lastname, birthDate, gender, address, phoneNumber, info.getBranch(), info.getWorkingPlace(), usernameNew, passwordNew);
            DoctorManager doctorManager = DoctorManager.getInstance();
            doctorManager.updateDoctor(doctorUpdatingDao);
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | FailureUpdatingException | ClassNotFoundException ex) {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
