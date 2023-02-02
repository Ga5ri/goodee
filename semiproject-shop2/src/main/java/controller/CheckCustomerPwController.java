package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/customer/checkPw")
public class CheckCustomerPwController extends HttpServlet {
	
	private CustomerService customerService;
	
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
		
		
		request.getRequestDispatcher("/WEB-INF/view/customer/checkPw.jsp").forward(request, response);
		
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
		String customerPw = request.getParameter("customerPw");
		
		// 디버깅
		System.out.println(targetUrl + " <-- targetUrl");
		
		if(targetUrl == null || customerPw == null || targetUrl.equals("") || customerPw.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(loginCustomer.getCustomerId());
		customer.setCustomerPw(customerPw);
		
		
		this.customerService = new CustomerService();
		
		// 비밀번호 일치하면 true, 틀리면 false
		boolean checkPw = this.customerService.checkPw(customer);
		
		System.out.println(checkPw + " <-- checkPw");
		
		if(checkPw) {
			
			response.sendRedirect(request.getContextPath() + targetUrl);
			
		} else {
			
			response.sendRedirect(request.getContextPath() + "/customer/checkPw?targetUrl=" + targetUrl);
			
		}
		
		
	}

}
