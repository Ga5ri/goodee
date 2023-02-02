<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrderForm</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){		
		/* 입력 값 제한 */
		$( '#usePoint' ).on("input", function() {
		    let point = Number($('#point').val());
		    let usePoint = Number($('#usePoint').val());
		    let orderPrice = Number($('#orderPrice').val());
		    if (usePoint > point) {
		    	$('#usePoint').val(point) ;
		    	return;
		    }
		    if ( usePoint < 0 || usePoint == null || usePoint == '') {
		    	alert("포인트를 확인해주세요");
		    	$('#usePoint').val(0) ;
		    	return;
		    }
	    }); /* 	var b = a.replace(/(^0+)/, ""); */

		/* 결제가격 계산 */
		$( '#goodsPrice, #orderQuantity, #usePoint' ).on("blur", function() {
		    let goodsPrice = Number($('#goodsPrice').val());
		    let orderQuantity = Number($('#orderQuantity').val());
		    let usePoint = Number($('#usePoint').val());
		    let resultPrice = goodsPrice * orderQuantity - usePoint;		    
		    $('#orderPrice').val(resultPrice);
		    if(orderQuantity < 0 || $('#orderPrice').val() < 0){
		    	alert("입력값를 확인해주세요");		
		    	orderQuantity.val(0);
		    }
	    });
		
		/* 주문일 계산 */
	    let now = new Date();
	    let year = ('0' + now.getFullYear()).slice(-2);
	    let month = ('0' + (now.getMonth() + 1)).slice(-2);
	    let day = ('0' + now.getDate()).slice(-2);

	    let today = year + '/' + month  + '/' + day;
	    $('#createdate').val(today); 
	  });	
</script>
<script>
</script>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	<h1>주문</h1>
	<form method="post" action="${pageContext.request.contextPath}/order/addOrder">
		<table class="table" border="1">		
			<!-- 고객아이디 : 로그인 후 받아옴  -->
			<input type="hidden" id="loginId" name="loginId" value="${loginId}">
			<!-- 주문상태 -->
			<input type="hidden" id="orderState" name="orderState" value="결제"> <!-- 일단 결제 -->
			<!-- 상품번호 -->
			<input type="hidden" id="goodsCode" name="goodsCode" value="${goodsCode}" readonly>
			<tr>
				<th>상품이름</th>
				<td><input type="text" id="goodsName" name="goodsName" value="${goodsName}" readonly></td>				
			</tr>
			<tr>
				<th>상품가격</th>
				<td><input type="text" id="goodsPrice" name="goodsPrice" value="${goodsPrice}" readonly></td>				
			</tr>
			<tr>
				<th>주문자</th>
				<td><input type="text" id="customerName" name="customerName" value="${customerName}" readonly></td>				
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" id="customerPhone" name="customerPhone" value="${customerPhone}" readonly></td>				
			</tr>
			<tr>
				<th>배송지</th> <!-- customerAddress에서 받아옴 -->
				<td>
					<select id="addressCode" name="addressCode">
						<c:forEach var="ad" items="${customerAddress}">
							<option value="${ad.addressCode}">${ad.address}</option> <!-- 실제주소 text를 보여주고 주소코드로 값 처리 -->
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>보유 포인트</th>
				<td>
				    <input type="number" id="point" name="point" value="${point}" readonly>
				    <label for="earnPoint">적립 포인트</label>
				</td>
		    </tr>
		    <tr>
				<th>사용 포인트</th>
				<td>
				    <input type="number" id="usePoint" name="usePoint" min="0" max="${orderPrice}" value="0">
				    <label for="usePoint">사용 포인트</label>
			    </td>		    
		    </tr>
			<tr>
				<th>주문수량</th><!-- 추후 최대 수량 재고로 제한 -->
				<td><input type="number" id="orderQuantity" name="orderQuantity" min="1" value="0"></td>
			</tr>
			<tr>
				<th>결제가격</t0h>
				<td><input type="number" id="orderPrice" name="orderPrice" min="0" readonly></td> <!-- goods에서 받아옴 -->
			</tr>
			<tr>
				<th>주문일</th>
				<td><input type="text" id="createdate" name="createdate" readonly></td>
			</tr>
		</table>
		<div>
			<button id="btnAdd" type="submit">작성완료</button>
		</div>
	</form>
</body>
</html>
