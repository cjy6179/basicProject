<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert 아아2 here</title>
</head>
<script type="text/javascript">




</script>
<body>
<h1> account book 아..ㅋㅋ </h1>
<table>
	<tr>
		<th>id</th>
		<td>
<%
out.print("<br> id : " + request.getParameter("id"));
%>  </td>
	</tr>
	<tr>
		<th>pwd</th>
		<td> <%request.getParameter("pwd"); %> </td>
	</tr>
</table>
</body>
</html>