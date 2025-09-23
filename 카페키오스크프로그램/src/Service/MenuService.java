package Service;

import static Common.DBUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.MenuDao;
import VO.Menu;

public class MenuService {

	private MenuDao dao = new MenuDao();

	// MenuDAO의 selectAllMenus를 호출하여 연결 객체를 전달한다.
	public List<Menu> getAllMenus() throws SQLException {

		Connection conn = getConnection();
		
		List<Menu> result = dao.selectAllMenus(conn);
		if (result != null) {
			conn.commit();
			System.out.println("주문 메뉴 출력 성공!");
		} else {
			conn.rollback();
			System.out.println("주문 메뉴 출력 실패!");
		}
		return result;
	}

}
