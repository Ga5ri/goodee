<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 숫자 표시에 콤마 찍기위한 포맷 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	
	<!-- 사업자별 상품 목록 -->
	<h1>${loginEmp.empId}님이 등록한 상품 리스트</h1>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${list}" varStatus="s">
				<!-- 테이블 td 5개씩 줄바꿈 -->
				<c:if test="${s.index != 0 && s.index % 5 == 0}">
					</tr><tr>
				</c:if>
				<td>
					<div>
						<img src="${pageContext.request.contextPath}/upload/${m.filename}" width="200" height="200">
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/goods/goodsOne?goodsCode=${m.goodsCode}">
							${m.goodsName}
						</a>
					</div>
					<div><fmt:formatNumber value="${m.goodsPrice}" pattern="#,###"/>원</div>
				</td>		
			</c:forEach>
		</tr>
	</table>
	<div>
		<c:if test="${loginEmp != null}">
			<a href="${pageContext.request.contextPath}/goods/addGoods">
				상품추가
			</a>
		</c:if>
	</div>
	
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/goods/goodsListByCompany?currentPage=1">처음</a>		
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/goods/goodsListByCompany?currentPage=${currentPage-1}">이전</a>
		</c:if>	
		<span>${currentPage}</span>	
		<c:if test="${currentPage < lastPage}">
			<a href="${pageContext.request.contextPath}/goods/goodsListByCompany?currentPage=${currentPage+1}">다음</a>
		</c:if>	
		<a href="${pageContext.request.contextPath}/goods/goodsListByCompany?currentPage=${lastPage}">마지막</a>
	</div>
</body>
</html>