package Common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static final String DB_PROPERTIES_FILE = "src/resources/Properties";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle JDBC 드라이버를 로드
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결(Connection 객체)을 만들어서 외부에 제공하는 역할을 하는 메소드
	public static Connection getConnection() {
		Properties properties = new Properties();
		Connection conn = null;

		try (FileInputStream fis = new FileInputStream(DB_PROPERTIES_FILE)) {
			properties.load(fis);

			String url = properties.getProperty("db.url");
			String user = properties.getProperty("db.user");
			String password = properties.getProperty("db.password");

			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (IOException | SQLException e) {
			System.err.println("DB 연결 실패: " + e.getMessage());
			e.printStackTrace();
		}

		return conn;
	}

	// 데이터베이스나 파일 등의 자원을 사용 후 반드시 닫기 위해 사용하는 메소드
	public static void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
