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


@WebServlet("/order/orderList")
public class OrderListController extends HttpServlet {
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
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		// 총 게시글 수
		int resultCnt = 0;
		ArrayList<Orders> list = null;
		String word = request.getParameter("word");
		this.ordersService = new OrdersService();

		// 수정 필요
		if(word == null || word == "") { // 검색어가 없을 경우
			resultCnt = ordersService.cntOrderListServie(customerId);
			list = ordersService.getOrderListByPage(currentPage, rowPerPage, customerId);
			System.out.println("검색어 없음");
		} else { // 검색어가 있을 경우
			resultCnt = ordersService.cntOrderListServie(customerId, word);
			list = ordersService.getOrderListByPage(currentPage, rowPerPage, customerId, word);
			System.out.println("검색어 있음");
		}
		
		// 마지막 페이지
		int lastPage = resultCnt / rowPerPage;
		if(resultCnt % rowPerPage != 0) {
			lastPage++;
		}
		System.out.println("rowPerPage : " + rowPerPage);
		System.out.println("currentPage : " + currentPage);
		System.out.println("lastPage : " + lastPage);
		System.out.println("word : " + word);
		
		request.setAttribute("orderList", list);
		request.setAttribute("currentPage", currentPage); // view에서 필요
		request.setAttribute("rowPerPage", rowPerPage); // view에서 필요
		request.setAttribute("lastPage", lastPage); // view에서 필요
		request.setAttribute("word", word); // view에서 필요		
		
		request.getRequestDispatcher("/WEB-INF/view/order/orderList.jsp").forward(request, response);
	}

}
