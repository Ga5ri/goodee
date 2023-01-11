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
import vo.Customer;
import vo.Emp;

@WebServlet("/emp/checkPw")
public class CheckEmpPwController extends HttpServlet {
	
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
		
		String targetUrl = request.getParameter("targetUrl");
		
		// 디버깅
		System.out.println(targetUrl + " <-- targetUrl");
		
		if(targetUrl == null || targetUrl.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		request.setAttribute("targetUrl", targetUrl);
		
		
		request.getRequestDispatcher("/WEB-INF/view/emp/checkPw.jsp").forward(request, response);
		
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
		
		String targetUrl = request.getParameter("targetUrl");
		String empPw = request.getParameter("empPw");
		
		// 디버깅
		System.out.println(targetUrl + " <-- targetUrl");
		
		if(targetUrl == null || empPw == null || targetUrl.equals("") || empPw.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		Emp emp = new Emp();
		emp.setEmpId(loginEmp.getEmpId());
		emp.setEmpPw(empPw);
				
		this.empService = new EmpService();
		
		// 비밀번호 일치하면 true, 틀리면 false
		boolean checkPw = this.empService.checkPw(emp);
		
		// 디버깅
		System.out.println(checkPw + " <-- checkPw");
		
		if(checkPw) {
			
			response.sendRedirect(request.getContextPath() + targetUrl);
			
		} else {
			
			response.sendRedirect(request.getContextPath() + "/emp/checkPw?targetUrl=" + targetUrl);
			
		}
		
		
	}


}
