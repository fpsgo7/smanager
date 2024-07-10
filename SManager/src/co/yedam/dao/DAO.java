package co.yedam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DB connection 기능
 */
//Data Access Object == DAO
public class DAO {
	Connection conn = null;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "jsp";// 유저아이디
		String pass = "jsp";// 유저 비번
		try {
			Class.forName(driver);// 드라이버가 있는지 확인한다.
			conn = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return conn;
	}
}
