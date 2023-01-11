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
import vo.Question;

@WebServlet("/question/modifyQuestion")
public class ModifyQuestionController extends HttpServlet {
	private QuestionService questionService;
	
	// 고객센터 문의글 수정
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/modifyQuestion.jsp
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
		request.setAttribute("questionCode", questionCode);
		request.setAttribute("customerId", customerId);
		request.setAttribute("loginCustomer", loginCustomer.getCustomerId());
		
		// 문의글 수정폼 View
		request.getRequestDispatcher("/WEB-INF/view/question/modifyQuestion.jsp").forward(request, response);
		
	}
	// 고객센터 문의글 수정액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/modifyQuestion.jsp
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession(); 
				
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
		
		// request 값세팅 
		request.setCharacterEncoding("UTF-8"); 
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		String category = request.getParameter("category"); 
		String questionMemo= request.getParameter("questionMemo");
		
		Question modifyQuestion = new Question();
		modifyQuestion.setQuestionCode(questionCode);
		modifyQuestion.setCategory(category);
		modifyQuestion.setQuestionMemo(questionMemo);
		
		// 모델호출
		this.questionService = new QuestionService();
		int resultRow = questionService.modifyQuestion(modifyQuestion);
				
		// 글작성 성공
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/question/questionList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/question/modfiyQuestion?questionCode="+questionCode); 
		
		
	}
}
