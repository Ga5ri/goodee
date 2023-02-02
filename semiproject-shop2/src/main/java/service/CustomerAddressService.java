package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerAddressDao;
import util.DBUtil;
import vo.CustomerAddress;

public class CustomerAddressService {

	private CustomerAddressDao customerAddressDao;
	
	// 주소 목록
	// 사용하는 곳 : CustomerAddressListController
	public ArrayList<CustomerAddress> getAddressList(CustomerAddress customerAddress) {
		
		ArrayList<CustomerAddress> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			list = this.customerAddressDao.selectAddressList(conn, customerAddress);
			
			conn.commit();
			
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
		
		return list;
		
	}
	
	// 주소 1개 추가
	// 사용하는 곳 : AddAddresssController
	public int addAddress(CustomerAddress customerAddress) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			resultRow = this.customerAddressDao.addAddress(conn, customerAddress);
			
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
	
	// 주소 1개 출력
	// 사용하는 곳 : ModifyAddressController
	public CustomerAddress getAddressOne(CustomerAddress customerAddress) {
		
		CustomerAddress ca = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			ca = this.customerAddressDao.selectAddressOne(conn, customerAddress);
			
			conn.commit();
			
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
		
		return ca;
		
		
		
		
		
	}
	
	
	// 주소 1개 수정
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
	
	
	
	
	// 주소 1개 삭제
	// 1) 해당 ID의 주소 갯수 구한 뒤
	// 2) 2개 이상일 때만 삭제 가능(최소 1개는 보관)
	// 사용하는 곳 : DeleteAddressController
	public int deleteAddress(CustomerAddress customerAddress) {
		
		int resultRow = 0;
		int countAddress = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.customerAddressDao = new CustomerAddressDao();
			
			countAddress = this.customerAddressDao.countAddress(conn, customerAddress);
			
			if(countAddress > 1) {
				resultRow = this.customerAddressDao.deleteAddress(conn, customerAddress);
			} else {
				System.out.println("[CustomerAddressService] 주소가 1개이므로 삭제할 수 없습니다.");
			}
			
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
	
	
	
}
