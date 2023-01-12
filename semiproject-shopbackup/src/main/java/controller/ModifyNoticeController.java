package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NoticeService;
import vo.Emp;
import vo.Notice;


@WebServlet("/notice/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		System.out.println(loginEmp.toString()+"<-- loginEmp");
		
		// 값 받아오기
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		System.out.println("noticecode 값 :"+noticeCode);
		// 호출
		noticeService = new NoticeService();
		ArrayList<Notice> list = null;
		list = noticeService.getNotiecOne(noticeCode);
		System.out.println(list+"<-noticemodify용 list");
		
		request.setAttribute("list", list);
		request.setAttribute("noticeCode", noticeCode);

		
		request.getRequestDispatcher("/WEB-INF/view/notice/modifyNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
