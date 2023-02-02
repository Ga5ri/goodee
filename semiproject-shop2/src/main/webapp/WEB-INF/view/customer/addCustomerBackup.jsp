<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
		<!-- Bootstrap CDN 시작 -->
		<!-- Latest compiled and minified CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Latest compiled JavaScript -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Bootstrap CDN 끝 -->
		
		<script>
			$(document).ready(function() {
				
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
				

				
				
				
				/*
				연습
				
				let strPhoneThree = phone.substring(0, 3)
				
				let phoneThreeP = ['010', '011', '016', '017', '018', '019'];
				console.log(phone + ' <-- strPhone');
				console.log(phone.length + ' <-- strPhone.length');
				console.log(strPhoneThree + ' <-- strPhoneThree');
				console.log(phoneThreeP + ' <-- phoneThreeP');
				
				console.log(isNaN('010') + ' <-- isNan(\'010\')' );
				
				
				for(let i=0; i<phoneThreeP.length; i+=1) {
					
					if(strPhoneThree == phoneThreeP[i]) {
						
						console.log(phoneThreeP[i] + ' equal');
						
					}
					
				}
				
				*/
				
			});
		
		
		
		</script>
		
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>CUSTOMER 회원가입</h1>
			</div>
		
			<div>
				<form action = "${pageContext.request.contextPath }/customer/addCustomer" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>customerId</th>
								<td>
									<input type = "text" name = "customerId" id = "customerId">
								</td>
							</tr>
							
							<tr>
								<th>customerPw</th>
								<td>
									<input type = "password" name = "customerPw" id = "customerPw">
								</td>
							</tr>
							
							<tr>
								<th>customerName</th>
								<td>
									<input type = "text" name = "customerName" id = "customerName">
								</td>
							</tr>
							
							<tr>
								<th>customerPhone</th>
								<td>
									<input type = "text" name = "customerPhone" id = "customerPhone">
								</td>
							</tr>
							
							<tr>
								<th>address</th>
								<td>
									<textarea rows = "3" cols = "50" name = "address" id = "address"></textarea>
								</td>
						</table>
					</div>
				
					<div>
						<button type = "submit">회원가입</button>
					</div>
				
				</form>
			</div>
		
		</div>
		
	</body>
</html>