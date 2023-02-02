<jsp:include page = "/WEB-INF/view/inc/menuTest.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Question List</title>
		<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>고객 관리</title>
		
		<!-- plugins:css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/feather/feather.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/ti-icons/css/themify-icons.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/css/vendor.bundle.base.css">
		<!-- endinject -->
		
		<!-- Plugin css for this page -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/vendors/ti-icons/css/themify-icons.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources2/js/select.dataTables.min.css">
		<!-- End plugin css for this page -->
		
		<!-- inject:css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resources2/css/vertical-layout-light/style.css">
		<!-- endinject -->
		
		<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources2/images/favicon.png" />
	  
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
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
		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-md-auto grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div class="content-wrapper">
									<h2 class = "font-weight-bold text-center">QUESTION COMMENT</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
										
										<!-- 검색 기능 (정렬, 검색, 페이징, 답변전후) -->
										<div class = "row justify-content-center">
												<form method="get" id="pageForm" action="${pageContext.request.contextPath}/questionComment/questionCommentList">
													<div>
														<div class="form-group">
															<div class="input-group">
																<div class = "input-group-prepend">
		
																	<!--  정렬 -->
																	<select class = "form-control" name="sort" id="sort">
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
																	<select class = "form-control" name="category" id="category">
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
																	<select class = "form-control" name="search" id="search" value="${search}">
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
																	
																	<!-- input & button -->
																	<input type="text" class = "form-control" name="word" id="word" value="${word}">
																	<div class="input-group-append">
																		<button class="btn btn-sm btn-primary" id="searchBtn" type="button">SEARCH</button>
																	</div>
																	
																	<!--  Question Comment 페이지당 출력수(10,20,30) -->
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
																</div>
															</div>
														</div>
													</form>
												</div>
												
												
												<!-- 리스트 -->
												<div class="table-responsive mb-5">
													<table class = "table expandable-table table-hover text-center">
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
													<!--  content  -->
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
										
										
												<!-- Qeustion 페이징 -->
												<div>
													<ul class="pagination justify-content-center">
													
													<!-- 페이지 처음 -->
													<li class="page-item"> 
														<a class="page-link" href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=1&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">
															<span>처음</span>
														</a>
													</li>
													
													<!-- 페이지 이전(-10의 1페이지) -->
													<c:if test="${previousPage > 0}">
														<li class="page-item">
															<a class="page-link" href="${pageContext.request.contextPath}/questionComment/questionCommentList?currentPage=${previousPage}&rowPerPage=${rowPerPage}&word=${word}&search=${search}&category=${category}&sort=${sort}">
																<span>이전</span>
															</a>
														</li>
													</c:if>
													
													<!-- 페이지 1 ~ 10 -->
														<c:forEach var="i" items="${pageList }">
															<!-- 현재페이지 active 속성 부여 -->
															<c:choose>
																<c:when test="${currentPage == i}">
																	<li class = "page-item active">
																</c:when >
																<c:otherwise>
																	<li class = "page-item">
																</c:otherwise>
															</c:choose>
														
															<!-- 마지막 페이지 까지만 출력 -->
															<c:if test="${i <= lastPage }"> 
																<a class="page-link" href="${pageContext.request.contextPath }/questionComment/questionCommentList?word=${word}&rowPerPage=${rowPerPage}&currentPage=${i}&search=${search}&category=${category}&sort=${sort}">
																	<span>${i }</span>
																</a>
															</c:if>
															
															
														</c:forEach>
												
														<!-- 페이지 다음(+10의 1페이지) -->
														<c:if test="${nextPage <= lastPage }">
															<li class="page-item"> 
																<a class="page-link" href="${pageContext.request.contextPath }/questionComment/questionCommentList?word=${word }&rowPerPage=${rowPerPage }&currentPage=${nextPage}&search=${search}&category=${category}&sort=${sort}">
																	<span>다음</span>
																</a>
															</li>
														</c:if>
												
														<!-- 페이지 마지막 -->
														<li class="page-item"> 
															<a class="page-link" href="${pageContext.request.contextPath }/questionComment/questionCommentList?word=${word }&rowPerPage=${rowPerPage }&currentPage=${lastPage}&search=${search}&category=${category}&sort=${sort}">
																<span>마지막</span>
															</a>
														</li>
													</ul>
												</div>
												<!-- question 페이징 처리 끝 -->	
											
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>   
		</div>
	</body>
</html>