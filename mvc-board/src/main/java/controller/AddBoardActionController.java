package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

// C -> M ===> 클라이언트 다른 컨트롤러를 요청하도록 리다이렉트
// view(jsp)파일들을 WEB-INF보안폴더안으로 이동해서 클라이언트가 View를 직접 호출 못하도록....
// list select					: C -> M -> V 
// form	페이지호출					: C -> V
// action insert/update/delete	: C -> M =====> response.sendRedirect()
@WebServlet("/AddBoardActionController")
public class AddBoardActionController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(title + " <-- AddBoardActionController.doPost.title");
		
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		// 모델호출
		BoardService boardService = new BoardService();
		boardService.addBoardService(board); // 서버스는 dao호출 dao.insertBoard(conn, board)
		
		// 뷰가 없다
		response.sendRedirect(request.getContextPath()+"/mvc/BoardListController");
	}

}