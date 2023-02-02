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

		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-md-auto grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h2 class = "font-weight-bold text-center text-primary">고객 회원가입</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-auto">
										
											
											
												<div>
													<form class = "pt-3" action = "${pageContext.request.contextPath }/customer/addCustomer" method = "post">
														<div class="form-group">
															<input type = "text" name = "customerId" id = "customerId"
																	class="form-control form-control-lg" placeholder="ID">
														</div>
														<div class="form-group">
															<input type = "password" name = "customerPw" id = "customerPw"
																	class="form-control form-control-lg" placeholder="Password">
														</div>
														<div class="form-group">
															<input type = "text" name = "customerName" id = "customerName"
																	class="form-control form-control-lg" placeholder="Name">
														</div>
														<div class="form-group">
															<input type = "text" name = "customerPhone" id = "customerPhone"
																	class="form-control form-control-lg" placeholder="Phone">
														</div>
														<div class="form-group">
															<textarea rows = "3" cols = "50" name = "address" id = "address"
																		class="form-control form-control-lg" placeholder="Address"></textarea>
														</div>
										                <div class="mt-5">
															<button type = "submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">회원가입</button>
														</div>
													
													</form>
												</div>
											
											
											
											
											
											
											
											
											
											
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

