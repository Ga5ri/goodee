<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
				<!-- 문의글 내용 유효성체크 -->
				$(document).ready(function() {
					// 최대 글자수
					${'MAX_COUNT'} = 500;
					
					// 입력된 메모값
					let questionMemo = document.querySelector('#questionMemo')
					
					$('#questionMemo').keyup(function() {
						// 입력한 값의 글자수
						${'len'} = $('#questionMemo').val().length; 
						
						// 결과
						if(len < MAX_COUNT){
							document.querySelector('#count').innerHTML = len;
						} else if(len > MAX_COUNT-1){
							alert(MAX_COUNT+'자까지 입력가능합니다');
							questionMemo.value = questionMemo.value.substring(0, MAX_COUNT);
						} 
					});	
							
					$('#addBtn').click(function() {
						// 미입력시
						if( ($('#questionMemo').val().length) < 1 )  {
							alert('입력된값이 없습니다.');
							$('#questionMemo').focus();
							return;
						}
						// 공백만 입력시
						if($('#questionMemo').val().trim() == '')  {
							alert('공백만 입력할 수 없습니다.');
							$('#questionMemo').text('');
							$('#questionMemo').focus();
							return;
						} 
						$('#addForm').submit();
					});
						
				});
	    </script>
	</head>
	<body>
		<header>
			<a href="${pageContext.request.contextPath}/home">홈으로</a>
			<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
			<a href="${pageContext.request.contextPath}/question/questionList">고객센터</a>
			<a href="${pageContext.request.contextPath}/question/questionListUser">나의문의</a>
			<a href="${pageContext.request.contextPath}/questionComment/questionCommentList">고객센터(관리자 페이지)</a>
		</header>
		<h2 align="center">문의 작성</h2>
		<div align="center" style="padding-right: 42em"> 
				<button onclick="history.back()">뒤로가기</button>
			</div>
		<div align="center">
			<div align="center" style="padding-left: 38em"> 
				글자수 : <span id="count">0</span> /500
			</div>
			<!-- 문의글 작성 페이지-->
			<div>
			<form id="addForm" action="${pageContext.request.contextPath}/question/addQuestion" method="post" enctype="multipart/form-data">
				<table border="1">
					<tr>
						<th>주문번호/상품명</th>
						<td>
							<select name="orderCode" id="orderCode">
								<c:forEach var="q" items="${orderCodeList}">
									<option value="${q.orderCode}" >${q.orderCode} : ${q.goodsName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<select name="category" id="category">
								<option value="배송" >배송</option>
								<option value="반품" >반품</option>
								<option value="교환" >교환</option>
								<option value="기타" >기타</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td>
							<textarea id="questionMemo" rows="8" cols="80" name="questionMemo"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="questionImg"></td>
					</tr>
				</table>
				<br>
				<button id="addBtn" type="button">작성</button>
			</form>
			</div>
		</div>
	</body>
</html>