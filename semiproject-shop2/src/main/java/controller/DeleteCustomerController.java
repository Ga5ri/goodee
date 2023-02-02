package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.CustomerService;
import service.OutidService;
import service.PwHistoryService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;
import vo.PwHistory;

@WebServlet("/customer/deleteCustomer")
public class DeleteCustomerController extends HttpServlet {
	
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
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerId = request.getParameter("customerId");
		
		// request null & 공백 체크 
		if(customerId == null || customerId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		
		// customer 삭제
		this.customerService = new CustomerService();
		int resultRow = this.customerService.deleteCustomer(customer);

		
		String targetUrl = null;
		
		// 삭제 실패시
		if(loginEmp != null && loginEmp.getAuthCode() == 0) {
			// 관리자라면
			targetUrl = "/customer/customerList";
		} else {
			// customer 라면
			targetUrl = "/home";
		}
		
		
		// 삭제 성공시
		if(resultRow == 1) {
			if(loginEmp != null && loginEmp.getAuthCode() == 0) {
				// 관리자라면
				targetUrl = "/customer/customerList";
			} else {
				// customer 라면
				// 받아온 세션값을 완전히 삭제.
				request.getSession().invalidate();
				
				targetUrl = "/login";
			}
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}

}
