package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	public static Connection makeConnection() {
		Connection con = null;
		constantVar constVar = new constantVar();
		
		try {
			String url = "jdbc:sqlserver://localhost:1433;databaseName=" + constVar.getDatabaseName();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, constVar.getUsername(), constVar.getPassword());
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
