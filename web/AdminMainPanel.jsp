<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Ana Paneli</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .container {
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .button {
                display: block;
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                text-align: center;
                background-color: #007BFF;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Admin Ana Paneli</h1>
            <form action="/PatientManagementSystem/admin-appointment" method="GET">
                <button type="submit">Randevu Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/doctor" method="GET">
                <button type="submit">Doktor Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/patient" method="GET">
                <button type="submit">Hasta Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/admin-report" method="GET">
                <button type="submit">Tibbi Raporlar Islemleri</button>
            </form>
            <form action="/PatientManagementSystem/login" method="GET">
                <button type="submit">Cikis Yap</button>
            </form>
        </div>
    </body>
</html>