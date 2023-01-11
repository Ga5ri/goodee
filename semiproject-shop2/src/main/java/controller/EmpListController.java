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
		if(active != null) {
			
			request.setAttribute("activeLength", active.length);
			request.setAttribute("active", active);
			
		}
		
		// request authCode
		String[] authCode = request.getParameterValues("authCode");
		if(authCode != null) {
			
			request.setAttribute("authCodeLength", authCode.length);
			request.setAttribute("authCode", authCode);
			
		}
		
		
		
		this.empService = new EmpService();
		
		ArrayList<Emp> empList = new ArrayList<Emp>();
		
		// empList
		empList = this.empService.getEmpList(searchCategory, searchText, currentPage, rowPerPage);
		
		// 페이징 처리
		ArrayList<HashMap<String, Object>> pageList = this.empService.getPage(searchCategory, searchText, currentPage, rowPerPage); 
		
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
		
		
		/*
		System.out.println(active.length + " <-- active.length");
		System.out.println(active + " <-- active");
		
		for(int i=0; i<active.length; i++) {
			System.out.println(active[i]);
			
		}
		
		request.setAttribute("num", 123);

		
		
		response.sendRedirect(request.getContextPath() + "/emp/empList");
		*/
		
	}
	
	

}
