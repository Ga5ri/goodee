package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Customer;
import vo.Emp;

@WebServlet("/question/questionOne")
public class QuestionOneController extends HttpServlet {
	private QuestionService questionService;
	
	// 고객센터 문의글 상세보기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/questionOne.jsp
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		request.setCharacterEncoding("UTF-8"); // request 한글코딩	
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		
		// 모델 호출
		HashMap<String, Object> q = new HashMap<String, Object>();
		this.questionService = new QuestionService();
		q = questionService.getQuestionOne(questionCode);
		int ordersCode = (int) q.get("ordersCode");
		String customerId = questionService.getQuestionOneCustomerIdByOrderCode(ordersCode);
		request.setAttribute("q", q);
		request.setAttribute("customerId", customerId);
		request.setAttribute("loginCustomer", loginCustomer.getCustomerId());
		
		// 고객센터 문의사항 상세보기 View
		request.getRequestDispatcher("/WEB-INF/view/question/questionOne.jsp").forward(request, response);
	}
}
