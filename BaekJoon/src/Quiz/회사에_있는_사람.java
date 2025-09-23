package Quiz;

import java.util.Scanner;

public class 회사에_있는_사람 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("출입 기록의 수는 ? : ");
        int num = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기

        String[] arr = new String[0];

        for (int i = 0; i < num; i++) {
            String record = sc.nextLine();
            
            // split() 함수: 이것을 사용해서 공백을 기준으로 입력값을 나누어주었다 
            // ex) ( Baha ) "공백" ( enter )  
            String[] split = record.split(" ");
            String name = split[0];
            String action = split[1];

            if (action.equals("enter")) {
                if (!contains(arr, name)) {
                    arr = addElement(arr, name);
                }
            } else if (action.equals("leave")) {
                arr = removeElement(arr, name);
            }
        }

        // 출력 (역순 정렬 원할 경우 정렬도 추가 가능)
        for (String s : arr) {
            System.out.println(s);
        }
    }

    public static boolean contains(String[] arr, String target) {
        for (String s : arr) {
            if (s.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static String[] addElement(String[] arr, String element) {
        String[] newArr = new String[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[arr.length] = element;
        return newArr;
    }

    public static String[] removeElement(String[] arr, String target) {
        int count = 0;
        for (String s : arr) {
            if (s.equals(target)) {
                count++;
            }
        }

        if (count == 0) {
            return arr;
        }

        String[] newArr = new String[arr.length - count];
        int index = 0;
        for (String s : arr) {
            if (!s.equals(target)) {
                newArr[index++] = s;
            }
        }
        return newArr;
    }
}
