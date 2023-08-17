import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Database {
Connection con;

public Database() throws ClassNotFoundException, SQLException {
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String database = "Election_db";
String server = "//UTTAM\\SQLEXPRESS";
int port = 1433;
String url = "jdbc:sqlserver:" + server + ":" + port + ";databaseName=" + database
+ ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
con = DriverManager.getConnection(url);
System.out.println("connection obtained");
}

public Connection getConnection() {
	return con;
}


}
