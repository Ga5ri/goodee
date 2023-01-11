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
import vo.Goods;
import vo.GoodsImg;


@WebServlet("/goods/modifyGoods")
public class ModifyGoodsController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 호출
		goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsOne(goodsCode);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/modifyGoods.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// 값 받아오기, 추가수정 필요-> 사진 업로드
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String goodsName = request.getParameter("goodsName");
		int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
		String hit = request.getParameter("hit");
		String empId = request.getParameter("empId");
		String soldout = request.getParameter("soldout");
		String filename = request.getParameter("goodsImg");

		
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(goodsPrice);
		goods.setHit(hit);
		goods.setEmpId(empId);
		goods.setSoldout(soldout);

		// 호출
		GoodsService goodsService = new GoodsService();
		int row = goodsService.modifyGoods(goods, filename); // row값
		System.out.println("goodsService.updateGoods(goods, filename, goodsCode)값 : "+row);
		
		if(row == 0) {
			System.out.println("상품 수정 실패!");
		} else {
			System.out.println("상품 수정 성공!");
		}
	response.sendRedirect(request.getContextPath()+"/goods/goodsListByAdmin");
	}

}
