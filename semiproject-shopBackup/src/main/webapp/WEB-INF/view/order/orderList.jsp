<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OrderList</title>
<!-- 부트스트랩5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#rowPerPage').change(function() {
			$('#pageForm').submit();
			alert('change');
		})
	});
</script>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	<div class = "container">
	<h1>주문목록</h1>
		<div class="d-flex justify-content-end">
			<form id="pageForm" method="get" action="${pageContext.request.contextPath}/order/orderList">
				<div align="right">
					<!-- n개씩 보는 선택폼 -->
					<select class="form-control" name="rowPerPage" id="rowPerPage">
						<c:if test="${rowPerPage == 10}">
							<option value="10" selected="selected">10개씩</option>
							<option value="20">20개씩</option>
							<option value="30">30개씩</option>
						</c:if>
						<c:if test="${rowPerPage == 20}">
							<option value="10">10개씩</option>
							<option value="20" selected="selected">20개씩</option>
							<option value="30">30개씩</option>
						</c:if>
						<c:if test="${rowPerPage == 30}">
							<option value="10">10개씩</option>
							<option value="20">20개씩</option>
							<option value="30" selected="selected">30개씩</option>
						</c:if>
					</select>
				</div>
			</form>
		</div>
	
		<!-- 주문목록 -->
		<table class="table">
			<tr class = "text-center">
				<th>주문번호</th>
				<th>상품이름</th>
				<th>상품가격</th>
				
				<th>고객아이디</th>
				<th>고객이름</th>
				<th>휴대폰번호</th>
				<th>주소 </th>
				
				<th>주문수량</th>
				<th>상품가격</th>
				<th>주문상태</th>
				<th>구매확정</th>
				<th>리뷰</th>
				
				
				<th>주문일</th>				
				<th>사용 포인트</th>
				<th>주문취소</th>
			</tr>
			<c:forEach var="o" items="${orderList}">
				<tr>
					<td>${o.orderCode}</td>
					<td><a type="button" href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${o.goodsCode}">${o.goodsName}</a></td>
					<td>${o.goodsPrice}</td>
					
					<td>${o.customerId}</td>
					<td>${o.customerName}</td>
					<td>${o.customerPhone}</td>
					<td>${o.address}</td>
					
					<td>${o.orderQuantity}</td>
					<td>${o.orderPrice}</td>
					<td>${o.orderState}</td>
					<td><a type="button" href="${pageContext.request.contextPath}/order/orderConfirm?goodsCode">구매확정</a></td>
					<td><a type="button" href="${pageContext.request.contextPath}/review/addReview?goodsCode=${o.goodsCode}">리뷰작성</a></td>
					
					<td>${o.createdate}</td>
					<td>${o.point}</td>
					<td><a type="button" href="${pageContext.request.contextPath}/order/deleteOrder?orderCode=${o.orderCode}&point=${o.point}">취소</a></td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 페이징 -->
	<nav aria-label="pagiantion">
		<ul class="pagination justify-content-center">
			<li class="page-item">
				<a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${1}&word=${word}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>		      
			<c:if test="${currentPage >= 5}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-4}&word=${word}">${currentPage-4}</a></li>
			</c:if>		    	
			<c:if test="${currentPage >= 4}">
		    	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-3}&word=${word}">${currentPage-3}</a></li>
	    	</c:if>		    	
			<c:if test="${currentPage >= 3}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-2}&word=${word}">${currentPage-2}</a></li>
	    	</c:if>		    	
			<c:if test="${currentPage >= 2}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}">${currentPage-1}</a></li>
	    	</c:if>
			<c:if test="${currentPage > 0}">
				<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage}&word=${word}">${currentPage}</a></li>
	    	</c:if>
			<c:if test="${lastPage - currentPage >= 1}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}">${currentPage+1}</a></li>
	    	</c:if>
			<c:if test="${lastPage - currentPage >= 2}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+2}&word=${word}">${currentPage+2}</a></li>
	    	</c:if>
			<c:if test="${lastPage - currentPage >= 3}">
	   	 		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+3}&word=${word}">${currentPage+3}</a></li>
	    	</c:if>
			<c:if test="${lastPage - currentPage >= 4}">
	    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${currentPage+4}&word=${word}">${currentPage+4}</a></li>
	    	</c:if>		    
		    <li class="page-item">
				<a class="page-link" href="${pageContext.request.contextPath}/order/orderList?rowPerPage=${rowPerPage}&currentPage=${lastPage}&word=${word}" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</ul>
	</nav>
	
	<!-- 검색창 -->
	<div class="d-flex justify-content-center">
		<form action="${pageContext.request.contextPath}/order/orderList" method="get">
			<div class="row">
				<div class="col-auto d-grid mx-auto">
					<c:choose>
						<c:when test="${word == null}"> 
							<input type="search" class="form-control-sm" name="word" id="word" placeholder="검색어를 입력해주세요">
						</c:when>
						<c:otherwise>
							<input type="search" class="form-control-sm" name="word" id="word" placeholder="검색어를 입력해주세요" value="${word}">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-auto">
		        	<button type="submit" class="btn btn-primary">검색</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>