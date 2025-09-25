package Quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class 대칭_차집합_개수 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArrayList<Integer> arr1 = new ArrayList<>();
		ArrayList<Integer> arr2 = new ArrayList<>();

		System.out.println("A의 원소 개수 :");
		int num1 = sc.nextInt();
		System.out.println("B의 원소 개수 : ");
		int num2 = sc.nextInt();

		for (int i = 0; i < num1; i++) {

			int arrnum1 = sc.nextInt();
			arr1.add(i, arrnum1);
		}

		for (int j = 0; j < num2; j++) {
			int arrnum2 = sc.nextInt();
			arr2.add(j, arrnum2);
		}

		ArrayList<Integer> result = new ArrayList<>();
		int count = 0;
		for (int x : arr1) { 
			if (!arr2.contains(x)) { // !와 contains를 활용해서 차집합을 구한다!!
				count++;
			}
		}
		for (int x : arr2) {
			if (!arr1.contains(x)) {
				result.add(x);
				count++;
			}
		}
		System.out.println(count);
	}

}
