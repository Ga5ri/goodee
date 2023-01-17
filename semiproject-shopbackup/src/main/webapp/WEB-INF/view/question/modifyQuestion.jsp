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
			
			// 수정 버튼 클릭시 확인 및 공백값 체크
			$('#modifyBtn').click(function(){
				var out = confirm('수정 하시겠습니까?');
				if(out == true){
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
						$('#modifyForm').submit();
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
			<h2 align="center">문의 수정</h2>
			<div align="center" style="padding-right: 42em"> 
				<button onclick="history.back()">뒤로가기</button>
			</div>
			<div align="center" style="padding-left: 38em"> 
				글자수 : <span id="count">0</span> /500
			</div>
			<!-- 고객센터 내용 (분류/주문번호, 문의작성일, 문의내용, 답변일, 답변내용-->
			<div align="center">
			<form id="modifyForm" method="post" action="${pageContext.request.contextPath}/question/modifyQuestion" enctype="multipart/form-data">
				<table border="1">
					<input type="hidden" name="questionCode" value="${q.questionCode}">
					<tr> 
						<th>문의번호/카테고리</th>
						<td>
							${q.questionCode}
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
						<th>주문번호/상품명</th>
						<td>${q.orderCode}&nbsp;${q.goodsName}</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${q.createdate}</td>
					</tr>
					<tr>
						<th>문의내용</th>
						<td><textarea rows="8" cols="80" id="questionMemo" name="questionMemo">${q.questionMemo}</textarea></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td><input type="file" name="questionImg" value="${q.questionImg}"></td>
					</tr>
				</table>
				<br>
				<!-- 아이디 유효성검사 (로그인ID 와 주문건의 ID가 일치한다면 수정버튼 활성화 -->
				<c:if test="${loginCustomer == customerId}">
					<button id="modifyBtn" type="button">수정하기</button>	
				</c:if>
				<c:if test="${loginCustomer != customerId}">
					<span>&nbsp;</span>
				</c:if>
			</form>
			</div>
		</div>
	</body>
</html>