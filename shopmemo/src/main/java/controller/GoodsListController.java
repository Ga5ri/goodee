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
		goodsService = new GoodsService();
		
		ArrayList<HashMap<String, Object>> list = goodsService.getItemList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
	}
}
