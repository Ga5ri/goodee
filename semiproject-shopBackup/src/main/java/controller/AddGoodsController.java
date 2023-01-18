package controller;

import java.io.IOException;

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

@WebServlet("/goods/addGoods")
public class AddGoodsController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 접근 가능
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		System.out.println(loginEmp+"<-로그인한사람");
		
		// 유효성 검사
		if(loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 세션에 저장
		request.setAttribute("loginEmp", loginEmp);
		
		request.getRequestDispatcher("/WEB-INF/view/goods/addGoods.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MultipartRequest에 들어갈 매개변수
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100;
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();

		// form -> mreq 저장
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// mreq 정보 불러오기
		int price = Integer.parseInt(mreq.getParameter("goodsPrice"));
		// System.out.println(price+"<-상품가격");
		String goodsName = mreq.getParameter("goodsName");
		String soldout = mreq.getParameter("soldout");
		String empId = mreq.getParameter("empId");
		String fileName = mreq.getFilesystemName("goodsImg"); // 서버에 실제 업로드된 파일명
		// System.out.println("fileName :"+fileName);
		String contentType = mreq.getContentType("goodsImg"); // 파일형태
		String originName = mreq.getOriginalFileName("goodsImg"); // 클라이언트가 업로드한 파일 원본

		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(price);
		goods.setEmpId(empId);
		goods.setSoldout(soldout);
		goods.setHit(contentType);	

		GoodsImg goodsImg = new GoodsImg(); 
		goodsImg.setFilename(fileName);
		goodsImg.setContentType(contentType);
		goodsImg.setOriginName(originName);

		GoodsService goodsService = new GoodsService();
		int row = goodsService.addItem(goods, goodsImg, dir, empId);
		System.out.println(row+"로우값");
		if(row == 0) {
			System.out.println("상품 업로드 실패");
		} else {
			System.out.println("상품 업로드 성공");
		}
		response.sendRedirect(request.getContextPath()+"/goods/goodsList");
	}
}