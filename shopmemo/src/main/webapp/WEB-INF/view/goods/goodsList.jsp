<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${list}" varStatus="s">
				<c:if test="${s.index != 0 && s.index % 5 == 0}">
					</tr><tr>
				</c:if>
				 
				<td>
					<div><img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200"></div>
					<div><a href="${pageContext.request.contextPath}/GoodsOneController?goodsCode=${m.goodsCode}">${m.goodsName}</a></div>
					<div>${m.goodsPrice}원</div>
					<!-- 중간관리자 이상 레벨에서만 보이게 변경필요 -->
					<div>수정 삭제</div>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>