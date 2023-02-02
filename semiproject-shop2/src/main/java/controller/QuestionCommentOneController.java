package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionCommentService;
import vo.Emp;

@WebServlet("/questionComment/questionCommentOne")
public class QuestionCommentOneController extends HttpServlet {
	private QuestionCommentService questionCommentService;
	
	// 고객센터 문의글 상세보기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/questionCommentOne.jsp
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		request.setCharacterEncoding("UTF-8"); // request 한글코딩	
		if(request.getParameter("questionCode") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		// 모델 호출
		HashMap<String, Object> q = new HashMap<String, Object>();
		this.questionCommentService = new QuestionCommentService();
		q = questionCommentService.getQuestionOne(questionCode);
		
		// 모델 호출(작성자 일치 유효성)
		int commentCode = (int) q.get("commentCode");
		String empId = questionCommentService.getQuestionOneEmpIdByCommentCode(commentCode);
		
		// view에서 필요한 값 
		request.setAttribute("q", q);
		request.setAttribute("empId", empId);
		request.setAttribute("loginEmp", loginEmp.getEmpId());
		
		// 고객센터(관리자) 답변 상세보기 View
		request.getRequestDispatcher("/WEB-INF/view/questionComment/questionCommentOne.jsp").forward(request, response);
	}
}
