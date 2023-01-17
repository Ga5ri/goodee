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
		  width: 100%;
		  border-collapse: collapse;
		  border-radius: 5px;
		  overflow: hidden;
		}
		
		th {
		  text-align: left;
		}
		  
		thead {
		  font-weight: bold;
		  color: #fff;
		  background: green;
		}
		  
		 td, th {
		  padding: 1em .5em;
		  vertical-align: middle;
		}
		  
		 td {
		  border-bottom: 1px solid rgba(0,0,0,.1);
		  background: #fff;
		}
		
		a {
		  color: #73685d;
		  text-decoration: none;
		}
		
		.a1 { 
		  color: orange;
		  text-decoration: none;
		}
		.a2 { 
		  color: blue;
		  text-decoration: none;
		}
	</style>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		<!--변경 확인 -->
		$(document).ready(function() {
			$('#rowPerPage').change(function() {
				$('#pageForm').submit();
				alert('change');
			})
			$('#category').change(function() {
				$('#pageForm').submit();
				alert('change');
			})
			$('#sort').change(function() {
				$('#pageForm').submit();
				alert('change');
			})
			
		<!-- 상품검색값 유효성체크 -->
		$('#searchBtn').click(function() {
			// 검색값 미입력시
			if( ($('#word').val().length) < 1 )  {
				alert('검색값이 없습니다.');
				$('#word').focus();
				return;
			}
			// 검색값 공백만 입력시
			if($('#word').val().trim() == '')  {
				alert('공백만 입력할 수 없습니다.');
				$('#word').text('');
				$('#word').focus();
				return;
			} 
			$('#pageForm').submit();
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
		
		<!-- 상단 제목 -->
		<h2 align="center">고객센터(관리자 페이지)</h2>
		
		<div>
			<!--정렬/ 검색 기능 / 페이지당 출력수 / 답변전후 -->
			<div>
				<form method="get" id="pageForm" action="${pageContext.request.contextPath}/questionComment/questionCommentList">
					<!--  정렬 -->
					<select name="sort" id="sort">
						<c:if test="${sort == ('sort')}">
							<option value="sort" selected="selected">-상태-</option>
							<option value="asc">답변작성</option>
							<option value="desc">답변완료</option>
						</c:if>
						<c:if test="${sort == ('asc')}">
							<option value="sort">-상태-</option>
							<option value="asc" selected="selected">답변작성</option>
							<option value="desc">답변완료</option>
						</c:if>
						<c:if test="${sort == ('desc')}">
							<option value="sort">-상태-</option>
							<option value="asc">답변작성</option>
							<option value="desc" selected="selected">답변완료</option>
						</c:if>
					</select>
					
					<!-- 분류별 -->
					<select name="category" id="category">
						<c:if test="${category == ('')}">
								<option value="" selected="selected">전체</option>
								<option value="배송">배송</option>
								<option value="반품">반품</option>
								<option value="교환">교환</option>
								<option value="기타">기타</option>
						</c:if>
						<c:if test="${category == '배송'}">
								<option value="">전체</option>
								<option value="배송" selected="selected">배송</option>
								<option value="반품">반품</option>
								<option value="교환">교환</option>
								<option value="기타">기타</option>
						</c:if>
						<c:if test="${category == '반품'}">
								<option value="" selected="selected">전체</option>
								<option value="배송">배송</option>
								<option value="반품" selected="selected">반품</option>
								<option value="교환">교환</option>
								<option value="기타">기타</option>
						</c:if> 
						<c:if test="${category == '교환'}">
								<option value="" selected="selected">전체</option>
								<option value="배송">배송</option>
								<option value="반품">반품</option>
								<option value="교환" selected="selected">교환</option>
								<option value="기타">기타</option>
						</c:if>
						<c:if test="${category == '기타'}">
								<option value="" selected="selected">전체</option>
								<option value="배송">배송</option>
								<option value="반품">반품</option>
								<option value="교환">교환</option>
								<option value="기타" selected="selected">기타</option>
						</c:if>
					</select>  
					<!-- 검색 기능(search: 주문번호 / 상품명 / 고객ID / 사원 ID)-->
					<select name="search" id="search" value="${search}">
						<c:if test="${search == ('search')}">
							<option value="search" selected="selected">-선택-</option>
							<option value="orderCode" >주문번호</option>
							<option value="goodsName" >상품명</option>
							<option value="customerId">고객ID</option>
							<option value="empId">사원ID</option>
						</c:if>
						<c:if test="${search eq 'orderCode'}">
							<option value="search" >-선택-</option>
							<option value="orderCode" selected="selected">주문번호</option>
							<option value="goodsName" >상품명</option>
							<option value="customerId">고객ID</option>
							<option value="empId">사원ID</option>
						</c:if>
						<c:if test="${search eq 'goodsName'}">
							<option value="search" >-선택-</option>
							<option value="orderCode" >주문번호</option>
							<option value="goodsName" selected="selected">상품명</option>
							<option value="customerId">고객ID</option>
							<option value="empId">사원ID</option>
						</c:if>
						<c:if test="${search eq 'customerId'}">
							<option value="search" >-선택-</option>
							<option value="orderCode" >주문번호</option>
							<option value="goodsName" >상품명</option>
							<option value="customerId" selected="selected">고객ID</option>
							<option value="empId">사원ID</option>
						</c:if>
						<c:if test="${search eq 'empId'}">
							<option value="search" >-선택-</option>
							<option value="orderCode" >주문번호</option>
							<option value="goodsName" >상품명</option>
							<option value="customerId">고객ID</option>
							<option value="empId" selected="selected">사원ID</option>
						</c:if>
					</select>
					<label for="word"> search : </label>
					<input type="text" name="word" id="word" value="${word}">
					<button id="searchBtn"type="button">search</button>
					
					<!--  페이지당 출력수(10,20,30) -->
					<select name="rowPerPage" id="rowPerPage">
						<c:if test="${rowPerPage == 10}">
							<option value="10" selected="selected">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</c:if>
						<c:if test="${rowPerPage == 20}">
							<option value="10">10</option>
							<option value="20" selected="selected">20</option>
							<option value="30">30</option>
						</c:if>
						<c:if test="${rowPerPage == 30}">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30" selected="selected">30</option>
						</c:if>	
					</select>
					<span>&nbsp;&nbsp;&nbsp;</span>
				</form>	
			</div>
			
			<!-- 고객센터 내용 -->
			<div>
			<table border="0">
				<thead>
				<tr>
					<th>문의번호</th>
					<th>주문번호</th>
					<th>카테고리</th>
					<th>상품명</th>
					<th>고객ID</th>
					<th>문의내용</th>
					<th>createdate</th>
					<th>상태</th>
					<th>작성자</th>
					<th>답변일</th>
					<th>답변 작성</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="q" items="${questionlist}">
						<tr>
							<td>${q.questionCode}</td>
							<td>${q.orderCode}</td>
							<td>${q.category}</td>
							<td>
								<a href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">
										${q.goodsName}
								</a>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">
										${q.customerId}
								</a>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">
										${q.questionMemo}	
								</a>
							</td>
							<td>${q.createdate}</td>
							<td>
								<c:if test="${q.commentMemo == null}">
										답변전
								</c:if> 
								<c:if test="${q.commentMemo != null}">
									<a class="a2" href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">
										답변완료
									</a>
								</c:if> 
							</td>
							<td>
								<c:if test="${q.empId == null}">
									&nbsp;
								</c:if> 
								<c:if test="${q.empId != null}">
									<a href="${pageContext.request.contextPath}/questionComment/questionCommentOne?questionCode=${q.questionCode}">
										${q.empId}
									</a>
								</c:if> 
							</td>
							<td>
								<c:if test="${q.commentCreatedate == null}">
									&nbsp;
								</c:if> 
								<c:if test="${q.commentCreatedate != null}">
									${q.commentCreatedate}
								</c:if> 
							</td>
							<td>
								<c:if test="${q.empId == null}">
									<a class="a1" href="${pageContext.request.contextPath}/questionComment/addQuestionComment?questionCode=${q.questionCode}">
										작성하기
									</a>
								</c:if> 
								<c:if test="${q.empId != null}">
									&nbsp;
								</c:if> 
							</td>
						</tr>	
					</c:forEach>
				</tbody>
			</table>
			</div>
			<br>
			<!-- 페이징 -->
			<div align="center">
				<!-- 첫 페이지 -->
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=1&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">처음</a>
				
				<!-- 이전 페이지 -->
				<c:if test="${currentPage>1}">
					<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${currentPage-1}&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">이전</a>
				</c:if>	
				
				<!-- 현재 페이지 -->
				<span>${currentPage}</span>
				
				<!-- 다음 페이지 -->
				<c:if test="${currentPage<lastPage}">
					<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${currentPage+1}&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">다음</a>
				</c:if>	
				
				<!-- 마지막 페이지 -->
				<a href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${lastPage}&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">마지막</a>
			</div>
		</div>
	</body>
</html>