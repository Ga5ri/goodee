package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NonMemberService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;

@WebServlet("/order/orderPageNonMember")
public class OrderPageNonMember extends HttpServlet {
     
	private NonMemberService nonMemberService;
	
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
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println("orderPageNonMember의 goodsCode : "+goodsCode);
		
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
		// 서비스 알고리즘 디버깅 코드 String customerId = "temp"+ 1 + 1 + 1; -> ID 중복입니다, ID 대체 시작, 대체 ID 출력 후 비회원 아이디 및 주소 생성
		String customerId = "temp"+ rdA + rdB + rdC;
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
		this.nonMemberService = new NonMemberService();		
		int resultRow = this.nonMemberService.addCustomer(customer, address);
		session.setAttribute("loginCustomer", customer);
		request.getRequestDispatcher("/WEB-INF/view/order/orderPageNonMember.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		System.out.println(session);
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		// 업데이트 할 정보 셋팅
		String customerId = loginCustomer.getCustomerId();
		String customerPw = request.getParameter("customerPw");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		String address = request.getParameter("address");
		
		// request null & 공백 체크
		if( customerName == null || customerPhone == null || address == null
				 || customerName.equals("") || customerPhone.equals("") || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/order/orderPageNonMember");
			return;
			
		}
		
		// 비회원 주문건에 대한 정보 업데이트(인적사항)
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		customer.setCustomerName(customerName);
		customer.setCustomerPhone(customerPhone);
		
		// 디버깅 System.out.println(customer+"test");
		
		// 비회원 주문건에 대한 정보 업데이트(주소)
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setAddress(address);
		customerAddress.setCustomerId(customerId);
		
		this.nonMemberService = new NonMemberService();
		int resultRow = this.nonMemberService.modifyCustomer(customer, customerAddress);
		
		if(resultRow == 1) {
			
			// 비회원 주문완료시 결제페이지로
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/member/login");
	}
}
