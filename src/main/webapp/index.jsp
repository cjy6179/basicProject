<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>


<script type="text/javascript">

var state = '<%= request.getAttribute("state") %>'


window.onload = function(){
	
	if(state == "F"){
		alert("로그인정보가 틀립니다.");
	}else if (state == "error"){
		alert("오류가 발생했습니다. 관리자에게 문의부탁드립니다.");
	}
}

function fnCreateUser(){
	var url = "jsp/user/createUser.jsp";
    var name = "popup test";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
    window.open(url, name, option);
}


function fnlogin(){
	var form = document.getElementById("loginFrom");
	form.method="post";
	form.action="login";
	
	form.submit();
}
</script>

<body>
	<h1> 로그인 </h1>
	<form id="loginFrom">
		<input type="text" id="id" name="id" value="test1"/>
		<input type="text" id="pwd" name="pwd" value="123"/>
		<input type="button" value="로그인" onClick="fnlogin()">
		<input type="button" id="createUser" value="회원가입" onClick="fnCreateUser()"/>
	</form>
</body>
</html>