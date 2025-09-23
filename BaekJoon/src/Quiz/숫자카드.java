package Quiz;

import java.util.Scanner;

public class 숫자카드 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("숫자 카드의 개수 : ");
		int count = sc.nextInt();

		int[] numArr1 = new int[count];

		System.out.println("각 카드의 정수는? : ");

		for (int i = 0; i < count; i++) {
			int num = sc.nextInt();
			numArr1[i] = num;
		}

		System.out.println("숫자를 셀 카드의 개수 : ");

		int count2 = sc.nextInt();

		int[] numArr2 = new int[count2];

		for (int j = 0; j < count2; j++) {
			int num2 = sc.nextInt();
			numArr2[j] = num2;

		}

		int[] correctCountArr = new int[numArr2.length];

		for (int i = 0; i < numArr2.length; i++) {
			for (int j = 0; j < numArr1.length; j++) {

				if (numArr2[i] == numArr1[j]) {

					correctCountArr[i]++;
				}
			}
			System.out.print(correctCountArr[i] + " ");
		}

	}

}
