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
import service.QuestionService;
import vo.Emp;
import vo.Question;

@WebServlet("/questionComment/addQuestionComment")
public class AddQuestionCommentController extends HttpServlet {
	private QuestionCommentService questionCommentService;
	// 고객센터(관리자 페이지) 답변글 작성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/question/addQuestion.jsp
		// 관리지만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 관리자 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		request.setCharacterEncoding("UTF-8"); // request 한글코딩	
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		
		// 모델호출
		this.questionCommentService = new QuestionCommentService();
		HashMap<String, Object> q = questionCommentService.selectOrderCode(questionCode);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("q", q);
		
		// 글작성 폼 View
		request.getRequestDispatcher("/WEB-INF/view/questionComment/addQuestionComment.jsp").forward(request, response);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션정보 :  session.setAttribute(loginEmp, Emp타입) 
		// 관리지만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 관리자 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// request 값세팅, 세션 Emp 값 세팅
		request.setCharacterEncoding("UTF-8"); 
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		String commentMemo = request.getParameter("commentMemo"); 
		String empId = loginEmp.getEmpId();
		
		// 모델호출
		this.questionCommentService = new QuestionCommentService();
		int resultRow = questionCommentService.addQuestionComment(questionCode, empId, commentMemo);
				
		// 글작성 성공
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/questionComment/questionCommentList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/questionComment/addQuestion"); 
	}
}
