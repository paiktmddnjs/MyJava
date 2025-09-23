package Map_입력_삭제;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {

	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	Scanner sc = new Scanner(System.in);

	public void Insert() {
		HashMap<String, Object> map = new HashMap<>();

		System.out.println("이름을 입력하시오: ");
		map.put("name", sc.next());
		System.out.println("나이를 입력하시오: ");
		map.put("age", sc.next());
		System.out.println("주소를 입력하시오: ");
		map.put("addr", sc.next());

	}

	public void Delete() {

		System.out.print("삭제할 내용을 입력하세요 : ");

		Object obj = sc.next();

		for (int i = 0; i < list.size(); i++) {

		}

	}

	public void Stream() {
		for (int i = 0; i < list.size(); i++) {
			System.out.print("이름:" + list.get(i).get("name"));
			System.out.print(", 나이:" + list.get(i).get("age"));
			System.out.println(", 주소:" + list.get(i).get("address"));
		}

	}

}
