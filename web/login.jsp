<%-- 
    Document   : login
    Created on : May 11, 2024, 4:38:29 PM
    Author     : kaan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Form</title>
</head>
<body>
<h2>Login Form</h2>

<%-- Hata mesajını göstermek için bir JavaScript kodu ekliyoruz --%>
<% if (request.getAttribute("errorMessage") != null) { %>
    <script type="text/javascript">
        alert("<%= request.getAttribute("errorMessage") %>");
    </script>
<% } %>

<form method='post' action='/PatientManagementSystem/login'> <!-- Form, /login endpoint'ine POST isteği gönderir -->
    Username: <input type='text' name='username'><br>
    Password: <input type='password' name='password'><br>
    <input type='submit' value='Login'>
</form>
</body>
</html>
