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
	<h1>상품 상세정보</h1>
	<input type="hidden" name="goodsCode" value="${m.goodsCode}">
	<c:forEach var="m" items="${list}" varStatus="s">
		<table border="1">
			<tr>
				<!-- 상품사진 가져오는것 아직 미완성 -->
				<td rowspan="4">
					<img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200">
				</td>
				<td>상품 이름</td>
				<td>${m.goodsName}</td>
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
				<td>상품 설명</td>
				<td>${m.goodsMemo}</td>
			</tr>
		</table>
	</c:forEach>
	<div>
		<button type="button"><a href="">장바구니</a></button>
		<button type="button"><a href="">바로구매</a></button>
	</div>
	<!-- 중간관리자 이상만 보이도록 설정해야함 -->
	<div>
		<button type="button"><a href="">수정</a></button>
		<button type="button"><a href="">삭제</a></button>
	</div>
</body>
</html>