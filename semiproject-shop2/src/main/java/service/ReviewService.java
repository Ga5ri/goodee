package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ReviewDao;
import util.DBUtil;
import vo.Review;

public class ReviewService {
	private ReviewDao reviewDao;
	
	// 리뷰목록
	public ArrayList<Review> getReviewListByPage(int currentPage, int rowPerPage, String customerId) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Review> list = null;
		reviewDao = new ReviewDao();
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;			
			conn = DBUtil.getConnection();
			list = reviewDao.selectReviewListByPage(conn, beginRow, endRow, customerId);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
	
	// 리뷰목록 검색추가
	public ArrayList<Review> getReviewListByPage(int currentPage, int rowPerPage, String customerId, String word) {
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage,rowPerPage를 가공
		 */
		ArrayList<Review> list = null;
		reviewDao = new ReviewDao();
		Connection conn = null;
		try {
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;
			conn = DBUtil.getConnection();
			list = reviewDao.selectReviewListByPage(conn, beginRow, endRow, customerId, word);
			conn.commit(); // DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // DBUtil.class에서 conn.setAutoCommit(false);
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
	
	// 페이징을 위한 ReviewList 수 수하기
	public int cntReviewListServie(String customerId) {
		int resultCnt = 0;
		reviewDao = new ReviewDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = reviewDao.cntReviewList(conn, customerId);
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
		return resultCnt;
	}
	
	// 페이징+검색을 위한 ReviewList 수 수하기
	public int cntReviewListServie(String customerId, String word) {
		int resultCnt = 0;
		reviewDao = new ReviewDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			resultCnt = reviewDao.cntReviewList(conn, word, customerId);
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
		return resultCnt;
	}
	
	// 리뷰쓰기
	public int addReviewService(Review review) {
		int row = 0;
		reviewDao = new ReviewDao();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			row = reviewDao.addReview(conn, review);
			System.out.println(review + " : 리뷰 작성완료");
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
	
	// 리뷰쓰기 전 사전정보
	public Review getInfoForAddReviewService(int orderCode) {
		Review review = new Review();
		reviewDao = new ReviewDao();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			review = reviewDao.selectInfoForReview(conn, orderCode);
			System.out.println("review info service : " + review);
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
		return review;
	}
}
