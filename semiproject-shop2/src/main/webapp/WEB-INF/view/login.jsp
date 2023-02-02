<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" >
	<head>
		<meta charset="UTF-8">
		<title> 로그인 </title>
		<link href="https://fonts.googleapis.com/css?family=Montserrat:500,800" rel="stylesheet">
		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/style.css">
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	    <script type="text/javascript">
	        $(document).ready(function() {
				
	        	 // When page loads...
	            $(".tab_content").hide(); //Hide all content
	            $("ul.tabs li:first").addClass("active").show(); //Activate first tab
	            $(".tab_content:first").show(); //Show first tab content
	
	            // On Click Event
	            $("ul.tabs li").click(function() {
	
	                $("ul.tabs li").removeClass("active"); //Remove any "active" class
	                $(this).addClass("active"); //Add "active" class to selected tab
	                $(".tab_content").hide(); //Hide all tab content
	
	                var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
	                $(activeTab).fadeIn(); //Fade in the active ID content
	                return false;
	            });
	            
				// 페이지에 바로 버턴 누름을 방지하기 위해
				// 고객 로그인
				$('#signinBtn').click(function() {
					
					// ID 유효성 체크
					if( ($('#customerId').val().length) < 1) {
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
				
				// 사원 로그인
				$('#empSigninBtn').click(function() {
					
					// ID 유효성 체크
					if( ($('#empId').val().length) < 1) {
						alert('아이디가 입력되지 않았습니다.');
						$('#empId').focus();
						return;
					}	
					// PW 유효성 체크
					if( ($('#empPw').val().length) < 1) {
						alert('비밀번호가 입력되지 않았습니다.');
						$('#empPw').focus();
						return;
					}	
					$('#empSigninForm').submit();
				});
	        });
	    </script>
	</head>
	<body>
	
	<!-- partial:index.partial.html -->
	<div class="container">
        <a href="${pageContext.request.contextPath}/home"><img src="${pageContext.request.contextPath}/resources/img/logo.png" alt=""></a>		
                
		<!-- Heading -->
		<h1>LOGIN</h1>
	  
		<!-- Links -->
		<ul class="links tabs">
		  <li>
		    <a href="#tab1" id="signin">고객 로그인</a>
		  </li>
		  <li>
		    <a href="#tab2" id="signinEmp">판매자 로그인</a>
		  </li>
		</ul>
		<!--탭 콘텐츠 영역 -->
		<div class="tab_container">
		
		<c:choose>
			<c:when test="${goodsCode eq 0}">
				<!-- Form -->
				<div id="tab1" class="tab_content">
					<form id="signinForm" action="${pageContext.request.contextPath}/login" method="post">
					 <!-- ID input -->
					 <div class="first-input input__block first-input__block">
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
					  <button class="google__btn" onclick="location.href='${pageContext.request.contextPath}/customer/addCustomer'">
					    <i class="fa"></i>
					    회원가입
					  </button>
				  
					  <!-- 비회원 button -->
					  <button class="github__btn" onclick="location.href='${pageContext.request.contextPath}/order/selectOrderForNonMember'">
					    <i class="fa"></i>
					    비회원 주문조회
					  </button>
				</div>
			</c:when>
			<c:otherwise>
				<%-- Form --%>
				<div id="tab1" class="tab_content">
					<form id="signinForm" action="${pageContext.request.contextPath}/login" method="post">
					    <input type="hidden" id="goodsCode" name="goodsCode" value="${goodsCode}">
					 <%-- ID input --%>
					 <div class="first-input input__block first-input__block">
					    <input type="text" placeholder="ID" class="input" id="customerId" name="customerId" value="test">
					 </div>
					 <%-- password input --%>
					 <div class="input__block">
					    <input type="password" placeholder="Password" class="input" id="customerPw" name="customerPw" value="1234">
					 </div>
					 <%-- Login button --%>
					  <button class="signin__btn" id="signinBtn" type="button">
					    로그인
					  </button>
					</form>
					
					<%-- separator --%>
					  <div class="separator">
					    <p>OR</p>
					  </div>
					  
					  <%-- 회원가입 button --%>
					  <button class="google__btn" onclick="location.href='${pageContext.request.contextPath}/customer/addCustomer'">
					    <i class="fa"></i>
					    회원가입
					  </button>
				  
					  <%-- 비회원 button --%>
					  <button class="github__btn" onclick="location.href='${pageContext.request.contextPath}/order/orderPageNonMember?goodsCode=${goodsCode}'">
					    <i class="fa"></i>
					    비회원 주문하기
					  </button>
				</div>
			</c:otherwise>
		</c:choose>
		  
			<!-- Form -->
			<div id="tab2" class="tab_content">
				<form id="empSigninForm" action="${pageContext.request.contextPath}/login" method="post">
				 <!-- ID input -->
				 <div class="first-input input__block first-input__block">
				    <input type="text" placeholder="ID" class="input" id="empId" name="empId" value="emptest">
				 </div>
				 <!-- password input -->
				 <div class="input__block">
				    <input type="password" placeholder="Password" class="input" id="empPw" name="empPw" value="1234">
				 </div>
				 <!-- Login button -->
				  <button class="signin__btn" id="empSigninBtn" type="button">
				    로그인
				  </button>
				</form>
				
				<!-- separator -->
				  <div class="separator">
				    <p>OR</p>
				  </div>
				  
				  <!-- 회원가입 button -->
				  <button class="google__btn" onclick="location.href='${pageContext.request.contextPath}/emp/addEmp'">
				    <i class="fa"></i>
				    사원가입
				  </button>
			</div>
		</div>
	  
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
	<!-- partial -->
	  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script><script  src="${pageContext.request.contextPath}/resources/dist/script.js"></script>
	</body>
</html>