package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.QuestionDao;
import service.QuestionService;
import util.DBUtil;
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
		int orderCode = (int) q.get("orderCode");
		String customerId = questionService.getQuestionOneCustomerIdByOrderCode(orderCode);
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
		
		// 프로젝트안 upload폴더의 실제 물리적 위치를 반환
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100; // 100Mbyte
				
		// 업로드 폴더내 동일한 이름이 있으면 뒤에 숫자를 추가 
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		// MultipartRequest로 원본 request를 랩핑후에는 스트림을 받을 필요없이  MultipartRequest의 api를 사용하면된다
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
				
		// request 값세팅 
		request.setCharacterEncoding("UTF-8"); 
		int questionCode = Integer.parseInt(mreq.getParameter("questionCode"));
		String category = mreq.getParameter("category"); 
		String questionMemo= mreq.getParameter("questionMemo");
		
		Question modifyQuestion = new Question();
		modifyQuestion.setQuestionCode(questionCode);
		modifyQuestion.setCategory(category);
		modifyQuestion.setQuestionMemo(questionMemo);
		
		// 이미지 파일 검사
	    String contentType = mreq.getContentType("questionImg"); // input type=file
	    if(contentType == null) {
	    	modifyQuestion.setQuestionImg(null);
	    } else { // Null이 아닌경우 체크
	    	 if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
	    		 modifyQuestion.setQuestionImg(mreq.getFilesystemName("questionImg"));
		    } else {
		        System.out.println("*.jpg, *.png파일만 업로드 가능");
		        File f = new File(dir+"\\"+mreq.getFilesystemName("questionImg"));
		        if(f.exists()) {
		           f.delete(); // 이미지가 아닌 파일이 업로드 되었기때문에 삭제
		        }
		     }
	    }
	    
		// 모델호출
		this.questionService = new QuestionService();
		int resultRow = questionService.modifyQuestion(modifyQuestion, dir);
				
		// 글작성 성공 
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/question/questionList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/question/modfiyQuestion?questionCode="+questionCode); 
		
		
	}
}
