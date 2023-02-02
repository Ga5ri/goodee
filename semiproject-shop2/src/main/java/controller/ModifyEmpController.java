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

@WebServlet("/emp/modifyEmp")
public class ModifyEmpController extends HttpServlet {

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
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empId = loginEmp.getEmpId();
		
		// request null & 공백 검사 
		if(empId == null || empId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		Emp emp = new Emp();
		
		this.empService = new EmpService();
		emp = this.empService.getEmpOne(empId);
		
		request.setAttribute("emp", emp);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/modifyEmp.jsp").forward(request, response);
		
		
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
	
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		
		// request null & 공백 검사
		if(empId == null || empName == null
				|| empId.equals("") || empName.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		
		this.empService = new EmpService();
		int resultRow = this.empService.modifyEmp(emp);
		
		// 수정 실패하면
		String targetUrl = "/emp/modifyEmp";
		if(resultRow == 1) {
			
			// 수정 성공하면
			// msg : emp 내정보가 수정되었습니다.
			System.out.println("emp 내 정보가 수정되었습니다.");
			targetUrl = "/home";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}
	
	

}
