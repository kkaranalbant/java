package patientmanagementsystem.repository;

import patientmanagementsystem.model.MedicalReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import patientmanagementsystem.connection.DefPersonColumns;
import patientmanagementsystem.connection.DefTableNames;

public class MedicalReportRepo extends Repo {

    private static MedicalReportRepo medicalReportRepo;

    private MedicalReportRepo() throws SQLException, ClassNotFoundException {
        super();
    }

    public static MedicalReportRepo getInstance() throws SQLException, ClassNotFoundException {
        if (medicalReportRepo == null) {
            medicalReportRepo = new MedicalReportRepo();
        }
        return medicalReportRepo;
    }

    public void createMedicalReport(MedicalReport medicalReport) throws SQLException {
        String sql = "INSERT INTO " + DefTableNames.getMedReport() + " (RaporTarihi, RaporIcerigi, HastaID, DoktorID) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(medicalReport.getPublicationDate()));
        preparedStatement.setString(2, medicalReport.getContext());
        preparedStatement.setLong(3, medicalReport.getPatientId());
        preparedStatement.setLong(4, medicalReport.getDoctorId());
        setPreparedStatement(preparedStatement);
        getPreparedStatement().executeUpdate();
    }

    public MedicalReport getMedicalReportById(Long id) throws SQLException {
        String sql = "SELECT * FROM " + DefTableNames.getMedReport() + " WHERE " + DefPersonColumns.getMedReportId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, id);
        setPreparedStatement(preparedStatement);
        ResultSet resultSet = getPreparedStatement().executeQuery();
        if (resultSet.next()) {
            LocalDate publicationDate = resultSet.getDate("RaporTarihi").toLocalDate();
            String context = resultSet.getString("RaporIcerigi");
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            return new MedicalReport(id, publicationDate, context, patientId, doctorId);
        }
        return null;
    }
    

    public List<MedicalReport> getAllMedicalReports() throws SQLException {
        List<MedicalReport> medicalReports = new ArrayList<>();
        String sql = "SELECT * FROM " + DefTableNames.getMedReport();
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        setPreparedStatement(preparedStatement);
        ResultSet resultSet = getPreparedStatement().executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("RaporID");
            LocalDate publicationDate = resultSet.getDate("RaporTarihi").toLocalDate();
            String context = resultSet.getString("RaporIcerigi");
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            medicalReports.add(new MedicalReport(id, publicationDate, context, patientId, doctorId));
        }
        return medicalReports;
    }

    public List<MedicalReport> getAllMedicalReportsByPatientId(Long patientId) throws SQLException {
        String query = "Select * from " + DefTableNames.getMedReport() + " where HastaID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, patientId);
        ResultSet reportSet = super.getPreparedStatement().executeQuery();
        return extractResultSet(reportSet);
    }

    public List<MedicalReport> getAllMedicalReportsByDoctorId(Long doctorId) throws SQLException {
        String query = "Select * from " + DefTableNames.getMedReport() + " where HastaID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, doctorId);
        ResultSet reportSet = super.getPreparedStatement().executeQuery();
        return extractResultSet(reportSet);
    }

    private List<MedicalReport> extractResultSet(ResultSet resultSet) throws SQLException {
        List<MedicalReport> reports = new ArrayList();
        while (resultSet.next()) {
            Long id = resultSet.getLong("RaporID");
            LocalDate date = resultSet.getDate("RaporTarihi").toLocalDate();
            String context = resultSet.getString("RaporIcerigi");
            Long patientId = resultSet.getLong("HastaID");
            Long doctorId = resultSet.getLong("DoktorID");
            MedicalReport medicalReport = new MedicalReport(id, date, context, patientId, doctorId);
            reports.add(medicalReport);
        }
        return reports;
    }

    public void updateMedicalReport(MedicalReport medicalReport) throws SQLException {
        String sql = "UPDATE " + DefTableNames.getMedReport() + " SET RaporTarihi = ?, RaporIcerigi = ? WHERE " + DefPersonColumns.getMedReportId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(medicalReport.getPublicationDate()));
        preparedStatement.setString(2, medicalReport.getContext());
        preparedStatement.setLong(3, medicalReport.getId());
        setPreparedStatement(preparedStatement);
        getPreparedStatement().executeUpdate();
    }

    public int deleteMedicalReport(Long id) throws SQLException {
        String sql = "DELETE FROM " + DefTableNames.getMedReport() + " WHERE " + DefPersonColumns.getMedReportId() + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, id);
        setPreparedStatement(preparedStatement);
        return getPreparedStatement().executeUpdate();
    }

    public void deleteAllMedicalReportsByPatientId(Long patientId) throws SQLException {
        deleteAllMedicalReportsByPersonId(patientId, "HastaID");
    }

    public void deleteAllMedicalReportsByDoctorId(Long doctorId) throws SQLException {
        deleteAllMedicalReportsByPersonId(doctorId, "DoktorID");
    }

    private void deleteAllMedicalReportsByPersonId(Long personId, String column) throws SQLException {
        String query = "Delete from " + DefTableNames.getMedReport() + " where " + column + " = ?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personId);
        super.setPreparedStatement(preparedStatement);
        super.getPreparedStatement().executeUpdate();
    }
}
