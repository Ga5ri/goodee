package controller;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/customer/addAddress")
public class AddAddressController extends HttpServlet {
	
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

		
		request.getRequestDispatcher("/WEB-INF/view/address/addAddress.jsp").forward(request, response);
		
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
		
		
		// request
		String customerId = loginCustomer.getCustomerId();
		String address = request.getParameter("address");
		
		// request null & 공백 체크
		if(address == null || address.equals("")) {
			
			response.sendRedirect(request.getContextPath() + "/customer/addAddress");
			return;
			
		}
		
		CustomerAddress ca = new CustomerAddress();
		ca.setCustomerId(customerId);
		ca.setAddress(address);
		
		this.customerAddressService = new CustomerAddressService();
		int resultRow = this.customerAddressService.addAddress(ca);
		
		// 배송지 추가 실패하면
		String targetUrl = "/customer/addAddress";
		
		if(resultRow == 1) {
			
			// 배송지 추가 성공하면
			targetUrl = "/customer/addressList";
			
		}
		
		response.sendRedirect(request.getContextPath() + targetUrl);
		
		
	}

}
