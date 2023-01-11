package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Emp;

public class EmpDao {
		
	// Login
	// 사용하는 곳: LoginController
	public Emp selectEmpLogin(Connection conn, Emp emp) throws Exception {
		Emp retrunEmp = null;
		String sql = "SELECT emp_code empCode, emp_id empId, emp_name empName"
				+ " FROM emp WHERE emp_id =? AND emp_pw = password(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			retrunEmp= new Emp();
			retrunEmp.setEmpCode(rs.getInt("empCode"));
			retrunEmp.setEmpId(rs.getString("empId"));
			retrunEmp.setEmpName(rs.getString("empName"));
		}
		rs.close();
		stmt.close();
		return retrunEmp;
	}
	

	
	// empList
	// 사용하는 곳 : EmpListController
	// 검색 페이징 포함
	public ArrayList<Emp> selectEmpList(Connection conn, String searchCategory, String searchText, int beginRow, int rowPerPage) throws Exception {
		
		ArrayList<Emp> list = null;
		
		String sql = "SELECT emp_code empCode"
				+ "			, emp_id empId"
				+ "			, emp_name empName"
				+ "			, active"
				+ "			, auth_code authCode"
				+ "			, createdate"
				+ "	 FROM emp"
				+ "	 WHERE " + searchCategory + " LIKE ?"
				+ "	 ORDER BY emp_code DESC"
				+ "	 LIMIT ?, ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1,  "%" + searchText + "%");
		stmt.setInt(2,  beginRow);
		stmt.setInt(3, rowPerPage);
		
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<Emp>();
		
		while(rs.next()) {
			
			Emp emp = new Emp();
			emp.setEmpCode(rs.getInt("empCode"));
			emp.setEmpId(rs.getString("empId"));
			emp.setEmpName(rs.getString("empName"));
			emp.setActive(rs.getString("active"));
			emp.setAuthCode(rs.getInt("authCode"));
			emp.setCreatedate(rs.getString("createdate"));
			
			list.add(emp);
			
		}
		
		return list;
		
	}
	
	
	// 검색 후 emp 총 카운트
	// 사용하는 곳 : EmpListController
	public int countEmp(Connection conn, String searchCategory, String searchText) throws Exception {
		
		int resultCount = 0;
		
		String sql = "SELECT COUNT(emp_code) cnt"
				+ "	 FROM emp"
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
	
	// empOne 한명의 정보 출력
	// 사용하는 곳 : EmpOneController, ModifyEmpController, ModifyEmpByAdminController
	public Emp selectEmpOne(Connection conn, String empId) throws Exception {
		
		Emp resultEmp = null;
		
		String sql = "SELECT emp_code empCode"
				+ "			, emp_id empId"
				+ "			, emp_name empName"
				+ "			, active"
				+ "			, auth_code authCode"
				+ "			, createdate"
				+ "	 FROM emp"
				+ "	 WHERE emp_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, empId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			
			resultEmp = new Emp();
			
			resultEmp.setEmpCode(rs.getInt("empCode"));
			resultEmp.setEmpId(rs.getString("empId"));
			resultEmp.setEmpName(rs.getString("empName"));
			resultEmp.setActive(rs.getString("active"));
			resultEmp.setAuthCode(rs.getInt("authCode"));
			resultEmp.setCreatedate(rs.getString("createdate"));
			
		}
		
		return resultEmp;
		
	}
	
	
	
	
	// 관리자가 emp 수정
	// 사용하는 곳 : ModifyEmpByAdminController
	public int modifyEmpByAdmin(Connection conn, Emp emp) throws Exception {
		int resultRow = 0;
		
		String sql = "UPDATE emp"
				+ "	 SET emp_name = ?"
				+ "		, active = ?"
				+ "		, auth_code = ?"
				+ "	 WHERE emp_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, emp.getEmpName());
		stmt.setString(2, emp.getActive());
		stmt.setInt(3, emp.getAuthCode());
		stmt.setInt(4, emp.getEmpCode());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("empByAdmin 수정 성공!");
		} else {
			System.out.println("empByAdmin 수정 실패!");
		}
		
		return resultRow;
		
	}
	
	
	
	
	
	// emp 내정보수정
	// 사용하는 곳 : ModifyEmpController
	
	public int modifyEmp(Connection conn, Emp emp) throws Exception {
		int resultRow = 0;
		
		String sql = "UPDATE emp"
				+ "	 SET emp_name = ?"
				+ "	 WHERE emp_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, emp.getEmpName());
		stmt.setString(2, emp.getEmpId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("emp 수정 성공!");
		} else {
			System.out.println("emp 수정 실패!");
		}
		
		return resultRow;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	// emp 삭제
	// 사용하는 곳 : DeleteEmpController
	public int deleteEmp(Connection conn, int empCode) throws Exception {
		
		int resultRow = 0;
		
		String sql = "DELETE"
				+ "	 FROM emp"
				+ "	 WHERE emp_code = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, empCode);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("emp 삭제 성공!");
		} else {
			System.out.println("emp 삭제 실패!");
		}
		
		return resultRow;
		
	}
	
	// emp 회원가입
	// 1) emp ID 중복확인
	// true : ID가 이미 존재(가입불가) false : ID 사용 가능(가입가능)
	// 사용하는 곳 : AddCustomerController, AddEmpController
	public boolean checkEmpId(Connection conn, String empId) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT emp_id empId"
				+ "	 FROM emp"
				+ "	 WHERE emp_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, empId);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
	// emp 회원가입
	// 2) 회원가입
	// 사용하는 곳 : AddEmpController
	public int addEmp(Connection conn, Emp emp) throws Exception {
		
		int resultRow = 0;
		
		String sql = "INSERT INTO emp ("
				+ "			emp_id"
				+ "			, emp_pw"
				+ "			, emp_name"
				+ "			, active"
				+ "			, auth_code"
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
		
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		stmt.setString(3, emp.getEmpName());
		stmt.setString(4, "N");		// 초기값 설정해야함
		stmt.setInt(5, 0);
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("emp 회원 가입 성공");
		} else {
			System.out.println("emp 회원 가입 실패");
		}
		
		
		return resultRow;
		
	}
	

	// 비밀번호 확인
	// 사용하는 곳 : CheckEmpPwController
	//				 (pw 확인 후 이동 DeleteEmpController, ModifyEmpController, ModifyEmpPwController)
	// true : 비밀번호 일치(메뉴사용가능) / false : 불일치(메뉴사용불가)
	public boolean checkPw(Connection conn, Emp emp) throws Exception {
		
		boolean result = false;
		
		String sql = "SELECT emp_id"
				+ "	 FROM emp"
				+ "	 WHERE emp_id = ?"
				+ "		 AND emp_pw = PASSWORD(?)";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = true;
		}
		
		return result;
		
	}
	
	
	// emp 비밀번호 변경
	// 사용하는 곳 : ModifyEmpPwController
	public int modifyEmpPw(Connection conn, Emp emp) throws Exception {
		
		int resultRow = 1;
		
		String sql = "UPDATE emp"
				+ "	 SET emp_pw = PASSWORD(?)"
				+ "	 WHERE emp_id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, emp.getEmpPw());
		stmt.setString(2, emp.getEmpId());
		
		resultRow = stmt.executeUpdate();
		
		if(resultRow == 1) {
			System.out.println("emp pw 변경 성공");
		} else {
			System.out.println("emp pw 변경 실패");
		}
		
		return resultRow;
		
	}
	
	
	
	
	
	
	
	
}
