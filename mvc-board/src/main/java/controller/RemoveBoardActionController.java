package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/RemoveBoardActionController")
public class RemoveBoardActionController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no = 0;
		no = Integer.parseInt(request.getParameter("no"));
		
		Board board = new Board();
		board.setNo(no);

	    // 모델호출
		BoardService boardService = new BoardService();
		boardService.removeBoardService(board);
		
		// view가 없으므로
		response.sendRedirect(request.getContextPath()+"/BoardListController");
	}
}