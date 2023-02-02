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
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__text">
                        <h4>주문상세</h4>
                        <div class="breadcrumb__links">
                            <a href="./index.html">Home</a>
                            <a href="./shop.html">Shop</a>
                            <span>주문상세</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- OrderList Section Begin -->
    <section class="orderList spad">
    	<div class = "container">
			<table class="table">
				<tr class = "text-center">
					<th>상품정보</th><!--  -->
					<th>주문일</th><!--  -->
					
					<th>주문금액(수량)</th><!--  -->
					<th colspan="2">주문상태</th>
				</tr>
				<tr>
					<td>
						<c:if test="${orderOne.filename ne null}">
							<img src="${pageContext.request.contextPath}/upload/${orderOne.filename}" width="100" height="100">
						</c:if>
						<c:if test="${orderOne.filename eq null}">
							<span>사진 준비중!</span>
						</c:if>
						<br>
						<a type="button" href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${orderOne.goodsCode}">${orderOne.goodsName}</a>
					</td>
					<td class="createdate">${orderOne.createdate}</td>
					
					<td>${orderOne.orderPrice}<br>${orderOne.orderQuantity}개</td>
					<td>
						<a type="button" href="${pageContext.request.contextPath}/order/orderConfirm?orderCode=${orderOne.orderCode}">구매확정</a>
						<br>
						${orderOne.orderState}
					</td>
											
					<c:choose>
						<c:when test="${orderOne.orderState eq '구매확정'}">
							<td><a type="button" href="${pageContext.request.contextPath}/review/addReview?orderCode=${orderOne.orderCode}">리뷰작성</a></td>
						</c:when>
						<c:otherwise>
							<td><a type="button" href="${pageContext.request.contextPath}/order/deleteOrder?orderCode=${orderOne.orderCode}&point=${orderOne.point}">주문취소</a></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>
    </section>
    <!-- OrderList Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
                <form action="#">
                    <div class="row">                    	
                        <div class="col-lg-8 col-md-6">
                            <h6 class="checkout__title">주문자 정보</h6>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>이름<span>*</span></p>
                                        <input type="text" value="${orderOne.customerName}" readonly>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>연락처<span>*</span></p>
                                        <input type="text" value="${orderOne.customerPhone}" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>주소<span>*</span></p>
                                <input type="text" value="${orderOne.address}" readonly>
                            </div>						            
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4 class="order__title">결제 정보</h4>
                                <ul class="checkout__total__products">
                                    <li>주문금액 <span>${orderOne.goodsPrice * orderOne.orderQuantity}원</span></li>
									<c:if test="${orderOne.pointKind eq '사용'}">
                                    	<li>포인트 할인 <span>-${orderOne.point}원</span></li>
									</c:if>
                                </ul>
                                <ul class="checkout__total__all">
                                    <li>최종결제 금액 <span>${orderOne.orderPrice}원</span></li>
                                </ul>                                
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->

    <!-- Footer Section Begin -->
    <div>
		<jsp:include page = "/WEB-INF/view/inc/footer.jsp"></jsp:include>
	</div>
    <!-- Footer Section End -->

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

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