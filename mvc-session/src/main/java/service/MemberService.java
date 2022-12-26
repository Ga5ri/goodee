package service;

import java.sql.Connection;

import dao.MemberDao;
import util.DBUtil;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	// 로그인 메서드
	public Member login(Member member) {
		Member returnMember = null;
		this.memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			System.out.println(conn+"<-connection 연결");
			returnMember = this.memberDao.selectMemberByIdPw(member, conn);
			System.out.println(returnMember+"<-returnMember");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return returnMember;
	}
}