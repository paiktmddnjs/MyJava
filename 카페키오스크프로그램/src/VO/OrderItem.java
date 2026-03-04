package VO;

//OrderItem.java

public class OrderItem {
	private int orderItemId;
	private String phone; // 주문자 전화번호 (FK)
	private String menuName;
	private int quantity;
	private double price;

	public OrderItem(int orderItemId, String phone, String menuName, int quantity, double price) {
		this.orderItemId = orderItemId;
		this.phone = phone;
		this.menuName = menuName;
		this.quantity = quantity;
		this.price = price;
	}

	// getter / setter
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
