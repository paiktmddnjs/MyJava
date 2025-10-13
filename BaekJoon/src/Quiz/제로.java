package Quiz;

import java.util.Scanner;
import java.util.Stack;

public class 제로 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Stack<Integer> stack = new Stack<>();
		System.out.println("입력할 줄 수 : ");

		int num = sc.nextInt();

		   for (int i = 0; i < num; i++) {
	            int input = sc.nextInt();

	            if (input == 0) {
	                if (!stack.isEmpty()) {
	                    stack.pop();
	                }
	            } else {
	                stack.push(input);
	            }
	        }
		   
		   int sum = 0;
		   
		   while(!stack.isEmpty()) {
			   
			sum += stack.pop();   
		   }
		   
		   System.out.println(sum);

	        sc.close();
		   

	}

}
