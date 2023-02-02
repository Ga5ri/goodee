<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>OrderList</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" type="text/css">  
    
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	<script>
		$(function() {			
			/* 주문일 계산 */
		    let now = new Date();
		    let year = now.getFullYear();
		    let month = ('0' + (now.getMonth() + 1)).slice(-2);
		    let day = ('0' + now.getDate()).slice(-2);

		    let today = year + '.' + month  + '.' + day;
		    $('.createdate').text(today); 
		});
	</script>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>리뷰</h4>
                        <div class="breadcrumb__links">
                            <a href="./index.html">Home</a>
                            <a href="./shop.html">Shop</a>
                            <span>리뷰</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
	
	<!-- review Section Begin -->
    <section class="review spad">
		<div class = "container">
			<div class="d-flex justify-content-end">
				<form id="pageForm" method="get" action="${pageContext.request.contextPath}/review/reviewList">
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
	
			<!-- 리뷰목록 -->
			<table class="table">
				<tr class = "text-center">
					<th>상품사진 & 이름</th>
					
					<th>리뷰내용</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>		
				<c:forEach var="r" items="${reviewList}">
					<tr>				
						<td><img src="${pageContext.request.contextPath}/upload/${r.filename}" width="200" height="200"><br>${r.goodsName}</td>
						
						<td>${r.reviewMemo}</td>
						<td>${r.customerId}</td>
						<td class="createdate">${r.createdate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	
		<!-- 페이징 -->
		<nav aria-label="pagiantion">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${1}&word=${word}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>		      
				<c:if test="${lastPage - currentPage <= 0 and lastPage > 4}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-4}&word=${word}">${currentPage-4}</a></li>
				</c:if>		    	
				<c:if test="${lastPage - currentPage <= 1 and lastPage > 3}">
			    	<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-3}&word=${word}">${currentPage-3}</a></li>
		    	</c:if>		    	
				<c:if test="${currentPage >= 3}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-2}&word=${word}">${currentPage-2}</a></li>
		    	</c:if>		    	
				<c:if test="${currentPage >= 2}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}">${currentPage-1}</a></li>
		    	</c:if>
				<c:if test="${currentPage > 0}">
					<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage}&word=${word}">${currentPage}</a></li>
		    	</c:if>
				<c:if test="${lastPage - currentPage >= 1}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}">${currentPage+1}</a></li>
		    	</c:if>
				<c:if test="${lastPage - currentPage >= 2}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage+2}&word=${word}">${currentPage+2}</a></li>
		    	</c:if>
				<c:if test="${currentPage <= 2 and lastPage > 3}">
		   	 		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage+3}&word=${word}">${currentPage+3}</a></li>
		    	</c:if>
				<c:if test="${currentPage <= 1 and lastPage > 4}">
		    		<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${currentPage+4}&word=${word}">${currentPage+4}</a></li>
		    	</c:if>		    
			    <li class="page-item">
					<a class="page-link" href="${pageContext.request.contextPath}/review/reviewList?rowPerPage=${rowPerPage}&currentPage=${lastPage}&word=${word}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
		
		<!-- 검색창 -->
		<div class="d-flex justify-content-center pt-3">
			<form action="${pageContext.request.contextPath}/review/reviewList" method="get">
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
	</section>
    <!-- review Section End --> 
	
    <!-- Js Plugins -->
    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.nice-select.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.nicescroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery.slicknav.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/mixitup.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
</body>
</html>