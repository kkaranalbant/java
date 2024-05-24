package patientmanagementsystem.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import patientmanagementsystem.connection.DefTableNames;
import patientmanagementsystem.model.LabResult;

public class LabResultRepo extends Repo {

    private static LabResultRepo labResultRepo;

    private LabResultRepo() throws SQLException, ClassNotFoundException {
    }

    public static LabResultRepo getInstance() throws SQLException, ClassNotFoundException {
        if (labResultRepo == null) {
            labResultRepo = new LabResultRepo();
        }
        return labResultRepo;
    }

    public LabResult getLabResultById(Long id) throws SQLException {
        String query = "SELECT * FROM " + DefTableNames.getLabResult() + " WHERE SonucID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, id);
        ResultSet labResultResultSet = super.getPreparedStatement().executeQuery();
        return createLabResultFromResultSet(labResultResultSet);
    }

    public List<LabResult> getAllLabResults() throws SQLException {
        String query = "SELECT * FROM " + DefTableNames.getLabResult();
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        ResultSet labResultResultSet = super.getPreparedStatement().executeQuery();
        return createLabResultListFromResultSet(labResultResultSet);
    }

    public LabResult getLabResultByMedicalReportIdAndUrl(Long medicalReportId, String url) throws SQLException {
        String query = "SELECT * FROM " + DefTableNames.getLabResult() + " WHERE RaporID = ? and SonucURL = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, medicalReportId);
        super.getPreparedStatement().setString(2, url);
        ResultSet labResultResultSet = super.getPreparedStatement().executeQuery();
        return createLabResultFromResultSet(labResultResultSet);
    }
    
    public LabResult getLabResultByMedicalReportId (Long medicalReportId) throws SQLException {
        String query = "Select * from from "+DefTableNames.getLabResult()+" where RaporID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, medicalReportId);
        return createLabResultFromResultSet(super.getPreparedStatement().executeQuery());
    }

    public int saveLabResult(LabResult labResult) throws SQLException {
        String query = "INSERT INTO " + DefTableNames.getLabResult() + "(RaporID, SonucURL) VALUES (?, ?)";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, labResult.getMedicalReportId());
        super.getPreparedStatement().setString(2, labResult.getContextUrl());
        return super.getPreparedStatement().executeUpdate();
    }

    public int updateLabResult(LabResult labResult) throws SQLException {
        String query = "UPDATE " + DefTableNames.getLabResult() + " SET RaporID=?, SonucURL=? WHERE SonucID=?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, labResult.getMedicalReportId());
        super.getPreparedStatement().setString(2, labResult.getContextUrl());
        super.getPreparedStatement().setLong(3, labResult.getId());
        return super.getPreparedStatement().executeUpdate();
    }

    public int deleteLabResultByReportIdId(Long reportId) throws SQLException {
        String query = "DELETE FROM " + DefTableNames.getLabResult() + " WHERE RaporID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, reportId);
        return super.getPreparedStatement().executeUpdate();
    }
    
    public int deleteLabResultByResultId(Long resultId) throws SQLException {
        String query = "DELETE FROM " + DefTableNames.getLabResult() + " WHERE SonucID = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, resultId);
        return super.getPreparedStatement().executeUpdate();
    }

    private LabResult createLabResultFromResultSet(ResultSet labResultResultSet) throws SQLException {
        LabResult labResult = null;
        while (labResultResultSet.next()) {
            Long id = labResultResultSet.getLong("SonucID");
            Long medicalReportId = labResultResultSet.getLong("RaporID");
            String url = labResultResultSet.getString("SonucURL");
            labResult = new LabResult(medicalReportId, url);
            labResult.setId(id);
        }
        return labResult;
    }

    private List<LabResult> createLabResultListFromResultSet(ResultSet resultSet) throws SQLException {
        List<LabResult> results = new ArrayList();
        while (resultSet.next()) {
            Long id = resultSet.getLong("SonucID");
            Long medicalReportId = resultSet.getLong("RaporID");
            String url = resultSet.getString("SonucURL");
            LabResult labResult = new LabResult(id, medicalReportId, url);
            results.add(labResult);
        }
        return results;
    }
}
