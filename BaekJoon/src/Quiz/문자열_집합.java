package Quiz;

import java.util.Scanner;

public class 문자열_집합 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        scan.nextLine(); // nextInt() 후 개행문자 제거용

        String[] strS = new String[n];
        int cnt = 0;

        // 집합 S 문자열 입력
        for (int i = 0; i < n; i++) {
            strS[i] = scan.nextLine();
        }

        // 검사할 문자열 입력 및 포함 여부 확인
        for (int i = 0; i < m; i++) {
            String input = scan.nextLine();

            for (int j = 0; j < n; j++) {
                if (strS[j].equals(input)) { // 완전 일치 검사
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);

        scan.close();
    }
}
