package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;

@WebServlet("/LoginFormController")
public class LoginFormController extends HttpServlet {
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 로그인 전에만 진입가능
		 HttpSession session = request.getSession();
		 // 로그인 전 : loginMember -> null
		 // 로그인 후 : loginMember -> not null
		 Member loginMember = (Member)session.getAttribute("loginMember");
		 if(loginMember != null) { // 이미 로그인 상태
			 response.sendRedirect(request.getContextPath()+"/HomeController");
			 return;
		 }
      
	// 로그인폼 View
	request.getRequestDispatcher("/WEB-INF/view/loginForm.jsp").forward(request, response);
	}
}