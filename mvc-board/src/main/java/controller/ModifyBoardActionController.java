package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/ModifyBoardActionController")
public class ModifyBoardActionController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
	    Board board = new Board();
	    board.setNo(no);
	    board.setTitle(title);
	    board.setContent(content);
	    
	    // 모델호출
		BoardService boardService = new BoardService();
		boardService.modifyBoardService(board);

		// view가 없으므로
		response.sendRedirect(request.getContextPath()+"/BoardListController");
	}
}