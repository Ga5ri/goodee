<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>empList.jsp</title>
		
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
			// checkBox 변화 있을시 submit
			$(document).ready(function() {
				$('#activeY').change(function() {
					$('#checkboxForm').submit();
				})
				
				$('#activeN').change(function() {
					$('#checkboxForm').submit();
				})
				
				$('#authCode0').change(function() {
					$('#checkboxForm').submit();
				})
				
				$('#authCode1').change(function() {
					$('#checkboxForm').submit();
				})
				
			});
		</script>
		
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>EMP LIST</h1>
			</div>
			
			<!-- active, authCode 체크박스 -->
			<div>
				<form id = "checkboxForm" method = "get" action = "${pageContext.request.contextPath }/emp/empList">
					<div>
						<table border = "1">
							<tr>
								<th>active</th>
								<td>
									<c:choose>
										<c:when test="${active != null && activeLength == 2}">
											<input type = "checkbox" name = "active" id = "activeY" value = "Y" checked>Y
											<input type = "checkbox" name = "active" id = "activeN" value = "N" checked>N
										</c:when>
										<c:when test="${active != null && activeLength == 1 && active[0] == 'Y'}">
											<input type = "checkbox" name = "active" id = "activeY" value = "Y" checked>Y
											<input type = "checkbox" name = "active" id = "activeN" value = "N">N
										</c:when>
										<c:when test="${active != null && activeLength == 1 && active[0] == 'N'}">
											<input type = "checkbox" name = "active" id = "activeY" value = "Y">Y
											<input type = "checkbox" name = "active" id = "activeN" value = "N" checked>N
										</c:when>
										<c:otherwise>
											<input type = "checkbox" name = "active" id = "activeY" value = "Y">Y
											<input type = "checkbox" name = "active" id = "activeN" value = "N">N
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							
							
							<tr>
								<th>authCode</th>
								<td>
									<c:choose>
										<c:when test="${authCode != null && authCodeLength == 2 }">
											<input type = "checkbox" name = "authCode" id = "authCode0" value = "0" checked>0 : 관리자
											<input type = "checkbox" name = "authCode" id = "authCode1" value = "1" checked>1 : 사업자
										</c:when>
										<c:when test="${authCode != null && authCodeLength == 1 && authCode[0] == 0 }">
											<input type = "checkbox" name = "authCode" id = "authCode0" value = "0" checked>0 : 관리자
											<input type = "checkbox" name = "authCode" id = "authCode1" value = "1">1 : 사업자
										</c:when>
										<c:when test="${authCode != null && authCodeLength == 1 && authCode[0] == 1 }">
											<input type = "checkbox" name = "authCode" id = "authCode0" value = "0">0 : 관리자
											<input type = "checkbox" name = "authCode" id = "authCode1" value = "1" checked>1 : 사업자
										</c:when>
										<c:otherwise>
											<input type = "checkbox" name = "authCode" id = "authCode0" value = "0">0 : 관리자
											<input type = "checkbox" name = "authCode" id = "authCode1" value = "1">1 : 사업자
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</div>				
				</form>
			</div>
			
			
			<div>
				<!-- 검색 -->
				<form method = "get" action = "${pageContext.request.contextPath }/emp/empList">
					<div>
						<select name = "searchCategory" id = "searchCategory">
							<c:if test="${param.searchCategory == null || param.searchCategory == 'emp_id'}">
								<option value = "emp_id" selected>empId</option>
								<option value = "emp_name">empName</option>
							</c:if>
							
							<c:if test="${param.searchCategory == 'emp_name' }">
								<option value = "emp_id">empId</option>
								<option value = "emp_name" selected>empName</option>
							</c:if>
							
						</select>
				
						<input type = "text" name = "searchText" id = "searchText">
						<button type = "submit" >검색</button>
					</div>
				</form>
				
			</div>
			
			<!-- ============================================================================================================================= -->
			<!-- 페이징 처리(모든 경우의 수) 시작 -->
			
			<c:if test="${activeLength == 0 && authCodeLength == 0 }">
				<!-- active : 0개 / authCode : 0개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 0 && authCodeLength == 1 }">
				<!-- active : 0개 / authCode : 1개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 0 && authCodeLength == 2 }">
				<!-- active : 0개 / authCode : 2개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 1 && authCodeLength == 0 }">
				<!-- active : 1개 / authCode : 0개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 1 && authCodeLength == 1 }">
				<!-- active : 1개 / authCode : 1개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 1 && authCodeLength == 2 }">
				<!-- active : 1개 / authCode : 2개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 2 && authCodeLength == 0 }">
				<!-- active : 2개 / authCode : 0개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 2 && authCodeLength == 1 }">
				<!-- active : 2개 / authCode : 1개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>

			<c:if test="${activeLength == 2 && authCodeLength == 2 }">
				<!-- active : 2개 / authCode : 2개 선택 -->
				<div>
					<ul class="pagination justify-content-center">
						
						<!-- 페이지 처음 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=1">
								<span>처음</span>
							</a>
						</li>
						
						<!-- 페이지 이전(-10의 1페이지) -->
						<c:if test="${previousPage > 0}">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${previousPage}">
									<span>이전</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 1 ~ 10 -->
						<c:forEach var="i" items="${pageList }">
							<!-- 현재페이지 active 속성 부여 -->
							<c:choose>
								<c:when test="${currentPage == i}">
									<li class = "page-item active">
								</c:when >
								<c:otherwise>
									<li class = "page-item">
								</c:otherwise>
							</c:choose>
						
							<!-- 마지막 페이지 까지만 출력 -->
							<c:if test="${i <= lastPage }">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${i}">
									<span>${i }</span>
								</a>
							</c:if>
							
									</li>
							
						</c:forEach>
				
						<!-- 페이지 다음(+10의 1페이지) -->
						<c:if test="${nextPage <= lastPage }">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${nextPage}">
									<span>다음</span>
								</a>
							</li>
						</c:if>
				
						<!-- 페이지 마지막 -->
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath }/emp/empList?active=${active[0] }&active=${active[1] }&authCode=${authCode[0] }&authCode=${authCode[1] }&searchText=${searchText }&searchCategory=${searchCategory }&rowPerPage=${rowPerPage }&currentPage=${lastPage}">
								<span>마지막</span>
							</a>
						</li>
					</ul>
				</div>
			
			</c:if>


			
			
			<!-- 페이징 처리(모든 경우의 수) 끝 -->			
			<!-- ============================================================================================================================= -->
			
			<div>
				<table border = "1">
					<tr>
						<th>empId</th>
						<th>empName</th>
						<th>active</th>
						<th>authCode</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
					
					<c:forEach var="emp" items="${empList }">
						<tr>
							<td>
								<a href = "${pageContext.request.contextPath }/emp/empOne?empId=${emp.empId }">
									${emp.empId }
								</a>
							</td>
							<td>${emp.empName }</td>
							<td>${emp.active }</td>
							<td>${emp.authCode }</td>
							<td>
								<a href = "${pageContext.request.contextPath }/emp/modifyEmp?empCode=${emp.empCode }">수정</a>
							</td>
							<td>
								<a href = "${pageContext.request.contextPath }/emp/deleteEmp?empCode=${emp.empCode }">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
		
		</div>
		
	</body>
</html>