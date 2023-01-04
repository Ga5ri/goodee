package controller;

import java.io.IOException;

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

@WebServlet("/AddGoodsController")
public class AddGoodsController extends HttpServlet {
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/goods/addGoods.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 인코딩
		
		// MultipartRequest에 들어갈 매개변수
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100;
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		
		// form에서 보낸 정보들을 req에 모두 저장
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		System.out.println(mreq+"<-merq");
		
		// req에서 불러올 정보 불러오기
		String goodsName = mreq.getParameter("goodsName");
		String goodsSoldout = mreq.getParameter("soldout");
		String empId = mreq.getParameter("emp_id");
		String fileName = mreq.getOriginalFileName("goodsImg");
		String contentType = mreq.getParameter("contentType");
		
		Goods goods = new Goods(); // goods에 상품이름 저장
		goods.setGoodsName(goodsName);
		goods.setEmpId(empId);
		goods.setSoldout(goodsSoldout);
		goods.setHit(contentType);

		
		GoodsImg goodsImg = new GoodsImg(); // goodsImg에 이미지파일이름 저장
		goodsImg.setFilename(fileName);
		goodsImg.setContentType(contentType);
		
		
		GoodsService goodsService = new GoodsService();
		int row = goodsService.addItem(goods, goodsImg, dir);
		System.out.println(row+"로우값");
		if(row == 0) {
			System.out.println("상품 업로드 실패");
		} else {
			System.out.println("상품 업로드 성공");
		}
		response.sendRedirect(request.getContextPath()+"/AddGoodsController");
	}

}
