<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>로그인</h1>
   <form method="post" action="${pageContext.request.contextPath}/employee/loginEmp">
      <table border="1">
         <tr>
            <td>empId</td>
            <td><input type="text" name="empId"></td>
         </tr>
         <tr>
            <td>empPw</td>
            <td><input type="text" name="empPw"></td>
         </tr>
      </table>
      <button type="submit">로그인</button>
   </form>
</body>
</html>