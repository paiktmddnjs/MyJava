package Practice16;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapExample {

	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<>();

		map.put("백승원", 20);
		map.put("이지수", 22);
		map.put("김수민", 23);
		map.put("최은혁", 16);

		Set<String> keyset = map.keySet();

		for (String st : keyset) {

			if (map.get(st) > 20) {
				System.out.println("키 : " + st + ", 값 : " + map.get(st));

			}
		}

		System.out.println("===================");

		Set<Map.Entry<String, Integer>> entryset = map.entrySet();

		for (Map.Entry<String, Integer> entry : entryset) {

			if (entry.getValue() > 20) {
				System.out.println("키 : " + entry.getKey() + ", 값 : " + entry.getValue());
			}
		}
		System.out.println("===================");
		// map이 포함하고 있는 값(Value)을 Collection객체로 반환
		Collection<Integer> values = map.values();
		for (int a : values) {
			System.out.println("값 : " + a);
		}
	}
}