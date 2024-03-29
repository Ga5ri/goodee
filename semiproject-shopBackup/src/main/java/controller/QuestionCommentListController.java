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

import service.QuestionCommentService;
import vo.Customer;
import vo.Emp;
import vo.QuestionComment;
	
@WebServlet("/questionComment/questionCommentList")
public class QuestionCommentListController extends HttpServlet {
private QuestionCommentService questionCommentService;
	
	// 고객센터(관리자 페이지)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/questionComment/questionCommentList.jsp
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		// 세션값으로 세팅
		Emp emp = new Emp(); 
		emp.setEmpId(loginEmp.getEmpId());
		emp.setEmpName(loginEmp.getEmpName());
		
		// 페이징에 쓸 값 세팅
		request.setCharacterEncoding("UTF-8");
		int cnt = 0;
		int currentPage = 1;
		   if(request.getParameter("currentPage") != null) {
			   currentPage = Integer.parseInt(request.getParameter("currentPage"));
		   }
	    int rowPerPage = 10;
		   if(request.getParameter("rowPerPage") != null) {
			   rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		   }
		int beginRow = (currentPage-1) * rowPerPage;
		
		String category = ("");
		   if(request.getParameter("category") != null) {
			   category =request.getParameter("category");
		   } 
	   
	    String word = ("");
		   if(request.getParameter("word") != null) {
			   word =request.getParameter("word");
		   } 
	   
	    String search = ("search");
		   if(request.getParameter("search") != null)  {
			   search =request.getParameter("search");
		   }
	   String sort = ("sort");
		   if(request.getParameter("sort") != null) {
			   sort =request.getParameter("sort");
		   } 
  
		// 모델 호출
		this.questionCommentService = new QuestionCommentService();
		request.setCharacterEncoding("UTF-8"); // request 한글코딩	
		
		// 카운트
		cnt = questionCommentService.count(); 
		int lastPage = (int)(Math.ceil((double)cnt / (double)rowPerPage));
		
		// 모델 리스트 및 페이징
		ArrayList<HashMap<String, Object>> list = questionCommentService.getQuestionListByPage(beginRow, rowPerPage, word, search, category, sort);
		request.setAttribute("questionlist", list);
		request.setAttribute("currentPage", currentPage); 
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("word", word);
		request.setAttribute("search", search);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);
		
		// 고객센터 (관리자 페이지) View
		request.getRequestDispatcher("/WEB-INF/view/questionComment/questionCommentList.jsp").forward(request, response);
	}
}
