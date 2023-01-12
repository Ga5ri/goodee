package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/empList")
public class EmpListController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		
		
		/*
		 * 로그인 확인, authCode 등급 확인 코드 넣어야함
		 * 
		 */
		
		
		
		
		String searchCategory = "emp_code";
		if(request.getParameter("searchCategory") != null) {
			searchCategory = request.getParameter("searchCategory");
		}
		
		String searchText = "";
		if(request.getParameter("searchText") != null) {
			searchText = request.getParameter("searchText");
		}
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}

		// request active 
		String[] active = request.getParameterValues("active");
		
		// active == null 일 때
		request.setAttribute("activeLength", 0);
		
		if(active != null) {
			
			request.setAttribute("activeLength", active.length);
			request.setAttribute("active", active);
			
		}
		
		// request authCode
		String[] strAuthCode = request.getParameterValues("authCode");
		
		// String 배열 -> int 배열 변환
		int[] authCode = null;
		
		// authCode == null 일 때
		request.setAttribute("authCodeLength", 0);
		
		if(strAuthCode != null) {
			authCode = new int[strAuthCode.length];
			
			for(int i=0; i<authCode.length; i++) {
				authCode[i] = Integer.parseInt(strAuthCode[i]); 
			}
		}
		
		// request 속성값 추가
		if(authCode != null) {
			
			request.setAttribute("authCodeLength", authCode.length);
			request.setAttribute("authCode", authCode);
			
		}
		
		System.out.println(active + " <-- active");
		System.out.println(authCode + " <-- authCode");
		
		
		this.empService = new EmpService();
		
		ArrayList<Emp> empList = new ArrayList<Emp>();
		
		// empList
		empList = this.empService.getEmpList(active, authCode, searchCategory, searchText, currentPage, rowPerPage);
		
		// 페이징 처리
		ArrayList<HashMap<String, Object>> pageList = this.empService.getPage(active, authCode, searchCategory, searchText, currentPage, rowPerPage); 
		
		for(HashMap<String, Object> hm : pageList) {
			
			request.setAttribute("previousPage", (int) hm.get("previousPage"));
			request.setAttribute("nextPage", (int) hm.get("nextPage"));
			request.setAttribute("lastPage", (int) hm.get("lastPage"));
			request.setAttribute("pageList", (ArrayList<Integer>) hm.get("pageList"));
		}
		
		request.setAttribute("empList", empList);
		request.setAttribute("searchText", searchText);	// view에서 필요
		request.setAttribute("searchCategory", searchCategory);	// view에서 필요
		request.setAttribute("currentPage", currentPage);	// view에서 필요
		request.setAttribute("rowPerPage", rowPerPage);		// view에서 필요
		
	
		request.getRequestDispatcher("/WEB-INF/view/emp/empList.jsp").forward(request, response);
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}
	
	

}
