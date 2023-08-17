import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    Connection con;
    Database db;
    Statement st;

    public CreateTable() throws ClassNotFoundException, SQLException {
        db = new Database();
        con = db.getConnection();
        st = con.createStatement();

        createTable("Login_tbl", "CREATE TABLE Login_tbl(username varchar(200), password varchar(200));");
        createTable("UserRegistration_tbl", "CREATE TABLE UserRegistration_tbl(name varchar(200), citizenship BIGINT, mobile BIGINT);");
        createTable("CandidateRegistration_tbl", "CREATE TABLE CandidateRegistration_tbl(name varchar(200), party varchar(200));");
        createTable("VoteCount_tbl", "CREATE TABLE VoteCount_tbl(citizenship BIGINT, candidate varchar(200), party varchar(200));");

        System.out.println("All tables created successfully");
    }

    private void createTable(String tableName, String createSql) throws SQLException {
        if (!tableExists(tableName)) {
            st.executeUpdate(createSql);
            System.out.println(tableName + " table created");
        } else {
            System.out.println(tableName + " table already exists");
        }
    }

    private boolean tableExists(String tableName) throws SQLException {
        return con.getMetaData().getTables(null, null, tableName, null).next();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        new CreateTable();
    }
}
