package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerAddressDao;
import dao.CustomerDao;
import dao.EmpDao;
import dao.NonMemberAddressDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Customer;
import vo.CustomerAddress;

public class NonMemberService {
	private CustomerDao customerDao;
	private CustomerAddressDao customerAddressDao;
	private NonMemberAddressDao nonMemberAddressDao;
	private EmpDao empDao;
	private OutidDao outidDao;
	
	
	// 비회원 및 비회원주소 삭제
	// 사용하는곳 : DeleteCustomerController
	public int deleteCustomer(Customer customer) {
		
		int resultRow = 0;
		int resultRowAddress = 0;
		Connection conn = null;
		String customerId = customer.getCustomerId();
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			// 비회원 임시 주소값 삭제 
			// 주소 코드 가져오기 위해 값세팅
			CustomerAddress customerAddress = new CustomerAddress();
			customerAddress.setCustomerId(customer.getCustomerId());
			System.out.println(customerAddress.getCustomerId()+"서비스 주소값11");
			
			// 주소값 삭제
			this.nonMemberAddressDao = new NonMemberAddressDao();
			resultRowAddress = this.nonMemberAddressDao.deleteAddress(conn, customerAddress);
			if(resultRowAddress==1) {
				// customer 삭제
				this.customerDao = new CustomerDao();
				resultRow = this.customerDao.deleteCustomer(conn, customerId);
					if(resultRow == 1) {
						conn.commit();
					}
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
		
	// 주문페이지에서 배송지 및 인적사항 입력시 customer, customerAddress 수정
	// 사용하는 곳 : OrderPageNonMemberController
	public int modifyCustomer(Customer customer, CustomerAddress customerAddress) {

		int resultRow = 0;
		int rusultSelect=0;
		int addressResultRow = 0;
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerDao = new CustomerDao();
			resultRow = this.customerDao.modifyCustomer(conn, customer);
			
			if(resultRow == 1) {
				this.nonMemberAddressDao = new NonMemberAddressDao();
				rusultSelect = this.nonMemberAddressDao.selectAddress(conn, customerAddress);
				customerAddress.setAddressCode(rusultSelect);
				addressResultRow = this.customerAddressDao.modifyAddress(conn, customerAddress);
					if(addressResultRow == 1) {
						conn.commit();
					}
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
				System.out.println(customer.getCustomerId());
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
					if(!checkCId) {
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