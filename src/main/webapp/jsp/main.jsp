<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.vo.test.PersonVO" %>
<!DOCTYPE html>
<html lang="en">
<head>


<meta charset="UTF-8">
<title>Account Book</title>
</head>
 <body>

<%
PersonVO test2 = (PersonVO)session.getAttribute("person");
String menuUrl = (String) request.getAttribute("menuUrl");
%>

<!-- Fixed navbar -->
<jsp:include page="top.jsp" flush="false" />

<%-- 
<div class="container theme-showcase" >
	<jsp:include page='<%= menuUrl%>' flush="false"/>
</div> 
--%>
<script>
window.onload = function(){

}	
</script>
</body>

</html>