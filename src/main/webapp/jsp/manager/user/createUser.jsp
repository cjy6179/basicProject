<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>createUser</title>
</head>
<script type="text/javascript">

var state = '<%= request.getAttribute("state") %>';


window.onload = function(){

	function isNull(value){
		return value == "" || value == null || value == "null"
	} 
	
	console.log("state = " + state);
	
	if( isNull(state) ) return;
	
	var id = '<%= request.getAttribute("id") %>';
	var name = '<%= request.getAttribute("name") %>';
	var age = '<%= request.getAttribute("age") %>';
	var gender = '<%= request.getAttribute("gender") %>';
	
	document.getElementById("id").value = id;
	document.getElementById("name").value = name;
	document.getElementById("age").value = age;
	document.getElementById(gender).checked = true;
		
	if(state == "U"){
		alert("정상적으로 저장되었습니다.");
		window.close();
		return;
	}

	alert("오류가 발생했습니다. 관리자에게 문의부탁드립니다.");
	
	
}

function fnPopupClose(){
	this.close();
}


function fnCreateUserInfo(){
	if(confirm("저장하시겠습니가?")){
		var form = document.getElementById("userInfo");
		form.action="/basicProject/createUserInfo";
		console.log(form)
		form.submit();	
	}
}

</script>
<body>
<form id="userInfo" method="post">
	<table>
		<tr>
			<th>아이디 : </th>
			<td>
				<input type="text" name="id" id="id" />
			</td>
		</tr>
		<tr>
			<th>비밀번호 : </th>
			<td>
				<input type="password" name="pwd" />
			</td>
		</tr>
		<tr>
			<th>이름 : </th>
			<td>
				<input type="text" name="name" id="name" />
			</td>
		</tr>
		<tr>
			<th>나이 : </th>
			<td>
				<input type="text" name="age" id="age" />
			</td>
		</tr>
		<tr>
			<th>성별 : </th>
			<td>
				<input type="radio" name="gender" value="f" id="f"/> 여성
				<input type="radio" name="gender" value="m" id="m"/> 남성
			</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="저장" onClick="fnCreateUserInfo()"/>
				<input type="button" value="창닫기" onClick="fnPopupClose()" />
			</td>
		</tr>
	</table>
</form>
</body>
</html>