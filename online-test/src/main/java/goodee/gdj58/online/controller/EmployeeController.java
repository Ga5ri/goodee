package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.vo.Employee;

@Controller 
public class EmployeeController {
	@Autowired 
	EmployeeService employeeService;
	   
	// 로그인
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		// 이미 로그인 되어있다면 redirect:/employee/empList
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		return "employee/loginEmp";
	}
	   
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		   Employee resultEmp = employeeService.login(emp);
		   if(resultEmp == null) {
			   return "redirect:/employee/loginEmp";
		   }
		   session.setAttribute("loginEmp", resultEmp);
		   return "redirect:/employee/empList";
	   }
	
	// 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/loginEmp";
	}
	/*
	*	로그인 후에 사용가능한 기
	*/
	   
	   
	// 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		employeeService.removeEmployee(empNo);
		return "redirect:/employee/empList";
	}
	   
	   
	// 입력
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		return "employee/addEmp"; // forword
	}
	   
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Employee employee) {
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/loginEmp";
		}
		int row = employeeService.addEmployee(employee);
		// row == 1 디버깅코드
		if(row == 1) {
			System.out.println("추가 성공!");
		} else {
			System.out.println("추가 실패!");
		}
		
		return "redirect:/employee/empList"; // sendRedirect, CM -> C
	}
	  
	// 리스트
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model
							, @RequestParam(value="currentPage", defaultValue = "1") int currentPage
							, @RequestParam(value="rowPerPage", defaultValue="10") int rowPerPage) { 
		// int currentPage = reuqest.getParamenter("currentPage");
	  
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage);
		// request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		return "employee/empList";
	}
}