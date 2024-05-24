<%-- 
    Document   : DoctorPanel
    Created on : May 18, 2024
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Ana Paneli</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 50%;
                margin: 0 auto;
                text-align: center;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            button {
                display: block;
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                font-size: 16px;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Doctor Panel</h1>
            <form action="/PatientManagementSystem/doctor-self-processes" method="GET">
                <button type="submit">Kullanici Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/doctor-appointment" method="GET">
                <button type="submit">Randevu Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/doctor-report" method="GET">
                <button type="submit">Tibbi Rapor Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/login" method="GET">
                <button type="submit">Cikis Yap</button>
            </form>
        </div>
    </body>
</html>
