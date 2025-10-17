package Quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class 균형잡힌_세계 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = br.readLine();

			// 입력이 단독으로 '.' 이면 종료
			if (line.equals(".")) {
				break;
			}

			// line 끝에 '.'이 포함되어 있는지 (문장 끝인지) 확인 후 검사
			if (line.endsWith(".")) {
				// 괄호 균형 검사
				boolean balanced = isBalanced(line);
				System.out.println(balanced ? "yes" : "no");
			} else {
				// 문제가 명확하지 않다면 여기선 처리하지 않고 넘어가거나 오류 출력 가능
				// 예시 입력에서는 항상 '.'이 있다고 가정
			}
		}
	}

	private static boolean isBalanced(String s) {
		Deque<Character> stack = new LinkedList<>();
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '[') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			} else if (c == ']') {
				if (stack.isEmpty() || stack.pop() != '[') {
					return false;
				}
			}
			// 괄호 외 문자 무시
		}
		return stack.isEmpty();
	}
}