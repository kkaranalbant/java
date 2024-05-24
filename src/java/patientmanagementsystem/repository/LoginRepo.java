package patientmanagementsystem.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import patientmanagementsystem.connection.DefTableNames;

public class LoginRepo extends Repo {

    private static LoginRepo loginRepo;

    private LoginRepo() throws SQLException, ClassNotFoundException {
    }

    public static LoginRepo getInstance() throws SQLException, ClassNotFoundException {
        if (loginRepo == null) {
            loginRepo = new LoginRepo();
        }
        return loginRepo;
    }

    public void createDoctorLoginInfo(Long doctorID, String username, String password) throws SQLException {
        String query = "INSERT INTO DoctorLoginInfo (DoctorID, Username, Password) VALUES (?, ?, ?)";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, doctorID);
        super.getPreparedStatement().setString(2, username);
        super.getPreparedStatement().setString(3, password);
        super.getPreparedStatement().executeUpdate();

    }

    public void createPatientLoginInfo(Long patientID, String username, String password) throws SQLException {
        String query = "INSERT INTO HastaLoginInfo (HastaID, Username, Password) VALUES (?, ?, ?)";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, patientID);
        super.getPreparedStatement().setString(2, username);
        super.getPreparedStatement().setString(3, password);
        super.getPreparedStatement().executeUpdate();
    }

    public boolean doesDoctorExist(String username, String password) throws SQLException {
        String query = "SELECT * FROM DoctorLoginInfo WHERE Username = ? AND Password = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        return super.getPreparedStatement().executeQuery().next();
    }

    public boolean doesPatientExist(String username, String password) throws SQLException {
        String query = "SELECT * FROM HastaLoginInfo WHERE Username = ? AND Password = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        return super.getPreparedStatement().executeQuery().next();
    }

    public boolean doesAdminExist(String username, String password) throws SQLException {
        String query = "SELECT * FROM YoneticiLoginInfo WHERE Username = ? AND Password = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        return super.getPreparedStatement().executeQuery().next();
    }

    public Long getPatientIdByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM HastaLoginInfo WHERE Username = ? AND Password = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        ResultSet patientResultSet = super.getPreparedStatement().executeQuery();
        patientResultSet.next();
        return patientResultSet.getLong("HastaID");
    }

    public Long getDoctorIdByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM DoctorLoginInfo WHERE Username = ? AND Password = ?";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        ResultSet patientResultSet = super.getPreparedStatement().executeQuery();
        patientResultSet.next();
        return patientResultSet.getLong("DoctorID");
    }

    public void removePatientByPatientId(Long patientId) throws SQLException {
        removePersonByPersonId(DefTableNames.getPatientLoginInfo(), "HastaID", patientId);
    }

    public void removeDoctorByDoctorId(Long patientId) throws SQLException {
        removePersonByPersonId(DefTableNames.getDoctorLoginInfo(), "DoctorID", patientId);
    }

    private void removePersonByPersonId(String tableName, String columnName, Long personId) throws SQLException {
        String query = "Delete from " + tableName + " where " + columnName + " = ? ";
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setLong(1, personId);
        super.getPreparedStatement().executeUpdate();
    }
    
    public void updatePatientByPatientId (Long patientId , String username , String password) throws SQLException {
        updatePersonByPersonId(DefTableNames.getPatientLoginInfo(), "HastaID", patientId, username, password);
    }
    
    public void updateDoctorByDoctorId (Long doctorId , String username , String password) throws SQLException {
        updatePersonByPersonId(DefTableNames.getDoctorLoginInfo(), "DoctorID", doctorId, username, password);
    }
    
    private void updatePersonByPersonId (String tableName , String columnName , Long personId , String username , String password) throws SQLException {
        String query = "Update "+tableName+" set Username = ? , Password = ? where "+columnName+" = ?" ;
        super.setPreparedStatement(super.getConnection().prepareStatement(query));
        super.getPreparedStatement().setString(1, username);
        super.getPreparedStatement().setString(2, password);
        super.getPreparedStatement().setLong(3, personId);
        super.getPreparedStatement().executeUpdate() ;
    }

}
