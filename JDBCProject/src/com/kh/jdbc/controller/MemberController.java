package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.vo.Member;
import com.kh.jdbc.view.MemberMenu;
import com.kh.model.dao.MemberDao;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 객체
 * 				해당 메서드로 전달된 데이터를 가공한 수 dao로 전달하여 기능을 수행.
 * 				dao로부터 반환받은 결과에 따라서 성공/살해 여부를 판단해서 응답화면 결정 -> View 호출
 */
public class MemberController {

	private MemberDao md = new MemberDao();
	/*
	 * 사용자의 추가요청을 처리하는 메서드 userId ~ hobby : 사용자가 입력한 정보를 매개변수로 받음
	 * 
	 */

	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, String hobby) {
		// view로부터 전달받은 값을 바로 dao로 전달x

		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);

		int result = md.insertMember(m);
		if (result > 0) {
			new MemberMenu().displaySuccess("회원 등록 성공!");
		} else {
			new MemberMenu().displayFail("회원 등록 실패 ㅠㅠ");
		}
	}

	// 회원을 모두 조회
	public void selectMemberAll() {
		List<Member> list = md.selectMemberList();

		if (list.isEmpty()) {
			new MemberMenu().displayNoData("회원목옥 조회결과가 없습니다!!");

		} else {

			new MemberMenu().displayList(list, "회원 목록");
		}

	}

	// userId, email, phone, address, hobby를 전달받아
	// Member를 수정하는 메서드

	public int updateMember(String userId, String email, String phone, String address, String hobby) {

		Member m = new Member();

		m.setUserId(userId);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);

		int result = md.updateMember(m);

		if (result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보를 수정하였습니다.");

		} else {
			new MemberMenu().displayFail("수정에 실패하였습니다...");
		}
		return result;
	}

	public int exitMember(String userId, String userPwd) {

		Member m = new Member();

		m.setUserId(userId);
		m.setUserPwd(userPwd);

		int result = md.exitMember(m);

		if (result > 0) {

			new MemberMenu().displaySuccess("성공적으로 회원 탈퇴하였습니다! ");
		} else {
			new MemberMenu().displayFail("탈퇴에 실패하였습니다!.");
		}
		return result;
	}
}