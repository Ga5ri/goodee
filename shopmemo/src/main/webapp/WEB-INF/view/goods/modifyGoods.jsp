<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상품 수정</h1>
	<form>
		<table border="1">
			<c:forEach var="b" items="${list}">
				<tr>
					<td>상품번호</td>
					<td><input type="text" name="goodsCode" value="${g.goodsCode}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>상품번호</td>
					<td><input type="text" name="goodsCode" value="${g.goodsCode}" readonly="readonly"></td>
				</tr>
				
			</c:forEach>
		</table>
	</form>
</body>
</html>