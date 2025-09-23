package Practice16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Run {

	public static Map<String, Integer> makeMap() {
		Map<String, Integer> products = new HashMap<>();

		// 상품의 이름과 값을 products에 추가해 보세요.
		products.put("kim", 1);
		products.put("lee", 2);
		products.put("lwe", 4);

		return products;
	}

	public static void main(String[] args) {

		Map<String, Integer> result = makeMap();
		System.out.println(result); // 출력 확인
	}
}
