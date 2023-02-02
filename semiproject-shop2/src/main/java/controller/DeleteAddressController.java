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

@WebServlet("/customer/deleteAddress")
public class DeleteAddressController extends HttpServlet {
	
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
		String customerId = loginCustomer.getCustomerId();
		String addressCode = request.getParameter("addressCode");
		
		
		// request null & 공백 체크
		if(addressCode == null || addressCode.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addressList");
			return;
			
		}
		
		CustomerAddress ca = new CustomerAddress();
		ca.setAddressCode(Integer.parseInt(addressCode));
		ca.setCustomerId(customerId);
		
		
		this.customerAddressService = new CustomerAddressService();
		int resultRow = this.customerAddressService.deleteAddress(ca);
		
		String targetUrl = "/customer/addressList";
		if(resultRow == 1) {
			// 삭제 성공하면
			// 메세지 분기
			
		} else {
			// 삭제 실패하면
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
		
		
		
		
		
		
		
	}


}
