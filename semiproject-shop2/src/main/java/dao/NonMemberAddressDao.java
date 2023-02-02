package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.CustomerAddress;

public class NonMemberAddressDao {

	
	// 주소 1개 삭제
	// 사용하는곳 : DeleteAddressController
	public int deleteAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int resultRowAddress = 0;
		System.out.println(customerAddress.getCustomerId()+"서비스 주소값");
		String sql = "DELETE"
				+ "	 FROM customer_address"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		
		resultRowAddress = stmt.executeUpdate();
		
		if(resultRowAddress == 1) {
			System.out.println("address 1개 삭제 성공!");
		} else {
			System.out.println("address 1개 삭제 실패!");
		}
		
		return resultRowAddress;
		
	}
	
	
	// 주소 select
	// 주문페이지에서 배송지 및 인적사항 입력시 customer, customerAddress 수정
	// 사용하는 곳 : OrderPageNonMemberController
	public int selectAddress(Connection conn, CustomerAddress customerAddress) throws Exception {
		
		int rusultSelect = 0;
		
		String sql = "SELECT address_code addressCode"
				+ "	 FROM customer_address"
				+ "	 WHERE customer_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, customerAddress.getCustomerId());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			rusultSelect = rs.getInt("addressCode");
		}
		
		return rusultSelect;
		
	}


}
