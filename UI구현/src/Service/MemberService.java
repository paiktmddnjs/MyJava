package Service;

import static Common.DBUtil.close;
import static Common.DBUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.MemberDao;
import DAO.OrderDao;
import VO.CartItem;
import VO.Member;
import VO.Order;
import View.ViewMenu;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	private OrderDao orderDAO = new OrderDao();

	// 전화번호로 회원 조회, 없으면 신규 회원 생성 후 반환
	public Member getOrCreateMember(String phoneNumber) throws SQLException {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member == null) { // 신규 회원 생성
			member = new Member(phoneNumber, 0, false);
			int result = memberDao.insertMember(conn, member);

			if (result > 0) {
				conn.commit();

			} else {
				conn.rollback();
				System.out.println("회원 삽입 실패!");

			}
		}
		close(conn);
		return member;
	}

	// 회원에게 도장 추가, 8개 도장 시 쿠폰 지급 및 도장 초기화
	public void stampMember(String phoneNumber, int quantity) throws SQLException {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member != null && !member.hasCoupon()) {

			member.setStampCount(member.getStampCount() + quantity);

			if (member.getStampCount() >= 8) {
				member.setStampCount(0);
				member.setHasCoupon(true);
				if (!ViewMenu.isGui) {
					System.out.println(" *^.^* 도장 8개 모음! 무료 음료 쿠폰이 지급되었습니다! ");
				} else
					JOptionPane.showMessageDialog(null, "*^.^* 도장 8개 모음! 무료 음료 쿠폰이 지급되었습니다!");
			}
			if (!ViewMenu.isGui) {
				System.out.println("도장이 추가되었습니다!");
			} else {
				JOptionPane.showMessageDialog(null, "도장이 추가되었습니다!");
			}
			int result = memberDao.updateMember(conn, member);

			if (result > 0) {
				conn.commit();

			} else {
				conn.rollback();
				System.out.println("\n회원 정보(쿠폰 지급) 업데이트 실패!");
			}

			close(conn);
		}

	}

	// 쿠폰 사용 (사용 성공 시 true 반환, 실패 시 false)
	public boolean useCoupon(String phoneNumber) throws SQLException {

		Connection conn = getConnection();

		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member != null) {

			member.setHasCoupon(false);
			int result = memberDao.updateMember(conn, member);

			if (result > 0) {
				conn.commit();
				System.out.println("회원 정보(갱신) 업데이트 성공!");
			} else {
				conn.rollback();
				System.out.println("회원 정보(갱신) 업데이트 실패!");
			}

			close(conn);
			return true;

		} else {

			close(conn);
			return false;

		}
	}

	// 주문자의 주문 내역을 판매자가 확인할수 있도록 주문 테이블에 저장한다.
	public void processPayment(String phone, List<CartItem> items) throws SQLException {

		Connection conn = getConnection();

		int total = 0;
		for (CartItem item : items) {
			total += item.getTotalPrice();
		}
		// 주문 객체 생성
		Order order = new Order(phone, new java.util.Date(), items.toString(), total, "주문완료");

		// 주문 DB 저장

		int result = orderDAO.insertOrder(conn, order);

		if (result > 0) {
			conn.commit();

		} else {
			conn.rollback();
			System.out.println("\n주문 저장 실패!");
		}
		close(conn);

	}

	// 주문 완료시 주문번호 출력
	public void selectOrderId(String phone) throws SQLException {

		Connection conn = getConnection();

		int result = orderDAO.selectOrderId(conn, phone);

		if (result != -1) {
			conn.commit();

		} else {
			conn.rollback();
			System.out.println("주문 번호 출력 실패!");
		}
		close(conn);

	}

	// JFrame 객체에서 결제버튼을 클릭했을때 해당 정보를 orderDAO에 전달한다.

}
