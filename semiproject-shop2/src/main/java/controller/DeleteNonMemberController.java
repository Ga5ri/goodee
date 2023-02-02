package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NonMemberService;
import vo.Customer;
import vo.Emp;

@WebServlet("/nonMember/deleteNonMember")
public class DeleteNonMemberController extends HttpServlet {
	
	private NonMemberService nonMemberService;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 비회원 세션값
		HttpSession session = request.getSession();
		
		// 비회원 값 체크
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
		// System.out.println(customerId+"삭제될 아이디");
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		
		this.nonMemberService = new NonMemberService();
		int resultRow = this.nonMemberService.deleteCustomer(customer);
		
		String targetUrl = null;
		
		// 삭제시 성공,실패
		if(resultRow == 1) {
			targetUrl = "/logout";
		} else {
			targetUrl = "/order/orderPageNonMember";
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
	}
}
