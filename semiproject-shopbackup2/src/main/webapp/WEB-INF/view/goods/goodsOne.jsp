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
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	
	<!-- 상품 상세정보 -->
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
				<td><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</td>
			</tr>
			<!-- 상품 품절상태일 경우 메세지 출력 -->
			<c:if test="${soldout eq 'Y'}">
				<tr>
					<td colspan="2" style=color:red;><strong>죄송합니다! 상품이 품절되었습니다.</strong></td>
				</tr>
			</c:if>
		</table>
		
		<!-- 관리자,고객이 보이는 버튼 분리 -->
		<c:choose>
			<c:when test="${loginEmp != null}">
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/goods/modifyGoods?goodsCode=${m.goodsCode}'">수정</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/goods/deleteGoods?goodsCode=${m.goodsCode}'">삭제</button>
			</c:when>
			<c:otherwise>
				<c:if test="${soldout eq 'N'}">
					<button type="button" onclick="">장바구니</button>
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/order/addOrder?goodsCode=${m.goodsCode}'">바로구매</button>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>