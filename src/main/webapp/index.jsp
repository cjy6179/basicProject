<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap/signin.css">



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="./js/bootstrap/bootstrap.min.js"></script>


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

function enterkey() {
    if (window.event.keyCode == 13) {

         // 엔터키가 눌렸을 때 실행할 내용
         fnlogin();
    }
}


function fnCreateUser(){
	var url = "jsp/manager/user/createUser.jsp";
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
<div class="container">
	<form class="form-signin" id="loginFrom">
		<h2 class="form-signin-heading">Please sign in</h2>
		<label for="id" class="sr-only">ID</label>
		<input type="text" id="id" name="id" class="form-control" placeholder="ID input"/>
		<label for="pwd" class="sr-only">PASSWORD</label>
		<input type="password" id="pwd" name="pwd" class="form-control" placeholder="password input" onkeyup="enterkey();"/>
		
		<input type="button" class="btn btn-lg btn-primary btn-block" value="Sign in" onClick="fnlogin()">
		<input type="button" class="btn btn-lg btn-primary btn-block" id="createUser" value="Registration" onClick="fnCreateUser()"/>
	</form>
</div>
 


</body>
</html>