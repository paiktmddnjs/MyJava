package Quiz;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class 큐2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 1. 명령어의 개수 입력
		int num = sc.nextInt();
		sc.nextLine(); // 버퍼의 줄 바꿈 문자 소비 (필수)
		
		// Deque 인터페이스를 LinkedList로 구현 (front, back 사용 위함)
		Deque<Integer> queue = new LinkedList<>();

		// 모든 명령에 대한 출력을 저장할 StringBuilder (출력 성능 향상)
		StringBuilder sb = new StringBuilder(); 

		for (int i = 0; i < num; i++) {

			String commandLine = sc.nextLine();
			String[] parts = commandLine.trim().split("\\s+");
			String command = parts[0];

			switch (command) {

			case "push":
				if (parts.length == 2) {
					try {
						int value = Integer.parseInt(parts[1]);
						queue.offer(value); // 큐에 값 추가
					} catch (NumberFormatException e) {
						// 예외 처리 (필요시)
					}
				}
				break;

			case "pop":
				// **[수정]** 큐가 비어있지 않은지 확인
				if (!queue.isEmpty()) { 
					sb.append(queue.poll()).append('\n'); // 맨 앞 요소 제거 및 출력
				} else {
					sb.append(-1).append('\n'); // 비어있으면 -1 출력
				}
				break;

			case "size":
				sb.append(queue.size()).append('\n');
				break;

			case "empty":
				// **[수정]** 큐가 비어있으면 1, 아니면 0
				if (queue.isEmpty()) { 
					sb.append(1).append('\n');
				} else {
					sb.append(0).append('\n');
				}
				break;

			case "front":
				// **[수정]** 큐가 비어있지 않은지 확인
				if (!queue.isEmpty()) {
					sb.append(queue.peek()).append('\n'); // 맨 앞 요소 확인
				} else {
					sb.append(-1).append('\n'); // 비어있으면 -1 출력
				}
				break;

			case "back":
				// **[수정]** 큐가 비어있지 않은지 확인
				if (!queue.isEmpty()) {
					sb.append(queue.peekLast()).append('\n'); // 맨 뒤 요소 확인
				} else {
					sb.append(-1).append('\n'); // 비어있으면 -1 출력
				}
				break;
			}
		}
		
		sc.close();
        
        // 모든 출력을 한 번에 출력 (성능 최적화)
		System.out.print(sb.toString()); 
	}
}