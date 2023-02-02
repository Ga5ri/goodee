<jsp:include page = "/WEB-INF/view/inc/menuTest.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>문의 상세페이지</title>
		
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
				$('#remove').click(function(){
					var out = confirm('삭제 하시겠습니까?');
					if(out == true){
						alert('삭제되었습니다.')
					} else { 
						alert('취소되었습니다.')
						return false;
					}
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
									<h2 class = "font-weight-bold text-center" align="center" style="padding-left: 3em">${q.customerId}님의 문의 ( ${q.questionCode} ) </h2>
									<div class="row justify-content-center mt-3">
										<div class="col-12">
										<br>
										<div align="center" style="padding-left: 50em">작성일 : ${q.createdate}</div>
										
										<!-- 문의 상세보기 (문의 종류, 문의내용, 답변일, 답변내용)-->
										<div class="table-responsive mb-5">
											<table border="1" class = "table expandable-table table-hover text-center">
												<tr>
													<th>문의 종류</th>
													<td>${q.category} 문의 </td>
												</tr>
												<tr>
													<th>상품명</th>
													<td>${q.goodsName}</td>
												</tr>
												<tr>
													<th>문의내용</th>
													<td><textarea rows="8" cols="100" readonly="readonly">${q.questionMemo}</textarea></td>
												</tr>
												
												<!-- 첨부 파일 있을시에만 보임 -->
												<c:if test="${q.questionImg == null}">
													<div>&nbsp;</div>
												</c:if> 
												<c:if test="${q.questionImg != null}">
													<tr>
														<th>첨부파일</th>
														<td>
															<div><img src="${pageContext.request.contextPath}/upload/${q.questionImg}" width="200" height="200"></div>
														</td>
													</tr>
												</c:if> 
												
												<tr>
													<th>답변일</th>
													<td>
														<c:if test="${q.commentMemo == null}">답변전</c:if> 
														<c:if test="${q.commentMemo != null}">
															${q.commentCreatedate}
														</c:if> 
													</td>
												</tr>
												<tr>
													<th >답변내용</th>
													<td >
														<c:if test="${q.commentMemo == null}">답변전</c:if> 
														<c:if test="${q.commentMemo != null}">
															<textarea rows="8" cols="100" readonly="readonly">
																${q.commentMemo}
															</textarea>
														</c:if>
													</td>
												</tr>
												</table>
											</div>
											
											<div class="input-group-append justify-content-center" style="padding-left: 8em">
												<!-- 문의글 수정 button -->
												<c:if test="${loginCustomer == customerId and q.commentMemo == null and loginEmp == null}">
													<button  class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/question/modifyQuestion?questionCode=${q.questionCode}'">
														<span>수정하기</span> 
													</button>	
												</c:if>
												<c:if test="${(loginCustomer != customerId and q.commentMemo != null) or (loginEmp != null  and q.commentMemo != null)}">
													<span>&nbsp;</span>
												</c:if>
												
												<!-- 문의글 삭제 button -->	
												<c:if test="${loginCustomer == customerId and q.commentMemo == null and loginEmp == null}">
													<button id="remove" class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/question/removeQuestion?questionCode=${q.questionCode}'">
														<span>삭제하기</span> 
													</button>
												</c:if>
												<c:if test="${(loginCustomer != customerId and q.commentMemo != null) or (loginEmp != null and q.commentMemo != null)}">
													<span>&nbsp;</span>
												</c:if>
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