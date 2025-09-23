package com.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kh.jdbc.common.JDBCTemplete.*;
import com.kh.jdbc.model.vo.Member;

public class MemberDao {
	public int insertMember(Member m, Connection conn) {
		// insertMember -> insert -> 처리된 행 수(int)

		// 필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// 아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 회원목록을 반환하는 메서드
	public ArrayList<Member> selectMemberList(Connection conn) {
		// select문(여러개) -> ResultSet -> ArrayList담기

		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<>(); // 비어있는 상태

		PreparedStatement pstmt = null;

		String sql = "SELECT * FROM MEMBER";

		try {
			// 완성된 sql
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
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

			// 반복문이 끝난시점
			// list -> 비어있거나/ 데이터가 들어있거나
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// Member객체 m을 통해서 update sql을 전달하는 메서드
	public int updateMember(Member m) {
		// update문 -> 처리된 행 수 : int -> 트랜잭션처리

		// 필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		// 실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "UPDATE MEMBER SET EMAIL=?, PHONE=?, ADDRESS=?, HOBBY=? WHERE USER_ID=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);

			// 아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getEmail());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getHobby());
			pstmt.setString(5, m.getUserId());

			result = pstmt.executeUpdate();

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

	// Member객체 m을 통해서 delete sql을 전달하는 메서드
	public int deleteMember(Member m) {
		// delete문 -> 처리된 행 수 : int -> 트랜잭션처리

		// 필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		// 실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "DELETE FROM MEMBER WHERE USER_ID=? AND USER_PWD=?";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);

			// 아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());

			result = pstmt.executeUpdate();

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

	public ArrayList<Member> nameSearchMember(String keyword, Connection conn) {

		ArrayList<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = "SELECT * FROM MEMBER WHERE USER_NAME LIKE ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
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

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int[] insertAllMember(List<Member> members, Connection conn) {

		int result[] = null;
		PreparedStatement pstmt = null;

		// 실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// 아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);

			for (Member m1 : members) {
				pstmt.setString(1, m1.getUserId());
				pstmt.setString(2, m1.getUserPwd());
				pstmt.setString(3, m1.getUserName());
				pstmt.setString(4, m1.getGender());
				pstmt.setInt(5, m1.getAge());
				pstmt.setString(6, m1.getEmail());
				pstmt.setString(7, m1.getPhone());
				pstmt.setString(8, m1.getAddress());
				pstmt.setString(9, m1.getHobby());

				pstmt.addBatch();
			}

			result = pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int[] deleteAllMember(List<Member> members, Connection conn) {

		int result[] = null;
		PreparedStatement pstmt = null;

		// 실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "DELETE FROM MEMBER WHERE USER_ID = ?";
		try {
			// 아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);

			for (Member m1 : members) {
				pstmt.setString(1, m1.getUserId());

				pstmt.addBatch();
			}

			result = pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}
}
