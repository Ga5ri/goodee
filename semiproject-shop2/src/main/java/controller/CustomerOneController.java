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

@WebServlet("/customer/customerOne")
public class CustomerOneController extends HttpServlet {
	
	private CustomerService customerService;
	
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
		
		// request
		String customerId = request.getParameter("customerId");
		
		if(customerId == null || customerId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		
		Customer resultCustomer = new Customer();

		this.customerService = new CustomerService();
		resultCustomer = this.customerService.getCustomerOne(customerId);
		
		request.setAttribute("customer", resultCustomer);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/customerOne.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
	
	}

}
