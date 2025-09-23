// OrderDAO.java
package DAO;

import java.sql.*;

import javax.swing.JOptionPane;

import VO.Order;
import View.ViewMenu;

public class OrderDao {

	// 주문 저장
	public int insertOrder(Connection conn, Order order) {

		String sql = "INSERT INTO orders (order_id, phone, order_date, show_order,total_price, status) VALUES (?, ?, SYSDATE ,? , ?, ?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, order.getPhone());
			pstmt.setString(3, order.getShowOrder());
			pstmt.setDouble(4, order.getTotalPrice());
			pstmt.setString(5, order.getStatus());
	
			int result = pstmt.executeUpdate();
			pstmt.close();
			
			return result;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	// 주문 번호를 출력한다.
	public int selectOrderId(Connection conn, String phone) {
		String sql = "SELECT order_id FROM (SELECT order_id FROM orders WHERE phone = ? ORDER BY order_id DESC) WHERE ROWNUM = 1";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int orderId = rs.getInt("order_id");
					if (!ViewMenu.isGui) {
						System.out.println("\n주문 번호: " + orderId);
					} else {
						JOptionPane.showMessageDialog(null, String.format("주문 번호: " + orderId));

					}
					return orderId;
				}
			}
		} catch (SQLException e) {
			System.err.println("주문번호 불러오기 실패!");
			e.printStackTrace();
		}
		return -1;
	}

	

}
