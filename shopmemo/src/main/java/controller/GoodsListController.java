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
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;

		GoodsService goodsService = new GoodsService();
		
		goodsService = new GoodsService();
		
		// 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> list = null;
		
		int totalCnt = 0;
		// 검색어 받기
		String searchWord = request.getParameter("searchWord");
		if(searchWord != null) {
			list = goodsService.getItemListBySearch(beginRow, rowPerPage, searchWord);
			totalCnt = goodsService.count(searchWord);
		} else {
			list = goodsService.getItemList(beginRow, rowPerPage);
			totalCnt = goodsService.count();
		}
		
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt % rowPerPage != 0) {
			lastPage++;
		}
		request.setAttribute("list", list);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
