package Quiz;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class 카드2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Deque<Integer> deque = new ArrayDeque<>();

		System.out.println("카드의 개수를 입력하세요 : ");
		int cardCount = sc.nextInt();

		for (int i = 1; i <= cardCount; i++) {

			deque.addLast(i);

		}

		while (!(deque.size() == 1)) {

			deque.removeFirst();

			deque.addLast(deque.removeFirst());

		}

		System.out.println(deque);
	}

}

/*
 * 평소에는 큐와 스택을 많이 사용했지만 이 문제를 풀어며 덱을 사용하게됨으로써 덱의 개념을 파악할 수 있었다.
 * 
 * 덱과 큐의 차이점: >> 큐 : 한쪽 끝(Rear)에서 삽입, 다른 쪽 끝(Front)에서 삭제가 일어나는 자료구조이다. >> 덱 : 양쪽
 * 끝(Front, Rear) 모두에서 삽입과 삭제가 일어나는 자료구조이다.
 */