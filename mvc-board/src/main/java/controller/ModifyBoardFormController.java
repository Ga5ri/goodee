package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/ModifyBoardFormController")
public class ModifyBoardFormController extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0; 
		no = Integer.parseInt(request.getParameter("no"));
		
	    Board board = new Board();
		BoardService boardService = new BoardService();
		board = boardService.getBoardOne(no);

		// view와 공유할 모델데이터 설정
		request.setAttribute("board", board);
		
		// view 있으면
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/mvcModifyBoardForm.jsp");		
		rd.forward(request, response);
	}
}