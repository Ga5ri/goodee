package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;

public class CustomerDao {
	
	// 로그인 LoginController
	public Customer selectCustomerLogin(Connection conn, Customer customer) throws Exception {
		Customer retrunCustomer = null;
		String sql = "SELECT customer_code customerCode, customer_id customerId, customer_name customerName, point "
				+ " FROM customer WHERE customer_id =? AND customer_pw = password(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			retrunCustomer= new Customer();
			retrunCustomer.setCustomerCode(rs.getInt("customerCode"));
			retrunCustomer.setCustomerId(rs.getString("customerId"));
			retrunCustomer.setCustomerName(rs.getString("customerName"));
			retrunCustomer.setPoint(rs.getInt("point"));
		}
		rs.close();
		stmt.close();
		return retrunCustomer;
	}
	
	
	// customerList
	// 사용하는 곳 : CustomerListController
	// 검색 페이징 포함
	public ArrayList<Customer> selectCustomerList(Connection conn, String searchCategory, String searchText, int beginRow, int rowPerPage) throws Exception {
		
		ArrayList<Customer> list = null;
		
		String sql = "SELECT customer_code customerCode"
				+ "			, customer_id customerId"
				+ "			, customer_name customerName"
				+ "			, customer_phone customerPhone"
				+ "			, point"
				+ "			, createdate"
				+ "	 FROM customer"
				+ "	 WHERE " + searchCategory + " LIKE ?"
				+ "	 ORDER BY customer_code DESC"
				+ "	 LIMIT ?, ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1,  "%" + searchText + "%");
		stmt.setInt(2,  beginRow);
		stmt.setInt(3, rowPerPage);
		
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<Customer>();
		
		while(rs.next()) {
			
			Customer customer = new Customer();
			customer.setCustomerCode(rs.getInt("customerCode"));
			customer.setCustomerId(rs.getString("customerId"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerPhone(rs.getString("customerPhone"));
			customer.setPoint(rs.getInt("point"));
			customer.setCreatedate(rs.getString("createdate"));
			
			list.add(customer);
			
		}
		
		return list;
		
	}
	
	

	// 검색 후 customer 총 카운트
	// 사용하는 곳 : CustomerListController
	public int countCustomer(Connection conn, String searchCategory, String searchText) throws Exception {
		
		int resultCount = 0;
		
		String sql = "SELECT COUNT(customer_code) cnt"
				+ "	 FROM customer"
				+ "	 WHERE " + searchCategory + " LIKE ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchText + "%");
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			resultCount = rs.getInt("cnt");
		}
		
		// 디버깅
		System.out.println(resultCount + " <-- resultCount");
		
		return resultCount;
		
	}
	
	// customerOne 한명의 정보 출력
	// 사용하는 곳 : CustomerOneController, ModifyCustomerController, ModifyCustomerByAdminController
	public Customer selectCustomerOne(Connection conn, String customerId) throws Exception {
		
		Customer resultCustomer = null;
		
		String sql = "SELECT customer_code customerCode"
				+ "			, customer_id customerId"
				+ "			, customer_name customerName"
				+ "			, customer_phone customerPhone"
				+ "			, point"
				+ "			, createdate"
				+ "	 FROM customer"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			resultCustomer = new Customer();
			
			resultCustomer.setCustomerCode(rs.getInt("customerCode"));
			resultCustomer.setCustomerId(rs.getString("customerId"));
			resultCustomer.setCustomerName(rs.getString("customerName"));
			resultCustomer.setCustomerPhone(rs.getString("customerPhone"));
			resultCustomer.setPoint(rs.getInt("point"));
			resultCustomer.setCreatedate(rs.getString("createdate"));
			
		}
		
		return resultCustomer;
		
	}
	
	
	// 관리자가 customer 수정
	// 사용하는 곳 : ModifyCustomerByAdminController
	
	public int modifyCustomerByAdmin(Connection conn, Customer customer) throws Exception {
		int resultRow = 0;
		
		String sql = "UPDATE customer"
				+ "	 SET customer_name = ?"
				+ "		, customer_phone = ?"
				+ "		, point = ?"
				+ "	 WHERE customer_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customer.getCustomerName());
		stmt.setString(2, customer.getCustomerPhone());
		stmt.setInt(3, customer.getPoint());
		stmt.setInt(4, customer.getCustomerCode());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("customerByAdmin 수정 성공!");
		} else {
			System.out.println("customerByAdmin 수정 실패!");
		}
		
		return resultRow;
		
	}
	
	

	// customer 내정보수정
	// 사용하는 곳 : ModifyCustomerController
	
	public int modifyCustomer(Connection conn, Customer customer) throws Exception {
		int resultRow = 0;
		
		String sql = "UPDATE customer"
				+ "	 SET customer_name = ?"
				+ "		, customer_phone = ?"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customer.getCustomerName());
		stmt.setString(2, customer.getCustomerPhone());
		stmt.setString(3, customer.getCustomerId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("customer 수정 성공!");
		} else {
			System.out.println("customer 수정 실패!");
		}
		
		return resultRow;
		
	}
	
	
	
	// customer 삭제
	// 사용하는 곳 : DeleteCustomerController
	public int deleteCustomer(Connection conn, String customerId) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM customer"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("customer 삭제 성공!");
		} else {
			System.out.println("customer 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	
	// customer 회원가입
	// 1) customer ID 중복확인
	// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
	// 사용하는 곳 : AddCustomerController, AddEmpController
	public boolean checkCustomerId(Connection conn, String customerId) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT customer_id customerId"
				+ "	 FROM customer"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
	// customer 회원가입
	// 2) 회원가입
	// 사용하는 곳 : AddCustomerController
	public int addCustomer(Connection conn, Customer customer) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO customer ("
				+ "			customer_id"
				+ "			, customer_pw"
				+ "			, customer_name"
				+ "			, customer_phone"
				+ "			, point"
				+ "			, createdate"
				+ "	 ) VALUES ("
				+ "			?"
				+ "			, PASSWORD(?)"
				+ "			, ?"
				+ "			, ?"
				+ "			, ?"
				+ "			, NOW()"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		stmt.setString(3, customer.getCustomerName());
		stmt.setString(4, customer.getCustomerPhone());
		stmt.setInt(5, 0);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("회원 가입 성공");
		} else {
			System.out.println("회원 가입 실패");
		}
		
		
		return resultRow;
		
	}
		
	
	// 비밀번호 확인
	// 사용하는 곳 : CheckCustomerPWConroller
	//				 (pw 확인 후 이동 DeleteCustomerController, ModifyCustomerController, ModifyCustomerPwController)
	// true : 비밀번호 일치(메뉴사용가능) / false : 불일치(메뉴사용불가)
	public boolean checkPw(Connection conn, Customer customer) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT customer_id"
				+ "	 FROM customer"
				+ "	 WHERE customer_id = ?"
				+ "		 AND customer_pw = PASSWORD(?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
	// 비밀번호 변경(수정)
	// 1) 비밀번호 이력(최신3개)과 중복이 없으면 변경 가능
	// 사용하는 곳 : ModifyCustomerPwController
	public int modifyCustomerPw(Connection conn, Customer customer) throws Exception {
		
		int resultRow = 1;
		
		String sql = "UPDATE customer"
				+ "	 SET customer_pw = PASSWORD(?)"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customer.getCustomerPw());
		stmt.setString(2, customer.getCustomerId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("customer pw 변경 성공");
		} else {
			System.out.println("customer pw 변경 실패");
		}
		
		return resultRow;
		
	}
	
	
	
}
