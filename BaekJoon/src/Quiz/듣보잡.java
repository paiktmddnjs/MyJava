package Quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class 듣보잡 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("듣도 못한 사람의 수 : ");
		int num1 = sc.nextInt();

		String[] arr1 = new String[num1];
		System.out.println("보도 못한 사람의 수 : ");

		int num2 = sc.nextInt();
		String[] arr2 = new String[num2];
		sc.nextLine(); // 입력 버퍼 비우기
		for (int i = 0; i < num1; i++) {

			String name1 = sc.nextLine();
			arr1[i] = name1;

		}
		for (int j = 0; j < num2; j++) {

			String name2 = sc.nextLine();
			arr2[j] = name2;
		}

		int count = 0;

		ArrayList<String> arr3 = new ArrayList<>();

		for (int i = 0; i < arr1.length; i++) {

			for (int j = 0; j < arr2.length; j++) {

				if (arr1[i].equals(arr2[j])) {
					arr3.add(arr1[i]);
					count++;
					break;
				}
			}
		}
		System.out.println();
		System.out.println(count);
		for (String name : arr3) {
			System.out.println(name);
		}

		/*
		 * for(int i = 0; i < count; i++) {
		 * 
		 * System.out.println(arr3.get(i)); }
		 */

	}

}
