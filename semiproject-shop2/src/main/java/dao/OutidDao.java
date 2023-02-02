package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Outid;

public class OutidDao {

	// outid 중복 확인
	// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
	// 사용하는 곳 : AddCustomerController, AddEmpController
	public boolean checkOutid(Connection conn, String outid) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT id"
				+ "	 FROM outid"
				+ "	 WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, outid);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
	
	// outid 추가
	// 탈퇴 or 관리자 삭제시
	// 사용하는 곳 : DeleteCustomerController, DeleteEmpController
	public int addOutid(Connection conn, String outid) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO outid ("
				+ "			id"
				+ "			, createdate"
				+ ") VALUES ("
				+ "			?"
				+ "			, NOW()"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, outid);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("outid 추가 완료!");
		} else {
			System.out.println("outid 추가 실패!");
		}
		
		return resultRow;
		
	}
	
	
}
