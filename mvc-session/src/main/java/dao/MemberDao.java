package dao;

import java.sql.*;

import vo.Member;

public class MemberDao {
	// 로그인 메서드
	public Member selectMemberByIdPw(Member member, Connection conn) throws Exception{
		Member returnMember = null;				
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		String sql = "SELECT member_id memberId, member_name memberName FROM member WHERE member_id = ? AND member_pw = PASSWORD(?)";
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		rs = stmt.executeQuery();
		if(rs.next()) {
			returnMember = new Member();
			returnMember.setMemberId(rs.getString("memberId"));
			returnMember.setMemberName(rs.getString("memberName"));
		}

		rs.close();
		stmt.close();
		return returnMember;
	}
}