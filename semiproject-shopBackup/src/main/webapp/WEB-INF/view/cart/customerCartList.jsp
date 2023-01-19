<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cartList.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
		<style>
			img {
				width : 100px;
				height : 100px;
			}
		</style>
		
		<script>
		
		$(document).ready(function() {
			
			var count = $('#iCount').val();
			
			console.log(count);
			
			for(let i=1; i<=count; i+=1) {
				
				$('#goodsCode' + i).hide();
				$('#quantity' + i).hide();
				
				// + 버튼 클릭 할 때				
				$('#btnPlus' + i).click(function() {
					$('#inputQuantity' + i).val(parseInt($('#inputQuantity' + i).val()) + 1);
					$('#quantity' + i).val($('#inputQuantity' + i).val());
					
					// 디버깅
					let x1 = $('#quantity' + i).val();
					let x2 = $('#inputQuantity' + i).val();
					
					console.log(x1 + ' <-- x1 / ' + i);
					console.log(x2 + ' <-- x2 / ' + i);
					
					$('#quantityForm' + i).submit();
					
				});
				
				// - 버튼 클릭 할때				
				$('#btnMinus' + i).click(function() {
					if($('#inputQuantity' + i).val() > 1) {
						$('#inputQuantity' + i).val(parseInt($('#inputQuantity' + i).val()) - 1);
						
						$('#quantity' + i).val($('#inputQuantity' + i).val());
						
						// 디버깅
						let x1 = $('#quantity' + i).val();
						let x2 = $('#inputQuantity' + i).val();
						
						console.log(x1 + ' <-- x1 / ' + i);
						console.log(x2 + ' <-- x2 / ' + i);
						
						$('#quantityForm' + i).submit();
					}
				});
				
				
				// input text 클릭하여 직접 수정할 때
				$('#inputQuantity' + i).change(function() {
					$('#quantity' + i).val($('#inputQuantity' + i).val());
					
					// 미완성
					let x1 = $('#quantity' + i).val();
					let x2 = $('#inputQuantity' + i).val();
					
					console.log(x1 + ' <-- x1 / ' + i);
					console.log(x2 + ' <-- x2 / ' + i);
					
					$('#quantityForm' + i).submit();
				});				
				

				// 수량에 따른 total 가격 표시
				let calPrice = $('#price' + i).val() * $('#inputQuantity' + i).val()
				$('#calPrice' + i).prepend(calPrice + '원');				
				
				
			}
			
			
			
			
			
			
				 
			});		
		
		</script>
		
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>회원 장바구니</h1>
			</div>

				<table border = "1">
				
					<c:forEach var="cart" items="${customerCartList }" varStatus="i">
						<form id = "quantityForm${i.count }" action = "${pageContext.request.contextPath }/cart/customerCartList?action=modifyQuantity" method = "post">
							<input type = "text" name = "goodsCode" id = "goodsCode${i.count }" value = ${cart.goodsCode }>
							<input type = "text" name = "quantity" id = "quantity${i.count }">
						</form>		
						<tr>
							<td rowspan = "2">
								<img src = "${pageContext.request.contextPath }/upload/${cart.filename }">
							</td>
							<td colspan = "3">상품명 : ${cart.goodsName }</td>
							<td rowspan = "2">
								<button type = "button" onClick="location.href='${pageContext.request.contextPath}/cart/customerCartList?action=deleteCart&goodsCode=${cart.goodsCode }'">삭제</button>
							</td>
						</tr>
					
						<tr>
							<td>사업자 : ${cart.empId }</td>
							<td>
								<button type = "button" id = "btnMinus${i.count }">-</button>
								<input type = "text" name = "inputQuantity${i.count }" id = "inputQuantity${i.count }" value = "${cart.cartQuantity }" style = "width : 20px;">
								<button type = "button" id = "btnPlus${i.count }">+</button>
							</td>
							<td>
								<input type = "hidden" id = "price${i.count }" value = "${cart.goodsPrice }">
								<span id = "calPrice${i.count }"></span>
							</td>
						</tr>
			
						<c:if test="${i.last }">
							<input type = "hidden" name = "iCount" id = "iCount" value = "${i.count }">
						</c:if>
						
					</c:forEach>
				
				</table>
		
		
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/customerCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "3">
				<button type = "submit">3번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/customerCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "29">
				<button type = "submit">29번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/customerCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "30">
				<button type = "submit">30번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/customerCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "31">
				<button type = "submit">31번추가</button>
			</form>
		</div>
		
		
		<div>
			<button type = "button" onClick="location.href='${pageContext.request.contextPath}/cart/customerCartList?action=emptyCart'">전체삭제</button>
		</div>
		
		<div>
			<button type = "button" onClick="location.href=''">구매</button>
		</div>
		
		
		
		
		
		
		
		
		
		</div>
		
	</body>
</html>