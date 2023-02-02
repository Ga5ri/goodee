package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/modifyEmpByAdmin")
public class ModifyEmpByAdminController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		System.out.println(loginEmp.toString() + " <-- loginEmp");
		
		if(loginEmp == null || loginEmp.getAuthCode() != 0) {
			
			// msg : 관리자만 입장 가능
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empId = request.getParameter("empId");
		
		// null, 공백 검사 
		if(empId == null || empId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		Emp emp = new Emp();
		
		this.empService = new EmpService();
		emp = this.empService.getEmpOne(empId);
		
		request.setAttribute("emp", emp);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/modifyEmpByAdmin.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		System.out.println(loginEmp.toString() + " <-- loginEmp");
		
		if(loginEmp == null || loginEmp.getAuthCode() != 0) {
			
			// msg : 관리자만 입장 가능
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empCode = request.getParameter("empCode");
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String active = request.getParameter("active");
		String authCode = request.getParameter("authCode");
		
		// null, 공백 검사
		if(empCode == null || empId == null || empName == null || active == null || authCode == null
				 || empCode.equals("") || empId.equals("") || empName.equals("") || active.equals("") || authCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpCode(Integer.parseInt(empCode));
		emp.setEmpName(empName);
		emp.setActive(active);
		emp.setAuthCode(Integer.parseInt(authCode));
		
		this.empService = new EmpService();
		int resultRow = this.empService.modifyEmpByAdmin(emp);
		
		// 수정 실패 시
		String targetUrl = "/emp/empList";
		
		if(resultRow == 1) {

			// 수정 성공하면
			targetUrl = "/emp/empOne?empId=" + empId;
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
				
		
	}

}
