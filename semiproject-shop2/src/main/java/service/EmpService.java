package service;

import java.sql.Connection;	
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerDao;
import dao.EmpDao;
import dao.OutidDao;
import util.DBUtil;
import util.Page;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	private CustomerDao customerDao;
	private OutidDao outidDao;
	
	// Login
	// 사용하는곳 LoginController
	public Emp login(Emp emp) {
		this.empDao = new EmpDao();
		Emp returnEmp= new Emp();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			empDao= new EmpDao();
			returnEmp = empDao.selectEmpLogin(conn, emp);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return returnEmp;	
	}
	

	// EmpList 출력
	// 사용하는 곳 : EmpListController
	public ArrayList<Emp> getEmpList(String[] active, int[] authCode, String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<Emp> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			list = new ArrayList<Emp>();
			
			int beginRow = Page.getBeginRow(currentPage, rowPerPage);
			
			this.empDao = new EmpDao();
			list = this.empDao.selectEmpList(conn, active, authCode, searchCategory, searchText, beginRow, rowPerPage);
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
	
	
	// EmpList 페이징
	// 사용하는 곳 : EmpListController
	public ArrayList<HashMap<String, Object>> getPage(String[] active, int[] authCode, String searchCategory, String searchText, int currentPage, int rowPerPage) {
		
		ArrayList<HashMap<String, Object>> list = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			this.empDao = new EmpDao();
			
			// 페이징 처리
			int pageLength = 10;
			int count = this.empDao.countEmp(conn, active, authCode, searchCategory, searchText);
			
			int previousPage = Page.getPreviousPage(currentPage, pageLength);
			int nextPage = Page.getNextPage(currentPage, pageLength);
			int lastPage = Page.getLastPage(count, rowPerPage);
			ArrayList<Integer> pageList = Page.getPageList(currentPage, pageLength);
			
			list = new ArrayList<HashMap<String, Object>>();
			
			HashMap<String, Object> m1 = new HashMap<String, Object>();
			m1.put("previousPage", previousPage);
			m1.put("nextPage", nextPage);
			m1.put("lastPage", lastPage);
			m1.put("pageList", pageList);
			
			list.add(m1);
			
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
	
	// empOne emp 한명의 정보를 출력
	// 사용하는 곳 : EmpOneController, ModifyEmpController, ModifyEmpByAdminController
	public Emp getEmpOne(String empId) {

		Emp resultEmp = null;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultEmp = this.empDao.selectEmpOne(conn, empId);
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
		
		return resultEmp;
		
	}
	
	

	
	
	// 관리자가 emp 수정
	// 사용하는 곳 : ModifyEmpByAdminController
	public int modifyEmpByAdmin(Emp emp) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultRow = this.empDao.modifyEmpByAdmin(conn, emp);

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
		
	
	
	
	
	// emp 수정
	// 사용하는 곳 : ModifyEmpController
	public int modifyEmp(Emp emp) {

		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultRow = this.empDao.modifyEmp(conn, emp);
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
		
		return resultRow;
		
	}
	
	
	// emp 삭제
	// 탈퇴 or 관리자 삭제 시 
	// 1) emp 삭제
	// 사용하는곳 : DeleteEmpController
	public int deleteEmp(int empCode) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();
			resultRow = this.empDao.deleteEmp(conn, empCode);
			
			if(resultRow == 1) {
				// 삭제 성공하면
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
	
	
	
	
	
	// emp 추가 -> 회원 가입
	// 1) customerId, empId, outid ID 중복확인
	// 2) emp 추가
	// 3) 모두 성공하면 commit
	// 사용하는 곳 : AddEmpController
	public int addEmp(Emp emp) {
		
		int resultRow = 0;
		
		boolean checkEId = false;
		boolean checkCId = false;
		boolean checkOId = false;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			this.empDao = new EmpDao();		// ID 중복확인 및 회원가입 
			this.customerDao = new CustomerDao();	// ID 중복확인
			this.outidDao = new OutidDao();		// ID 중복확인
			
			// checkCId, checkEId, checkOId
			// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
			checkEId = this.empDao.checkEmpId(conn, emp.getEmpId());
			checkCId = this.customerDao.checkCustomerId(conn, emp.getEmpId());
			checkOId = this.outidDao.checkOutid(conn, emp.getEmpId());
			
			if(checkCId || checkEId || checkOId) {
				// 셋중 하나라도 중복(true)되면 가입 불가
				
				System.out.println("EmpService ID 중복입니다.");
				return resultRow;
				
			}
			
			resultRow = this.empDao.addEmp(conn, emp);
			
			if(resultRow == 1) {
				
				// 회원 가입 성공하면
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
	
	// 비밀번호 확인
	// 사용하는 곳 : CheckEmpPwController
	//				 (pw 확인 후 이동 DeleteEmpController, ModifyEmpController)
	public boolean checkPw(Emp emp) {
		
		boolean result = false;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.empDao = new EmpDao();
			result = this.empDao.checkPw(conn, emp);
			
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
		
		
		return result;
		
	}
		

	
	// 비밀번호 변경
	// 사용하는 곳 : ModifyEmpPwController
	public int modifyEmpPw(Emp emp) {
		
		int resultRow = 0;
		
		Connection conn = null;
		
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.empDao = new EmpDao();
			resultRow = this.empDao.modifyEmpPw(conn, emp);
			
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
