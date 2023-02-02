package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Emp;

@WebServlet("/emp/empOne")
public class EmpOneController extends HttpServlet {
	
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		System.out.println(loginEmp.toString() + " <-- loginEmp");
		
		if(loginEmp == null || loginEmp.getAuthCode() != 0) {
			
			// msg : 관리자만 입장 가능
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// request
		String empId = request.getParameter("empId");
		
		if(empId == null || empId.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/emp/empList");
			return;
			
		}
		
		
		Emp resultEmp = new Emp();

		this.empService = new EmpService();
		resultEmp = this.empService.getEmpOne(empId);
		
		request.setAttribute("emp", resultEmp);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/empOne.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
	
	}

}
