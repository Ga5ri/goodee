package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
		// 모델호출
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = questionService.selectOrdersCode(loginCustomer);
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("orderCodeList", list);
		
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
		
		// 프로젝트안 upload폴더의 실제 물리적 위치를 반환
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100; // 100Mbyte
		
		// 업로드 폴더내 동일한 이름이 있으면 뒤에 숫자를 추가 
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		// MultipartRequest로 원본 request를 랩핑후에는 스트림을 받을 필요없이  MultipartRequest의 api를 사용하면된다
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// request 값세팅 
		request.setCharacterEncoding("UTF-8"); 
		int orderCode = Integer.parseInt(mreq.getParameter("orderCode"));
		String category = mreq.getParameter("category"); 
		String questionMemo= mreq.getParameter("questionMemo"); 
		
		// 메서드 호출시 매개변수
		Question addQuestion = new Question(); 
		addQuestion.setOrderCode(orderCode);
		addQuestion.setCategory(category);
		addQuestion.setQuestionMemo(questionMemo);
				
		// 이미지 파일 검사
	    String contentType = mreq.getContentType("questionImg"); // input type=file
	    if(contentType == null) {
	    	addQuestion.setQuestionImg(null);
	    } else { // Null이 아닌경우 체크
	    	 if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
		         addQuestion.setQuestionImg(mreq.getFilesystemName("questionImg"));
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
		int resultRow = questionService.addQuestion(addQuestion, dir);
				
		// 글작성 성공
		if(resultRow !=0) {
			response.sendRedirect(request.getContextPath()+"/question/questionList");
			return;
		}
		// 작성실패(입력값 확인)
		response.sendRedirect(request.getContextPath() + "/question/addQuestion"); 
	}
}
