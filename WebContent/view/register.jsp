<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>注册</title>
<style type="text/css">
body{      
        background-image: url(images/login.jpg);      
        background-size:cover;    
     }
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
function check_no(){
	var no=/^[0-9]+$/;
	var ano=document.getElementById("ano");
	if(ano.value==""){
        document.getElementById("anoinfo").innerHTML="账号不能为空";
        document.getElementById("anoinfo").className="errorInfo";
    }
	else if(!no.test(ano.value)){
		document.getElementById("anoinfo").innerHTML="格式错误";
        document.getElementById("anoinfo").className="errorInfo";
	}
	else{
		document.getElementById("anoinfo").innerHTML=" ";
        document.getElementById("anoinfo").className="trueInfo";
	} 
}

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
function check_name(){
	var aname=document.getElementById("aname");
	if(aname.value==""){
        document.getElementById("anameinfo").innerHTML="姓名不能为空";
        document.getElementById("anameinfo").className="errorInfo";
    }
	else{
		document.getElementById("anameinfo").innerHTML=" ";
        document.getElementById("anameinfo").className="trueInfo";
	} 
}
function check_Id(){
	var Id=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	var aId=document.getElementById("aId");
	if(aId.value==""){
        document.getElementById("aIdinfo").innerHTML="身份号码不能为空";
        document.getElementById("aIdinfo").className="errorInfo";
    }
	else if(!Id.test(aId.value)){
		document.getElementById("aIdinfo").innerHTML="格式错误";
        document.getElementById("aIdinfo").className="errorInfo";
	}
	else{
		document.getElementById("aIdinfo").innerHTML=" ";
        document.getElementById("aIdinfo").className="trueInfo";
	} 
}
function check_tel(){
	var tel=/^1[3456789]\d{9}$/;
	var atel=document.getElementById("atel");
	if(atel.value==""){
        document.getElementById("atelinfo").innerHTML="电话不能为空";
        document.getElementById("atelinfo").className="errorInfo";
    }
	else if(!tel.test(atel.value)){
		document.getElementById("atelinfo").innerHTML="格式错误";
        document.getElementById("atelinfo").className="errorInfo";
	}
	else{
		document.getElementById("atelinfo").innerHTML=" ";
        document.getElementById("atelinfo").className="trueInfo";
	}
}
function check_mail(){
	var mail=/^([0-9a-zA-Z])+\@([a-zA-Z0-9])+\.([a-zA-Z])+$/;
	var amail=document.getElementById("amail");
	if(amail.value==""){
        document.getElementById("amailinfo").innerHTML="邮箱不能为空";
        document.getElementById("amailinfo").className="errorInfo";
    }
	else if(!mail.test(amail.value)){
		document.getElementById("amailinfo").innerHTML="格式错误";
        document.getElementById("amailinfo").className="errorInfo";
	}
	else{
		document.getElementById("amailinfo").innerHTML=" ";
        document.getElementById("amailinfo").className="trueInfo";
	}
}
function check_address(){
	var address=document.getElementById("address");
	if(address.value==""){
        document.getElementById("addressinfo").innerHTML="地址不能为空";
        document.getElementById("addressinfo").className="errorInfo";
    }
	else{
		document.getElementById("addressinfo").innerHTML=" ";
        document.getElementById("addressinfo").className="trueInfo";
	} 
}
</script>
</head>
<body>
<div>
<h2  align="center">账户注册</h2>
<hr>
</div>
<br>
<br>
<form name="f2" method="post" action="register.action">
<table align="center">
<tr height="30px">
<td>账号:</td><td><input type="text" name="ano" id="ano" onblur="check_no()"></td><td><span id="anoinfo"></span></td>
</tr>
<tr height="30px">
<td>密码:</td><td><input type="password" name="apwd" id="apwd" onblur="check_pwd()"></td><td><span id="apwdinfo"></span></td>
</tr>
<tr height="30px">
<td>确认密码:</td><td><input type="password" name="confirm" id="confirm" onblur="check_confirm()"></td><td><span id="confirminfo"></span></td>
</tr>
<tr height="30px">
<td>姓名:</td><td><input type="text" name="aname" id="aname" onblur="check_name()"></td><td><span id="anameinfo"></span></td>
</tr>
<tr height="30px">
<td>身份号码:</td><td><input type="text" name="aId" id="aId" onblur="check_Id()"></td><td><span id="aIdinfo"></span></td>
</tr>
<tr height="30px">
<td>性别:</td><td><input type="radio" name="asex" value="男" checked="checked">男
<input type="radio" name="asex" value="女">女</td>
</tr>
<tr height="30px">
<td>电话:</td><td><input type="text" name="atel" id="atel" onblur="check_tel()"></td><td><span id="atelinfo"></span></td>
</tr>
<tr height="30px">
<td>邮箱:</td><td><input type="text" name="amail" id="amail" onblur="check_mail()"></td><td><span id="amailinfo"></span></td>
</tr>
<tr height="30px">
<td>地址:</td><td><input type="text" name="address" id="address" onblur="check_address()"></td><td><span id="addressinfo"></span></td>
</tr>
<tr height="40px">
<td></td>
<td><input type="submit" value="提交"><input type="reset" value="重置">
<a href="login.jsp"><input type="button" value="返回"></a></td>
</tr>
</table>
</form>
</body>
</html>