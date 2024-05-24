<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Page</title>
    </head>
    <body>
        <h1>Patient Registration Form</h1>
        <form action="/PatientManagementSystem/patient" method="post">
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


        <h1>Patient Update Form</h1>
        <form action="/PatientManagementSystem/patient" method="post">
            <input type="hidden" name="_method" value="put">
            <label for="id">Patient ID:</label><br>
            <input type="text" id="id" name="id"><br><br>

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

            <input type="submit" value="Update Patient">
        </form>


        <h1>Patient Deleting Form</h1>
        <form action="/PatientManagementSystem/patient" method="post">
            <input type="hidden" name="_method" value="delete">
            <label for="id">Person ID:</label><br>
            <input type="text" id="id" name="id"><br><br>

            <input type="submit" value="Delete Person">
        </form>

    </body>
</html>
