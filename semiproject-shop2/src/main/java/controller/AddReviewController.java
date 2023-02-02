package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.Customer;
import vo.Review;

@WebServlet("/review/addReview")
public class AddReviewController extends HttpServlet {
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
		
		// 상품번호 필요
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		Review reviewInfo = null;
		System.out.println(orderCode);
		
		this.reviewService = new ReviewService();
		reviewInfo = reviewService.getInfoForAddReviewService(orderCode);
		System.out.println("reviewInfo : " + reviewInfo);
				
		// view와 공유할 모델데이터 설정
		request.setAttribute("orderCode", reviewInfo.getOrderCode());
		request.setAttribute("filename", reviewInfo.getFilename());
		request.setAttribute("goodsCode", reviewInfo.getGoodsCode());
		request.setAttribute("goodsName", reviewInfo.getGoodsName());
		request.getRequestDispatcher("/WEB-INF/view/review/addReviewForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");

		// 로그인 여부확인
		HttpSession session = request.getSession();
		// 로그인 값 체크  - 비 로그인 시 로그인 창으로
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("로그인 값 없음");
			return;
		}
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String goodsName = request.getParameter("goodsName");
		String reviewMemo = request.getParameter("reviewMemo");
		String createdate = request.getParameter("createdate");
		
		Review review= new Review();
		review.setOrderCode(orderCode);
		review.setGoodsName(goodsName);
		review.setReviewMemo(reviewMemo);
		review.setCreatedate(createdate);

		// 모델호출
		reviewService = new ReviewService();
		reviewService.addReviewService(review);
		
		// view
		response.sendRedirect(request.getContextPath()+"/review/reviewList");
	}
}
