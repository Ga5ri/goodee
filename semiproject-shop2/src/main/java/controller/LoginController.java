package controller;
	
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.*;
import vo.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private EmpService empService;
	private CustomerService customerService;
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/login.jsp
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if((request.getParameter("customerId") != null) && (request.getParameter("customerPw") != null)) {
			System.out.println(loginCustomer);
		} else if((request.getParameter("empId") != null) && (request.getParameter("empPw") != null)) {
			System.out.println(loginEmp);
		}
		
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		// 로그아웃 상태에서 구매버튼 클릭 -> 로그인, 비회원 로그인 후 상품 정보 조회를 위한 정보 
		int goodsCode = 0;
		if(request.getParameter("goodsCode") != null) {
		goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		}
		System.out.println("goodsCode : " + goodsCode);

		request.setAttribute("goodsCode", goodsCode);
		// 로그인 폼 View
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	
	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션정보 :  session.setAttribute("loginEmp" or "loginCustomer", Customer 타입 또는 Emp타입) 
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer != null || loginEmp != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
			
		// request 값세팅
		request.setCharacterEncoding("UTF-8");
		String customerId = null;
		String customerPw = null;
		Customer customer = null; // 메서드 호출시 매개변수
		String empId = null;
		String empPw = null;
		Emp emp = null; // 메서드 호출시 매개변수
		
		//System.out.println(request.getParameter("customerId"));
		//System.out.println(request.getParameter("customerPw"));
		//System.out.println(request.getParameter("empId"));
		//System.out.println(request.getParameter("empPw"));
		
		// 로그인 시도된 값이 고객인지 사원인지 구분
		if((request.getParameter("customerId") != null) && (request.getParameter("customerPw") != null)) {
			customer = new Customer(); 
			customer.setCustomerId(request.getParameter("customerId"));
			customer.setCustomerPw(request.getParameter("customerPw"));
			System.out.println(customer.getCustomerId()+"님의 고객 아이디로 로그인이 시도되었습니다."); // 디버깅
			// 모델 호출
			this.customerService = new CustomerService();			
			Customer returnCustomer = customerService.login(customer);
			// 결과값 있다면
			session.setAttribute("loginCustomer", returnCustomer);
			
			// 구매 선택 후 로그인 요청 시 addOrder로 바로가기 위한 정보
			int goodsCode = 0;
			if(request.getParameter("goodsCode") != null) {
				goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
				session.setAttribute("goodsCode", goodsCode);
				System.out.println("login goodsCode : " + goodsCode);
			    response.sendRedirect(request.getContextPath() + "/order/addOrder");
			    return;
			} else {
		    response.sendRedirect(request.getContextPath() + "/home");
		    return;
			}
	     } else if((request.getParameter("empId") != null) && (request.getParameter("empPw") != null)) {
	    	emp = new Emp(); //  
			emp.setEmpId(request.getParameter("empId"));
			emp.setEmpPw(request.getParameter("empPw"));
			System.out.println(emp.getEmpId()+"님의 사원아이디로 로그인이 시도되었습니다."); // 디버깅
			// 모델 호출
			this.empService = new EmpService();						 
			Emp returnEmp = empService.login(emp);
			// Result
			if(returnEmp == null){ // 결과값이 없다면
				response.sendRedirect(request.getContextPath()+"/login");
				return;
			    }
			
			// 결과값이 있다면
			session.setAttribute("loginEmp", returnEmp);
		    response.sendRedirect(request.getContextPath() + "/home");
	     } 
	}
}