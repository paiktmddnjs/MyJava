package VO;

public class Menu {
	private int id;
	private String name;
	private String category; // "커피", "음료", "디저트"
	private int price;

	public Menu(int id, String name, String category, int price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category
		= category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return id + ". " + name + " (" + category + ") - " + price + "원";
	}
}
