package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Review;

public class ReviewDao {
	public ArrayList<Review> selectReviewListByPage(Connection conn, int beginRow, int endRow, String customerId) throws Exception {
		Review r = null;		
		ArrayList<Review> list = new ArrayList<Review>();
		
		String sql = "SELECT rnum, o.order_code orderCode"
				+ "			, g.goods_code goodsCode, g.goods_name goodsName"
				+ "			, gi.filename"
				+ "			, c.customer_id customerId, c.point point"
				+ "			, r.review_memo reviewMemo, r.createdate createdate"
				+ "		 FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, review_memo, createdate"
				+ "				 FROM review) r"
				+ "		 INNER JOIN orders o ON r.order_code = o.order_code"
				+ "		 INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ "		 INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ "		 INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ "		 WHERE rnum BETWEEN ? AND ? AND o.customer_id = ? ORDER BY r.order_code desc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		stmt.setString(3, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			r = new Review();
			r.setRnum(rs.getInt("rnum"));
			r.setOrderCode(rs.getInt("orderCode"));
			r.setGoodsCode(rs.getInt("goodsCode"));
			r.setGoodsName(rs.getString("goodsName"));
			r.setCustomerId(rs.getString("customerId"));
			r.setPoint(rs.getInt("point"));
			r.setReviewMemo(rs.getString("reviewMemo"));
			r.setCreatedate(rs.getString("createdate"));
			list.add(r);
		}
		return list;
	}
	
	// 주문목록 검색추가 - (goods_code로 goods_name 조인해와야 함)
	public ArrayList<Review> selectReviewListByPage(Connection conn, int beginRow, int endRow, String customerId, String word) throws Exception {
		Review r = null;		
		ArrayList<Review> list = new ArrayList<Review>();
		
		String sql = "SELECT rnum, o.order_code orderCode"
				+ "			, g.goods_code goodsCode, g.goods_name goodsName"
				+ "			, gi.filename"
				+ "			, c.customer_id customerId, c.point point"
				+ "			, r.review_memo reviewMemo, r.createdate createdate"
				+ "		 FROM (SELECT ROW_NUMBER() OVER(ORDER BY order_code desc) rnum, order_code, review_memo, createdate"
				+ "				 FROM review WHERE review_memo Like ?) r"
				+ "		 INNER JOIN orders o ON r.order_code = o.order_code"
				+ "		 INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ "		 INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ "		 INNER JOIN customer c ON o.customer_id = c.customer_id"
				+ "		 WHERE rnum BETWEEN ? AND ? AND o.customer_id = ? ORDER BY r.order_code desc";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		stmt.setString(4, customerId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			r = new Review();
			r.setRnum(rs.getInt("rnum"));
			r.setOrderCode(rs.getInt("orderCode"));
			r.setGoodsCode(rs.getInt("goodsCode"));
			r.setGoodsName(rs.getString("goodsName"));
			r.setCustomerId(rs.getString("customerId"));
			r.setPoint(rs.getInt("point"));
			r.setReviewMemo(rs.getString("reviewMemo"));
			r.setCreatedate(rs.getString("createdate"));
			list.add(r);
		}
		return list;
	}
	
	// 페이징을 위한 주문목록 페이지 수
	public int cntReviewList (Connection conn, String customerId) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt"
				+ "		 FROM review r"
				+ "		 INNER JOIN orders o ON o.order_code = r.order_code"
				+ "		 WHERE o.customer_id = ?";
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
		String sql = "SELECT COUNT(*) cnt"
				+ "		 FROM review r"
				+ "		 INNER JOIN orders o ON o.order_code = r.order_code"
				+ "		 WHERE o.customer_id = ? AND review_memo LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		stmt.setString(2, "%"+word+"%");
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}		
		return cnt;
	}
	
	// 리뷰추가
	public int addReview(Connection conn, Review review) throws Exception {
		int row = 0;

		String sql = "INSERT INTO review(order_code, review_memo, createdate)"
				+ " 		VALUES (?, ?, NOW())"; //sysdate는 시시각각 변함, now는 한 번 호출되면 변하지 않음
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, review.getOrderCode());
		stmt.setString(2, review.getReviewMemo());
		
		row = stmt.executeUpdate();
		return row;
	}
	// 리뷰추가를 위한 검색
	public Review selectInfoForReview(Connection conn, int orderCode) throws Exception {
		Review r = null;
		System.out.println(orderCode + "어디인가");
		String sql = "SELECT o.order_code orderCode, g.goods_name goodsName, gi.filename filename"
				+ "		 FROM orders o"
				+ "		 INNER JOIN goods g ON o.goods_code = g.goods_code"
				+ "		 INNER JOIN goods_img gi ON g.goods_code = gi.goods_code"
				+ "		 WHERE o.order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			r = new Review();
			r.setOrderCode(rs.getInt("OrderCode"));
			r.setGoodsName(rs.getString("goodsName"));
			r.setFilename(rs.getString("filename"));
			System.out.println("리뷰를 위한 : " + rs.getString("goodsName") + rs.getString("filename"));
		}		
		return r;
	}
}
