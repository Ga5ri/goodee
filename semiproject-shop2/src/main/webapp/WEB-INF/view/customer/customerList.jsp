<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">



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
		
	</head>


	<body>

		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-md-auto grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h2 class = "font-weight-bold text-center">CUSTOMER</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-auto">
										
											<!-- 리스트 -->
											<div class="table-responsive mb-5">
												<table class = "table expandable-table table-hover text-center">
													<thead>
														<tr>
															<th>ID</th>
															<th>NAME</th>
															<th>PHONE</th>
															<th>POINT</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="customer" items="${customerList }">
															<tr>
																<td>
																	<span class = "font-weight-bold">
																		<a href = "${pageContext.request.contextPath }/customer/customerOne?customerId=${customer.customerId }">
																			${customer.customerId }
																		</a>
																	</span>
																</td>
																<td>${customer.customerName }</td>
																<td>${customer.customerPhone }</td>
																<td>${customer.point }</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											
											
											
											
											<!-- 검색 -->
											<div class = "row justify-content-center">
												<form method = "get" action = "${pageContext.request.contextPath }/customer/customerList">
													<div>
														<div class="form-group">
															<div class="input-group">
																<div class = "input-group-prepend">
																	<select class = "form-control" name = "searchCategory" id = "searchCategory">
																		<c:if test="${param.searchCategory == null || param.searchCategory == 'customer_id' }">
																			<option value = "customer_id" selected>ID</option>
																			<option value = "customer_name">NAME</option>
																			<option value = "customer_phone">PHONE</option>
																		</c:if>
																		
																		<c:if test="${param.searchCategory == 'customer_name' }">
																			<option value = "customer_id">ID</option>
																			<option value = "customer_name" selected>NAME</option>
																			<option value = "customer_phone">PHONE</option>
																		</c:if>
																		
																		<c:if test="${param.searchCategory == 'customer_phone' }">
																			<option value = "customer_id">ID</option>
																			<option value = "customer_name">NAME</option>
																			<option value = "customer_phone" selected>PHONE</option>
																		</c:if>
																		
																	</select>
																</div>
												
																<!-- input & button -->
																<input type="text" class="form-control" name = "searchText">
																<div class="input-group-append">
																	<button class="btn btn-sm btn-primary" type="submit">SEARCH</button>
																</div>
															</div>
														</div>													
													</div>
												</form>
												
											</div>											
											
											
										
											
								          	<!-- customer 페이징 처리 시작 -->
											<div>
												<ul class="pagination justify-content-center">
													
													<!-- 페이지 처음 -->
													<li class="page-item">
														<a class="page-link" href="${pageContext.request.contextPath }/customer/customerList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
															<span>처음</span>
														</a>
													</li>
													
													<!-- 페이지 이전(-10의 1페이지) -->
													<c:if test="${previousPage > 0}">
														<li class="page-item">
															<a class="page-link" href="${pageContext.request.contextPath }/customer/customerList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
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
															<a class="page-link" href="${pageContext.request.contextPath }/customer/customerList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
																<span>${i }</span>
															</a>
														</c:if>
														
																</li>
														
													</c:forEach>
											
													<!-- 페이지 다음(+10의 1페이지) -->
													<c:if test="${nextPage <= lastPage }">
														<li class="page-item">
															<a class="page-link" href="${pageContext.request.contextPath }/customer/customerList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
																<span>다음</span>
															</a>
														</li>
													</c:if>
											
													<!-- 페이지 마지막 -->
													<li class="page-item">
														<a class="page-link" href="${pageContext.request.contextPath }/customer/customerList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
															<span>마지막</span>
														</a>
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

