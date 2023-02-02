package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.CustomerAddress;

public class CustomerAddressDao {

	// 주소 추가
	// 사용하는 곳 : AddCustomerController, AddAddressController
	public int addAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO customer_address ("
				+ "			customer_id"
				+ "			, address"
				+ "			, createdate"
				+ ") VALUES ("
				+ "			?"
				+ "			, ?"
				+ "			, NOW()"
				+ ")";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		stmt.setString(2, customerAddress.getAddress());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("주소 추가 성공");
		} else {
			System.out.println("주소 추가 실패");
		}
		
		
		return resultRow;
		
	}
	
	
	// 주소 목록
	// 사용하는 곳 : CustomerAddressListController
	public ArrayList<CustomerAddress> selectAddressList(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		ArrayList<CustomerAddress> list = null;
		
		String sql = "SELECT address_code addressCode"
				+ "			, address"
				+ "	 FROM customer_address"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<CustomerAddress>();
		
		while(rs.next()) {
		
			CustomerAddress ca = new CustomerAddress();
			ca.setAddressCode(rs.getInt("addressCode"));
			ca.setAddress(rs.getString("address"));
			
			list.add(ca);
			
		}
		
		return list;
		
	}
	
	// 주소 1개 출력
	// 사용하는 곳 : ModifyAddressController
	public CustomerAddress selectAddressOne(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		CustomerAddress resultCA = null;
		
		String sql = "SELECT address_code addressCode"
				+ "			, address"
				+ "	 FROM customer_address"
				+ "	 WHERE address_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, customerAddress.getAddressCode());
		
		ResultSet rs = stmt.executeQuery();
		
		
		if(rs.next()) {
			
			resultCA = new CustomerAddress();
			resultCA.setAddressCode(rs.getInt("addressCode"));
			resultCA.setAddress(rs.getString("address"));
			
		}		
		
		return resultCA;
	}
	
	// 주소 수정
	public int modifyAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultRow = 0;
		
		String sql = "UPDATE customer_address"
				+ "	 SET address = ?"
				+ "	 WHERE address_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getAddress());
		stmt.setInt(2, customerAddress.getAddressCode());
		
		// 디버깅
		// System.out.println(customerAddress.toString() + " <-- CA.toString");
		
		resultRow = stmt.executeUpdate();

		if(resultRow == 1) {
			System.out.println("address 수정 성공!");
		} else {
			System.out.println("address 수정 실패!");
		}
		
		
		return resultRow;
		
	}
	
	// 주소 1개 삭제
	// 사용하는곳 : DeleteAddressController
	public int deleteAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM customer_address"
				+ "	 WHERE address_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, customerAddress.getAddressCode());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("address 1개 삭제 성공!");
		} else {
			System.out.println("address 1개 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	
	// 주소 count
	// 1) 주소 최소 1개는 남기기 위해
	// 사용하는 곳 : DeleteAddressController
	public int countAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultCount = 0;
		
		String sql = "SELECT COUNT(address_code) count"
				+ "	 FROM customer_address"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			resultCount = rs.getInt("count");
		}
		
		return resultCount;
		
	}
	
	
}
