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
			
			setTimeout(function() {

				alert("세션이 만료되었습니다.");

				location.href = "${pageContext.request.contextPath}/nonMember/deleteNonMember?customerId="+'${loginCustomer.customerId}';

				}, 600000) // 10분동안 주문없을시 세션만료 비회원생성된 아이디 삭제
				
			// 핸드폰 유효성 검사
			/*
				유효성체크
				1) 문자열 내의 모든 - 제거
				2) 문자열이 숫자인지 체크
				3) 총 11자리인지 체크
				4) 앞 3자리 가능한 번호인지 체크
				5) DB 저장할 땐 다시 - 추가
			*/
			
			let customerPhone = $('#customerPhone');
			let phone;
			let phoneThree;
			let result;
			let possibleThree = ['010', '011', '016', '017', '018', '019'];			
			
			customerPhone.on('blur', function(event) {
				
				// 디버깅
				console.log(customerPhone.val() + ' <-- customerPhon.val()');
				
				// replace(A, B) : A를 B로 바꿈
				// /-/g : 문자열 정규표현식. 문자열 내의 모든 -을 뜻 함.
				// replace(/-/g, "") : 문자열 내의 모든 - 를 ""으로 대체하여 
				// 						모든 - 삭제하는 역할.
				phone = customerPhone.val().replace(/-/g, "");				
				// 디버깅
				console.log(phone + ' <--  - 제거');			
				
				// 모든 - 제거 한 후 숫자인지 체크
				// isNan 숫자 일 경우 false, 숫자가 아닐경우 true
				if(isNaN(phone)) {					
					// 디버깅
					console.log(isNaN(phone) + ' <-- isNaN(phone)');					
					// input value 초기화
					customerPhone.val('');					
					alert('올바른 휴대폰 번호를 입력하세요.');					
					return;					
				}
				
				// 총 11자리 인지 체크
				if(phone.length != 11) {					
					// 디버깅
					console.log(phone.length + ' <-- phone.length');				
					// input value 초기화
					customerPhone.val('');					
					alert('올바른 휴대폰 번호를 입력하세요.');
					return;					
				}
				
				// 번호 앞 3자리 추출
				phoneThree = phone.substring(0, 3);				
				// 디버깅
				console.log(phoneThree + ' <-- phoneThree');				
				// 앞 3자리가 가능한 3자리 번호인지 체크
				for(let i=0; i<possibleThree.length; i+=1) {					
					if(phoneThree == possibleThree[i]) {
						// 같으면 가능한 앞 3자리						
						result = 1;						
						// DB에 저장할 땐 다시 - 추가 되도록 value 값 설정
						customerPhone.val(phoneThree + '-' + phone.substring(3, 7) + '-' + phone.substring(7, 11));						
						// 디버깅
						console.log(possibleThree[i] + ' <-- 가능한 앞 3자리');						
					}					
				}			
				// 앞 3자리가 가능한 번호가 아닐경우
				if(result != 1) {					
					alert('올바른 휴대폰 번호를 입력하세요.');					
					// input value 초기화
					customerPhone.val('');					
					return;					
				}									
			});			
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
                        <h4>비회원 주문 페이지</h4>
                        <div class="breadcrumb__links">
                            <a href="./index.html">Home</a>
                            <a href="./shop.html">Shop</a>
                            <span>비회원 주문 페이지</span>
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
				<form action = "${pageContext.request.contextPath}/order/orderPageNonMember" method = "post">
					<input type = "hidden" name = "customerPw" id = "customerPw" value="1234">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
	                    	<h6 class="checkout__title">주문자 정보</h6>
	                        <div class="row">
	                        	<div class="col-lg-6">
	                            	<div class="checkout__input">
	                                	<p>이름<span>*</span></p>
										<input type = "text" name = "customerName" id = "customerName">
	                                </div>
	                            </div>
	                            <div class="col-lg-6">
	                            	<div class="checkout__input">
	                                    <p>연락처<span>*</span></p>
										<input type = "text" name = "customerPhone" id = "customerPhone">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="checkout__input">
	                            <p>주소<span>*</span></p>
								<input type="text" name = "address" id = "address">
	                        </div>						            
		                </div>
	                    <div class="col-lg-4 col-md-6">
	                        <div class="checkout__order">
	                            <h4 class="order__title">결제 정보</h4>
	                            <ul class="checkout__total__products">
	                                <li>주문금액 <span>${orderOne.goodsPrice * orderOne.orderQuantity}원</span></li>
									<c:if test="${orderOne.pointKind eq '사용'}">
	                                	<li>포인트 할인 <span>회원 주문시 혜택 제공</span></li>
									</c:if>
	                            </ul>
	                            <ul class="checkout__total__all">
	                            	<li>최종결제 금액 <span>${orderOne.orderPrice}원</span></li>
	                        	</ul>
	                    		<button type="submit" class="site-btn">PLACE ORDER</button>
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