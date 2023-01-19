package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import service.CustomerService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;

@WebServlet("/order/orderPageNonMember")
public class OrderPageNonMember extends HttpServlet {
     
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		// 랜덤 객체 생성
		String rdA;
		String rdB;
		String rdC;
		Random rd = new Random();	
		rdA = Integer.toString(rd.nextInt(100));
		rdB = Integer.toString(rd.nextInt(100));
		rdC = Integer.toString(rd.nextInt(100));
		
		// 비회원 객체 생성  
		// 비회원 ID 맨앞 temp로 시작		
		String customerId = "temp"+ 1 + 1 + 1;
		String customerPw = "temp"+ rdA + rdB + rdC;
		String customerName = "tempName";
		String customerPhone = "tempPhone";
		String address = "tempAddress";
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		System.out.println(customerId);
		
		// 비회원 아이디 생성
		this.customerService = new CustomerService();		
		int resultRow = this.customerService.addCustomer(customer, address);
		
		String targetUrl = "/customer/addCustomer";
		if(resultRow == 1) {
			
			// 비회원 아이디 생성 성공시 결제완료페이지로
			targetUrl = "/home";
			
		}
		
		request.getRequestDispatcher("/WEB-INF/view/order/orderPageNonMember.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/goods/goodsList");
			return;
		}
		
		
		// 업데이트 할 정보 셋팅
		String customerId = loginCustomer.getCustomerId();
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String address = request.getParameter("address");
		
		// request null & 공백 체크
		if(customerId == null || customerPw == null || customerName == null || customerPhone == null || address == null
				 || customerId.equals("") || customerPw.equals("") || customerName.equals("") || customerPhone.equals("") || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addCustomer");
			return;
			
		}
		
		// 비회원 주문건에 대한 정보 업데이트(인적사항)
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		// 비회원 주문건에 대한 정보 업데이트(주소)
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddress(address);
		
		
		
	}
}
