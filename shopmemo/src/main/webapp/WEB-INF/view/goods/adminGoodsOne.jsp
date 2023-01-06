<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품 상세정보(고객)</h1>
	<form>
	<input type="hidden" name="goodsCode" value="${m.goodsCode}">
		<c:forEach var="m" items="${list}" varStatus="s">
			<table border="1">
				<tr>
					<td rowspan="4">
						<img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200">
					</td>
					<td>상품 이름</td>
					<td>${m.goodsName}</td>
				</tr>
				<tr>
					<td>등록자 아이디</td>
					<td>
						<input type="text" name="empId" value="${m.empId}">
					</td>
				</tr>
				<tr>
					<td>상품 타입</td>
					<td>${m.contentType}</td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td>${m.goodsPrice}원</td>
				</tr>
				<tr>
					<td>품절상태</td>
					<td>
						<input type="radio" name="soldout" value="Y" <c:if test="${soldout == Y}">checked</c:if>>Y
						<input type="radio" name="soldout" value="Y" <c:if test="${soldout == N}">checked</c:if>>N
					</td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td>${m.goodsPrice}원</td>
				</tr>
				
				<!-- soldout Y 체크시 품절 상태이므로 상태란에 품절메세지 뜨고 장바구니,바로구매 기능 비활성화 기능 구현해야함 -->
			</table>
		</c:forEach>
	</form>
	<!-- 페이지 분기해서 customer 페이지에서 보이는 메뉴 아직 구현X-->
	<div>
		<button type="button" onclick="">수정</button>
		<button type="button" onclick="">삭제</button>
	</div>
</body>
</html>