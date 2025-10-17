package Quiz;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class 도키도키_간식드리미 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		Stack<Integer> stackRecived = new Stack<>();
		
		System.out.println("줄을 선 학생 수 : ");
		
		int number = sc.nextInt();
		int min = 0;
		
		for(int i = 0; i < number; i++) {
			
			int studentNum = sc.nextInt();
			
			stack1.push(studentNum);
		
		}
		
		Collections.reverse(stack1);
		
		int minValue = stack1.get(0);
        int minIndex = 0;
        
        for (int i = 1; i < stack1.size(); i++) {
            if (stack1.get(i) < minValue) {
                minValue = stack1.get(i);
                minIndex = i;
            }
        }
        
        
        for (int i = minIndex + 1; i < stack1.size(); i++) {
        	
        	stack2.push(stack1.get(i));
      
        	
        }
		
        System.out.println(stack1);
        System.out.println(stack2);
	
		
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            int top1 = stack1.peek();
            int top2 = stack2.peek();
            
      
            if(top1 < top2) {
            	 stack1.pop();
            	 stackRecived.push(top1);
            } else {
                 stack2.pop();
                 stackRecived.push(top2);
            }
          
        }
		
		
			
		
		
		
		
	}

}
