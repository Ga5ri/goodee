package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.GoodsService;
import vo.Goods;
import vo.GoodsImg;


@WebServlet("/goods/modifyGoods")
public class ModifyGoodsController extends HttpServlet {
	// 상품 변경 폼(기존 적혀있던 내용들 보여주기)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		GoodsService goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsOne(goodsCode);
		System.out.println(goodsCode+"<-goodsCode");
		request.setAttribute("list", list);
		// 호출
		request.getRequestDispatcher("/WEB-INF/view/goods/modifyGoods.jsp?goodsCode"+goodsCode).forward(request, response);
	}
	
	// 상품 변경 액션(테이블 2개의 내용을 수정하여 업데이트)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			상품 수정 = 중간관리자 이상만 가능
			로그인 확인 및 레벨 확인 후 수정 가능
			HttpSession session = request.getSession();
			Emp loginMember = (Emp)session.getAttribute("loginMember");
	 		if(loginmember == null && loginmember.level>?) {
				request.getRequestDispatcher("/WEB-INF/view/goods/goodsList.jsp").forward(request, response);
			}
		*/
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		response.sendRedirect(request.getContextPath()+"/WEB-INF/view/goods/goodsOne.jsp?goodsCode"+goodsCode);
	}

}
