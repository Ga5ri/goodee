package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;
import vo.Notice;

@WebServlet("/notice/noticeOne")
public class NoticeOneController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		// System.out.println("noticeCode값:"+noticeCode);
		
		noticeService = new NoticeService();
		ArrayList<Notice> list = null;
		list = noticeService.getNotiecOne(noticeCode);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeOne.jsp").forward(request, response);
	}
}
