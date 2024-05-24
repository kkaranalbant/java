<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Page</title>
    </head>
    <body>
        <h1>Doctor Registration Form</h1>
        <form action="/PatientManagementSystem/doctor" method="post">
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

            <label for="branch">Branch:</label><br>
            <input type="text" id="branch" name="branch"><br><br>

            <label for="workingPlace">Working Place:</label><br>
            <input type="text" id="workingPlace" name="workingPlace"><br><br>
            
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username"><br><br>

            <label for="password">Password:</label><br>
            <input type="text" id="password" name="password"><br><br>

            <input type="submit" value="Submit">
        </form>

        <h1>Doctor Updating Form</h1>
        <form action="/PatientManagementSystem/doctor" method="post">
            <input type="hidden" name="_method" value="put">
            <label for="id">Doctor ID:</label><br>
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

            <label for="branch">Branch:</label><br>
            <input type="text" id="branch" name="branch"><br><br>

            <label for="workingPlace">Working Place:</label><br>
            <input type="text" id="workingPlace" name="workingPlace"><br><br>
            
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username"><br><br>

            <label for="password">Password:</label><br>
            <input type="text" id="password" name="password"><br><br>

            <input type="submit" value="Update Doctor">
        </form>

        <h1>Doctor Deleting Form</h1>
        <form action="/PatientManagementSystem/doctor" method="post">
            <input type="hidden" name="_method" value="delete">
            <label for="doctorId">Doctor ID:</label><br>
            <input type="text" id="doctorId" name="id"><br><br>
            <input type="submit" value="Delete Doctor">
        </form>
    </body>
</html>
