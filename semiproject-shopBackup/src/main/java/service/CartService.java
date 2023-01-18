package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import util.DBUtil;
import vo.Goods;

public class CartService {

	private CartDao cartDao;
	
	// Cart 목록(장바구니)
	// 사용하는 곳 : 
	public ArrayList<HashMap<String, Object>> getCartList(Goods goods) {
		
		ArrayList<HashMap<String, Object>> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			list = this.cartDao.selectCartList(conn, goods);
			
			conn.commit();
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return list;
		
	}
	
	
	
}
