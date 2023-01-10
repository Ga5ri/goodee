package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Notice;

public class NoticeDao {
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
