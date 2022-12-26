package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;
// Model(일반 클래스) - Controller(Servlet클래스 상속) - View(JSP)
@WebServlet("/mvc/BoardListController")
public class BoardListController extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      BoardService boardService = new BoardService();
      ArrayList<Board> list = boardService.getBoardList();
         
      // view와 공유할 모델데이터 설정
      request.setAttribute("list", list);
      
      // view 연결
      // RequestDispatcher 1) include 2) forward
      RequestDispatcher rd 
         = request.getRequestDispatcher("/WEB-INF/view/mvcBoardList.jsp");
      rd.forward(request, response);
   }
}