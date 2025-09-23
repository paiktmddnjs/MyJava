package practice15;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class 가장많이등장한문자 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str;
		System.out.print("문장을 입력하세요! : ");
		str = sc.nextLine();
		Map<String, Integer> map = new HashMap<>();

		String[] str_arr = str.split(""); // 한글자씩(공백포함) 배열에 저장한다!.

		int maxVal = 0;
		String maxString = "";

		for (String s : str_arr) {

			if (!map.containsKey(s)) {

				map.put(s, 1);
			} else {
				map.put(s, map.get(s) + 1);
			}

		}
		for (Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
			if (e.getValue() > maxVal && e.getKey().equals(" ") == false) {
				maxVal = e.getValue();
				maxString = e.getKey();
			}
		}

		System.out.println("가장 많이 등장한 문자: " + maxString);
		System.out.println("가장 많이 등장한 문자의 최댓값 : " + maxVal);

	}
}