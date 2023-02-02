package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/emp/deleteEmp")
public class DeleteEmpController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String empCode = request.getParameter("empCode");
		
		System.out.println(empCode + " <-- empCode");
		// null, 공백 검사 
		if(empCode == null || empCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		this.empService = new EmpService();
		int resultRow = this.empService.deleteEmp(Integer.parseInt(empCode));
		

		
		String targetUrl = null;
		
		// 삭제 실패시
		if(loginEmp != null && loginEmp.getAuthCode() == 0 && Integer.parseInt(empCode) == loginEmp.getEmpCode()) {
			// 관리자 자기 탈퇴
			targetUrl = "/home";
		} else if(loginEmp != null && loginEmp.getAuthCode() == 0 ){
			// 관리자가 다른 emp 삭제
			targetUrl = "/emp/empList";
		} else {
			// emp 탈퇴
			targetUrl = "/home";
		}
		
		// 삭제 성공시
		if(resultRow == 1) {
			
			if(loginEmp != null && loginEmp.getAuthCode() == 0 && Integer.parseInt(empCode) == loginEmp.getEmpCode()) {
				// 관리자 자기 탈퇴

				// 받아온 세션값을 완전히 삭제.
				request.getSession().invalidate();
				targetUrl = "/home";
				
			} else if(loginEmp != null && loginEmp.getAuthCode() == 0 ){
				// 관리자가 다른 emp 삭제
				
				targetUrl = "/emp/empList";
				
			} else {
				// emp 탈퇴
				
				// 받아온 세션값을 완전히 삭제.
				request.getSession().invalidate();			
				targetUrl = "/home";
			}
				
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
		
	}

}
