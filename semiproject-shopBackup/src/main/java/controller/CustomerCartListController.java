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

@WebServlet("/cart/customerCartList")
public class CustomerCartListController extends HttpServlet {
	
	private CartService cartService;
	private GoodsService goodsService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/cart/customerCartList");
			return;
		}
		
		
        String action = request.getParameter("action");
        
        if(action != null) {
        	
	        switch(action) {
	        case "cartList":	// 회원 장바구니 목록
	            this.cartList(request, response);
	            break;
	        case "addCart":		// 장바구니 1개 추가	// 나중에 doGet으로 바꿔야함
	            this.addCart(request, response);
	            break;	            
	        case "emptyCart":	// 전체 삭제
	        	this.emptyCart(request, response);
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
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath()+"/cart/customerCartList");
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
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		this.cartService = new CartService();
		this.goodsService = new GoodsService();
		
		// customerCartList : 장바구니의 상품, 이미지 정보를 포함하는 리스트
		ArrayList<HashMap<String, Object>> customerCartList = new ArrayList<HashMap<String, Object>>();
		
		if(session.getAttribute("nonMemberTempCartList") != null) {
			// 비회원에서 추가한 장바구니가 있을 때
			
			// tempList에 세션에서 가져온 비회원 장바구니(goodsCode, quantity) 리스트 대입 
			ArrayList<Cart> tempList = (ArrayList<Cart>) session.getAttribute("nonMemberTempCartList");
			
			// 디버깅
			// System.out.println(tempList.toString());
			
			// 비회원 장바구니 세션 초기화
			session.removeAttribute("nonMemberTempCartList");
			session.removeAttribute("nonMemberCartList");

			// tempList 에 저장된 비회원 장바구니를 회원 cart DB에 저장
			for(int i=0; i<tempList.size(); i+=1) {
				
				tempList.get(i).setCustomerId(loginCustomer.getCustomerId());
				
				int resultRow = this.cartService.addCart(tempList.get(i));
				
			}
			
			// 저장후 tempList 세션 초기화
			session.removeAttribute("tempList");
		}
		
		// 해당 회원의 장바구니(goodsCode, customerId, cartQuantity) 정보 리스트 가져오기
		ArrayList<Cart> customerTempCartList = new ArrayList<Cart>();
		customerTempCartList = this.cartService.getCartList(loginCustomer.getCustomerId());
		
		// 디버깅
		// System.out.println(tempList.size() + " <-- size");
		// System.out.println(tempList.toString() + " <-- toString");
		
		// System.out.println(customerTempCartList.toString() + "<--customerTempCartList");
		
		
		// customerTempCartList 를 이용하여 상품, 이미지 정보 리스트 가져오기
		for(int i=0; i<customerTempCartList.size(); i+=1) {
			
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			list = this.goodsService.getGoodsOne((int) customerTempCartList.get(i).getGoodsCode());
			
			// 디버깅
			// System.out.println(list.toString() + " <-- list.toString");
			
			// 장바구니 수량 추가
			// goodsService.getGoodsOne 에는 수량(cartQuantity) 정보가 없으므로 HashMap 키워드와 값 추가
			list.get(0).put("cartQuantity", (int) customerTempCartList.get(i).getCartQuantity());
			
			// customerCartList : 최종 출력할 상품, 이미지, 수량 정보 포함하는 리스트
			customerCartList.add(list.get(0));
			
		}
		
		// 마지막으로 세션에 다시 저장
		request.setAttribute("customerCartList", customerCartList);
		
		// 디버깅
		//System.out.println(customerCartList.toString() + "<-- customerCartList.toString");
			
		
		request.getRequestDispatcher("/WEB-INF/view/cart/customerCartList.jsp?action=cartList").forward(request, response);
			
		
	}
	
	
	
	protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		// request null & 공백 체크
		if(goodsCode == null || goodsCode.equals("")) {
			
			// 해당 상품 장바구니 누르기 전 페이지
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		// 해당 상품, 로그인ID, 초기 수량은 1로 설정
		Cart cart = new Cart();
		cart.setGoodsCode(Integer.parseInt(goodsCode));
		cart.setCustomerId(loginCustomer.getCustomerId());
		cart.setCartQuantity(1);
		
		this.cartService = new CartService();
		
		// 해당 회원의 장바구니에 상품 추가
		// cartService 단에서 중복 확인 후 추가
		int resultRow = this.cartService.addCart(cart);
			
		response.sendRedirect(request.getContextPath() + "/cart/customerCartList?action=cartList");
		
	}
		
	
	
	protected void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		
		// request null & 공백 체크
		if(goodsCode == null || goodsCode.equals("")) {
			
			// 해당 상품 장바구니 누르기 전 페이지
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		// 해당 goodsCode와 customerId 설정
		Cart cart = new Cart();
		cart.setGoodsCode(Integer.parseInt(goodsCode));
		cart.setCustomerId(loginCustomer.getCustomerId());
		
		this.cartService = new CartService();
		
		// 해당 회원의 장바구니에서 상품 삭제
		int resultRow = this.cartService.deleteCart(cart);
		
		
		response.sendRedirect(request.getContextPath() + "/cart/customerCartList?action=cartList");
		
	}

	
	
	
	protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		if(loginCustomer == null) {
			
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		// 전체삭제이므로 goodsCode 없이 해당ID의 장바구니 전체 삭제
		Cart cart = new Cart();
		cart.setCustomerId(loginCustomer.getCustomerId());
		
		this.cartService = new CartService();
		
		// 해당 ID의 장바구니 전체 삭제
		int resultRow = this.cartService.emptyCart(cart);
		
		response.sendRedirect(request.getContextPath() + "/cart/customerCartList?action=cartList");
		
	}	
	
	
	
	
	protected void modifyQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		
		// request
		String goodsCode = request.getParameter("goodsCode");
		String quantity = request.getParameter("quantity");
		
		// request null & 공백 체크
		if(goodsCode == null || goodsCode.equals("") || quantity == null || quantity.equals("")) {
			
			// 해당 상품 장바구니 누르기 전 페이지
			response.sendRedirect(request.getContextPath() + "/home");
			return;
			
		}
		
		// DB에 해당상품 찾기위해 goodsCode, customerId 설정
		// 변경될 수량 받아와서(request) 수정
		Cart cart = new Cart();
		cart.setGoodsCode(Integer.parseInt(goodsCode));
		cart.setCustomerId(loginCustomer.getCustomerId());
		cart.setCartQuantity(Integer.parseInt(quantity));
		
		
		
		this.cartService = new CartService();
		
		// 해당 ID 장바구니의 상품 수량 조정
		int resultRow = this.cartService.modifyCart(cart);
		
		
		response.sendRedirect(request.getContextPath() + "/cart/customerCartList?action=cartList");
		
	}	
	
	
	

}
