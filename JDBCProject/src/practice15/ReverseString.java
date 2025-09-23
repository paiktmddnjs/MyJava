package practice15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.err.println("문자열을 입력하세요 : ");

		String str = sc.next();
		StringBuilder sb = new StringBuilder();
		List<String> arr = new ArrayList<>();

		System.out.println("기존 문자열: " + str);

		String[] str_arr = str.split("");

		for (String st : str_arr) { // for each문과 str.toCharArray()를 통해 간결하게 작성할 수 있다.
			arr.add(st);
		}

		// 0번 인덱스에 지정해서 넣으면 거꾸로 저장될 것이다.
//		for (char ch : str.toCharArray()) { 
//			arr.add(0, ch);
//		}

		for (int i = arr.size() - 1; i > -1; i--) {
			sb.append(arr.get(i));
		}

		System.out.println("변경된 문자열: " + sb);
	}
}