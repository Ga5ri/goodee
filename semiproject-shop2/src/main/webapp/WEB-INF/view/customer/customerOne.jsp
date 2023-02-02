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
		
	</head>


	<body>

		<div class="container-scroller">
		    <div class="container-fluid">
				<div class="row justify-content-center">
					<div class="content-wrapper row justify-content-center">
						<div class="col-md-auto grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h2 class = "font-weight-bold text-center text-primary">${customer.customerName }님 상세정보</h2>
									<div class="row justify-content-center mt-3">
										<div class="col-auto">
										
											<!-- CustomerCode -->
											<div class="form-group row pt-3">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">No</label>
												<div class="col-sm-8">
													<input type = "text" name = "customerCode" id = "customerCode" value = "${customer.customerCode }" readonly = "readonly"
															class="form-control" id="exampleInputUsername2">											
												</div>
											</div>
											
											<!--  CustomerId -->
											<div class="form-group row">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">ID</label>
												<div class="col-sm-8">
													<input type = "text" name = "customerId" id = "customerId" value = "${customer.customerId }" readonly = "readonly"
															class="form-control" id="exampleInputUsername2">											
												</div>
											</div>
											
											<!-- customerName -->
											<div class="form-group row">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">Name</label>
												<div class="col-sm-8">
													<input type = "text" name = "customerName" value = "${customer.customerName }" readonly = "readonly"
															class="form-control" id="exampleInputUsername2">										
												</div>
											</div>
											
											<!-- customerPhone -->
											<div class="form-group row">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">Phone</label>
												<div class="col-sm-8">
													<input type = "text" name = "customerPhone" value = "${customer.customerPhone }" readonly = "readonly"
															class="form-control" id="exampleInputUsername2">										
												</div>
											</div>
											
											<!-- point -->
											<div class="form-group row">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">Point</label>
												<div class="col-sm-8">
													<input type = "text" name = "point" value = "${customer.point }" readonly = "readonly"
															class="form-control" id="exampleInputUsername2">										
												</div>
											</div>
											
											<!-- createdate -->
											<div class="form-group row">
												<label for="exampleInputUsername2" class="col-sm-4 col-form-label text-center">Create Date</label>
												<div class="col-sm-8">
												<input type = "text" name = "createdate" id = "createdate" value = "${customer.createdate }" readonly = "readonly"
														class="form-control" id="exampleInputUsername2">									
												</div>
											</div>
											
			
			<div>
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/customer/modifyCustomerByAdmin?customerId=${customer.customerId }'">
						수정
				</button>
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/customer/deleteCustomer?customerId=${customer.customerId }'">
						삭제
				</button>
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

