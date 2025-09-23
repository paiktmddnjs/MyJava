package com.kh.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 공통 템플릿(매번 반복적으로 작성될 코드르 메서드 정의)

public class JDBCTemplete {

	// 모든 메서드를 전부 static메서드로 만듬

	// 1. Connection 객체 생성 후 해당 Connection객체 반환
	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");

			conn.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}

//2. Connection 객체를 전달받아 상태를 확인후 commit하는 메서드
	public static void commit(Connection conn) {

		try {
			if (conn != null && !conn.isClosed()) {

				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 4. Statement 관련 객체를 전달받아 반납시켜주는 메서드
	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 5. ResultSet관련 객체를 전달받아 반납시켜주는 메서드
	public static void close(ResultSet rset) {

		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
