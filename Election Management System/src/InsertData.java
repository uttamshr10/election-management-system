import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
	Connection con;
	Database db;
	Statement st;
	ResultSet set;
	public InsertData() throws ClassNotFoundException, SQLException {
		db = new Database();
		con = db.getConnection();
		st = con.createStatement();
		// Change this for administrator login...
		String insert = "INSERT INTO Login_tbl Values('Uttam', 'Admin@10')";
		st.executeUpdate(insert);
		System.out.println("Data inserted successfully.");
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new InsertData();
	}

}
