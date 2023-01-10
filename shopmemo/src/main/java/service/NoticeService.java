package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.NoticeDao;
import util.DBUtil;
import vo.Notice;

public class NoticeService {
	private NoticeDao noticeDao;
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
