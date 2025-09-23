package Practice16;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public char solution(int n, String str) {
		char answer = ' ';

		HashMap<Character, Integer> map = new HashMap<>();
		// 문자 key값, 빈도수 value값 저장
		for (char x : str.toCharArray()) {
			map.put(x, map.getOrDefault(x, 0) + 1); // 너무 너무 중요함!!!
		}
		int max = Integer.MIN_VALUE;
		for (char key : map.keySet()) {
//			System.out.println(key + " " + map.get(key));
			if (map.get(key) > max) {
				max = map.get(key);
				answer = key;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Main T = new Main();
		Scanner kb = new Scanner(System.in);

		int n = 0;
		System.out.println("문자 입력: ");
		String str = kb.next();
		System.out.println(T.solution(n, str));
	}
}
