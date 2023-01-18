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

import service.QuestionService;
import vo.Customer;
import vo.Emp;

@WebServlet("/question/questionListUser")
public class QuestionListUserController extends HttpServlet {
	private QuestionService questionService;

		// 고객센터
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// VIEW -> /WEB-INF/view/question/questionListUser.jsp
			// 로그인 후에만 진입가능(세션값 request)
			HttpSession session = request.getSession(); 
			
			// 로그인 값 체크
			Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
			Emp loginEmp = (Emp)session.getAttribute("loginEmp");
			if(loginCustomer == null && loginEmp == null) {
				response.sendRedirect(request.getContextPath()+"/login");
				return;
			}
			
			// 페이징에 쓸 값 세팅
			int cnt = 0;
			int currentPage = 1;
			   if(request.getParameter("currentPage") != null) {
				   currentPage = Integer.parseInt(request.getParameter("currentPage"));
			   }
			int rowPerPage = 10;
			int beginRow = (currentPage-1) * rowPerPage;
			String word = ("");
			   if(request.getParameter("word") != null) {
				   word =request.getParameter("word");
			   } 
			// 모델 호출
			this.questionService = new QuestionService();
			request.setCharacterEncoding("UTF-8"); // request 한글코딩	
			
			// 모델 리스트 및 페이징
			ArrayList<HashMap<String, Object>> list = questionService.getQuestionListUserByPage(beginRow, rowPerPage, loginCustomer, word);
			
			// 카운트
			cnt = questionService.countUser(loginCustomer); 
			int lastPage = (int)(Math.ceil((double)cnt / (double)rowPerPage));
			
			request.setAttribute("questionlist", list);
			request.setAttribute("currentPage", currentPage); 
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("word", word);
			
			// 고객센터 폼 View
			if(loginCustomer != null && loginEmp == null) {
				request.getRequestDispatcher("/WEB-INF/view/question/questionListUser.jsp").forward(request, response);
			}
		}
}
