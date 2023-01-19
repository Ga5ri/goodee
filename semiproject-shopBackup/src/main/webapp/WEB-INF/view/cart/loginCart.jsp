<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-KR">
	<head>
	    <meta charset="UTF-8">
	    <title>Document</title>
	
	    <style type="text/css">
	        
	        body {
	            font-family:"Malgun Gothic";
	            font-size: 0.8em;
	
	        }
	        /*TAB CSS*/
	
	        ul.tabs {
	            margin: 0;
	            padding: 0;
	            float: left;
	            list-style: none;
	            height: 32px; /*--Set height of tabs--*/
	            border-bottom: 1px solid #999;
	            border-left: 1px solid #999;
	            width: 100%;
	        }
	        ul.tabs li {
	            float: left;
	            margin: 0;
	            padding: 0;
	            height: 31px; /*--Subtract 1px from the height of the unordered list--*/
	            line-height: 31px; /*--Vertically aligns the text within the tab--*/
	            border: 1px solid #999;
	            border-left: none;
	            margin-bottom: -1px; /*--Pull the list item down 1px--*/
	            overflow: hidden;
	            position: relative;
	            background: #e0e0e0;
	        }
	        ul.tabs li a {
	            text-decoration: none;
	            color: #000;
	            display: block;
	            font-size: 1.2em;
	            padding: 0 20px;
	            /*--Gives the bevel look with a 1px white border inside the list item--*/
	            border: 1px solid #fff; 
	            outline: none;
	        }
	        ul.tabs li a:hover {
	            background: #ccc;
	        }
	        html ul.tabs li.active, html ul.tabs li.active a:hover  {
	             /*--Makes sure that the active tab does not listen to the hover properties--*/
	            background: #fff;
	            /*--Makes the active tab look like it's connected with its content--*/
	            border-bottom: 1px solid #fff; 
	        }
	
	        /*Tab Conent CSS*/
	        .tab_container {
	            border: 1px solid #999;
	            border-top: none;
	            overflow: hidden;
	            clear: both;
	            float: left; 
	            width: 100%;
	            background: #fff;
	        }
	        .tab_content {
	            padding: 20px;
	            font-size: 1.2em;
	        }
	    </style>
	
	
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
					if( ($('#custoemrId').val().length) < 1) {
						alert('아이디가 입력되지 않았습니다.');
						$('#custoemrId').focus();
						return;
					}	
					// PW 유효성 체크
					if( ($('#custoemrPw').val().length) < 1) {
						alert('비밀번호가 입력되지 않았습니다.');
						$('#custoemrPw').focus();
						return;
					}	
					$('#signinForm').submit();
				});
	        });
	    </script>
	</head>
	<body>
		<div id="wrapper">    
		
		    <!--탭 콘텐츠 영역 -->
		    <div class="tab_container">
		    
				<!--Customer 로그인-->
		        <div id="tab1" class="tab_content">
		            <form id="signinForm" action="${pageContext.request.contextPath}/cart/loginCart" method="post">
					<div>
						<label>Login ID&nbsp;</label>
						<input id="custoemrId" type="text"  name="customerId">
					</div>
					<div>
						<label>Password</label>
						<input id="custoemrPw" type="password" name="customerPw">
					</div>
					<div>
				    	<button id="signinBtn" type="button">로그인</button>
				    </div>
				    </form>
						<div>
						 	<a href="${pageContext.request.contextPath}/customer/addCustomer">회원가입</a>  
					    </div>
		        </div>
		    </div>
		</div>
	</body>
</html>