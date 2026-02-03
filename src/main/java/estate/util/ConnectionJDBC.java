package estate.util;

import java.sql.*;
public class ConnectionJDBC {
	private static final String USER = "root";
	private static final String PASSWORD = "12345";
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
