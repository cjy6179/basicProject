<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert 테스트</title>
</head>
<body>
<%

String test = (String) request.getAttribute("menuUrl");
out.print(test);
%>

<jsp:forward page="/jsp/main.jsp"></jsp:forward>

</body>
</html>