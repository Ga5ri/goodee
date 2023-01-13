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


@WebServlet("/notice/addNotice")
public class AddNoticeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 진입가능
		HttpSession session = request.getSession();

		Emp loginEmp = (Emp)session.getAttribute("loginEmp");		
		System.out.println(loginEmp+"<-로그인한사람");
		
		// 유효성 검사
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
		// 세션에 저장
		request.setAttribute("loginEmp", loginEmp);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/addNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 값 받아오기 
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String empId = request.getParameter("empId");
		
		// 호출
		Notice notice = new Notice();
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setEmpId(empId);
		
		int row = 0;
		NoticeService noticeService = new NoticeService();
		row = noticeService.insertNotice(notice);
		
		if(row == 1) {
			System.out.println("공지 추가 성공!");
			response.sendRedirect(request.getContextPath()+"/notice/noticeList");
		} else {
			System.out.println("공지 추가 실패!");
			response.sendRedirect(request.getContextPath()+"/notice/addNotice");
		}
	}

}
