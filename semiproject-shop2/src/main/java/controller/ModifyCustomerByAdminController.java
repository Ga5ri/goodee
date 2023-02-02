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

@WebServlet("/customer/modifyCustomerByAdmin")
public class ModifyCustomerByAdminController extends HttpServlet {
	
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
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String customerId = request.getParameter("customerId");
		
		// null, 공백 검사 
		if(customerId == null || customerId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		Customer customer = new Customer();
		
		this.customerService = new CustomerService();
		customer = this.customerService.getCustomerOne(customerId);
		
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("/WEB-INF/view/customer/modifyCustomerByAdmin.jsp").forward(request, response);
		
		
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
		String customerCode = request.getParameter("customerCode");
		String customerId = request.getParameter("customerId");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String point = request.getParameter("point");
		
		// null, 공백 검사
		if(customerCode == null || customerId == null || customerName == null || customerPhone == null || point == null
				 || customerCode.equals("") || customerId.equals("") || customerName.equals("") || customerPhone.equals("") || point.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/customerList");
			return;
			
		}
		
		Customer customer = new Customer();
		customer.setCustomerCode(Integer.parseInt(customerCode));
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		customer.setPoint(Integer.parseInt(point));
		
		this.customerService = new CustomerService();
		int resultRow = this.customerService.modifyCustomerByAdmin(customer);
		
		// 수정 실패 시
		String targetUrl = "/customer/customerList";
		
		if(resultRow == 1) {

			// 수정 성공하면
			targetUrl = "/customer/customerOne?customerId=" + customerId;
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
				
		
	}

}
