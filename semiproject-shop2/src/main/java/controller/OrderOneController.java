package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrdersService;
import vo.Customer;
import vo.Orders;


@WebServlet("/order/orderOne")
public class OrderOneController extends HttpServlet {
	private OrdersService ordersService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 여부확인, 로그인 되어있지 않으면 홈으로 이동
		HttpSession session = request.getSession();		
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		System.out.println(loginCustomer + "아이디");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		String customerId = loginCustomer.getCustomerId();
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		Orders list = null;
		this.ordersService = new OrdersService();

		list = ordersService.getOrderOne(orderCode, customerId);
		System.out.println(list);
		request.setAttribute("orderOne", list);
		request.getRequestDispatcher("/WEB-INF/view/order/orderOne.jsp").forward(request, response);
	}

}
