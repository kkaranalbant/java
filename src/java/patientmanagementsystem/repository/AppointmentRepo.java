package patientmanagementsystem.repository;

import patientmanagementsystem.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import patientmanagementsystem.connection.DefPersonColumns;
import patientmanagementsystem.connection.DefTableNames;

public class AppointmentRepo extends Repo {

    private static AppointmentRepo appointmentRepo;

    private AppointmentRepo() throws SQLException, ClassNotFoundException {
        super();
    }

    public static AppointmentRepo getInstance() throws SQLException, ClassNotFoundException {
        if (appointmentRepo == null) {
            appointmentRepo = new AppointmentRepo();
        }
        return appointmentRepo;
    }

    public void createAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO " + DefTableNames.getAppointment() + " (HastaID, DoktorID, RandevuTarihi, RandevuSaati) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, appointment.getPatientId());
        preparedStatement.setLong(2, appointment.getDoctorId());
        preparedStatement.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentTime().toLocalDate()));
        preparedStatement.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime().toLocalTime()));
        setPreparedStatement(preparedStatement);
        getPreparedStatement().executeUpdate();
    }

    public Appointment getAppointmentById(Long id) throws SQLException {
        String sql = "SELECT * FROM " + DefTableNames.getAppointment() + " WHERE " + DefPersonColumns.getAppointmentId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, id);
        setPreparedStatement(preparedStatement);
        ResultSet resultSet = getPreparedStatement().executeQuery();
        if (resultSet.next()) {
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            LocalDateTime appointmentDateTime = resultSet.getTimestamp("RandevuTarihi").toLocalDateTime();
            return new Appointment(id, patientId, doctorId, appointmentDateTime);
        }
        return null;
    }

    public List<Appointment> getAppointmentByDoctorId(Long doctorId) throws SQLException {
        return getAppointmentByPersonId("DoktorID", doctorId);
    }
    
    public List<Appointment> getAppointmentByPatientId(Long doctorId) throws SQLException {
        return getAppointmentByPersonId("HastaID", doctorId);
    }
    
    private List <Appointment> getAppointmentByPersonId (String columnName , Long personId) throws SQLException {
         List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM " + DefTableNames.getAppointment() + " WHERE "+ columnName +" = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, personId);
        setPreparedStatement(preparedStatement);
        ResultSet resultSet = getPreparedStatement().executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong(DefPersonColumns.getAppointmentId());
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            LocalDateTime appointmentDateTime = resultSet.getTimestamp("RandevuTarihi").toLocalDateTime();
            LocalTime appointmentTime = resultSet.getTime("RandevuSaati").toLocalTime();
            appointmentDateTime = appointmentDateTime.withHour(appointmentTime.getHour()).withMinute(appointmentTime.getMinute());
            appointments.add(new Appointment(id, patientId, doctorId, appointmentDateTime));
        }
        return appointments;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM " + DefTableNames.getAppointment();
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        setPreparedStatement(preparedStatement);
        ResultSet resultSet = getPreparedStatement().executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong(DefPersonColumns.getAppointmentId());
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            LocalDate appointmentDate = resultSet.getDate("RandevuTarihi").toLocalDate() ;
            LocalTime appointmentTime = resultSet.getTime("RandevuSaati").toLocalTime(); 
            LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentDate, appointmentTime);
            appointments.add(new Appointment(id, patientId, doctorId, appointmentDateTime));
        }
        return appointments;
    }
    
    public boolean isSuitableTime (Long doctorId , LocalDateTime appointmentDateTime) throws SQLException {
        String query = "Select * from "+DefTableNames.getAppointment()+" where DoktorID = ? and RandevuTarihi = ? and RandevuSaati = ?" ;
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, doctorId);
        super.getPreparedStatement().setDate(2, java.sql.Date.valueOf(appointmentDateTime.toLocalDate()));
        super.getPreparedStatement().setTime(3, java.sql.Time.valueOf(appointmentDateTime.toLocalTime()));
        return !super.getPreparedStatement().executeQuery().next() ;
    }
    
    public Long getAppointmentIdByDoctorIdAndAppointmentDate (Long doctorId , LocalDateTime appointmentDateTime) throws SQLException {
        String query = "Select * from "+DefTableNames.getAppointment()+" where DoktorID = ? and RandevuTarihi = ? and RandevuSaati = ?" ;
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, doctorId);
        super.getPreparedStatement().setDate(2, java.sql.Date.valueOf(appointmentDateTime.toLocalDate()));
        super.getPreparedStatement().setTime(3, java.sql.Time.valueOf(appointmentDateTime.toLocalTime()));
        ResultSet appointmentResultSet = super.getPreparedStatement().executeQuery() ;
        Long id = null ;
        while (appointmentResultSet.next()) {
            id = appointmentResultSet.getLong(DefPersonColumns.getAppointmentId());
        }
        return id ;
    }

    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE " + DefTableNames.getAppointment() + " SET HastaID = ?, DoktorID = ?, RandevuTarihi = ?, RandevuSaati = ? WHERE " + DefPersonColumns.getAppointmentId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, appointment.getPatientId());
        preparedStatement.setLong(2, appointment.getDoctorId());
        preparedStatement.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentTime().toLocalDate()));
        preparedStatement.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime().toLocalTime()));
        preparedStatement.setLong(5, appointment.getId());
        setPreparedStatement(preparedStatement);
        getPreparedStatement().executeUpdate();
    }

    public void deleteAppointment(Long id) throws SQLException {
        String sql = "DELETE FROM " + DefTableNames.getAppointment() + " WHERE " + DefPersonColumns.getAppointmentId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, id);
        setPreparedStatement(preparedStatement);
        getPreparedStatement().executeUpdate();
    }
    
    public void deleteAllAppointmentsByDoctorId (Long doctorId) throws SQLException {
        deleteAllAppointmentsByPersonId(doctorId, "DoktorID");
    }
    
    public void deleteAllAppointmentsByPatientId (Long patientId) throws SQLException {
        deleteAllAppointmentsByPersonId(patientId, "HastaID");
    }
    
    private void deleteAllAppointmentsByPersonId (Long personId , String column) throws SQLException {
        String query = "delete from "+DefTableNames.getAppointment()+" where "+column+" = ?" ;
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, personId);
        super.getPreparedStatement().executeUpdate() ;
    }
}
