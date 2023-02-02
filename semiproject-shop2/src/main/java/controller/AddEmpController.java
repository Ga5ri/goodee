package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.EmpService;
import service.OutidService;
import vo.Customer;
import vo.Emp;
import vo.Outid;

@WebServlet("/emp/addEmp")
public class AddEmpController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}		

		request.getRequestDispatcher("/WEB-INF/view/emp/addEmp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		String empName = request.getParameter("empName");
		
		// request null & 공백 체크
		if(empId == null || empPw == null || empName == null || empId.equals("") || empPw.equals("") || empName.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/addEmp");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empPw);
		emp.setEmpName(empName);
		
		// 회원 가입
		this.empService = new EmpService();
		int resultRow = this.empService.addEmp(emp);
		
		String targetUrl = "/emp/addEmp";
		if(resultRow == 1) {
			
			// 회원가입 성공한다면 로그인화면으로
			targetUrl = "/login";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}

}
