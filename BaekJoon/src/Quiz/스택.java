package Quiz;

import java.util.Scanner;
import java.util.Stack;

public class 스택 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Stack<Number> stack = new Stack<>();

		System.out.print("입력할 명령수 : ");
		int num = sc.nextInt();
		for (int i = 0; i < num; i++) {

			int choose = sc.nextInt();
			switch (choose) {

			case 1:
				int number = sc.nextInt();
				stack.push(number);
				break;
			case 2:
				if (!stack.isEmpty()) {
					System.out.println(stack.pop()); // 팝한 값 출력
				} else {
					System.out.println(-1);
				}
				break;

			case 3:
				System.out.println(stack.size());
				break;
			case 4:
				if (stack.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case 5:
				if (!stack.isEmpty()) {
					System.out.println(stack.peek());

				} else {
					System.out.println(-1);
				}
				break;
			}

		}
	}

}
