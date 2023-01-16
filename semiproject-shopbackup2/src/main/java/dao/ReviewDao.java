package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Orders;

public class ReviewDao {
	public ArrayList<Orders> selectReviewListByPage(Connection conn, int beginRow, int endRow, String customerId) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql = "SELECT o.order_code orderCode"
				+ "			, g.goods_code goodsCode, g.goods_name goodsName"
				+ "			, c.customer_id customerID, c.point point"
				+ "			, o.order_quantity orderQuantity, o.order_price orderPrice, o.order_state orderState, o.createdate createdate"
				+ " 	FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, goods_code, customer_id, address_code"
				+ "					, order_quantity, order_price, order_state, createdate"
				+ " 			FROM orders) o"
				+ " 	INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ " 	INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ " 	WHERE rnum BETWEEN ? AND ? AND o.customer_id = ? ORDER BY o.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setGoodsName(rs.getString("goodsName"));
			o.setGoodsPrice(rs.getInt("goodsPrice"));
			o.setSoldout(rs.getString("soldout"));
			o.setCustomerId(rs.getString("customerID"));
			o.setCustomerName(rs.getString("customerName"));
			o.setCustomerPhone(rs.getString("customerPhone"));
			o.setPoint(rs.getInt("point"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setAddress(rs.getString("address"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			list.add(o);
		}
		return list;
	}
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<Orders> selectReviewListByPage(Connection conn, int beginRow, int endRow, String customerId, String word) throws Exception {
		Orders o = null;
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		String sql =  "SELECT r.order_code orderCode"
				+ "			, g2.goods_code goodsCode, g2.goods_name goodsName, g2.goods_price goodsPrice, g2.soldout soldout"
				+ "			, c.customer_id customerId, c.customer_name customerName, c.customer_phone customerPhone, c.point point"
				+ "			, ca.address_code addressCode, ca.address address"
				+ "			, r.order_quantity orderQuantity, r.order_price orderPrice, r.order_state orderState, r.createdate createdate"
				+ "		 FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, o.order_code, g1.goods_code, o.customer_id, o.address_code"
				+ "					, o.order_quantity, o.order_price, o.order_state, o.createdate"
				+ "				 FROM orders o INNER JOIN goods g1 ON o.goods_code = g1.goods_code WHERE g1.goods_name LIKE ?) r"
				+ " 	INNER JOIN goods g2 ON r.goods_code = g2.goods_code"
				+ " 	INNER JOIN customer c ON r.customer_id = c.customer_id"
				+ " 	INNER JOIN customer_address ca ON r.address_code = ca.address_code"
				+ " 	WHERE rnum BETWEEN ? AND ? AND r.customer_id = ? ORDER BY r.order_code desc"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		stmt.setString(4, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			o = new Orders();
			o.setOrderCode(rs.getInt("orderCode"));
			o.setGoodsCode(rs.getInt("goodsCode"));
			o.setGoodsName(rs.getString("goodsName"));
			o.setGoodsPrice(rs.getInt("goodsPrice"));
			o.setSoldout(rs.getString("soldout"));
			o.setCustomerId(rs.getString("customerId"));
			o.setCustomerName(rs.getString("customerName"));
			o.setCustomerPhone(rs.getString("customerPhone"));
			o.setPoint(rs.getInt("point"));
			o.setAddressCode(rs.getInt("addressCode"));
			o.setAddress(rs.getString("address"));
			o.setOrderQuantity(rs.getInt("orderQuantity"));
			o.setOrderPrice(rs.getInt("orderPrice"));
			o.setOrderState(rs.getString("orderState"));
			o.setCreatedate(rs.getString("createdate"));
			list.add(o);
		}
		return list;
	}
	
	// 페이징을 위한 주문목록 페이지 수
	public int cntReviewList (Connection conn, String customerId) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM orders WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;	
	}
	
	// 페이징+검색을 위한 주문목록 페이지 수
	public int cntReviewList (Connection conn, String customerId, String word) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt from orders WHERE customer_id = ? AND goods_name LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setString(2, "%"+word+"%");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;
	}
	
	// 주문상세보기
	public Orders selectReviewOne (Connection conn, int orderCode, String customerId) throws Exception {
		Orders orders = null;
		
		String sql = "SELECT o.order_code orderCode"
				+ "			, g.goods_code, g.goods_name, g.goods_price, g.soldout"
				+ "			, c.customer_id, c.customer_name, c.customer_phone, c.point point"
				+ "			, ca.address_code, ca.address"
				+ "			, o.order_quantity, o.order_price, o.order_state , o.createdate"
				+ "			, p.point_kind pointKind, p.point pointHistory"
				+ " 	FROM orders o INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ " 	INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ " 	INNER JOIN customer_address ca ON o.address_code = ca.address_code"
				+ " 	INNER JOIN point_history p On o.order_code = p.order_code"
				+ " 	WHERE o.customer_id = ? AND o.order_code = ? ORDER BY o.order_code desc;"; // WHERE rnum >=? AND rnum <=?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setInt(2, orderCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			orders = new Orders();
			orders.setOrderCode(rs.getInt("orderCode"));
			orders.setGoodsCode(rs.getInt("goodsCode"));
			orders.setGoodsName(rs.getString("goodsName"));
			orders.setGoodsPrice(rs.getInt("goodsPrice"));
			orders.setSoldout(rs.getString("soldout"));
			orders.setCustomerId(rs.getString("customerID"));
			orders.setCustomerName(rs.getString("customerName"));
			orders.setCustomerPhone(rs.getString("customerPhone"));
			orders.setPoint(rs.getInt("point"));
			orders.setAddressCode(rs.getInt("addressCode"));
			orders.setAddress(rs.getString("address"));
			orders.setOrderQuantity(rs.getInt("orderQuantity"));
			orders.setOrderPrice(rs.getInt("orderPrice"));
			orders.setOrderState(rs.getString("orderState"));
			orders.setCreatedate(rs.getString("createdate"));
			orders.setPointKind(rs.getString("pointKind"));
			orders.setPoint(rs.getInt("pointHistory"));
		}		
		return orders;
	}
}
