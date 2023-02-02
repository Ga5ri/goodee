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

@WebServlet("/customer/modifyCustomerPw")
public class ModifyCustomerPwController extends HttpServlet {
	
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
		
		request.getRequestDispatcher("/WEB-INF/view/customer/modifyCustomerPw.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//==================================================
		// 변경할 비밀번호 확인은 자바스크립트로(현재 미구현) 
		//==================================================
		
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
		String customerId = loginCustomer.getCustomerId();
		String customerNewPw = request.getParameter("customerNewPw");
		String customerNewPwCheck = request.getParameter("customerNewPwCheck");
		
		// request null & 공백 체크
		if(customerId == null || customerNewPw == null || customerNewPwCheck == null 
				 || customerId.equals("") || customerNewPw.equals("") || customerNewPwCheck.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}

		// customerNewPw == customerNewPwCheck 체크
		if(!customerNewPw.equals(customerNewPwCheck)) {
			// 새로운 비밀번호와 새로운 비밀번호 확인이 다를경우
			System.out.println("새 비밀번호와 새 비밀번호 확인이 다릅니다.");
			response.sendRedirect(request.getContextPath() + "/customer/modifyCustomerPw");
			return;
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerNewPw);
		
		this.customerService = new CustomerService();
		int resultRow = this.customerService.modifyCustomerPw(customer);
		
		// 변경 실패하면
		String targetUrl = "/customer/modifyCustomerPw";
		
		if(resultRow == 1) {
			// 변경 성공하면
			
			// 받아온 세션값을 완전히 삭제.
			request.getSession().invalidate();
			
			targetUrl = "/login";			
			
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
		
	}

}
