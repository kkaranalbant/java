<%-- 
    Document   : AdminAppointmentPanel
    Created on : May 18, 2024
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Appointment Panel</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 70%;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .appointment {
                margin-bottom: 20px;
                padding: 10px;
                border: 1px solid #ddd;
            }
            form {
                margin-bottom: 20px;
            }
            input, textarea, button {
                display: block;
                width: 100%;
                margin: 10px 0;
                padding: 10px;
                font-size: 16px;
            }
            textarea {
                resize: vertical;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Admin Appointment Panel</h1>

            <h2>Mevcut Randevular</h2>
            <div id="appointments">
                <pre><%= request.getAttribute("infoString")%></pre>
            </div>

            <h2>Yeni Randevu Ekle</h2>
            <form action="/PatientManagementSystem/admin-appointment" method="POST">
                <input type="text" name="doctorID" placeholder="Doktor ID" required>
                <input type="text" name="patientId" placeholder="Hasta ID" required>
                <input type="datetime-local" name="appointmentDateTime" placeholder="Randevu Tarihi ve Saati" required>
                <button type="submit">Randevu Ekle</button>
            </form>

            <h2>Randevu Sil</h2>
            <form action="/PatientManagementSystem/admin-appointment" method="POST">
                <input type="hidden" name="_method" value="DELETE">
                <input type="text" name="appointmentId" placeholder="Randevu ID" required>
                <button type="submit">Randevu Sil</button>
            </form>
        </div>
    </body>
</html>
