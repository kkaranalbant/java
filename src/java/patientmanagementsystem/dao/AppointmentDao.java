package patientmanagementsystem.dao ;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class AppointmentDao {

    @JsonProperty("patientName")
    private String patientName;

    @JsonProperty("patientLastname")
    private String patientLastname;

    @JsonProperty("doctorName")
    private String doctorName;

    @JsonProperty("doctorLastname")
    private String doctorLastname;

    @JsonProperty("appointmentTime")
    private LocalDateTime appointmentTime;

    public AppointmentDao() {
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientLastname() {
        return patientLastname;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorLastname() {
        return doctorLastname;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
}
