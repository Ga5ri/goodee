package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.GoodsService;
import vo.Emp;
import vo.Goods;
import vo.GoodsImg;


@WebServlet("/goods/deleteGoods")
public class DeleteGoodsController extends HttpServlet {
	private GoodsService goodsService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 진입가능
		HttpSession session = request.getSession();

		Emp loginEmp = (Emp)session.getAttribute("loginEmp");		
		System.out.println(loginEmp+"<-로그인한사람");
		
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		
		GoodsImg goodsImg = new GoodsImg();
		goodsImg.setGoodsCode(goodsCode);
		
		GoodsService goodsService = new GoodsService();
		int row = goodsService.deleteGoods(goods, goodsImg);
		if(row == 1) {
			System.out.println("상품 삭제 성공!");
		} else {
			System.out.println("상품 삭제 실패!");
		}	
		response.sendRedirect(request.getContextPath()+"/goods/goodsList");
	}
}
