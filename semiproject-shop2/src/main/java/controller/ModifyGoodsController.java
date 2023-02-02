package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.GoodsService;
import vo.Emp;
import vo.Goods;
import vo.GoodsImg;


@WebServlet("/goods/modifyGoods")
public class ModifyGoodsController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		System.out.println(loginEmp+"<-로그인한사람");
		
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 호출
		goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = null;
		list = goodsService.getGoodsOne(goodsCode);
		
		request.setAttribute("list", list);
		request.setAttribute("goodsCode", goodsCode);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/modifyGoods.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100;
		
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// 값 받아오기
		int goodsCode = Integer.parseInt(mreq.getParameter("goodsCode"));
		String goodsName = mreq.getParameter("goodsName");
		int goodsPrice = Integer.parseInt(mreq.getParameter("goodsPrice"));
		String hit = mreq.getParameter("hit");
		String empId = mreq.getParameter("empId");
		String soldout = mreq.getParameter("soldout");
		String filename = mreq.getFilesystemName("goodsImg");
		String contentType = mreq.getContentType("goodsImg");
		String originName = mreq.getOriginalFileName("goodsImg");

		
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(goodsPrice);
		goods.setHit(hit);
		goods.setEmpId(empId);
		goods.setSoldout(soldout);
		
		GoodsImg goodsImg = new GoodsImg();
		goodsImg.setFilename(filename);
		goodsImg.setContentType(contentType);
		goodsImg.setOriginName(originName);
		goodsImg.setGoodsCode(goodsCode);

		// 호출
		GoodsService goodsService = new GoodsService();
		int row = goodsService.modifyGoods(goods, goodsImg, dir);
		System.out.println("goodsService.updateGoods(goods, filename, goodsCode)값 : "+row);
		
		if(row == 1) {
			System.out.println("상품 수정 성공!");
			response.sendRedirect(request.getContextPath()+"/goods/goodsOne?goodsCode="+goodsCode);
		} else {
			System.out.println("상품 수정 실패!");
			response.sendRedirect(request.getContextPath()+"/goods/modifyGoods?goodsCode="+goodsCode);
		}
	}

}
