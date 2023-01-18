package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GoodsDao;
import dao.NoticeDao;
import util.DBUtil;
import vo.Notice;

public class NoticeService {
	private NoticeDao noticeDao;
	// 공지 삭제
	public int deleteNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			row = noticeDao.deleteNotice(conn, notice);
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
	
	// 공지 수정
	public int modifyNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			row = noticeDao.modifyNotice(conn, notice);
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
	
	// 공지 상세보기
	public ArrayList<Notice> getNotiecOne(int noticeCode) {
		ArrayList<Notice> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(goodsList)");
			conn.setAutoCommit(false);
			noticeDao = new NoticeDao();
			list = noticeDao.selectNoticeOne(conn, noticeCode);
			conn.commit();
		} catch(Exception e) {
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
	
	// 공지 리스트
	public ArrayList<Notice> getNoticeList(int beginRow, int rowPerPage) {
		ArrayList<Notice> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("db 접속(noticeList)");
			conn.setAutoCommit(false);
			noticeDao = new NoticeDao();
			list = noticeDao.selectNoticeListByPage(conn, beginRow, rowPerPage);
			conn.commit();
		} catch(Exception e) {
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
	
	// 공지리스트 페이징
	public int noticeCount() {
		NoticeDao noticeDao = new NoticeDao();
		int cnt = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			cnt = noticeDao.noticeCount(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return cnt;
	}
	// 공지 추가
	public int insertNotice(Notice notice) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			noticeDao = new NoticeDao();
			row = noticeDao.insertNotice(conn, notice);
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
