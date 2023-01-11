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

@WebServlet("/goods/goodsListByAdmin")
public class GoodsListByAdminController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;

		GoodsService goodsService = new GoodsService();
		
		// 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> list = null;
		
		int totalCnt = 0;
		// 검색어 받기
		String searchWord = request.getParameter("searchWord");
		if(searchWord != null) { // 검색값이 있다면
			list = goodsService.getItemListBySearch(beginRow, rowPerPage, searchWord);
			totalCnt = goodsService.count(searchWord);
		} else { // 검색값이 없다면
			list = goodsService.getItemList(beginRow, rowPerPage);
			totalCnt = goodsService.count();
		}
		// 마지막 페이지
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt % rowPerPage != 0) {
			lastPage++;
		}
		request.setAttribute("list", list);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		System.out.println(searchWord + " <--searcrWord");
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsListByAdmin.jsp").forward(request, response);
	}

}
