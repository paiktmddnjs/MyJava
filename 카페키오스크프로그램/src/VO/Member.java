package VO;

public class Member {
	private String phoneNumber;
	private int stampCount;
	private boolean hasCoupon;

	// 생성자, getter/setter 생략

	public Member() {

		super();
	}

	public Member(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Member(String phoneNumber, int stampCount, boolean hasCoupon) {
		this.phoneNumber = phoneNumber;
		this.stampCount = stampCount;
		this.hasCoupon = hasCoupon;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getStampCount() {
		return stampCount;
	}

	public boolean hasCoupon() {
		return hasCoupon;
	}

	public void addStamp() {
		if (!hasCoupon) {
			stampCount++;
			if (stampCount >= 8) {
				stampCount = 0;
				hasCoupon = true;
				System.out.println("🎉 도장 8개 모음! 무료 음료 쿠폰이 지급되었습니다!");
			}
		}
	}


	public void setStampCount(int stampCount) {
		this.stampCount = stampCount;
	}

	public void setHasCoupon(boolean hasCoupon) {
		this.hasCoupon = hasCoupon;
	}

	@Override
	public String toString() {
		return "회원번호: " + phoneNumber + ", 도장: " + stampCount + ", 쿠폰 보유: " + hasCoupon;
	}

	// DB용 변환
	public String getHasCouponAsString() {
		return hasCoupon ? "Y" : "N";
	}

	public void setHasCouponFromString(String s) {
		this.hasCoupon = "Y".equals(s);
	}
}
