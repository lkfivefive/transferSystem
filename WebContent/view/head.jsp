<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="GB18030">
<title>Insert title here</title>
<script type="text/javascript">
	    function showTime(){
	    	//使用对象.innerText修改span元素间的内容
			document.getElementById("now").innerText="当前时间："+new Date().toLocaleString();
		}		
		setInterval("showTime()",1000);//每隔1000毫秒(1s)调用showTime函数
    </script>
</head>
<body style="background-image: url(images/head.jpg);background-size: 100% 100%;background-repeat: no-repeat;">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr height="35">
        <td width="50"><img height="35" align="middle" src="images/logo.png"></td>
        <td width="300"style="font-weight: bold; font-size: 23px; color: #F0F8FF;" align="left">
       		 转账系统安全研究与应用实现
        </td>
        <td style="font-weight: bold; font-size: 16px; color: #FFF; padding-top: 20px;" align="center">
                                       当前用户：<%= request.getSession().getAttribute("accountname") %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
                <span id="now"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       
         </td>
         <td style="font-weight: bold; font-size: 16px; color: #FFF; padding-top: 20px;" align="right">
         <span><a id="menuChild" style="font-size: 16px; color:white; font-weight: bold;text-decoration:none;" href="exit.action" target="_top">注销</a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         </td>
         
        </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr height="4" bgcolor="#1c5db6">
            <td></td>
        </tr>
    </table>
</body>
</html>