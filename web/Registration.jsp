<%-- 
    Document   : Registration
    Created on : May 19, 2024, 9:30:30 PM
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
    </head>
    <body>
        <h1>Patient Registration Form</h1>
        <form action="/PatientManagementSystem/register" method="post">
            <label for="name">Name:</label><br>
            <input type="text" id="name" name="name"><br><br>

            <label for="lastname">Lastname:</label><br>
            <input type="text" id="lastname" name="lastname"><br><br>

            <label for="birthDate">Birth Date:</label><br>
            <input type="date" id="birthDate" name="birthDate"><br><br>

            <label for="gender">Gender:</label><br>
            <select id="gender" name="gender">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
            </select><br><br>

            <label for="address">Address:</label><br>
            <textarea id="address" name="address" rows="4" cols="50"></textarea><br><br>

            <label for="phoneNumber">Phone Number:</label><br>
            <input type="text" id="phoneNumber" name="phoneNumber"><br><br>
            
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username"><br><br>

            <label for="password">Password:</label><br>
            <input type="text" id="password" name="password"><br><br>

            <input type="submit" value="Register Patient">
        </form>
    </body>
</html>
