<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 숫자 표시에 콤마 찍기위한 포맷 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 상품 수정 -->
	<h1>상품 수정</h1>
	<form action="${pageContext.request.contextPath}/goods/modifyGoods" enctype="multipart/form-data" method="post">
		<c:forEach var="m" items="${list}" varStatus="s">
			<div>
				<img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200">
			</div>
			<table border="1">
				<tr>
					<td>상품 번호</td>
					<td><input type="text" name="goodsCode" value="${m.goodsCode}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>상품 등록자 ID</td>
					<td><input type="text" name="empId" value="${m.empId}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="goodsName" value="${m.goodsName}"></td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td><input type="text" name="goodsPrice" value="${m.goodsPrice}"></td>
				</tr>
				<tr>
					<td>상품 재고(Y선택시 품절)</td>
					<td>
						<input type="radio" name="soldout" value="Y" <c:if test="${soldeout == Y}"> checked </c:if>>Y
						<input type="radio" name="soldout" value="N" <c:if test="${soldeout == N}"> checked </c:if>>N
					</td>
				</tr>
				<tr>
					<td>상단 등록 레벨</td>
					<td><input type="text" name="hit" value="${m.hit}"></td>
				</tr>
				<tr>
					<td>상품 이미지</td>
					<td><input type="file" name="goodsImg" value="${m.filename}"></td>
				</tr>
			</table>
		</c:forEach>
		<div>
			<button type="submit">수정하기</button>
		</div>
	</form>
</body>
</html>