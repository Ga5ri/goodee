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

import service.GoodsService;
import vo.Customer;
import vo.Emp;
import vo.Goods;

@WebServlet("/goods/goodsListByCompany")
public class GoodsListByCompanyController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		System.out.println(loginEmp.toString() + " <-- loginEmp");
		
		if(loginEmp == null || loginEmp.getAuthCode() != 0) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;

		// 상품 리스트 초기화
		ArrayList<HashMap<String, Object>> list = null;
		
		int totalCnt = 0;
		String empId = loginEmp.getEmpId();
		// 세션 authcode 수정 후 동작
		Goods goods = new Goods();
		goods.setEmpId(empId);
		System.out.println("empid="+empId);
		GoodsService goodsService = new GoodsService();
		list = goodsService.getItemListByCompany(beginRow, rowPerPage, goods);
		System.out.println("list는=>"+list);
		totalCnt = goodsService.countByCompany(goods);


		// 마지막 페이지
		int lastPage = totalCnt / rowPerPage;
		if(totalCnt % rowPerPage != 0) {
			lastPage++;
		}
		request.setAttribute("list", list);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);

		request.getRequestDispatcher("/WEB-INF/view/goods/goodsListByCompany.jsp").forward(request, response);
	}

}
