package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerAddressService;
import vo.Customer;
import vo.CustomerAddress;
import vo.Emp;

@WebServlet("/customer/modifyAddress")
public class ModifyAddressController extends HttpServlet {
	
	private CustomerAddressService customerAddressService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String addressCode = request.getParameter("addressCode");
		
		// 디버깅
		// System.out.println(addressCode + " <-- addressCode");
		
		// request null & 공백 체크
		if(addressCode == null || addressCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addressList");
			return;
		}
		
		// addressCode 넣어서 보낼 객체
		CustomerAddress ca = new CustomerAddress();
		
		// 정보 받아올 객체
		CustomerAddress resultCA = new CustomerAddress();
		
		ca.setAddressCode(Integer.parseInt(addressCode));
		
		this.customerAddressService = new CustomerAddressService();
		resultCA = this.customerAddressService.getAddressOne(ca);
		
		request.setAttribute("address", resultCA);
		
		// 디버깅
		// System.out.println(resultCA.toString() + " <-- resultCA.toString");
		
		
		request.getRequestDispatcher("/WEB-INF/view/address/modifyAddress.jsp").forward(request, response);
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 후 진입가능
		HttpSession session = request.getSession();
		
		// 로그인 값 체크
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		if(loginCustomer == null && loginEmp == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 인코딩 : UTF-8
		request.setCharacterEncoding("UTF-8");
		
		// request
		String addressCode = request.getParameter("addressCode");
		String address = request.getParameter("address");
		
		// request null & 공백 체크
		if(addressCode == null || address == null || addressCode.equals("") || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/modifyAddress");
			return;
			
		}
		
		CustomerAddress ca = new CustomerAddress();
		
		ca.setAddressCode(Integer.parseInt(addressCode));
		ca.setAddress(address);
		
		this.customerAddressService = new CustomerAddressService();
		int resultRow = this.customerAddressService.modifyAddress(ca);
		
		// 수정 실패하면
		String targetUrl = "/customer/modifyAddress";
		
		if(resultRow == 1) {
			
			// 수정 성공하면
			targetUrl = "/customer/addressList";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
	}

}
