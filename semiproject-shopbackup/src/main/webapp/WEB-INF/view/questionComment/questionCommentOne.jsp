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
			$('#remove').click(function(){
				var out = confirm('삭제 하시겠습니까?');
				if(out == true){
					alert('삭제되었습니다.')
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
			<h2>상세보기</h2>
			<div>
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">뒤로가기</a>
			</div>
			<!-- 문의 내용대한 답변내용 상세보기 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div>
				<table border="1">
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
						<c:if test="${q.commentMemo == null}">답변전</c:if> 
						<c:if test="${q.commentMemo != null}">${q.commentCreatedate}</c:if> 
					</td>
				</tr>
				<tr>
					<th>commentMemo</th>
				</tr>
				<tr>
					<td>
						<c:if test="${q.commentMemo == null}">답변전</c:if> 
						<c:if test="${q.commentMemo != null}">${q.commentMemo}</c:if>
					</td>
				</tr>
				</table>
			</div>
			<!-- 문의글 수정, 삭제 -->
			<div>
				<td>
					<c:if test="${loginEmp == empId}">
						<a href="${pageContext.request.contextPath}/questionComment/modifyQuestionComment?commentCode=${q.commentCode}">
							<span>수정</span> 
						</a>	
					</c:if>
					<c:if test="${loginEmp != empId}">
						<span>&nbsp;</span>
					</c:if>
				</td>
				<td>
					<c:if test="${loginEmp == empId}">
						<a id="remove" href="${pageContext.request.contextPath}//questionComment/removeCommentQuestion?commentCode=${q.commentCode}">
							<span>삭제</span> 
						</a>
					</c:if>
					<c:if test="${loginEmp != empId}">
						<span>&nbsp;</span>
					</c:if>
				</td>
			</div>
		</div>
	</body>
</html>