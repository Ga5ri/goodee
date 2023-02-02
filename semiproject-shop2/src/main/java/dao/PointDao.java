package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.Orders;
import vo.PointHistory;

public class PointDao {

	// 고객 포인트 조회
	public int selectPoint(Connection conn, String customerId) throws Exception {
		int point = 0;		
		String sql = "SELECT point FROM customer WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			point = rs.getInt("point");
		}
		return point;
	}

	// 포인트 사용 || 구매확정 시 포인트 처리
	public int updatePoint(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET point = ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer.getPoint());
		stmt.setString(2, customer.getCustomerId());
		System.out.println(customer.getPoint() + " : 사용 후 포인트");

		row = stmt.executeUpdate();
		return row;
	}
	
	// 포인트 히스토리 기록
	public int addPointHistory(Connection conn, PointHistory pointHistory) throws Exception {
		int row = 0;
		
		String sql = "INSERT INTO point_history(order_code, point_kind, point) VALUES(?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pointHistory.getOrderCode());
		System.out.println(pointHistory.getOrderCode() + " : orderCode");
		stmt.setString(2, pointHistory.getPointKind());
		System.out.println(pointHistory.getPointKind() + " : pointKind");
		stmt.setInt(3, pointHistory.getPoint());
		System.out.println(pointHistory.getPoint() + " : point");

		row = stmt.executeUpdate();
		return row;
	}	
	
	// 삭제를 위한 포인트 히스토리 조회
	public PointHistory selectPointHistoryForDelete(Connection conn, int orderCode) throws Exception {
		PointHistory pointHistory = new PointHistory();
		
		String sql = "SELECT point_kind pointKind, point FROM point_history WHERE order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		System.out.println(orderCode + " : orderCode");

		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			pointHistory = new PointHistory();
			pointHistory.setPointKind(rs.getString("pointKind"));
			pointHistory.setPoint(rs.getInt("point"));
		}
		System.out.println(rs.getString("pointKind") + rs.getInt("point"));
		return pointHistory;
	}
	
	// 주문취소 시 포인트 기록 삭제
	public int deletePointHistory(Connection conn, int orderCode) throws Exception {
		int row = 0;

		String sql = "DELETE FROM point_history WHERE order_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
			
		row = stmt.executeUpdate();
		return row;
	}
}