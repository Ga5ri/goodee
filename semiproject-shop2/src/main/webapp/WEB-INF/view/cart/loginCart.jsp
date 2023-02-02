<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
	<head>
	    <meta charset="UTF-8">
	    <title>Document</title>
		<link href="https://fonts.googleapis.com/css?family=Montserrat:500,800" rel="stylesheet">
		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/style.css">
		
	    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	    
	    <script type="text/javascript">
	        $(document).ready(function() {
	        	
				// 페이지에 바로 버턴 누름을 방지하기 위해
				// 고객 로그인
				$('#signinBtn').click(function() {
					
					// 디버깅
					// console.log($('#customerId').val());
					// console.log($('#customerPw').val());
					
					// ID 유효성 체크
					if( $('#customerId').val().length < 1) {
						alert('아이디가 입력되지 않았습니다.');
						$('#customerId').focus();
						return;
					}	
					// PW 유효성 체크
					if( ($('#customerPw').val().length) < 1) {
						alert('비밀번호가 입력되지 않았습니다.');
						$('#customerPw').focus();
						return;
					}	
					
					$('#signinForm').submit();
				});
	        });
	    </script>
	</head>
	<body>
		<div id="wrapper">    
		
			<!-- Heading -->
			<h1>LOGIN</h1>			
		
			<!-- Form -->
			<form id="signinForm" action="${pageContext.request.contextPath}/cart/loginCart" method="post">
			 <!-- ID input -->
			 <div class="first-input input__block ">
			    <input type="text" placeholder="ID" class="input" id="customerId" name="customerId" value="test">
			 </div>
			 <!-- password input -->
			 <div class="input__block">
			    <input type="password" placeholder="Password" class="input" id="customerPw" name="customerPw" value="1234">
			 </div>
			 <!-- Login button -->
			  <button class="signin__btn" id="signinBtn" type="button">
			    로그인
			  </button>
			</form>
			
			<!-- separator -->
			  <div class="separator">
			    <p>OR</p>
			  </div>
			  
			  <!-- 회원가입 button -->
			  <button class="google__btn" onClick="location.href='${pageContext.request.contextPath}/customer/addCustomer'">
			    <i class="fa"></i>
			    회원가입
			  </button>
		  
			  <!-- 비회원 button -->
			  <button class="github__btn" onClick="location.href='${pageContext.request.contextPath}/order/orderPageNonMember'">
			    <i class="fa"></i>
			    비회원 주문하기
			  </button>
			
			<footer>
			  <p>
			    Thank you for watching
			    <i class="fa fa-heart"></i> 
			  </p>
			  <p>
			    SNS :
			    <a href="https://www.facebook.com/chouaib45" >
			      <i class="fa fa-facebook"></i>
			    </a>
			    <a href="http://twitter.com/chouaibblgn45">
			      <i class="fa fa-twitter"></i> 
			    </a>
			    <a href="http://instagram.com/chouaib_blgn">
			      <i class="fa fa-instagram"></i> 
			    </a>
			    <a href="http://linkedin.com/in/chouaibblgn/">
			      <i class="fa fa-linkedin"></i>
			    </a>
			    <a href="https://www.behance.net/geek30">
			      <i class="fa fa-behance"></i>
			    </a>
			  </p>
			</footer>			
				
		</div>
	</body>
</html>