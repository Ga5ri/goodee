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
		<title>문의 작성</title>
		
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
				// 최대 글자수
				${'MAX_COUNT'} = 500;
				
				// 입력된 메모값
				let questionMemo = document.querySelector('#questionMemo')
				
				$('#questionMemo').keyup(function() {
					// 입력한 값의 글자수
					${'len'} = $('#questionMemo').val().length; 
					
					// 결과
					if(len < MAX_COUNT){
						document.querySelector('#count').innerHTML = len;
					} else if(len > MAX_COUNT-1){
						alert(MAX_COUNT+'자까지 입력가능합니다');
						questionMemo.value = questionMemo.value.substring(0, MAX_COUNT);
					} 
				});	
						
				$('#addBtn').click(function() {
					// 미입력시
					if( ($('#questionMemo').val().length) < 1 )  {
						alert('입력된값이 없습니다.');
						$('#questionMemo').focus();
						return;
					}
					// 공백만 입력시
					if($('#questionMemo').val().trim() == '')  {
						alert('공백만 입력할 수 없습니다.');
						$('#questionMemo').text('');
						$('#questionMemo').focus();
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
									<h2 class = "font-weight-bold text-center">문의 작성</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
											<br>
											<div align="center" style="padding-left: 50em"> 
												글자수 : <span id="count">0</span> / 500
											</div>
											
											<!-- 문의 작성 리스트 -->
											<div class="table-responsive mb-5">
												<form id="addForm" action="${pageContext.request.contextPath}/question/addQuestion" method="post" enctype="multipart/form-data">
													<table border="1" class = "table expandable-table table-hover text-center">
														<tr>
															<th>주문번호/상품명</th>
															<td>
																<select class = "form-control" name="orderCode" id="orderCode">
																	<c:forEach var="q" items="${orderCodeList}">
																		<option value="${q.orderCode}" >${q.orderCode} : ${q.goodsName}</option>
																	</c:forEach>
																</select>
															</td>
														</tr>
														<tr>
															<th>카테고리</th>
															<td>
																<select class = "form-control" name="category" id="category">
																	<option value="배송" >배송</option>
																	<option value="반품" >반품</option>
																	<option value="교환" >교환</option>
																	<option value="기타" >기타</option>
																</select>
															</td>
														</tr>
														<tr>
															<th>문의내용</th>
															<td>
																<textarea id="questionMemo" rows="8" cols="100" name="questionMemo"></textarea>
															</td>
														</tr>
														<tr>
															<th>첨부파일</th>
															<td><input type="file" name="questionImg"></td>
														</tr>
													</table>
													<br>
													<!-- button -->
													<div class="input-group-append justify-content-center">
														<button class="btn btn-sm btn-primary" id="addBtn" type="button" >작성 하기</button>
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