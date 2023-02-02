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
import service.ReviewService;
import vo.Customer;
import vo.Orders;
import vo.Review;


@WebServlet("/review/reviewList")
public class reviewListController extends HttpServlet {
	private ReviewService reviewService;
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
		ArrayList<Review> list = null;
		String word = request.getParameter("word");
		this.reviewService = new ReviewService();
	
		// 수정 필요
		if(word == null || word == "") { // 검색어가 없을 경우
			resultCnt = reviewService.cntReviewListServie(customerId);
			list = reviewService.getReviewListByPage(currentPage, rowPerPage, customerId);
			System.out.println("검색어 없음");
		} else { // 검색어가 있을 경우
			resultCnt = reviewService.cntReviewListServie(customerId, word);
			list = reviewService.getReviewListByPage(currentPage, rowPerPage, customerId, word);
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
		
		request.setAttribute("reviewList", list);
		request.setAttribute("currentPage", currentPage); // view에서 필요
		request.setAttribute("rowPerPage", rowPerPage); // view에서 필요
		request.setAttribute("lastPage", lastPage); // view에서 필요
		request.setAttribute("word", word); // view에서 필요
		
		request.getRequestDispatcher("/WEB-INF/view/review/reviewList.jsp").forward(request, response);
	}
}
