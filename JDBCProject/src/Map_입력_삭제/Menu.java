package Map_입력_삭제;

import java.util.Scanner;

public class Menu {

	Scanner sc = new Scanner("System.in");

	public void mainMenu() {

		while (true) {
			System.out.print("(1)입력, (2)삭제, (3)출력, (4)종료 : ");
			int num = sc.nextInt();

			switch (num) {

			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				System.out.println("프로그램을 종료합니다!!!");
				return;
				

			}
		}
	}
}
