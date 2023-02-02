package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/emp/modifyEmpPw")
public class ModifyEmpPwController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/emp/modifyEmpPw.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
	
		// request
		String empId = loginEmp.getEmpId();
		String empNewPw = request.getParameter("empNewPw");
		String empNewPwCheck = request.getParameter("empNewPwCheck");
		
		// request null & 공백 체크
		if(empId == null || empNewPw == null || empNewPwCheck == null
				 || empId.equals("") || empNewPw.equals("") || empNewPwCheck.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		// empNewPw == empNewPwCheck 체크
		if(!empNewPw.equals(empNewPwCheck)) {
			// 새로운 비밀번호와 새로운 비밀번호 확인이 다를경우
			System.out.println("새 비밀번호와 새 비밀번호 확인이 다릅니다.");
			response.sendRedirect(request.getContextPath() + "/emp/modifyEmpPw");
			return;
		}
		
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empNewPw);
		
		this.empService = new EmpService();
		int resultRow = this.empService.modifyEmpPw(emp);
		
		// 비밀번호 변경 실패하면
		String targetUrl = "/emp/modifyEmpPw";
		
		if(resultRow == 1) {
			// 변경 성공하면
			
			// 받아온 세션값을 완전히 삭제.
			request.getSession().invalidate();
			
			targetUrl = "/login";			
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);		
		
	}

}
