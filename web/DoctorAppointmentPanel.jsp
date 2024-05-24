<%-- 
    Document   : DoctorAppointmentPanel
    Created on : May 18, 2024, 11:38:15 AM
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Appointments</title>
    </head>
    <body>
        <h1>Doctor Appointments</h1>
        
        <%
            String infoString = (String) request.getAttribute("infoString");
            if (infoString != null && !infoString.isEmpty()) {
        %>
            <h2>Appointments:</h2>
            <pre><%= infoString %></pre>
        <%
            } else {
        %>
            <p>No appointments found.</p>
        <%
            }
        %>
        
        <h2>Delete an Appointment</h2>
        <form action="doctor-appointment" method="POST">
            <input type="hidden" name="_method" value="DELETE">
            <label for="appointmentID">Appointment ID:</label>
            <input type="text" id="appointmentID" name="appointmentID" required><br><br>
            <button type="submit">Delete Appointment</button>
        </form>
    </body>
</html>
