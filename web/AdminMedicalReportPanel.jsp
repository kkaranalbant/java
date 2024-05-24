<%-- 
    Document   : AdminMedicalReportPanel
    Created on : May 18, 2024
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Medical Report Panel</title>
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
            .report {
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
            <h1>Admin Medical Report Panel</h1>

            <h2>Mevcut Tıbbi Raporlar</h2>
            <div id="reports">
                <pre><%= request.getAttribute("infoString") %></pre>
            </div>

            <h2>Yeni Tibbi Rapor Ekle</h2>
            <form action="/PatientManagementSystem/admin-report" method="POST">
                <input type="text" name="patientId" placeholder="Hasta ID" required>
                <input type="text" name="doctorId" placeholder="Doktor ID" required>
                <textarea name="context" placeholder="Rapor Icerigi" required></textarea>
                <input type="text" name="url" placeholder="Laboratuvar Sonucu URL" required>
                <button type="submit">Rapor Ekle</button>
            </form>

            <h2>Tibbi Rapor Guncelle</h2>
            <form action="/PatientManagementSystem/admin-report" method="POST">
                <input type="hidden" name="_method" value="PUT">
                <input type="text" name="reportId" placeholder="Rapor ID" required>
                <input type="text" name="patientId" placeholder="Hasta ID" required>
                <input type="text" name="doctorId" placeholder="Doktor ID" required>
                <textarea name="context" placeholder="Rapor İçeriği" required></textarea>
                <input type="text" name="url" placeholder="Laboratuvar Sonucu URL">
                <button type="submit">Rapor Guncelle</button>
            </form>

            <h2>Tibbi Rapor Sil</h2>
            <form action="/PatientManagementSystem/admin-report" method="POST">
                <input type="hidden" name="_method" value="DELETE">
                <input type="text" name="patientId" placeholder="Hasta ID" required>
                <input type="text" name="doctorId" placeholder="Doktor ID" required>
                <button type="submit">Rapor Sil</button>
            </form>
        </div>
    </body>
</html>

