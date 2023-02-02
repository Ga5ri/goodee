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
		<title>고객센터</title>
		
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
			<!-- 상품검색값 유효성체크 -->
			$(document).ready(function() {
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
					$('#searchForm').submit();
				
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
									<h2 class = "font-weight-bold text-center">QUESTION</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
										
											<!-- 문의글 작성 링크-->
											<div class="input-group-append">
												<button  class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/question/addQuestion'">
													문의작성
												</button>
											</div>
											<br>
											
											<!-- 리스트 -->
											<div class="table-responsive mb-5">
												<table class = "table expandable-table table-hover text-center">
													<thead>
														<tr>
															<th>문의번호</th>
															<th>카테고리</th>
															<th>상품명</th>
															<th>문의 메모</th>
															<th>작성자</th>
															<th>작성일</th>
															<th>답변</th>
														</tr>
													</thead>
												<!--  content ( 문의번호, 카테고리, 상품명, 문의메모, 작성일, 답변유무 ) -->
													<tbody>
														<c:forEach var="q" items="${questionlist}">
															<tr>
																<td>${q.questionCode}</td>
																<td>${q.category}</td>
																<td>
																	<a href="${pageContext.request.contextPath}/question/questionOne?questionCode=${q.questionCode}">
																		${q.goodsName}	
																	</a>
																</td>
																<td>
																	<a href="${pageContext.request.contextPath}/question/questionOne?questionCode=${q.questionCode}">
																		${q.questionMemo}	
																	</a>
																</td>
																<td>${q.customerId}</td>
																<td>${q.createdate}</td>
																<td>
																	<c:if test="${q.commentMemo == null}">답변전</c:if> 
																	<c:if test="${q.commentMemo != null}">답변완료</c:if> 	
																</td>
															</tr>	
														</c:forEach>
													</tbody>
												</table>
											</div>
												
											<!-- 검색 기능(search: 상품명)-->
											<div class = "row justify-content-center">
												<form method="get" id="searchForm" action="${pageContext.request.contextPath}/question/questionList">
													<div>
														<div class="form-group">
															<div class="input-group">
																<div class = "input-group-prepend">
																	<select class = "form-control" >
																		<option selected>상품명</option>
																	</select>
																</div>
																<!-- input & button -->
																<input type="text" class = "form-control" name="word" id="word" value="${word}">
																<div class="input-group-append">
																	<button class="btn btn-sm btn-primary" id="searchBtn" type="button">SEARCH</button>
																</div>
															</div>
														</div>
													</div>
												</form>
											</div>
						
											<!-- Qeustion 페이징 -->
											<div>
												<ul class="pagination justify-content-center">
												
												<!-- 페이지 처음 -->
												<li class="page-item">
													<a class="page-link" href="${pageContext.request.contextPath}/question/questionList?currentPage=1&rowPerPage=${rowPerPage}&word=${word}">
														<span>처음</span>
													</a>
												</li>
													
												<!-- 페이지 이전(-10의 1페이지) -->
												<c:if test="${previousPage > 0}">
													<li class="page-item">
														<a class="page-link" href="${pageContext.request.contextPath}/question/questionList?currentPage=${previousPage}&rowPerPage=${rowPerPage}&word=${word}">
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
															<a class="page-link" href="${pageContext.request.contextPath }/question/questionList?word=${word}&rowPerPage=${rowPerPage}&currentPage=${i}">
																<span>${i }</span>
															</a>
														</c:if>
														
													</c:forEach>
											
													<!-- 페이지 다음(+10의 1페이지) -->
													<c:if test="${nextPage <= lastPage }">
														<li class="page-item"> 
															<a class="page-link" href="${pageContext.request.contextPath }/question/questionList?word=${word }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
																<span>다음</span>
															</a>
														</li>
													</c:if>
											
													<!-- 페이지 마지막 -->
													<li class="page-item"> 
														<a class="page-link" href="${pageContext.request.contextPath }/question/questionList?word=${word }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
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