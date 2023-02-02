package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 로그인 세션정보 : ex) session.invalidate
		 * redirect -> get방식 home <- 컨트롤러 요청
		 */
		request.getSession().invalidate();  // 받아온 세션값을 완전히 삭제.
		response.sendRedirect(request.getContextPath()+"/login");
	}
}

