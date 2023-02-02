package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import service.QuestionService;
import vo.Customer;
import vo.Emp;
import vo.Question;
@WebServlet("/question/questionList")
public class QuestionListController extends HttpServlet {
	private QuestionService questionService;
	
	// 고객센터
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
				
		// VIEW -> /WEB-INF/view/question/questionList.jsp
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 세션의 ID 매개값으로 세팅
		Customer customer = new Customer(); 
		customer.setCustomerId("loginCustomer");
		
		String word = ("");
		if(request.getParameter("word") != null) {
			word =request.getParameter("word");
		} 
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}   
		
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		// 모델 호출
		this.questionService = new QuestionService();
		
		// questionList
		ArrayList<HashMap<String, Object>> list = questionService.getQuestionListByPage(currentPage, rowPerPage, word);
				
		// questionPage
		ArrayList<HashMap<String, Object>> pageList = this.questionService.getPage(word, currentPage, rowPerPage); 
		
		for(HashMap<String, Object> hm : pageList) {
			
			request.setAttribute("previousPage", (int) hm.get("previousPage"));
			request.setAttribute("nextPage", (int) hm.get("nextPage"));
			request.setAttribute("lastPage", (int) hm.get("lastPage"));
			request.setAttribute("pageList", (ArrayList<Integer>) hm.get("pageList"));
		}
		
		request.setAttribute("questionlist", list);
		request.setAttribute("word", word);
		request.setAttribute("currentPage", currentPage); 
		request.setAttribute("rowPerPage", rowPerPage);
		
		
		// 고객센터 폼 View
		request.getRequestDispatcher("/WEB-INF/view/question/questionList.jsp").forward(request, response);
	}
}
