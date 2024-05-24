<%-- 
    Document   : PatientSelfProcessesPanel
    Created on : May 17, 2024, 9:08:52 PM
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hasta Bilgileri Paneli</title>
    </head>
    <body>
        <h1>Hasta Bilgi Güncelleme Formu</h1>
        <form action="/PatientManagementSystem/patient-self-update" method="post">
            <input type="hidden" name="_method" value="put">
            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name" value="<%= request.getAttribute("name")%>"><br><br>

            <label for="lastname">Lastname:</label><br>
            <input type="text" id="lastname" name="lastname" value="<%= request.getAttribute("lastname")%>"><br><br>

            <label for="birthDate">Birth Date:</label><br>
            <input type="date" id="birthDate" name="birthDate" value="<%= request.getAttribute("birthDate")%>"><br><br>

            <label for="gender">Gender:</label><br>
            <select id="gender" name="gender">
                <option value="MALE" <%= (String) request.getAttribute("gender") == "MALE" ? "selected" : ""%>>Erkek</option>
                <option value="FEMALE" <%= (String) request.getAttribute("gender") == "FEMALE" ? "selected" : ""%>>Kadin</option>
            </select><br><br>

            <label for="address">Address:</label><br>
            <textarea id="address" name="address" rows="4" cols="50"><%= request.getAttribute("address")%></textarea><br><br>

            <label for="phoneNumber">Phone Number:</label><br>
            <input type="text" id="phoneNumber" name="phoneNumber" value="<%= request.getAttribute("phoneNumber")%>"><br><br>

            <!-- Username and Password -->
            <input type="hidden" id="username" name="username" value="<%= request.getAttribute("username")%>">
            <input type="hidden" id="password" name="password" value="<%= request.getAttribute("password")%>">

            <input type="submit" value="Update Patient">
        </form>

        <form action="/PatientManagementSystem/patient-self-update" method="post" onsubmit="return confirm('Bu hesabı silmek istediğinizden emin misiniz?');">
            <input type="hidden" name="_method" value="delete">
            <input type="submit" value="Delete Account" style="background-color: red; color: white;">
        </form>

        <!-- Display infoString -->
        <div>
            <h2>Patient Information</h2>
            <pre>
                <%= request.getAttribute("infoString")%>
            </pre>
        </div>
    </body>
</html>
