<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>공지 사항</title>
		
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
	</head>
<body>
	
	<div class="container-scroller">
	    <div class="container-fluid">
			<div class="row justify-content-center">
				<div class="content-wrapper row justify-content-center">
					<div class="col-md-auto grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<h2 class = "font-weight-bold text-center">Notice</h2>
								<div class="row justify-content-center mt-3">
									<div class="col-auto">
										<!-- 관리자 이상만 공지 작성 가능 -->
										<c:if test="${loginEmp != null}">
											<div>
												<button type="button" class="btn btn-sm btn-primary" onclick="location.href='${pageContext.request.contextPath}/notice/addNotice'">공지 추가</button>
											</div>
										</c:if>
										<br>
										<!-- 리스트 -->
										<div class="table-responsive mb-5">
											<table class = "table expandable-table table-hover text-center">
												<thead>
													<tr>
														<th>공지번호</th>
														<th>공지 제목</th>
														<th>날짜</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="n" items="${list}">
														<tr>
															<td>${n.noticeCode}</td>
															<td><a href="${pageContext.request.contextPath}/notice/noticeOne?noticeCode=${n.noticeCode}">${n.noticeTitle}</a></td>
															<td>${n.createdate}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
							          	<!-- customer 페이징 처리 시작 -->
										<div>
											<ul class="pagination justify-content-center">
												
												<!-- 페이지 처음 -->
												<li class="page-item">
													<a class="page-link" href="${pageContext.request.contextPath}/notice/noticeList?currentPage=1">처음</a>
												</li>
												
												<!-- 페이지 이전(-5의 1페이지) -->
												<c:if test="${currentPage > 1}">
													<li class="page-item">
														<a class="page-link" href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage-1}">이전</a>													
													</li>
												</c:if>
												<!-- 페이지 1 ~ 10 -->
												<c:forEach var="index" begin="${startPage}" end="${endPage}" step="1">
													<!-- 현재페이지 active 속성 부여 -->
													<c:choose>
														<c:when test="${currentPage == index}">
															<li class = "page-item active">
														</c:when >
														<c:otherwise>
															<li class = "page-item">
														</c:otherwise>
													</c:choose>
													
													<!-- 마지막 페이지 까지만 출력 -->
													<c:if test="${index <= lastPage }">	
														<a class="page-link" href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${index}">${index}</a>
													</c:if>													
												</c:forEach>											

												<!-- 페이지 다음(+5의 1페이지) -->
												<c:if test="${currentPage < lastPage}">
													<li class="page-item">
														<a class="page-link" href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage+1}">다음</a>
													</li>
												</c:if>
										
												<!-- 페이지 마지막 -->
												<li class="page-item">
													<a class="page-link" href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${lastPage}">마지막</a>
												</li>
											</ul>
										</div>
										<!-- customer 페이징 처리 끝 -->																			
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>   
		<!-- page-body-wrapper ends -->
		<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
	</div>
	<!-- container-scroller -->
	
	<!-- plugins:js -->
	<!-- endinject -->
	<!-- Plugin js for this page -->
	
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<!-- endinject -->
	<!-- Custom js for this page-->
	<!-- End custom js for this page-->
</body>
</html>