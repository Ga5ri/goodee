package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PointDao;
import util.DBUtil;
import vo.Customer;
import vo.PointHistory;

public class PointService {
	private PointDao pointDao;
	// 고객 포인트 조회
	public int selectPointServie(String customerId) {
		int point = 0;
		pointDao = new PointDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			point = pointDao.selectPoint(conn, customerId);
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
		return point;
	}
	
	// 구매확정 시 포인트 처리
	public int updatePointService(Customer customer) {
		pointDao = new PointDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = pointDao.updatePoint(conn, customer);
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
		return row;
	}
	
	// 구매확정 시 포인트 처리
	public int addPointHistoryService(PointHistory pointHistory) {
		pointDao = new PointDao();
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			row = pointDao.addPointHistory(conn, pointHistory);
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
		return row;
	}
	
}