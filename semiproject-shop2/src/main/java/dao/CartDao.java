package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Cart;
import vo.Goods;

public class CartDao {

	// CartList : 장바구니에 추가한 상품의 리스트 출력
	// 사용하는 곳 : 
	public ArrayList<Cart> selectCartList(Connection conn, String customerId) throws Exception {
		
		ArrayList<Cart> list = null;
		
		String sql = "SELECT goods_code goodsCode"
				+ "		, customer_id customerId"
				+ "		, cart_quantity cartQuantity"
				+ "		, createdate"
				+ "	 FROM cart"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<Cart>();
		
		while(rs.next()) {
			
			Cart c = new Cart();
			
			c.setGoodsCode(rs.getInt("goodsCode"));
			c.setCustomerId(rs.getString("customerId"));
			c.setCartQuantity(rs.getInt("cartQuantity"));
			c.setCreatedate(rs.getString("createdate"));
			
			System.out.println(c.toString());
			
			list.add(c);
			
		}
		
		return list;
		
	}
	
	
	
	// Cart(장바구니) 추가
	// 사용하는 곳 :
	public int addCart(Connection conn, Cart cart) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO cart ("
				+ "		goods_code"
				+ "		, customer_id"
				+ "		, cart_quantity"
				+ ") VALUES ("
				+ "		?"
				+ "		, ?"
				+ "		, ?"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		stmt.setInt(3, cart.getCartQuantity());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("장바구니 추가 완료!");
		} else {
			System.out.println("장바구니 추가 실패!");
		}
		
		return resultRow;
		
	}
	
	
	
	// Cart(장바구니) 1개 삭제
	// 사용하는 곳 :
	public int deleteCart(Connection conn, Cart cart) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM cart"
				+ "	 WHERE goods_code = ?"
				+ "		 AND customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("장바구니 삭제 완료!");
		} else {
			System.out.println("장바구니 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	
	
	// Cart(장바구니) 전체 삭제
	// 사용하는 곳 :
	public int emptyCart(Connection conn, Cart cart) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM cart"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		System.out.println(cart.toString() + "<-- cart.toString()");
		
		stmt.setString(1, cart.getCustomerId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow != 0) {
			System.out.println("장바구니 전체 삭제 완료!");
		} else {
			System.out.println("장바구니 전체 삭제 실패!");
		}
		
		return resultRow;
		
	}	
	
	
	// Cart(장바구니) 중복 확인
	// true : 동일한 상품이 이미 존재 false : 상품 장바구니에 추가 가능
	// 사용하는 곳 :
	public boolean checkCart(Connection conn, Cart cart) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT goods_code goodsCode"
				+ "	 FROM cart"
				+ "	 WHERE goods_code = ?"
				+ "		 AND customer_id = ?";
		
		
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		
		ResultSet rs = stmt.executeQuery();
		
		
		if(rs.next()) {
			
			result = true;
			
		}
		
		return result;
		
	}
	
	
	
	// Cart(장바구니) 수량 수정
	// 사용하는 곳 :
	public int modifyCart(Connection conn, Cart cart) throws Exception {
		
		int resultRow = 0;
		
		String sql = "UPDATE cart"
				+ "	 SET cart_quantity = ?"
				+ "	 WHERE goods_code = ?"
				+ "		 AND customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, cart.getCartQuantity());
		stmt.setInt(2, cart.getGoodsCode());
		stmt.setString(3, cart.getCustomerId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("장바구니 수량 수정 완료!");
		} else {
			System.out.println("장바구니 수량 수정 실패!");
		}
		
		return resultRow;
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
