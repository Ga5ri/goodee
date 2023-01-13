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
import service.PointService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Goods;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/order/addOrder")
public class AddOrderController extends HttpServlet {
	private OrdersService ordersService;
	private PointService pointService;
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
		
		// 상품번호 ,고객아이디, 배송지 필요
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));	
		Customer customer = null;
		ArrayList<CustomerAddress> customerAddress = null;
		Goods goods = null;	
		
		this.ordersService = new OrdersService();
		customer = ordersService.getCustomerInfoForOrderService(customerId);
		customerAddress = ordersService.getCustomerAddressForOrderService(customerId);
		goods = ordersService.getGoodsForOrderService(goodsCode);		

		
		System.out.println("customer : " + customer);
		System.out.println("customerAddress : " + customerAddress);
		System.out.println("goods : " + goods);
		
		// view와 공유할 모델데이터 설정
		request.setAttribute("goodsCode", goods.getGoodsCode());
		request.setAttribute("goodsName", goods.getGoodsName());
		request.setAttribute("goodsPrice", goods.getGoodsPrice());
		request.setAttribute("loginId", customerId);
		request.setAttribute("customerAddress", customerAddress);
		request.setAttribute("point", customer.getPoint());
		
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("/WEB-INF/view/order/addOrderForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		/*
		// 로그인 여부확인
		HttpSession session = request.getSession();
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		*/
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = request.getParameter("loginId");
		int addressCode = Integer.parseInt(request.getParameter("addressCode"));
		int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		String orderState = request.getParameter("orderState"); // 주문상태는 일단 결제
		String createdate = request.getParameter("createdate");
		
		//포인트 로직 필요
		String pointKind = null;
		int usePoint = Integer.parseInt(request.getParameter("usePoint"));
		
		
		Orders orders = new Orders();
		orders.setGoodsCode(goodsCode);
		orders.setCustomerId(customerId);
		orders.setAddressCode(addressCode);
		
		orders.setOrderQuantity(orderQuantity);
		orders.setOrderPrice(orderPrice);
		orders.setOrderState(orderState);
		orders.setCreatedate(createdate);

		PointHistory pointHistory = new PointHistory();
		pointHistory.setPointKind(pointKind);
		pointHistory.setPoint(usePoint);
		

		if(usePoint == 0) { // 적립만 point_history '적립 예정으로'
			pointKind = "적립예정";
			int earnPoint = Math.round(orderPrice / 100);
			System.out.println(earnPoint);
			pointHistory.setPointKind(pointKind);
			pointHistory.setPoint(earnPoint);
			
		} else { // 포인트 사용 및 기록 : point update, pointHistory --- 수정중
			pointKind = "사용";
			pointHistory.setPointKind(pointKind);
			pointHistory.setPoint(usePoint);
			
		}

		// 모델호출
		ordersService = new OrdersService();
		ordersService.addOrderService(orders, pointHistory);
		//this.pointService = new PointService();
		System.out.println("전달"+orders);
		
		// view
		response.sendRedirect(request.getContextPath()+"/order/orderList");
	}
}
