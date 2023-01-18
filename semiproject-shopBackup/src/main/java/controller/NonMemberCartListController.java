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

import service.CartService;
import service.GoodsService;

@WebServlet("/cart/nonMemberCartList")
public class NonMemberCartListController extends HttpServlet {
	
	private CartService cartService;
	private GoodsService goodsService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String action = request.getParameter("action");
        switch(action) {
        case "cartList":	// 비회원 장바구니 목록
            this.cartList(request, response);
            break;
        case "emptyCart":	// 전체 삭제
            request.getSession().removeAttribute("goodsCodeList");
            System.out.println(1212);
            response.sendRedirect(request.getContextPath()+"/cart/nonMemberCartList?action=cartList");
            break;
        case "deleteCart":	// 장바구니 1개 삭제
        	this.deleteCart(request, response);
        	break;
        default:
            break;
        }
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String action = request.getParameter("action");
        switch(action) {
        case "addCart":		// 장바구니 1개 추가
            this.addCart(request, response);
            break;

        default:
            break;
        }
		
	}
	
	
	protected void cartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 세션
		HttpSession session = request.getSession();

		ArrayList<HashMap<String, Object>> cartList = new ArrayList<HashMap<String, Object>>();
		
		if(session.getAttribute("goodsCodeList") != null) {
			
			ArrayList<HashMap<String, Object>> goodsCodeList = (ArrayList<HashMap<String, Object>>) session.getAttribute("goodsCodeList");
			
			this.goodsService = new GoodsService();
			
			for(int i=0; i<goodsCodeList.size(); i+=1) {
				
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				
				list = this.goodsService.getGoodsOne((int) goodsCodeList.get(i).get("goodsCode"));
				
				cartList.add(list.get(0));
				
			}
			
			request.setAttribute("nonMembersCartList", cartList);
			
		} 
		
		request.getRequestDispatcher("/WEB-INF/view/cart/nonMemberCartList.jsp").forward(request, response);	
		
		
	}

	
	protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		
		if(session.getAttribute("goodsCodeList") == null) {
			session.setAttribute("goodsCodeList", new ArrayList<Integer>());
		} 
		
		ArrayList<HashMap<String, Object>> goodsCodeList = (ArrayList<HashMap<String, Object>>) session.getAttribute("goodsCodeList");
		
		if(goodsCode != null && !goodsCode.equals("")) {
			
			for(int i=0; i<goodsCodeList.size(); i+=1) {
				
				if(Integer.parseInt(goodsCode) == (int) goodsCodeList.get(i).get("goodsCode")) {
					
					// msg : 이미등록된 상품입니다.
					// response.sendRedirect(request.getContextPath() + "해당 상품 페이지");
					System.out.println("비회원 이미 장바구니에 등록된 상품입니다.");
					
					response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");
					
					return;
					
				}
				
			}
		
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("goodsCode", Integer.parseInt(goodsCode));
			
			goodsCodeList.add(map);
			
		}		
		
		session.setAttribute("goodsCodeList", goodsCodeList);
		
		response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");

	}
	
	
	protected void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		
		if(session.getAttribute("goodsCodeList") == null) {
			session.setAttribute("goodsCodeList", new ArrayList<Integer>());
		} 
		
		ArrayList<HashMap<String, Object>> goodsCodeList = (ArrayList<HashMap<String, Object>>) session.getAttribute("goodsCodeList");
		
		if(goodsCode != null && !goodsCode.equals("")) {
			
			for(int i=0; i<goodsCodeList.size(); i+=1) {
				
				if(Integer.parseInt(goodsCode) == (int) goodsCodeList.get(i).get("goodsCode")) {
					
					goodsCodeList.remove(i);
					
					System.out.println("비회원 장바구니 삭제 완료!");
					
				}
				
			}
		
			
		}		
		
		session.setAttribute("goodsCodeList", goodsCodeList);

		response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");

	}

}
