package com.kh.model.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kh.jdbc.model.vo.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Dao(Data Access Object) : db에 직접적으로 접근해서 사용자의 요청에 맞게 sql문을 실행 후 결과를 반환
 * 
 */

public class MemberDao {

	public int insertMember(Member m) {

		// db에 member를 insert
		// JDBC를 사용
		/*
		 * JDBC 사용순서 1) JDBC Driver등록 :JDBC내의 대양한 인터페이스가 특정 DBMS가 제공하는 클래스에 의해서 구현됨.(사용할
		 * 수 있또록 등록) 2) Connection 생성 : 연결하고자하는 db정보를 햐덩 dbn와 연결할 수 있는 객체 생성 3)
		 * [Prepared]Statement : Connection객페를 이용해서 생성, sql문을 실행하고 결과를 받아주는 객체 4) sql문을
		 * 전댈해서 실행 : Statement객체를 이영ㅎ새ㅓ sql문 실행 5) 결과받기 -> select문 실행 -> ResultSet객체(조회된
		 * 결과를 담아줌) -> DML(insert, update, delete) -> 처리된 결과를 담아줌 -> 6_1 6_1)
		 * ResultSet객체에 담겨있는 데이터를 하나씩 추축ㄹ햐서 자바메모이에 담아 사용 6_2) 틀냊개션 처리(성공했으면 commit,
		 * 실해댔다면 rollback 실행) 7) 다 사용한 JDCB객체를 반드시 반납(close - : 생성의 역순)
		 * 
		 */

		// insertMember - > insert -> 처리된 행수 (int)

		// 필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?,?,?,?,?,?,?,?,?,SYSDATE)";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false); // 몯느 SQL 실행 시 자동으로 커밋되게 한다!.

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserId());
			// Member 객체인 m에서 UserId를 반환하는 getUserId() 함수를 이용해 값을 가져와서
			// 첫번쨰 위치에 넣는다는 의미이다.

			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());

			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			result = pstmt.executeUpdate();
			conn.commit();

			System.out.println(result + "명의 회원이 추가되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생");
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원목록을 반환하는 메서드
	public ArrayList<Member> selectMemberList() {
		// select문(여러개) -> ResultSet -> ArrayList담기

		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "Select * from member";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);

			// 완성된 sql
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) { // 결과에 데이터가 있으면 한 행씩 순차적으로 읽기 위해 이동한다.
				Member m = new Member();
				m.setUserNo(rset.getInt("USER_NO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserPwd(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getTimestamp("ENROLL_DATE").toLocalDateTime());

				list.add(m);
			}

			// 반복문이 끝난 시점
			// list -> 비어있거나 / 데이터가 들어있거나
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list;

	}

	// Member 객체 m을 통해서 update sql를 전달하는 메서드
	public int updateMember(Member m) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// update문 -> 처리된 행 수 : int-> 트랜잭션처리
		String sql = "UPDATE MEMBER SET EMAIL = ?, PHONE=?, ADDRESS=?, HOBBY=? WHERE USER_ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false); // 몯느 SQL 실행 시 자동으로 커밋되게 한다!.

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserId());
			// Member 객체인 m에서 UserId를 반환하는 getUserId() 함수를 이용해 값을 가져와서
			// 첫번쨰 위치에 넣는다는 의미이다.

			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getHobby());
			pstmt.setString(6, m.getUserId());

			result = pstmt.executeUpdate();
			conn.commit();

			System.out.println(result + "명의 회원이 추가되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생");
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int exitMember(Member m) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM MEMBER WHERE USER_ID=? AND USER_PWD=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false); // 몯느 SQL 실행 시 자동으로 커밋되게 한다!.

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			// Member 객체인 m에서 UserId를 반환하는 getUserId() 함수를 이용해 값을 가져와서
			// 첫번쨰 위치에 넣는다는 의미이다.

			result = pstmt.executeUpdate();
			conn.commit();
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
