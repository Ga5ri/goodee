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

@WebServlet("/customer/addressList")
public class AddressListController extends HttpServlet {

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
		
		String customerId = loginCustomer.getCustomerId();
		
		CustomerAddress ca = new CustomerAddress();
		ca.setCustomerId(customerId);
		
		
		ArrayList<CustomerAddress> list = new ArrayList<CustomerAddress>();
		
		this.customerAddressService = new CustomerAddressService();
		list = this.customerAddressService.getAddressList(ca);
		
		request.setAttribute("addressList", list);
		
		
		request.getRequestDispatcher("/WEB-INF/view/address/addressList.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
