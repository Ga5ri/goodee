package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionCommentService;
import vo.Customer;
import vo.Emp;

@WebServlet("/questionComment/removeCommentQuestion")
public class RemoveQuestionCommentController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 후에만 진입가능(세션값 request)
		HttpSession session = request.getSession(); 
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 매개변수, request 값세팅
		int commentCode = Integer.parseInt(request.getParameter("commentCode"));
		
		// 모델 호출
		QuestionCommentService questionCommentService = new QuestionCommentService();
		int resultRow = questionCommentService.removeQuestionComment(commentCode, loginEmp);
		
		// 삭제성공
		if(resultRow !=0) {
		response.sendRedirect(request.getContextPath()+"/questionComment/questionCommentList");
		return;
		}
		// 삭제실패
		response.sendRedirect(request.getContextPath() + "/questionComment/questionCommentOne");
	}
}
