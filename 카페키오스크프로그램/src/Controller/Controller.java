package Controller;

import java.sql.SQLException;

import java.util.Scanner;

import javax.swing.JOptionPane;

import Service.MemberService;

import VO.Cart;

import VO.Member;

import View.ViewMenu;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 객체
 *              해당 메서드로 전달된 데이터를 가공한 후 dao로 전달하여 기능을 수행.
 *              dao로부터 반환받은 결과에 따라서 성공/실해 여부를 판단해서 응답화면 결정 -> View호출
 * */
public class Controller {
	private Member currentMember;

	private MemberService ms = new MemberService();
	public Cart cart = new Cart();
	Scanner sc = new Scanner(System.in);

	public Controller() {
		super();
	}

	// ViewMenu에서 받은 PhoneNumber을 이용해 신규 회원을 등록하고 도장 개수와 쿠폰 여부를 출력
	public Member memberLogin(String phoneNumber) throws SQLException {

		currentMember = ms.getOrCreateMember(phoneNumber);
		cart.clearCart();
		return currentMember;
	}

	// 주문자의 장바구니의 메뉴 여부를 판단한다
	public void showCart() {

		if (cart.isEmpty()) {
			new ViewMenu().cartDisplay("장바구니가 비어 있습니다.");

		}

	}

	// 결제할때 쿠폰을 사용하거나 결제시 주문번호를 만들어주는 기능을 구현하였다.
	public void checkout() {

		try {
			ms.stampMember(currentMember.getPhoneNumber(), cart.TotalQuantity());
			// 업데이트된 새 도장 개수, 쿠폰 여부 다시 불러오기 (DB 반영 위해)
			currentMember = ms.getOrCreateMember(currentMember.getPhoneNumber());
			ms.processPayment(currentMember.getPhoneNumber(), cart.getItems());
			ms.selectOrderId(currentMember.getPhoneNumber());
		} catch (SQLException e) {
			System.err.println("도장 추가에 실패했습니다!");
			e.printStackTrace();
		}

		if (ViewMenu.isGui == false) {
			System.out.println(currentMember);
		} else if (ViewMenu.isGui == true) {
			JOptionPane.showMessageDialog(null, currentMember);
		}

		cart.clearCart();

	}

	public Member getCurrentMember() {
		return currentMember;
	}

}
