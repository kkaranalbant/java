<%-- 
    Document   : DoctorSelfProcessesPanel
    Created on : May 18, 2024, 1:23:09 AM
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Self Processes Panel</title>
    </head>
    <body>
        <h1>Doctor Self Processes</h1>
        <form action="doctor-self-processes" method="POST">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br><br>

            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" required><br><br>

            <label for="birthDate">Birth Date:</label>
            <input type="date" id="birthDate" name="birthDate" required><br><br>

            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="MALE">Erkek</option>
                <option value="FEMALE">Kadin</option>
            </select><br><br>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required><br><br>

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required><br><br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <button type="submit">Update</button>
        </form>
        
        <%
            String infoString = (String) request.getAttribute("infoString");
            if (infoString != null) {
        %>
            <h2>Current Information</h2>
            <pre><%= infoString %></pre>
        <%
            }
        %>
    </body>
</html>
