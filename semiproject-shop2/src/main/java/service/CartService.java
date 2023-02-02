package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import util.DBUtil;
import vo.Cart;
import vo.Goods;

public class CartService {

	private CartDao cartDao;
	
	// Cart 목록(장바구니)
	// 사용하는 곳 : 
	public ArrayList<Cart> getCartList(String customerId) {
		
		ArrayList<Cart> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			list = this.cartDao.selectCartList(conn, customerId);
			
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
	
	
	
	// Cart(장바구니) 중복 확인 후 추가
	// 사용하는 곳 : 
	public int addCart(Cart cart) {

		int resultRow = 0;
		boolean result = false;		// 중복 확인 결과
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			// true : 동일한 상품이 이미 존재 false : 상품 장바구니에 추가 가능
			result = this.cartDao.checkCart(conn, cart);
			
			if(!result) {
				// 중복이 없을 때 장바구니 추가
				resultRow = this.cartDao.addCart(conn, cart);
			} else {
				System.out.println("CartService / 이미 장바구니에 있는 상품입니다.");
			}
			
			if(resultRow == 1) {
				conn.commit();
			}
			
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
		
		return resultRow;
		
	}	
	
	
	
	
	
	// Cart(장바구니) 삭제
	// 사용하는 곳 : 
	public int deleteCart(Cart cart) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			resultRow = this.cartDao.deleteCart(conn, cart);
			
			if(resultRow == 1) {
				conn.commit();
			}
			
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
		
		return resultRow;
		
	}	
	
	
	
	
	// Cart(장바구니) 전체 삭제
	// 사용하는 곳 : 
	public int emptyCart(Cart cart) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			resultRow = this.cartDao.emptyCart(conn, cart);
			
			if(resultRow != 0) {
				conn.commit();
			}
			
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
		
		return resultRow;
		
	}	
		
	
	
	
	// Cart(장바구니) 중복확인
	// true : 동일한 상품이 이미 존재 false : 상품 장바구니에 추가 가능	
	// 사용하는 곳 : 
	public boolean checkCart(Cart cart) {

		boolean result = false;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			result = this.cartDao.checkCart(conn, cart);
			
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
		
		return result;
		
	}	
	
	
	// Cart(장바구니) 수량 수정
	// 사용하는 곳 : 
	public int modifyCart(Cart cart) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.cartDao = new CartDao();
			
			resultRow = this.cartDao.modifyCart(conn, cart);
			
			if(resultRow == 1) {
				conn.commit();
			}
			
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
		
		return resultRow;
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
