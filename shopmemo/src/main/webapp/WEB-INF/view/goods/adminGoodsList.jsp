<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품목록(관리자)(메뉴 들어갈 자리)</h1>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${list}" varStatus="s">
				<c:if test="${s.index != 0 && s.index % 5 == 0}">
					</tr><tr>
				</c:if>
				<td>
					<div><img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200"></div>
					<div><a href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${m.goodsCode}">${m.goodsName}</a></div>
					<div>${m.goodsPrice}원</div>
					<div><a href="${pageContext.request.contextPath}/goods/modifyGoods?goodsCode=${m.goodsCode}">수정</a>삭제</div>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>