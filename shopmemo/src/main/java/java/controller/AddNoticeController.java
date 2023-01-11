package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;


@WebServlet("/notice/addNotice")
public class AddNoticeController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/addNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String empId = request.getParameter("empId");
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
