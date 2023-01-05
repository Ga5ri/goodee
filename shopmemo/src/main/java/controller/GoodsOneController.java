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

@WebServlet("/GoodsOneController")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			상품 수정 = 중간관리자 이상만 가능
			로그인 확인 및 레벨 확인 후 수정 가능
			HttpSession session = request.getSession();
			Emp loginMember = (Emp)session.getAttribute("loginMember");
	 		if(loginmember == null && loginmember.level>?) {
				request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
			}
		 */
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println("상품번호-->"+goodsCode);
		
		goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsOne(goodsCode);
		System.out.println(goodsService.getGoodsOne(goodsCode)+"<-Controller굿즈코드");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/goods/goodsOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
