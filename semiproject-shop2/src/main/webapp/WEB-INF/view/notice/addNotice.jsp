<jsp:include page = "/WEB-INF/view/inc/menuTest.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>공지 작성</title>
		
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
			<!-- 문의글 내용 유효성체크 -->
			$(document).ready(function() {						
				$('#addBtn').click(function() {
					// 미입력시
					if( ($('#noticeMemo').val().length) < 1 )  {
						alert('입력된값이 없습니다.');
						$('#noticeMemo').focus();
						return;
					}
					// 공백만 입력시
					if($('#noticeMemo').val().trim() == '')  {
						alert('공백만 입력할 수 없습니다.');
						$('#noticeMemo').text('');
						$('#noticeMemo').focus();
						return;
					} 
					$('#addForm').submit();
				});
					
			});
		</script>
	</head>
	
	<body>
		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-md-auto grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div class="content-wrapper">
									<div align="center" style="padding-right: 55em"> 
										<button class="btn btn-sm btn-primary" onclick="history.back()">뒤로가기</button>
									</div>	
									<h2 class = "font-weight-bold text-center">공지 작성</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
											<div class="table-responsive mb-5">
												<form id="addForm" action="${pageContext.request.contextPath}/notice/addNotice" method="post">
													<table border="1" class = "table expandable-table table-hover text-center">
														<tr>
															<th>작성자 아이디</th>
															<td><input type="text" name="empId" value="${loginEmp.empId}" readonly="readonly"></td>
														</tr>
														<tr>
															<th>공지 제목</th>
															<td><input type="text" name="noticeTitle"></td>
														</tr>
														<tr>
															<th>공지 내용</th>
															<td><textarea rows="5" cols="30" id="noticeMemo" name="noticeContent" placeholder="공지 내용을 입력하세요."></textarea></td>
														</tr>
													</table>
													<br>
													<!-- button -->
													<div class="input-group-append justify-content-center">
														<button class="btn btn-sm btn-primary" id="addBtn" type="button" >공지 추가</button>
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
		</div>
	</body>
</html>