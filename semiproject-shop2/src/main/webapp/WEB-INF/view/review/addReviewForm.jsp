<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrderForm</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){		
		/* 작성일 계산 */
	    let now = new Date();
	    let year = ('0' + now.getFullYear()).slice(-2);
	    let month = ('0' + (now.getMonth() + 1)).slice(-2);
	    let day = ('0' + now.getDate()).slice(-2);

	    let today = year + '/' + month  + '/' + day;
	    $('#createdate').val(today); 
	  });	
</script>
<script>
</script>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	<h1>리뷰쓰기</h1>
	<form method="post" action="${pageContext.request.contextPath}/review/addReview">
		<input type="hidden" name="orderCode" value="${orderCode}" readonly>
		<table class="table" border="1">
			<tr>
				<th>상품사진</th>
				<td>
					<c:if test="${filename ne null}">
						<img src="${pageContext.request.contextPath}/upload/${filename}" width="200" height="200">
					</c:if>
					<c:if test="${filename eq null}">
						<span>사진 준비중!</span>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td><input type="text" id="goodsName" name="goodsName" value="${goodsName}" readonly></td>				
			</tr>
			<tr>
				<th>리뷰내용</th>
				<td><textarea name="reviewMemo" cols="30" rows="5"></textarea></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><input type="text" id="createdate" name="createdate" readonly></td>
			</tr>
		</table>
		<div>
			<button id="btnAdd" type="submit">작성완료</button>
		</div>
	</form>
</body>
</html>
