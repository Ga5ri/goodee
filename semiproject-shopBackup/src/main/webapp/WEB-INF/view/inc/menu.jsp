<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
	<div>
		<div>
			<h3>
				<c:if test="${loginCustomer != null}">
					<span>${loginCustomer.customerId}님 환영합니다.</span>
				</c:if>
				<c:if test="${loginEmp != null}">
					<span>사업자/관리자 ${loginEmp.empId}님 환영합니다.</span>
				</c:if>
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
				<!-- authCode 수정되면 eq뒤에 값 1로 변경예정 -->
				<c:choose>
					<c:when test="${loginEmp != null && loginEmp.empId eq 'compuzone'}">
						<a href = "${pageContext.request.contextPath }/goods/goodsListByCompany">
							사업자용리스트
						</a>
					</c:when>
					<c:otherwise>
						<a href = "${pageContext.request.contextPath }/goods/goodsList">
							상품리스트
						</a>
					</c:otherwise>
				</c:choose>
				<c:if test="${loginCustomer != null}">
					<span>&nbsp;</span>
					<a href = "${pageContext.request.contextPath }/order/orderList">
						내주문
					</a>
				</c:if>
				
					<span>&nbsp;</span>
					
				<c:choose>
					<c:when test="${loginCustomer == null }">
						<a href = "${pageContext.request.contextPath }/cart/nonMemberCartList?action=cartList">
							장바구니(비회원)
						</a>
						
						<span>&nbsp;</span>
						
					</c:when>
					<c:otherwise>
						<a href = "${pageContext.request.contextPath }/cart/customerCartList?action=cartList">
							장바구니(회원)
						</a>
					
						<span>&nbsp;</span>
						
					</c:otherwise>
				</c:choose>					
				
				
				<a href = "${pageContext.request.contextPath }/notice/noticeList">
					공지사항
				</a>
				<span>&nbsp;</span>
				<a href = "${pageContext.request.contextPath }/review/reviewList">
					리뷰
				</a>
			</h3>
		</div>
		
		<c:if test="${loginCustomer != null}">
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/customer/customerList">
						customer회원관리
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/customer/addCustomer">
						customer회원가입
					</a>			
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomer">
						customer 정보 수정
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/modifyCustomerPw">
						customer 비밀번호 변경
					</a>				
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/customer/checkPw?targetUrl=/customer/deleteCustomer?customerId=${loginCustomer.customerId }">
						customer 회원 탈퇴
					</a>				
					<a href = "${pageContext.request.contextPath }/customer/addressList">
						customer 배송지 관리
					</a>
				</h3>		
			</div>
		</c:if>
		
		<c:if test="${loginEmp != null}">
			<div>
				<h3>
					<a href = "${pageContext.request.contextPath }/emp/empList">
						emp회원관리
					</a>				
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/emp/addEmp">
						emp회원가입
					</a>				
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmp">
						emp 정보 수정
					</a>				
					<span>&nbsp;</span>
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/modifyEmpPw">
						emp 비밀번호 변경
					</a>			
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/emp/checkPw?targetUrl=/emp/deleteEmp?empCode=${loginEmp.empCode }">
						emp 회원 탈퇴
					</a>									
				</h3>
			</div>
		</c:if>
		
		<div>
			<h3>
				<a href = "${pageContext.request.contextPath }/question/questionList">
					고객센터
				</a>				
				<c:if test="${loginCustomer != null}">
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/question/questionListUser">
						나의문의보기
					</a>
					<span>&nbsp;</span>				
					<a href = "${pageContext.request.contextPath }/question/addQuestion">
						고객센터 문의글작성
					</a>
				</c:if>
				
				<c:if test="${loginEmp != null}">	
					<span>&nbsp;</span>			
					<a href = "${pageContext.request.contextPath }/questionComment/questionCommentList">
						고객센터(관리자)
					</a>			
				</c:if>	
			</h3>
		</div>	
	</div>
</body>
</html>