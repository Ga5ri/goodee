package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrdersService;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/order/deleteOrder")
public class DeleteOrderController extends HttpServlet {
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
		System.out.println(customerId);
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		System.out.println(orderCode);
		int point = Integer.parseInt(request.getParameter("point"));
		System.out.println(point);
		// 고객 포인트, 고객 아이디, 주문 코드, 
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setPoint(point);
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);

	    // 모델호출
		OrdersService ordersService = new OrdersService();
		ordersService.deleteOrderService(orders, customer);
		
		// view가 없으므로
		response.sendRedirect(request.getContextPath()+"/order/orderList");
	}
}
