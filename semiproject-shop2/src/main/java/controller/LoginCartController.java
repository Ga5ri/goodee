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

@WebServlet("/cart/loginCart")
public class LoginCartController extends HttpServlet {
	
	private CustomerService customerService; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// VIEW -> /WEB-INF/view/login.jsp
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		// 로그인 폼 View
		request.getRequestDispatcher("/WEB-INF/view/cart/loginCart.jsp").forward(request, response);
	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// VIEW -> /WEB-INF/view/login.jsp
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		// request
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");

		// request null & 공백 체크
		if(customerId == null || customerPw == null
				 || customerId.equals("") || customerId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/cart/loginCart");
			return;
			
		}
		
		Customer customer = new Customer(); 
		customer.setCustomerId(customerId);
		customer.setCustomerPw(customerPw);
		System.out.println(customer.getCustomerId()+"님의 고객 아이디로 로그인이 시도되었습니다."); // 디버깅
		// 모델 호출
		this.customerService = new CustomerService();			
		Customer returnCustomer = customerService.login(customer);
		// 결과값 있다면
		session.setAttribute("loginCustomer", returnCustomer);
	    response.sendRedirect(request.getContextPath() + "/cart/customerCartList?action=cartList");
		
	}

}
