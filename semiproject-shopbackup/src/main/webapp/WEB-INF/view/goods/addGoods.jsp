<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<!-- 중간관리자 이상만 가능한 기능 -->
	<h1>상품등록</h1>
	<form action="${pageContext.request.contextPath}/goods/addGoods" enctype="multipart/form-data" method="post" >
		<table border="1">
			<tr>
				<td>상품 등록자 ID</td>
				<td><input type="text" name="empId" value="${loginEmp.empId}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input type="text" name="goodsName"></td>
			</tr>
			<tr>
				<td>상품 가격</td>
				<td><input type="text" name="goodsPrice"></td>
			</tr>
			<tr>
				<td>상품 재고(Y선택시 품절)</td>
				<td>
					<input type="radio" name="soldout" value="Y">Y
					<input type="radio" name="soldout" value="N" checked="checked">N
				</td>
			</tr>
			<!-- 미정
			<tr>
				<td>상단 등록 레벨</td>
				<td><input type="text" name="hit"></td>
			</tr>
			-->
			<tr>
				<td>상품 이미지</td>
				<td><input type="file" name="goodsImg"></td>
			</tr>
		</table>
		<button type="submit">상품 등록</button>
	</form>
</body>
</html>