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
			<h2 align="center">답변글 수정</h2>
			<div align="center" style="padding-right: 42em">
				<button onclick="history.back()">뒤로가기</button>
			</div>
			<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div align="center">
			<form method="post" action="${pageContext.request.contextPath}/questionComment/modifyQuestionComment">
				<table border="1">
					<input type="hidden" name="commentCode" value="${q.commentCode}">
					<!-- 문의내용 -->
					<tr>
						<th>문의번호/주문번호</th>
					</tr>
					<tr>
						<td>${q.questionCode} : ${q.orderCode}</td>
					</tr>
					<tr> 
						<th>카테고리/상품명</th>
					</tr>
					<tr>
						<td>${q.category} : ${q.goodsName}</td>
					</tr>
					<tr>
						<th >작성일</th>
					</tr>
					<tr>
						<td >${q.createdate}</td>
					</tr>
					<tr>
						<th >문의내용</th>
					</tr>
					<tr>
						<td >${q.questionMemo}</td>
					</tr>
					<!-- 답변정보 -->
					<tr>
						<th >답변일</th>
					</tr>
					<tr>
						<td>
							${q.commentCreatedate}
						</td>
					</tr>
					<tr>
						<th>답변내용</th>
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