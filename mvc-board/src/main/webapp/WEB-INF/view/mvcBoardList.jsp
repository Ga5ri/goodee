<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%
   // 컨트롤러에서 공유한 모델데이터를 셋팅
   ArrayList<Board> list = (ArrayList<Board>)(request.getAttribute("list"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jspBoardList.jsp</title>
</head>
<body>
   <h1>MODEL1 BOARD LIST</h1>
   
   <div>
      <a href="<%=request.getContextPath()%>/AddBoardFormController">글입력</a>
   </div>
   <table border="1">
      <thead>
         <tr>
            <th>no</th>
            <th>title</th>
         </tr>   
      </thead>
      <tbody>
         <%
            for(Board b : list) { // ResultSet타입은 내부적 이터레이터 패턴이 적용된 API를 가지고 있다
         %>
               <tr>
                  <td><%=b.getNo()%></td>
                  <td>
                     <a href='<%=request.getContextPath()%>/BoardOneController?no=<%=b.getNo()%>'>
                        <%=b.getTitle()%>
                     </a>
                  </td>
               </tr>
         <%      
            }
         %>
      </tbody>
   </table>
</body>
</html>