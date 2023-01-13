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
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		System.out.println(loginEmp+"<-로그인한사람");
		
		// 값 받아오기
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		// System.out.println("noticecode 값 :"+noticeCode);
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
		request.setCharacterEncoding("utf-8"); // 한글 인코딩

		// 값 받아오기
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String empId = request.getParameter("empId");
		
		// 호출
		Notice notice = new Notice();
		notice.setNoticeCode(noticeCode);
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setEmpId(empId);
		
		NoticeService noticeService = new NoticeService();
		int row = noticeService.modifyNotice(notice);
		if(row == 1) {
			System.out.println("공지 수정 성공!");
		} else {
			System.out.println("공지 수정 실패!");
		}
		response.sendRedirect(request.getContextPath()+"/notice/noticeOne?noticeCode="+noticeCode);
	}

}
