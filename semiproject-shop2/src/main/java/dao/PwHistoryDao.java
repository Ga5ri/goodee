package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.PwHistory;

public class PwHistoryDao {

	// 비밀번호 이력 추가
	// 1) 처음 가입할 때 사용
	// 2) 비밀번호 변경 할 때 사용
	// 사용하는 곳 : AddCustomerController, ModifyCustomerPwController
	public int addPwHistory(Connection conn, PwHistory pwHistory) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO pw_history ("
				+ "			customer_id"
				+ "			, pw"
				+ "			, createdate"
				+ ") VALUES ("
				+ "			?"
				+ "			, PASSWORD(?)"
				+ "			, NOW()"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, pwHistory.getCustomerId());
		stmt.setString(2, pwHistory.getPw());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("PW 이력 추가 성공");
		} else {
			System.out.println("PW 이력 추가 실패");
		}
		
		return resultRow;
		
	}
	
	// 비밀번호 이력 전체 삭제(관리자 삭제, 탈퇴)
	// 사용하는곳 : DeleteCustomerController
	public int deletePwHistory(Connection conn, String customerId) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM pw_history"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("pw 이력 전체 삭제 성공!");
		} else {
			System.out.println("pw 이력 전체 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	
	// 비밀번호 이력 count
	// 1) 비밀번호 이력은 최신 3개만 보관
	// 사용하는 곳 : ModifyCustomerPwController
	public int countPwHistory(Connection conn, String customerId) throws Exception {
		
		int resultCount = 0;
		
		String sql = "SELECT COUNT(customer_id) count"
				+ "	 FROM pw_history"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			resultCount = rs.getInt("count");
		}
		
		return resultCount;
		
	}
	
	
	// 비밀번호 이력 삭제(1개)
	// 1) 비밀번호 이력 최신 3개만 보관하기 때문에
	//    비밀번호 이력 3개 초과시 제일 오래된 이력 1개 삭제
	// 사용하는 곳 : ModifyCustomerPwController
	public int deletePwHistoryOne(Connection conn, String customerId) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM pw_history"
				+ "	 WHERE customer_id = ?"
				+ "		 AND createdate = (SELECT MIN(createdate)"
				+ "							 FROM pw_history"
				+ "							 WHERE customer_id = ?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		stmt.setString(2, customerId);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("pw 오래된 이력 삭제 성공!");
		} else {
			System.out.println("pw 오래된 이력 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	// 비밀번호 중복 확인
	// 1) 비밀번호 이력(최신 3개)과 비교
	// 2) 중복일시 비밀번호 변경 불가
	// true : pw가 이미 존재(변경불가) false : pw 사용 가능(변경 가능)
	// 사용하는 곳 : ModifyCustomerPwController
	public boolean checkPwHistory(Connection conn, PwHistory pwHistory) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT pw"
				+ "	 FROM pw_history"
				+ "	 WHERE customer_id = ?"
				+ "		 AND pw = PASSWORD(?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, pwHistory.getCustomerId());
		stmt.setString(2, pwHistory.getPw());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			System.out.println("pw_history 존재");
			result = true;
			
		}
		
		return result;
		
	}
	
	
	
}
