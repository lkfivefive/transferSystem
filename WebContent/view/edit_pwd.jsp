<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>Insert title here</title>
<style type="text/css">
.errorInfo {
    color: red;
    width: 150px;
    height: 30px;
    font-size: 12px;
}//错误信息
.trueInfo {
    color: blue;
    width: 150px;
    height: 30px;
    font-size: 12px;
}//正确信息
</style>
<script type="text/javascript">
function check_pwd(){
	var apwd=document.getElementById("apwd");
	if(apwd.value==""){
        document.getElementById("apwdinfo").innerHTML="密码不能为空";
        document.getElementById("apwdinfo").className="errorInfo";
    }
	else{
		document.getElementById("apwdinfo").innerHTML=" ";
        document.getElementById("apwdinfo").className="trueInfo";
	} 
}
function check_confirm(){
	var apwd=document.getElementById("apwd");
	var confirm=document.getElementById("confirm");
	if(apwd.value!=confirm.value){
        document.getElementById("confirminfo").innerHTML="密码不一致";
        document.getElementById("confirminfo").className="errorInfo";
    }
	else{
		document.getElementById("confirminfo").innerHTML=" ";
        document.getElementById("confirminfo").className="trueInfo";
	} 
}
</script>
</head>
<body>
<div>
<h2  align="center">修改密码</h2>
<hr>
</div>
<br>
<form name="f9" method="post" action="edit_pwd.action">
<table align="center">
<tr height="30px">
<td>旧的密码:</td><td><input type="password" name="oldpwd" id="oldpwd"></td>
</tr>
<tr height="30px">
<td>新的密码:</td><td><input type="password" name="apwd" id="apwd" onblur="check_pwd()"></td><td><span id="apwdinfo"></span></td>
</tr>
<tr height="30px">
<td>确认密码:</td><td><input type="password" name="confirm" id="confirm" onblur="check_confirm()"></td><td><span id="confirminfo"></span></td>
</tr>
<tr height="40px">
<td></td>
<td><input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
</td>
</tr>
</table>
</form>
</body>
</html>