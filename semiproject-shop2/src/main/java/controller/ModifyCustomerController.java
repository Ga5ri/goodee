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

@WebServlet("/customer/modifyCustomer")
public class ModifyCustomerController extends HttpServlet {
	
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
		String customerId = loginCustomer.getCustomerId();
		
		// request null & 공백 검사
		if(customerId == null || customerId.equals("")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		Customer customer = new Customer();
		
		this.customerService = new CustomerService();
		customer = this.customerService.getCustomerOne(customerId);
		
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/modifyCustomer.jsp").forward(request, response);
		
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
		String customerId = request.getParameter("customerId");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		customer.toString();
		// null, 공백 검사
		if(customerId == null || customerName == null || customerPhone == null
				 || customerId.equals("") || customerName.equals("") || customerPhone.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		
		this.customerService = new CustomerService();
		int resultRow = this.customerService.modifyCustomer(customer);
		
		// 수정 실패하면
		String targetUrl = "/customer/modifyCustomer";
		
		if(resultRow == 1) {
			
			// 수정 성공하면
			// msg : 내정보가 수정되었습니다.
			System.out.println("customer 내정보가 수정되었습니다.");
			targetUrl = "/home";
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
	}

}
