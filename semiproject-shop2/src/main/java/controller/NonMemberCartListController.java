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
import vo.Cart;
import vo.Customer;
import vo.Emp;

@WebServlet("/cart/nonMemberCartList")
public class NonMemberCartListController extends HttpServlet {
	
	private CartService cartService;
	private GoodsService goodsService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
        String action = request.getParameter("action");
        
        if(action != null) {
        	
	        switch(action) {
	        case "cartList":	// 비회원 장바구니 목록
	            this.cartList(request, response);
	            break;
	        case "addCart":		// 장바구니 1개 추가
	            this.addCart(request, response);
	            break;	            
	        case "emptyCart":	// 전체 삭제
	            request.getSession().removeAttribute("nonMemberTempCartList");
	            response.sendRedirect(request.getContextPath()+"/cart/nonMemberCartList?action=cartList");
	            break;
	        case "deleteCart":	// 장바구니 1개 삭제
	        	this.deleteCart(request, response);
	        	break;
	        default:
	            break;
	        }
        }
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		
		if(loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
        String action = request.getParameter("action");
        switch(action) {
        case "modifyQuantity":	// 장바구니 수량 조정
        	this.modifyQuantity(request, response);
        	break;
        default:
            break;
        }
		
	}
	
	
	protected void cartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 세션
		HttpSession session = request.getSession();

		// 장바구니(상품, 이미지) 리스트
		ArrayList<HashMap<String, Object>> nonMemberCartList = new ArrayList<HashMap<String, Object>>();
		
		if(session.getAttribute("nonMemberTempCartList") != null) {
			// 세션에 장바구니(goodsCode, cartQuantity) 리스트가 존재할 때
			
			// 장바구니(goodsCode, cartQuantity) 리스트
			ArrayList<Cart> nonMemberTempCartList = (ArrayList<Cart>) session.getAttribute("nonMemberTempCartList");
			
			this.goodsService = new GoodsService();
			
			// DB에서 nonMemberTempCartList 에 맞는 상품, 이미지 정보들 가져와서
			// nonMemberCartList 에 추가
			for(int i=0; i<nonMemberTempCartList.size(); i+=1) {
				
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
				
				list = this.goodsService.getGoodsOne((int) nonMemberTempCartList.get(i).getGoodsCode());
				
				nonMemberCartList.add(list.get(0));
				
			}
			
			// 비회원 장바구니의 상품, 이미지 리스트를 세션에 추가
			request.setAttribute("nonMemberCartList", nonMemberCartList);
			
		} 
		
		request.getRequestDispatcher("/WEB-INF/view/cart/nonMemberCartList.jsp").forward(request, response);	
		
		
	}

	
	protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		// 세션에 nonMemberTempCartList 없다면 Integer 리스트 객체 생성
		if(session.getAttribute("nonMemberTempCartList") == null) {
			session.setAttribute("nonMemberTempCartList", new ArrayList<Integer>());
		} 
		
		// nonMemberTempCartList : goodsCode, cartQuantity 정보 포함하는 리스트
		ArrayList<Cart> nonMemberTempCartList = (ArrayList<Cart>) session.getAttribute("nonMemberTempCartList");
		
		if(goodsCode != null && !goodsCode.equals("")) {
			
			// 이미 비회원 장바구니에 등록된 상품인지 확인
			for(int i=0; i<nonMemberTempCartList.size(); i+=1) {
				
				if(Integer.parseInt(goodsCode) == (int) nonMemberTempCartList.get(i).getGoodsCode()) {
					
					// msg : 이미등록된 상품입니다.
					// response.sendRedirect(request.getContextPath() + "해당 상품 페이지");
					System.out.println("비회원 이미 장바구니에 등록된 상품입니다.");
					
					response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");
					
					return;
					
				}
				
			}
		
			// 비회원 장바구니에 새로 추가할 상품과 수량1 설정
			Cart cart = new Cart();
			cart.setGoodsCode(Integer.parseInt(goodsCode));
			cart.setCartQuantity(1);
			
			// nonMemberTempCartList 에 goodsCode, cartQuantity 추가
			nonMemberTempCartList.add(cart);
			
		}		
		
		// nonMemberTempCartList 세션에 다시 저장
		session.setAttribute("nonMemberTempCartList", nonMemberTempCartList);
		
		response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");

	}
	
	
	protected void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		// 세션에 nonMemberTempCartList 없다면 Integer 리스트 객체 생성		
		if(session.getAttribute("nonMemberTempCartList") == null) {
			session.setAttribute("nonMemberTempCartList", new ArrayList<Integer>());
		} 
		
		ArrayList<Cart> nonMemberTempCartList = (ArrayList<Cart>) session.getAttribute("nonMemberTempCartList");
		
		if(goodsCode != null && !goodsCode.equals("")) {
			
			// 삭제할 goodsCode 찾은 뒤 삭제
			for(int i=0; i<nonMemberTempCartList.size(); i+=1) {
				
				if(Integer.parseInt(goodsCode) == (int) nonMemberTempCartList.get(i).getGoodsCode()) {
					
					nonMemberTempCartList.remove(i);
					
					System.out.println("비회원 장바구니 삭제 완료!");
					
				}
				
			}
		
			
		}		
		
		// 세션에 다시 저장
		session.setAttribute("nonMemberTempCartList", nonMemberTempCartList);

		response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");

	}

	
	protected void modifyQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		ArrayList<Cart> nonMemberTempCartList = (ArrayList<Cart>) session.getAttribute("nonMemberTempCartList");
		
		// 디버깅
		// System.out.println(nonMemberTempCartList.get(0).getGoodsCode());
		
		// request
		String strGoodsCode = request.getParameter("goodsCode");
		String strQuantity = request.getParameter("quantity");
		
		// 디버깅
		System.out.println(strGoodsCode + " <-- strGoodsCode");
		System.out.println(strQuantity + " <-- strQuantity");
		
		// request null & 공백 체크
		if(strGoodsCode == null || strQuantity == null
				 || strGoodsCode.equals("") || strQuantity.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");
			return;
			
		}
		
		int goodsCode = Integer.parseInt(strGoodsCode);
		int quantity = Integer.parseInt(strQuantity);
		
		// 해당 goodsCode의 상품 수량 조정
		for(int i=0; i<nonMemberTempCartList.size(); i+=1) {
			
			if(nonMemberTempCartList.get(i).getGoodsCode() == goodsCode) {
				
				nonMemberTempCartList.get(i).setCartQuantity(quantity);
				
			}
			
		}
		
		// 수량 조정 후 세션에 다시 저장
		request.setAttribute("nonMemberTempCartList", nonMemberTempCartList);
		
		// 디버깅
		/*
		for(int i=0; i<nonMemberTempCartList.size(); i+=1) {
			System.out.println(nonMemberTempCartList.get(i).toString());
		}
		*/
		
		response.sendRedirect(request.getContextPath() + "/cart/nonMemberCartList?action=cartList");
		
	}
	
}
