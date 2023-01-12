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
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	<h1>상품 상세정보</h1>
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
				<td>상품 가격</td>
				<td>${m.goodsPrice}원</td>
			</tr>
			<!-- soldout Y 체크시 품절 상태이므로 상태란에 품절메세지 뜨고 장바구니,바로구매 기능 비활성화 기능 구현해야함 -->
		</table>
		<c:choose>
			<c:when test="${loginEmp != null}">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/goods/modifyGoods?goodsCode=${m.goodsCode}'">수정</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/goods/deleteGoods?goodsCode=${m.goodsCode}'">삭제</button>
			</c:when>
			<c:otherwise>
				<button type="button" onclick="">장바구니</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/order/addOrder?goodsCode=${m.goodsCode}'">바로구매</button>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>