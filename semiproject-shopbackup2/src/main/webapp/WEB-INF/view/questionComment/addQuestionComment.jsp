<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<a href="${pageContext.request.contextPath}/home">홈으로</a>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
		<a href="${pageContext.request.contextPath}/question/questionList">나의문의</a>
		<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">고객센터(관리자 페이지)</a>
	</header>
	<h2>답변글 작성</h2>
	<div>
		<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">뒤로가기</a>
	</div>
	<div>
		<!-- 문의글 작성 페이지-->
		<div>
		<form action="${pageContext.request.contextPath}/questionComment/addQuestionComment" method="post">
			<table border="1">
			<input type="hidden" name="questionCode" value="${q.questionCode}">
				<!-- 사용자 문의 정보 -->
				<tr>
					<th>문의번호</th>
					<td>
						${q.questionCode}
					</td>
				</tr>
				<tr>
					<th>주문번호/상품명</th>
					<td>
						${q.orderCode} : ${q.goodsName}
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						${q.category}
					</td>
				</tr>
				<tr>
					<th>문의날짜</th>
					<td>
						${q.createdate}
					</td>
				</tr>
				<tr>
					<th>문의내용</th>
					<td>
						<textarea rows="6" cols="80" name="commentMemo" readonly="readonly">${q.questionMemo}</textarea>
					</td>
				</tr>
				<!-- 문의에 대한 답변 작성란 -->
				<tr>
					<th>답변내용</th>
					<td>
						<textarea rows="6" cols="80" name="commentMemo"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit">답변 작성</button>
		</form>
		</div>
	</div>
</body>
</html>