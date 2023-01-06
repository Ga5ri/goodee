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
	<h1>상품 수정</h1>
	<form action="${pageContext.request.contextPath}/ModifyGoodsController" method="post">
		<c:forEach var="m" items="${list}" varStatus="s">
			<table border="1">
					<tr>
						<td rowspan="9"><img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200"></td>
						<td>상품번호</td>
						<td><input type="text" name="goodsCode" value="${m.goodsCode}" readonly="readonly"></td>
					</tr>
					<tr>
						<td>상품 이름</td>
						<td><input type="text" name="goodsName" value="${m.goodsName}"></td>
					</tr>
					<tr>
						<td>상품 타입</td>
						<td><input type="text" name="contentType" value="${m.contentType}"></td>
					</tr>
					<tr>
						<td>상품 원본타입</td>
						<td><input type="text" name="originName" value="${m.originName}"></td>
					</tr>
					<tr>
						<td>상품 가격</td>
						<td><input type="text" name="goodsPrice" value="${m.goodsPrice}"></td>
					</tr>
					<tr>
						<td>품절상태</td>
						<td>
							<input type="radio" name="soldout" value="Y" <c:if test="${soldout == Y}">checked</c:if>>Y
							<input type="radio" name="soldout" value="N" <c:if test="${soldout == N}">checked</c:if>>N
						</td>
					</tr>
					<tr>
						<td>상단 호출 레벨</td>
						<td><input type="text" name="hit" value="${m.hit}"></td>
					</tr>
					<tr>
						<td>상품 이미지</td>
						<td><input type="file" name="goodsImg"></td>
					</tr>
			</table>
		</c:forEach>
		<div>
			<button type="submit">수정하기</button>
		</div>
	</form>
</body>
</html>