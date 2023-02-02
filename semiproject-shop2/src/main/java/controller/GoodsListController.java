package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import service.GoodsService;


@WebServlet("/goods/goodsList")
public class GoodsListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String category = "";
		if(request.getParameter("category") != null) {
			category =request.getParameter("category");
			System.out.println(category+"<--category공백처리");
		} 
		
		// 페이징
		
		// 현재 페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 마지막 페이지
		int endRow = 9 * currentPage;	// endRow
		
		// 페이지당 보일 수
		int realRowPerPage = 9;
		
		// 시작 페이지
		int beginRow = (9 * (currentPage - 1)) +1;	
		
		// 호출
		GoodsService goodsService = new GoodsService();
		
		// 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> list = null;
		
		// hit 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> topList = null;
		
		topList = goodsService.getItemListByTop();
		
		int totalCnt = 0;
		// 검색어 받기
		String searchWord = request.getParameter("searchWord");
		// 검색값 없을 때
		if(searchWord == null || searchWord.equals("")) {
			list = goodsService.getItemList(beginRow, endRow, searchWord, category);
			totalCnt = goodsService.count();
		// 검색값 있을 때
		} else {
			list = goodsService.getItemListBySearch(beginRow, endRow, searchWord, category);
			totalCnt = goodsService.count(searchWord);
		}
		System.out.println("LIST :"+list);
		System.out.println("행수카운트 :"+totalCnt);
		
		// 마지막 페이지
		int lastPage = totalCnt / realRowPerPage ;
		if(totalCnt % endRow != 0) {
			lastPage++;
		}
		
		System.out.println("endRow값 :"+endRow);
		System.out.println("마지막페이지 :"+lastPage);
		
		request.setAttribute("list", list);
		request.setAttribute("topList", topList);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("endRow", endRow);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("category", category);
		request.setAttribute("totalCnt", totalCnt);
		

		System.out.println(category+"<--category");
		System.out.println(searchWord+"<--searcrWord");
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
