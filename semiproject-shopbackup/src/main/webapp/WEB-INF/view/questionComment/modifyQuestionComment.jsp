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
			<h2>modify Question Comment</h2>
			<div>
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">뒤로가기</a>
			</div>
			<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div>
			<form method="post" action="${pageContext.request.contextPath}/questionComment/modifyQuestionComment">
				<table border="1">
					<input type="hidden" name="commentCode" value="${q.commentCode}">
					<!-- 문의내용 -->
					<tr>
						<th>questionCode</th>
					</tr>
					<tr>
						<td>${q.questionCode}</td>
					</tr>
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
						<td>${q.category}</td>
					</tr>
					<tr>
						<th >작성일</th>
					</tr>
					<tr>
						<td >${q.createdate}</td>
					</tr>
					<tr>
						<th >questionMemo</th>
					</tr>
					<tr>
						<td >${q.questionMemo}</td>
					</tr>
					<!-- 답변정보 -->
					<tr>
						<th>commentCode</th>
					</tr>
					<tr>
						<td>
							${q.commentCode}
						</td>
					</tr>
					<tr>
						<th >답변일</th>
					</tr>
					<tr>
						<td>
							${q.commentCreatedate}
						</td>
					</tr>
					<tr>
						<th>commentMemo</th>
					</tr>
					<tr>
						<td>
							<textarea rows="8" cols="80" name="commentMemo">${q.commentMemo}</textarea>
						</td>
					</tr>
				</table>
					<button id="modify" type="submit">수정하기</button>	
			</form>
			</div>
		</div>
	</body>
</html>