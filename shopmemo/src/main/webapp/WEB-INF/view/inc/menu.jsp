<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>menu.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/home">
						홈으로
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/login">
						로그인
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/logout">
						로그아웃
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/emp/addEmp">
						emp회원가입
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/customer/addCustomer">
						customer회원가입
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/">
						내정보(미구현)
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/order/orderList">
						내주문
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/emp/empList">
						emp회원관리
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/customer/customerList">
						customer회원관리
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/goods/goodsList">
						상품리스트
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/">
						장바구니(미구현)
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/">
						공지사항(미구현)
					</a>
					
					<span>&nbsp;</span>
					
					<a href = "${pageContext.request.contextPath }/question/questionList">
						고객센터
					</a>
				</h3>
			</div>
			
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/customer/updateCustomerPw">
						customer 비밀번호 변경
					</a>
					
					<span>&nbsp;</span>
				
					<a href = "${pageContext.request.contextPath }/emp/updateEmpPw">
						emp 비밀번호 변경
					</a>
				</h3>
			
			</div>
		
		
		
		
		</div>
		
	</body>
</html>