package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerAddressDao;
import dao.CustomerDao;
import dao.EmpDao;
import dao.OutidDao;
import dao.PwHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;
import vo.PwHistory;

public class NonMemberService {
	private CustomerDao customerDao;
	private CustomerAddressDao customerAddressDao;
	
	private EmpDao empDao;
	private OutidDao outidDao;
	
	// 주소 수정
	// 사용하는 곳 : ModifyAddresssController
	public int modifyAddress(CustomerAddress customerAddress) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			resultRow = this.customerAddressDao.modifyAddress(conn, customerAddress);
			
			if(resultRow == 1) {
				conn.commit();
			}
			
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
		
		
		return resultRow;
		
	}	
		
	// customer 수정
	// 사용하는 곳 : ModifyCustomerController
	public int modifyCustomer(Customer customer) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			resultRow = this.customerDao.modifyCustomer(conn, customer);

			if(resultRow == 1) {
				conn.commit();
			}
				
			
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
		
		return resultRow;
		
	}	
		
	// customer 추가 -> 비회원 계정 생성
	// 1) customerId, empId, outid ID 중복확인
	// 2) customer, address 추가
	// 3) 모두 성공하면 commit
	// 사용하는 곳 : OrderPageNonMemberController
	public int addCustomer(Customer customer, String address) {
		
		int resultRow = 0;
		
		boolean checkCId = false;
		boolean checkEId = false;
		boolean checkOId = false;
		
		int resultRowA = 0;
		int resultRowC = 0;
		
		CustomerAddress customerAddress = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			this.customerAddressDao = new CustomerAddressDao();	// customer 주소 추가
			this.empDao = new EmpDao();		// ID 중복 확인
			this.outidDao = new OutidDao();	// ID 중복 확인
			
			// checkCId, checkEId, checkOId
			// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
			checkCId = this.customerDao.checkCustomerId(conn, customer.getCustomerId());
			checkEId = this.empDao.checkEmpId(conn, customer.getCustomerId());
			checkOId = this.outidDao.checkOutid(conn, customer.getCustomerId());
			
			if(checkCId || checkEId || checkOId) {
				// 셋중 하나라도 중복(true)되면 가입 불가
				
				System.out.println("nonMember ID 중복입니다.");
				
				// 중복시 대체 ID 
				// 비회원 ID 끝 3자리 숫자에 +1 값 셋팅
				String replaceTemp = customer.getCustomerId();
				int rtLen = replaceTemp.length(); // 대체할 ID 길이
				int rtNum= Integer.parseInt(replaceTemp.substring(rtLen-3, rtLen));
				
				System.out.println("nonMember ID 대체시작합니다.");
				breakOut :
				for (int rt = 0; rt < 10 ; rt++) {
					rtNum = rtNum + 1;
					String replaceId = replaceTemp.substring(0, rtLen-3) + rtNum;
					customer.setCustomerId(replaceId);
					checkCId = this.customerDao.checkCustomerId(conn, customer.getCustomerId());
					if(checkCId) {
						System.out.println("대체 ID는 " + customer.getCustomerId() + "입니다.");
						break breakOut;
					}
				}
			}	
			customerAddress = new CustomerAddress();
			customerAddress.setCustomerId(customer.getCustomerId());
			customerAddress.setAddress(address);
			
			
			resultRowC = this.customerDao.addCustomer(conn, customer);
			resultRowA = this.customerAddressDao.addAddress(conn, customerAddress);
			
			if(resultRowC == 1 && resultRowA == 1) {
				// customer, pwHistory, address 모두 추가 성공하면
				resultRow = 1;
				
				conn.commit();
			}
			
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
		return resultRow;
	}
}