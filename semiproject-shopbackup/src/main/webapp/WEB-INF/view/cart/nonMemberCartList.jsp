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
			/*
			for(let i=1; i<2; i+=1) {
				//$('#active').hide(); // 숨기기	
			}
			*/
			
			var count = $('#iCount').val();
			
			console.log(count);
			
			for(let i=1; i<=count; i+=1) {
				
				
				$('#btnPlus' + i).click(function() {
					$('#inputQuantity' + i).val(parseInt($('#inputQuantity' + i).val()) + 1);
				});
				
				
				$('#btnMinus' + i).click(function() {
					if($('#inputQuantity' + i).val() > 1) {
						$('#inputQuantity' + i).val(parseInt($('#inputQuantity' + i).val()) - 1);
					}
				});
				
				// 미완성
				$('#quantity' + i).val($('#inputQuantity' + i).val());
				
				console.log(($('#quantity') + i));
				
				
				
			}
			
			
			
			
			
			
				 
			});		
		
		</script>
		
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>장바구니</h1>
			</div>

			<form id = "cartForm" action = "${pageContext.request.contextPath }/cart/NonMemberCartList" method = "post">
				<table border = "1">
				
					<c:forEach var="cart" items="${nonMembersCartList }" varStatus="i">
						<tr>
							<td rowspan = "2">
								<input type = "checkbox" name = "active" id = "active${i.count }">active${i.count }
								<input type = "checkbox" name = "quantity" id = "quantity${i.count }">quantity${i.count }
							</td>
							<td rowspan = "2">
								<img src = "${pageContext.request.contextPath }/upload/${cart.filename }">
							</td>
							<td colspan = "3">상품명 : ${cart.goodsName }</td>
							<td rowspan = "2">
								<button type = "button" onClick="location.href='${pageContext.request.contextPath}/cart/nonMemberCartList?action=deleteCart&goodsCode=${cart.goodsCode }'">삭제</button>
							</td>
						</tr>
					
						<tr>
							<td>사업자 : ${cart.empId }</td>
							<td>${cart.goodsPrice }원</td>
							<td>
								<button type = "button" id = "btnMinus${i.count }">-</button>
								<input type = "text" name = "inputQuantity${i.count }" id = "inputQuantity${i.count }" value = "1" style = "width : 20px;">
								<button type = "button" id = "btnPlus${i.count }">+</button>
							</td>
						</tr>
			
						<c:if test="${i.last }">
							<input type = "hidden" name = "iCount" id = "iCount" value = "${i.count }">
						</c:if>
						
					</c:forEach>
				
				</table>
			</form>		
		
		
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/nonMemberCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "3">
				<button type = "submit">3번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/nonMemberCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "29">
				<button type = "submit">29번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/nonMemberCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "30">
				<button type = "submit">30번추가</button>
			</form>
		</div>
		
		<div>
			<form action = "${pageContext.request.contextPath }/cart/nonMemberCartList" method = "post">
				<input type = "hidden" name = "action" value = "addCart">
				<input type = "hidden" name = "goodsCode" value = "31">
				<button type = "submit">31번추가</button>
			</form>
		</div>
		
		
		<div>
			<button type = "button" onClick="location.href='${pageContext.request.contextPath}/cart/nonMemberCartList?action=emptyCart'">삭제</button>
		</div>
		
		
		
		
		
		
		
		
		
		
		
		</div>
		
	</body>
</html>