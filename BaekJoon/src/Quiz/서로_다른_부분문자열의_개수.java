package Quiz;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 서로_다른_부분문자열의_개수 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("입력할 문자열 : ");
		String input = sc.nextLine();

		Set<String> substrings = new HashSet<>();

		for (int i = 0; i < input.length(); i++) {

			for (int j = i + 1; j < input.length() + 1; j++) {
				String sub = input.substring(i, j); //substring()을 사용하여 i부터 j까지 문자열을 저장한다.
				substrings.add(sub);
			}
		}
		
// 		StringBuilder을 사용한 방법이다!
		
//		for (int i = 0; i < input.length(); i++) {
//		    for (int j = i + 1; j <= input.length(); j++) {
//		        StringBuilder sb = new StringBuilder();
//		        for (int k = i; k < j; k++) {
//		            sb.append(input.charAt(k));  // 또는 char 배열 사용: chars[k]
//		        }
//		        substrings.add(sb.toString());
//		    }
//		}

		System.out.println(substrings.size());
	}

}
