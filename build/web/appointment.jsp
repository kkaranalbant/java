<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Book Appointment</title>
    </head>
    <body>

        <form id="appointmentForm" action="/PatientManagementSystem/book-appointment" method="post">
            <label for="hospitalDropdown">Hastane:</label>
            <select id="hospitalDropdown" name="hospital">
                <%
                    List<String> hospitals = (List<String>) request.getAttribute("hospital");
                    if (hospitals != null) {
                        for (String hospital : hospitals) {
                %>
                <option value="<%= hospital%>"><%= hospital%></option>
                <%
                        }
                    }
                %>
            </select>


            <label for="branchDropdown">Brans:</label>
            <select id="branchDropdown" name="branch">
                <%
                    List<String> branches = (List<String>) request.getAttribute("branch");
                    if (branches != null) {
                        for (String branch : branches) {
                %>
                <option value="<%= branch%>"><%= branch%></option>
                <%
                        }
                    }
                %>
            </select>

            <label for ="doctorDropdown">Doktor:</label>
            <select id="doctorDropdown" name="doctor" style="width: 75px;">
            </select>

            <label for ="dateDropdown">Tarih:</label>
            <select id="dateDropdown" name="date" style="width: 75px;">
            </select>

            <button type="button" onclick="document.getElementById('appointmentForm').submit()">Randevu Oluştur</button>
        </form>

        <script>
            function fetchDoctors() {
                var hospitalDropdown = document.getElementById("hospitalDropdown");
                var selectedHospital = hospitalDropdown.value;
                
                var branchDropdown = document.getElementById("branchDropdown");
                var selectedBranch = branchDropdown.value;

                // Endpoint'i oluştururken sadece seçili değerleri ekleyin
                var endpoint = "/PatientManagementSystem/get-doctor?hospital=" + encodeURIComponent(selectedHospital) + "&branch=" + encodeURIComponent(selectedBranch);

                // Fetch isteği
                fetch(endpoint)
                        .then(response => response.json()) // JSON yanıtını ayrıştır
                        .then(data => {
                            // Doctor dropdown'u seçeneklerini temizle
                            var doctorDropdown = document.getElementById("doctorDropdown");
                            doctorDropdown.innerHTML = ""; // Doctor dropdown'u temizle
                            alert(data);
                            data.forEach(doctor => {
                                var option = document.createElement("option");
                                option.text = doctor.name + " " + doctor.surname; // Doktorun adını option olarak ekle
                                option.value = doctor.id; // Doktorun ID'sini value olarak ekle
                                doctorDropdown.add(option); // Yeni option'u doctorDropdown'a ekle
                            });
                        })
                        .catch(error => {
                            console.error('Failed Fetch Operation : ', error);
                        });
            }

            function fetchSuitableDateAndTimes() {
                var doctorDropdown = document.getElementById("doctorDropdown");
                var selectedDoctor = doctorDropdown.value;

                var endpoint = "/PatientManagementSystem/date?doctor=" + encodeURIComponent(selectedDoctor);
                fetch(endpoint)
                        .then(response => response.json())
                        .then(data => {
                            var dateDropdown = document.getElementById('dateDropdown');
                            data.forEach(dateTime => { // forEach doğru yazılımıdır
                                var option = document.createElement('option');
                                option.text = "Tarih:" + dateTime.date + " Saat:" + dateTime.time;
                                option.value = dateTime.id;
                                dateDropdown.add(option);
                            });
                        })
                        .catch(error => {
                            console.error('Failed Fetch Operation : ', error);
                        });
            }



// Sayfa tamamen yüklendiğinde hastane ve branş dropdown listeleri tetiklensin
            document.addEventListener("DOMContentLoaded", fetchDoctors);

            var hospitalDropdown = document.getElementById("hospitalDropdown");
            hospitalDropdown.addEventListener("change", fetchDoctors); // Seçim değiştiğinde doktorları getir

            var branchDropdown = document.getElementById("branchDropdown");
            branchDropdown.addEventListener("change", fetchDoctors); // Seçim değiştiğinde doktorları getir

            var doctorDropdown = document.getElementById("doctorDropdown");
            doctorDropdown.addEventListener("change", fetchSuitableDateAndTimes);

        </script>

        <form id="deleteAppointmentForm" action="/PatientManagementSystem/book-appointment" method="post">
            <input type="hidden" name="_method" value="delete">
            <label for="appointmentId">Randevu ID:</label>
            <input type="text" id="appointmentId" name="appointmentId" required>
            <button type="submit">Randevu Sil</button>
        </form>


        <h2>Randevu Bilgileri</h2>
        <%
            String appointmentInfo = (String) request.getAttribute("appointmentInfo");
            if (appointmentInfo != null && !appointmentInfo.isEmpty()) {
        %>
        <pre><%= appointmentInfo%></pre>
        <%
        } else {
        %>
        <p>Henuz randevu bilgisi bulunmamaktadir.</p>
        <%
            }
        %>

    </body>
</html>
