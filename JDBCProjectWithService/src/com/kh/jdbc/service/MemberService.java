package com.kh.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.kh.jdbc.common.JDBCTemplete.*;
import com.kh.jdbc.model.dao.MemberDao;
import com.kh.jdbc.model.vo.Member;
import com.kh.jdbc.view.MemberMenu;

/*
 * Service
 * 트랜잭션 관리와같은 비즈니스 로직을 처리하는 계층, Dao와 Contoller의 중간역할
 */
public class MemberService {
	private MemberDao md;

	public MemberService() {
		super();
		this.md = new MemberDao();
	}

	public int insertMember(Member m) {
		Connection conn = getConnection();

		int result = md.insertMember(m, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	public List<Member> selectMemberList() {
		Connection conn = getConnection();

		List<Member> list = md.selectMemberList(conn);
		close(conn);

		return list;
	}

	public int updateMember(Member m) {
		return 0;
	}

	public int deleteMember(Member m) {
		return 0;
	}

	public ArrayList<Member> nameSearchMember(String keyword) {
		Connection conn = getConnection();
		ArrayList<Member> list = md.nameSearchMember(keyword, conn);
		close(conn);
		return list;

	}

	public int insertAllMember(List<Member> members) {
		Connection conn = getConnection();

		int[] resultArr = md.insertAllMember(members, conn);

		int result = (resultArr != null) ? resultArr.length : 0;

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;

	}

	public int deleteAllMember(List<Member> members) {

		Connection conn = getConnection();

		int[] resultArr = md.deleteAllMember(members, conn);

		int result = (resultArr != null) ? resultArr.length : 0;
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

}
