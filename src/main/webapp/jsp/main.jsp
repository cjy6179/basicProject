<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.vo.test.PersonVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>

<jsp:useBean id="person" class="common.vo.test.PersonVO" scope="request"/>
<jsp:setProperty name="person" property="*" />

<h1><%= person.getId() %> </h1>
<h1><%= person.getName() %> </h1>
<h1><%= person.getAge() %> </h1>
<h1><%= person.getGender() %> </h1>

<%
PersonVO test = (PersonVO) request.getAttribute("person");
out.println(test.getId());
out.println(test.getAge());
out.println(test.getName());
out.println(test.getGender());
%>

</body>
</html>