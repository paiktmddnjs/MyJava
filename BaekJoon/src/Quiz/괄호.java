package Quiz;

import java.util.Scanner;
import java.util.Stack;

public class 괄호 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 괄호 문자열 개수

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            Stack<Character> stack = new Stack<>();
            boolean isValid = true;

            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);

                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.isEmpty()) {
                        isValid = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (isValid && stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
