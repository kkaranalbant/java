<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Report Form</title>
    </head>
    <body>
        
        <h2>Mevcut Tibbi Raporlar</h2>
        <pre>${infoString}</pre>
        
        <h2>Medical Report Creating Form</h2>
        <form method="post" action="/PatientManagementSystem/doctor-report">
            <label for="name">Patient Name:</label><br>
            <input type="text" id="name" name="name"><br>
            <label for="lastname">Patient Lastname:</label><br>
            <input type="text" id="lastname" name="lastname"><br>
            <label for="context">Report Context:</label><br>
            <textarea id="context" name="context"></textarea><br>
            <label for="url">Lab Result URL:</label><br>
            <input type="text" id="url" name="url"><br><br>
            <input type="submit" value="Submit">
        </form>

        <h2>Medical Report Update Form</h2>
        <form action="/PatientManagementSystem/doctor-report"  method="post" >
            <input type="hidden" name="_method" value="put">
            <label for="id">Report ID:</label><br>
            <input type="text" id="id" name="id"><br>
            <label for="context">Report Context:</label><br>
            <textarea id="context" name="context"></textarea><br>
            <label for="url">Lab Result URL:</label><br>
            <input type="text" id="url" name="labResultUrl"><br><br>
            <input type="submit" value="Update">
        </form>

        <h2>Delete Medical Report</h2>
        <form action="/PatientManagementSystem/doctor-report" method="post">
            <input type="hidden" name="_method" value="delete">
            <label for="reportId">Report ID:</label><br>
            <input type="text" id="reportId" name="id"><br><br>
            <input type="submit" value="Delete Medical Report">
        </form>
    </body>
</html>
