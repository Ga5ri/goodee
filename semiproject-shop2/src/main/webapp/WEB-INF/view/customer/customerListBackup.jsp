<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>empList.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
		<!-- Bootstrap CDN 시작 -->
		<!-- Latest compiled and minified CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Latest compiled JavaScript -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Bootstrap CDN 끝 -->
		
		
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>CUSTOMER LIST</h1>
			</div>
			
			<div>
				<!-- 검색 -->
				<form method = "get" action = "${pageContext.request.contextPath }/customer/customerList">
					<div>
						<select name = "searchCategory" id = "searchCategory">
							<c:if test="${param.searchCategory == null || param.searchCategory == 'customer_id' }">
								<option value = "customer_id" selected>customerId</option>
								<option value = "customer_name">customerName</option>
								<option value = "customer_phone">customerPhone</option>
							</c:if>
							
							<c:if test="${param.searchCategory == 'customer_name' }">
								<option value = "customer_id">customerId</option>
								<option value = "customer_name" selected>customerName</option>
								<option value = "customer_phone">customerPhone</option>
							</c:if>
							
							<c:if test="${param.searchCategory == 'customer_phone' }">
								<option value = "customer_id">customerId</option>
								<option value = "customer_name">customerName</option>
								<option value = "customer_phone" selected>customerPhone</option>
							</c:if>
							
						</select>
				
						<input type = "text" name = "searchText" id = "searchText">
						<button type = "submit" >검색</button>
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
			
			
			<div>
				<table border = "1">
					<tr>
						<th>customerId</th>
						<th>customerName</th>
						<th>customerPhone</th>
						<th>point</th>
					</tr>
					
					<c:forEach var="customer" items="${customerList }">
						<tr>
							<td>
								<a href = "${pageContext.request.contextPath }/customer/customerOne?customerId=${customer.customerId }">
									${customer.customerId }
								</a>
							</td>
							<td>${customer.customerName }</td>
							<td>${customer.customerPhone }</td>
							<td>${customer.point }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		
		</div>
		
	</body>
</html>