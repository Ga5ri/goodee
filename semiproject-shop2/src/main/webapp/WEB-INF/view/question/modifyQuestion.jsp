<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#modify').click(function(){
				var out = confirm('수정 하시겠습니까?');
				if(out == true){
					alert('수정되었습니다.')
				} else { 
					alert('취소되었습니다.')
					return false;
				}
			});
		});
	</script>
	</head>
	
	<body>
		<header>
			<a href="${pageContext.request.contextPath}/home">홈으로</a>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
			<a href="${pageContext.request.contextPath}/question/questionList">나의문의</a>
			<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">고객센터(관리자 페이지)</a>
		</header>
		<div>
			<h2>modify Question</h2>
			<div>
				<a href="${pageContext.request.contextPath}/question/questionOne?questionCode=${q.questionCode}">뒤로가기</a>
			</div>
			<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div>
			<form method="post" action="${pageContext.request.contextPath}/question/modifyQuestion">
				<table border="1">
				<input type="hidden" name="questionCode" value="${questionCode}">
				<tr>
					<th>ordersCode</th>
				</tr>
				<tr>
					<td>${q.ordersCode}</td>
				</tr>
				<tr> 
					<th>category</th>
				</tr>
				<tr>
					<td>
						<select name="category" id="category">
							<c:if test="${q.category == '배송'}">
								<option value="배송" selected="selected">배송</option>
								<option value="반품" >반품</option>
								<option value="교환" >교환</option>
								<option value="기타" >기타</option>
							</c:if>
							<c:if test="${q.category == '반품'}">
								<option value="배송" >배송</option>
								<option value="반품" selected="selected">반품</option>
								<option value="교환" >교환</option>
								<option value="기타" >기타</option>
							</c:if>
							<c:if test="${q.category == '교환'}">
								<option value="배송" >배송</option>
								<option value="반품" >반품</option>
								<option value="교환" selected="selected">교환</option>
								<option value="기타" >기타</option>
							</c:if>
							<c:if test="${q.category == '기타'}">
								<option value="배송" >배송</option>
								<option value="반품" >반품</option>
								<option value="교환" >교환</option>
								<option value="기타" selected="selected">기타</option>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<th>작성일</th>
				</tr>
				<tr>
					<td>${q.createdate}</td>
				</tr>
				<tr>
					<th>questionMemo</th>
				</tr>
				<tr>
					<td ><textarea rows="8" cols="80" name="questionMemo">${q.questionMemo}</textarea></td>
				</tr>
				</table>
					<c:if test="${loginCustomer == customerId}">
						<button id="modify" type="submit">수정하기</button>	
					</c:if>
					<c:if test="${loginCustomer != customerId}">
						<span>&nbsp;</span>
					</c:if>
			</form>
			</div>
		</div>
	</body>
</html>