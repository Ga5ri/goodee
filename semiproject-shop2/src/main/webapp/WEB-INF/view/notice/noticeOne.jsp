<jsp:include page = "/WEB-INF/view/inc/menuTest.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>공지 상세페이지</title>
		
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
									<h2 class = "font-weight-bold text-center" align="center" style="padding-left: 3em">공지사항 자세히보기</h2>
									<div class="row justify-content-center mt-3">
										<c:forEach var="n" items="${list}">
											<div class="col-12">
											<br>
												<div align="center" style="padding-left: 50em">작성일 : ${n.createdate}</div>
											
												<!-- 공지 상세보기 (공지 종류, 공지 내용)-->
												<div class="table-responsive mb-5">
														<table border="1" class = "table expandable-table table-hover text-center">
															<tr>
																<th>공지제목</th>
																<td>${n.noticeTitle}</td>
															</tr>
															<tr>
																<th>공지내용</th>
																<td>${n.noticeContent}</td>
															</tr>
														</table>												
												</div>
													
												<div class="input-group-append justify-content-center" style="padding-left: 8em">
													<!-- 공지수정, 관리자만 접근가능 -->
													<c:if test="${loginEmp != null}">
														<button  class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/notice/modifyNotice?noticeCode=${n.noticeCode}'">
															<span>수정하기</span> 
														</button>	
														<button id="remove" class="btn btn-sm btn-primary" onClick="location.href='${pageContext.request.contextPath}/emp/checkPw?targetUrl=/notice/deleteNotice?noticeCode=${n.noticeCode}'">
															<span>삭제하기</span> 
														</button>
													</c:if>
												</div>
											</div>
										</c:forEach>
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