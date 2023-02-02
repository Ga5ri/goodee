<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		body {
		  padding:1.5em;
		  background: #f5f5f5
		}
		
		table {
		  border: 1px #a39485 solid;
		  font-size: .9em;
		  box-shadow: 0 2px 5px rgba(0,0,0,.25);
		  width: 40%;
		  border-collapse: collapse;
		  border-radius: 5px;
		  overflow: hidden;
		}
		th {
		  border: 1px solid rgba(0,0,0,.1);
		  text-align: center;
		}
		  
		thead {
		  font-weight: bold;
		  color: #fff;
		}
		  
		 td, th {
		  padding: 1em .5em;
		  vertical-align: middle;
		}
		  
		 td {
		  border-bottom: 1px solid rgba(0,0,0,.1);
		  text-align: center
		}
		a {
		  text-decoration: none;
		}
	</style>
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
			<h2 align="center">상세보기</h2>
			<div align="center" style="padding-right: 42em">
				<button onclick="history.back()">뒤로가기</button>
			</div>
			<br>
			<!-- 문의 내용대한 답변내용 상세보기 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div align="center">
				<table border="1">
				<!-- 문의내용 -->
				<tr>
					<th>문의번호/카테고리</th>
					<td>${q.questionCode} :${q.category}</td>
				<tr> 
					<th>주문번호/상품명</th>
					<td> ${q.orderCode} : ${q.goodsName}</td>
				</tr>
				<tr>
					<th >문의날짜</th>
					<td >${q.createdate}</td>
				</tr>
				<tr>
					<th >문의내용</th>
					<td >${q.questionMemo}</td>
				</tr>
				<!-- 첨부 파일 있을시에만 보임 -->
					<c:if test="${q.questionImg == null}">
						<div>&nbsp;</div>
					</c:if> 
					<c:if test="${q.questionImg != null}">
						<tr>
							<th>첨부파일</th>
							<td>
								<div><img src="${pageContext.request.contextPath}/upload/${q.questionImg}" width="200" height="200"></div>
							</td>
						</tr>
					</c:if> 
					<!-- 답변정보 -->
					<tr>
						<th>작성자/답변일</th>
						<td>
							<c:if test="${q.commentMemo == null}">답변전</c:if> 
							<c:if test="${q.commentMemo != null}">${q.empId} : ${q.commentCreatedate}</c:if> 
						</td>
					</tr>
					<tr>
						<th>답변내용</th>
						<td>
							<c:if test="${q.commentMemo == null}">답변전</c:if> 
							<c:if test="${q.commentMemo != null}">${q.commentMemo}</c:if>
						</td>
					</tr>
					</table>
				</div>
			<br>
			<!-- 문의글 수정, 삭제 -->
			<div align="center">
				<td>
					<c:if test="${loginEmp == empId}">
						<a href="${pageContext.request.contextPath}/questionComment/modifyQuestionComment?questionCode=${q.questionCode}">
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