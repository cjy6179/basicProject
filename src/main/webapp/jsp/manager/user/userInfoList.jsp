<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="common.user.UserInfoVO" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<script>
window.onload = function(){	
	var form = document.getElementById("userInfoFrom");
	form.method="post";
	form.action="/basicProject/selectUserInfo";
	
	//form.submit();
}



</script>
<body>
<!-- Fixed navbar -->
<jsp:include page="../../top.jsp" flush="false" />

<form id="userInfoFrom">
	<div class="container">
		<div class="page-header">
			<h1>사용자 관리</h1>
		</div>
		 <div class="row">
	        <div class="col-md">
	          <table class="table">
	            <thead>
	              <tr>
	              	<th>#</th>
	                <th>ID</th>
	                <th>성명</th>
	                <th>나이</th>
	                <th>성별</th>
	              </tr>
	            </thead>
	            <tbody>
<% 
List<UserInfoVO> userInfoList = (List<UserInfoVO>)request.getAttribute("userInfoList");
for(int i=0; i < userInfoList.size(); i++){
%>	            
	              <tr>
	              	<td> <%=(int)userInfoList.get(i).getIndex()%> </td>
	                <td> <%=userInfoList.get(i).getId()%> </td>
	                <td> <%=userInfoList.get(i).getName()%> </td>
	                <td> <%=userInfoList.get(i).getAge()%> </td>
	                <td> <%=userInfoList.get(i).getGender()%> </td>
	              </tr>
<%
}
%>
	            </tbody>
	          </table>
	        </div>
	      </div>
	</div>
</form>
</body>


</html>