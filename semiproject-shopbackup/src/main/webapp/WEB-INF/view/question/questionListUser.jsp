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
	<h2>나의 문의사항</h2>
	<div>
		<!-- 문의글 작성 링크-->
		<div>
			<a href="${pageContext.request.contextPath}/question/addQuestion">문의글 작성</a>
		</div>
		
		<!-- 고객센터 내용 -->
		<div>
			<table border="0">
			<thead>
			<tr>
				<th>category</th>
				<th>questionMemo</th>
				<th>createdate</th>
				<th>답변</th>
				<th>답변일</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="q" items="${questionlist}">
					<tr>
						<td>${q.category}</td>
						<td>
							<a href="${pageContext.request.contextPath}/question/questionOne?questionCode=${q.questionCode}">
							${q.questionMemo}	
							</a>
						</td>
						<td>${q.createdate}</td>
						<td>
							<c:if test="${q.commentMemo == null}">답변전</c:if> 
							<c:if test="${q.commentMemo != null}">답변완료</c:if> 	
						</td>
						<td>
							<c:if test="${q.commentCreatedate == null}">&nbsp;</c:if> 
							<c:if test="${q.commentCreatedate != null}">${q.commentCreatedate}</c:if> 	
						</td>
					</tr>	
				</c:forEach>
			</tbody>
			</table>
		</div>
		<br>
		<!-- 페이징 -->
		<div>
			<!-- 첫 페이지 -->
			<a href="${pageContext.request.contextPath}/question/questionList?currentPage=1">처음</a>
			
			<!-- 이전 페이지 -->
			<c:if test="${currentPage>1}">
				<a href="${pageContext.request.contextPath}/question/questionList?currentPage=${currentPage-1}">이전</a>
			</c:if>	
			
			<!-- 현재 페이지 -->
			<span>${currentPage}</span>
			
			<!-- 다음 페이지 -->
			<c:if test="${currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/question/questionList?currentPage=${currentPage+1}">다음</a>
			</c:if>	
			
			<!-- 마지막 페이지 -->
			<a href="${pageContext.request.contextPath}/question/questionList?currentPage=${lastPage}">마지막</a>
		</div>
	</div>
</body>
</html>