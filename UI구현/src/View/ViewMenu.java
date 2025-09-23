package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ButtonDialog.QuantityDialog;
import Controller.Controller;
import Service.MemberService;
import Service.MenuService;
import VO.CartItem;
import VO.Menu;

public class ViewMenu {

	public Controller c = new Controller();// 메뉴 관련 서비스
	public Scanner sc = new Scanner(System.in);

	MemberService ms = new MemberService();
	public static boolean isGui = false;
	MenuService mn = new MenuService();

	public void start() {
		while (true) {
			System.out.println("\n======= 카페 키오스크 ========");
			System.out.println("1. 회원 로그인/등록");
			System.out.println("2. 메뉴 보기");
			System.out.println("3. 장바구니 보기");
			System.out.println("4. 결제하기");
			System.out.println("5. 종료");
			System.out.print("선택 > ");

			int choice = Integer.parseInt(sc.nextLine()); // 문자열을 정수로 변환한다.

			switch (choice) {
			case 1:
				memberLogin();
				break;
			case 2:
				showMenus();
				break;
			case 3:
				showCart();
				break;
			case 4:
				checkout();
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}

	public void memberLogin() {
		System.out.print("전화번호 입력 (회원 로그인/등록): ");
		String phone = sc.nextLine();

		if (phone.matches("\\d{11,11}")) { // match()를 활용해 숫자만 입력가능하고 11자리만 입력가능하게 설정
			try {
				c.memberLogin(phone); // Controller에 있는 로그인 메서드 호출
				System.out.println("환영합니다! 현재 도장 개수: " + c.getCurrentMember().getStampCount() + ", 쿠폰 보유: "
						+ (c.getCurrentMember().hasCoupon() ? "있음" : "없음"));
			} catch (Exception e) {
				System.err.println("회원 로그인/등록 중 오류 발생: " + e.getMessage());
			}

		} else {
			System.out.println("잘못된 전화번호 입력입니다!!");
		}

	}

	public void showMenus() {
		if (c.getCurrentMember() == null) {
			System.out.println("회원 로그인이 필요합니다. 메뉴를 보기 전에 1번을 선택해주세요.");
			return;
		}
		isGui = true;
		List<Menu> menus = null;
		try {
			menus = mn.getAllMenus();
		} catch (SQLException e) {
			System.err.println("주문 목록 불러오기 실패!");
			e.printStackTrace();
		}

		JFrame frame = new JFrame("=== 메뉴 목록 ===");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);

		// 레이아웃 설정
		frame.setLayout(new BorderLayout()); // BorderLatour 5개의 영역에 컴포넌트를 배치할수 있다.

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(6, 3, 10, 10));

		for (Menu m : menus) {
			JButton btnmenu = new JButton(m.toString());
			btnmenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					QuantityDialog qDialog = new QuantityDialog(menuPanel, m.getName());
					qDialog.setVisible(true);

					int quantity = qDialog.getQuantity();
					if (quantity > 0) {
						c.cart.addItem(m, quantity);
						JOptionPane.showMessageDialog(menuPanel, m.getName() + " x " + quantity + " 담았습니다!");
					} else {
						// 취소하거나 유효하지 않은 경우
						JOptionPane.showMessageDialog(menuPanel, "수량 선택이 취소되었습니다.");
					}
				}
			});

			menuPanel.add(btnmenu);
			frame.add(menuPanel, BorderLayout.CENTER);
		}

		// FlowLayout : 왼쪽에서 오른쪽으로 흐르듯이 컴포넌트를 배치한다.
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		JButton btnback = new JButton("뒤로가기");
		btnback.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		btnback.setFont(new Font("굴림", Font.BOLD, 16));

		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isGui = false;
				frame.dispose(); // 창 닫기

			}
		});

		bottomPanel.add(btnback);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		JButton btnPay = new JButton("결제하기");
		btnPay.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		btnPay.setFont(new Font("굴림", Font.BOLD, 16));
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 창 닫기
				checkout();
				frame.dispose();
				isGui = false;

			}
		});

		bottomPanel.add(btnPay);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);

	}

	public void showCart() {
		c.showCart();
		if (!c.cart.isEmpty()) {
			System.out.println("\n========== ++ 장바구니 ++ ==========");
			System.out.printf("%-5s %-15s %-8s %-8s\n", "No", "메뉴명", "수량", "금액");

			c.cart.showCart();
			int total = c.cart.getTotalPrice();
			System.out.println("------------------------------");
			System.out.printf("총 금액: %,d원\n", total);
			System.out.println("==============================\n");
		}
		System.out.print("\n삭제할 항목 번호 입력 (0: 취소): ");
		int input = Integer.parseInt(sc.nextLine());

		if (input == 0) {
			System.out.println("삭제 취소");
			return;
		}

		if (input < 1 || input > c.cart.getItems().size()) {
			System.out.println("잘못된 번호입니다.");
			return;
		}

		CartItem removed = c.cart.getItems().get(input - 1);
		c.cart.removeItem(input - 1);
		System.out.printf("[%s] 삭제되었습니다.\n", removed.getMenu().getName());
	}

	public void checkout() {

		if (c.getCurrentMember() == null) {
			memberLoginDisplay("회원 로그인이 필요합니다.");
			return;
		}

		if (c.cart.isEmpty()) {
			cartDisplay("장바구니가 비어 있습니다.");
			return;
		}

		String answer = null; // answer를 메서드 바깥에서 선언하여 범위 확장
		int result = 0; // result도 메서드 바깥에서 선언하여 범위 확장

		if (c.getCurrentMember().hasCoupon()) {
			if (!isGui) {
				System.out.print("쿠폰을 사용하시겠습니까? (y/n): ");
				answer = sc.nextLine();
			} else {

				result = JOptionPane.showConfirmDialog(null, "쿠폰을 사용하시겠습니까?", "쿠폰 사용", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {
					// 사용자가 창을 닫았을 때: 결제 중단

					return;

				}
			}

			if ((!isGui && "y".equalsIgnoreCase(answer)) || (isGui && result == JOptionPane.YES_OPTION)) {
				try {
					boolean used = ms.useCoupon(c.getCurrentMember().getPhoneNumber());

					if (used && c.cart.getTotalPrice() > 5000) {
						if (!isGui) {
							System.out.printf("@@ 쿠폰을 사용했습니다! 할인된 총 결제액은 %d입니다!\n", c.cart.getTotalPrice() - 5000);
						} else {
							JOptionPane.showMessageDialog(null,
									String.format("@@ 쿠폰 사용 완료! 할인된 총 결제액은 %,d원입니다!", c.cart.getTotalPrice() - 5000));
						}
					} else if (used && c.cart.getTotalPrice() <= 5000) {
						if (!isGui) {
							System.out.println("@@ 쿠폰을 사용했습니다! 무료 음료를 즐기세요!");
						} else {
							JOptionPane.showMessageDialog(null, "@@ 쿠폰 사용 완료! 무료 음료를 즐기세요!");
						}
					}

					c.checkout();

				} catch (SQLException e) {
					System.err.println("쿠폰 처리중 오류 발생!");
					e.printStackTrace();
				}

				return;

			} else {
				// 쿠폰 사용 안 함
				if (!isGui) {
					System.out.printf("결제 금액: %d원\n", c.cart.getTotalPrice());
					System.out.println("결제를 완료했습니다!! 감사합니다!");
				} else {
					JOptionPane.showMessageDialog(null, String.format("결제 금액: %,d원", c.cart.getTotalPrice()));
					JOptionPane.showMessageDialog(null, "결제를 완료했습니다! 감사합니다!");
				}

				c.checkout();
				return;
			}

		} else {
			// 쿠폰 없음
			if (!isGui) {
				System.out.printf("결제 금액: %d원\n", c.cart.getTotalPrice());
				System.out.println("결제를 완료했습니다!! 감사합니다!");
			} else {
				JOptionPane.showMessageDialog(null, String.format("결제 금액: %,d원", c.cart.getTotalPrice()));
				JOptionPane.showMessageDialog(null, "결제를 완료했습니다! 감사합니다!");
			}
			c.checkout();
		}
	}

	public void memberLoginDisplay(String msg) {

		System.out.println(msg);

	}

	public void cartDisplay(String msg) {

		System.out.println(msg);

	}
}