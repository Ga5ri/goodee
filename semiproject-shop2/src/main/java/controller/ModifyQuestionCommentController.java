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
import vo.QuestionComment;

@WebServlet("/questionComment/modifyQuestionComment")
public class ModifyQuestionCommentController extends HttpServlet {
	private QuestionCommentService questionCommentService;
	
	// 고객센터 문의글 수정
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/questionComment/modifyQuestionComment.jsp
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
			
		// 매개변수, request 값세팅
		Emp loignEmp = loginEmp;
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		
		// 모델 호출
		HashMap<String, Object> q = new HashMap<String, Object>();
		this.questionCommentService = new QuestionCommentService();
		q = questionCommentService.getQuestionOne(questionCode);
		
		request.setAttribute("q", q);
		request.setAttribute("loginEmp", loginEmp.getEmpId());
		
		// 문의글 수정폼 View
		request.getRequestDispatcher("/WEB-INF/view/questionComment/modifyQuestionComment.jsp").forward(request, response);
		
	}
	// 고객센터 문의글 수정액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// VIEW -> /WEB-INF/view/questionComment/modifyQuestionComment.jsp
		// 로그인 후에만 진입가능
		HttpSession session = request.getSession(); 
				
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// request 값세팅 
		request.setCharacterEncoding("UTF-8"); 
		int commentCode = Integer.parseInt(request.getParameter("commentCode"));
		String commentMemo= request.getParameter("commentMemo");
		
		// 메서드 호출시 매개변수
		QuestionComment questionComment = new QuestionComment();
		questionComment.setCommentCode(commentCode);
		questionComment.setCommentMemo(commentMemo);
		
		// 모델호출
		this.questionCommentService = new QuestionCommentService();
		int resultRow = questionCommentService.modifyQuestionComment(questionComment, loginEmp);
				
		// 글작성 성공
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/questionComment/questionCommentList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/questionComment/modfiyQuestionComment?commentCode="+commentCode); 
		
		
	}
}
