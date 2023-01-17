package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Notice;

public class NoticeDao {
	// 공지 삭제하기
	public int deleteNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		String sql = "DELETE FROM notice WHERE notice_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, notice.getNoticeCode());
		
		row = stmt.executeUpdate();
		
		return row;
	}
	
	// 공지 수정하기
	public int modifyNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		String sql = "UPDATE notice"
				+ 	" SET notice_title = ?, notice_content = ?, emp_id = ?, createdate = now()"
				+ 	" WHERE notice_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setString(3, notice.getEmpId());
		stmt.setInt(4, notice.getNoticeCode());
		
		row = stmt.executeUpdate();
		
		return row;
	}
	
	// 공지 상세보기
	public ArrayList<Notice> selectNoticeOne(Connection conn, int noticeCode) throws Exception {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT notice_code noticeCode, notice_title noticeTitle, notice_content noticeContent, emp_id empId"
				+ 	" FROM notice WHERE notice_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, noticeCode);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setEmpId(rs.getString("empId"));
			list.add(n);
		}
		return list;
	}
	
	// 공지 리스트(페이징)
	public ArrayList<Notice> selectNoticeListByPage(Connection conn, int beginRow, int rowPerPage) throws Exception {
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "SELECT notice_code noticeCode, notice_title noticeTitle"
				+ 	" , notice_content noticeContent, emp_id empId, DATE_FORMAT(createdate, '%Y-%m-%d')"
				+ 	" FROM notice ORDER BY notice_code DESC LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setEmpId(rs.getString("empId"));
			n.setCreatedate(rs.getString("DATE_FORMAT(createdate, '%Y-%m-%d')"));
			list.add(n);
		}
		return list;
	}
	
	// 공지사항 전체 수(페이징)
	public int noticeCount(Connection conn) throws Exception {
		int cnt = 0;
		String sql = "SELECT COUNT(*) cnt FROM notice";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		return cnt;
	}
	
	// 공지사항 추가
	public int insertNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		String sql = "INSERT INTO notice(notice_title, notice_content, emp_id, createdate)"
				+ 	" VALUES(?,?,?,NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setString(3, notice.getEmpId());
		row = stmt.executeUpdate();
		return row;
	}
}
