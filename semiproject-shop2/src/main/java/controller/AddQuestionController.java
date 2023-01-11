package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

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

@WebServlet("/question/addQuestion")
public class AddQuestionController extends HttpServlet {
	private QuestionService questionService;
	// 고객센터 문의추가
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/addQuestion.jsp
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		ArrayList<Question> list = new ArrayList<Question>();
		
		// 모델호출
		this.questionService = new QuestionService();
		list = questionService.selectOrdersCode(loginCustomer);
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("ordersCodeList", list);
		
		// 글작성 폼 View
		request.getRequestDispatcher("/WEB-INF/view/question/addQuestion.jsp").forward(request, response);
	}
	
	// 고객센터 문의추가 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션정보 :  session.setAttribute("loginEmp" or "loginCustomer", Customer 타입 또는 Emp타입) 
		// 로그인 전에만 진입가능
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
		int ordersCode = Integer.parseInt(request.getParameter("ordersCode"));
		String category = request.getParameter("category"); 
		String questionMemo= request.getParameter("questionMemo"); 
		
		// 메서드 호출시 매개변수
		Question addQuestion = new Question(); 
		addQuestion.setOrderCode(ordersCode);
		addQuestion.setCategory(category);
		addQuestion.setQuestionMemo(questionMemo);
		
		// 모델호출
		this.questionService = new QuestionService();
		int resultRow = questionService.addQuestion(addQuestion);
				
		// 글작성 성공
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/question/questionList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/question/addQuestion"); 
	}
}
